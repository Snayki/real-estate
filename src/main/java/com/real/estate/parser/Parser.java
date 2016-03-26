package com.real.estate.parser;

import org.jsoup.nodes.Element;

import java.util.List;

/**
 * Created by Snayki on 22.03.2016.
 */
public interface Parser<T> {

    List<Element> parse();

    T createFromElement(Element element);
}
