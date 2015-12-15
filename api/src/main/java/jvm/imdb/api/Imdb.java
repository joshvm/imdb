package jvm.imdb.api;

import jvm.imdb.api.factoid.biography.Biographies;
import jvm.imdb.api.factoid.character.Characters;
import jvm.imdb.api.factoid.company.Companies;
import jvm.imdb.api.factoid.keyword.Keywords;
import jvm.imdb.api.factoid.name.Names;
import jvm.imdb.api.factoid.plot.PlotSummaries;
import jvm.imdb.api.factoid.quote.Quotes;
import jvm.imdb.api.factoid.title.Titles;

public final class Imdb {

    public static final String BASE_URL = "http://imdb.com";

    private static final Names NAMES = new Names();
    private static final Titles TITLES = new Titles();
    private static final Characters CHARACTERS = new Characters();
    private static final Companies COMPANIES = new Companies();
    private static final Keywords KEYWORDS = new Keywords();
    private static final PlotSummaries PLOT_SUMMARIES = new PlotSummaries();
    private static final Biographies BIOGRAPHIES = new Biographies();
    private static final Quotes QUOTES = new Quotes();

    private Imdb() {
    }

    public static Names names() {
        return NAMES;
    }

    public static Titles titles() {
        return TITLES;
    }

    public static Characters characters() {
        return CHARACTERS;
    }

    public static Companies companies() {
        return COMPANIES;
    }

    public static Keywords keywords() {
        return KEYWORDS;
    }

    public static PlotSummaries plotSummaries() {
        return PLOT_SUMMARIES;
    }

    public static Biographies biographies() {
        return BIOGRAPHIES;
    }

    public static Quotes quotes() {
        return QUOTES;
    }
}
