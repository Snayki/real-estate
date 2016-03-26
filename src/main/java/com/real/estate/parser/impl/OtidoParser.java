package com.real.estate.parser.impl;

import com.real.estate.domain.Ad;
import com.real.estate.domain.City;
import com.real.estate.domain.Material;
import com.real.estate.parser.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Snayki on 21.03.2016.
 */
public class OtidoParser implements Parser<Ad> {

    public static final String EXPRESSION = "(\\d)-[кімн]+.+\\s(\\d+)/\\d+/(.).+\\s(\\d+)/(\\d+)/\\d+.*\\s(\\d+)[грн]+\\s\\((\\d+)\\)";
    public static final String URL = "http://otido.ua/main.php?l=112";
    public static final String LINK_EXSPRESSION = "a[href^=main.php?l=112]";
    public static final Pattern PATTERN = Pattern.compile(EXPRESSION, Pattern.UNICODE_CHARACTER_CLASS);
    public static final String MOZILLA = "Mozilla/5.0 (Macintosh; U; Intel Mac OS X; de-de) AppleWebKit/523.10.3 (KHTML, like Gecko) Version/3.0.4 Safari/523.10";
    public static final String JSOUP_SEARCH_EXPRESSION = "td:not(:has(td)):matches(%s)";

    public List<Element> parse() {

        try {
            Document doc = Jsoup.connect(URL).get();
            Set<String> links = doc.select(LINK_EXSPRESSION).stream()
                    .map(link -> link.attr("abs:href"))
                    .collect(Collectors.toSet());

            links.add(URL);

            return links.stream()
                    .map(this::retrieveElementsFormUrl)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private List<Element> retrieveElementsFormUrl(String url) {
        try {
            Document doc = Jsoup.connect(url).userAgent(MOZILLA).get();
            return doc.select(String.format(JSOUP_SEARCH_EXPRESSION, EXPRESSION));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public Ad createFromElement(Element element) {
        Matcher matcher = PATTERN.matcher(element.text());

        if (matcher.find()) {
            Ad ad = new Ad();
            ad.setCity(City.Cherkasy);
            ad.setContent(matcher.group(0));

            ad.setNumberOfRooms(Integer.parseInt(matcher.group(1)));
            ad.setFloor(Integer.parseInt(matcher.group(2)));
            ad.setTypeOfMaterial(Material.materialOf(matcher.group(3)));

            ad.setTotalArea(Integer.parseInt(matcher.group(4)));
            ad.setLivingSpace(Integer.parseInt(matcher.group(5)));

            ad.setPrice(Integer.parseInt(matcher.group(6)));
            ad.setPricePerSquareMeter(Integer.parseInt(matcher.group(7)));

            return ad;
        }

        return null;
    }
}
