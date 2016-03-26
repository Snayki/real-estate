package com.real.estate.utils;

/**
 * Created by Snayki on 26.03.2016.
 */
public class Constants {
    public static final int PAGE_SIZE = 30;

    /******************
     * Request params
     *****************/
    public static final String CITY = "city";
    public static final String FILTER_QUERY = "filterQuery";

    /***************
     * Filter Types
     ***************/
    public static final String MATCH_FILTER = "match";
    public static final String RANGE_FILTER = "range";

    /****************
     * Filter params
     ***************/
    public static final String TYPE_PROP = "type";
    public static final String VALUE_PROP = "value";
    public static final String FROM_PROP = "from";
    public static final String TO_PROP = "to";

    public static final int PERCENTILES_25 = 25;
    public static final int PERCENTILES_75 = 75;
    public static final String MIN_AGGREGATION_NAME = "min_%s";
    public static final String MAX_AGGREGATION_NAME = "max_%s";
    public static final String AVG_AGGREGATION_NAME = "avg_%s";
    public static final String PERCENTILES_25_AGGREGATION_NAME = "%s_25_percentiles";
    public static final String PERCENTILES_75_AGGREGATION_NAME = "%s_75_percentiles";

    /**********************
     * Domains Properties
     *********************/
    public static final String PRICE_FIELD = "price";
    public static final String PRICE_PER_SQUARE_METER_FIELD = "pricePerSquareMeter";

    /****************
     * Errors
     ***************/
    public static final String UNSUPPORTED_CITY = "Дане місто не підтримується!";
}
