package jvm.imdb.api.factoid.keyword;

import jvm.imdb.api.Imdb;
import jvm.imdb.api.factoid.FactoidEngine;
import jvm.imdb.api.utils.OptionUtils;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Keywords extends FactoidEngine<Keyword> {

    public static final int EXACT_MATCH = 1 << 1;

    private static final Pattern TITLE_COUNT_PATTERN = Pattern.compile("(\\d+)\\s*titles?");

    @Override
    protected String url(final String query, final int options) {
        if(OptionUtils.option(options, EXACT_MATCH))
            return String.format("http://www.imdb.com/find?q=%s&s=kw&exact=true&ref_=fn_kw_ex", query);
        return String.format("http://www.imdb.com/find?q=%s&s=kw&ref_=fn_kw", query);
    }

    @Override
    protected void parseRow(final String query, final int options, final Element tr, final List<Keyword> results) {
        final Element result = tr.getElementsByAttributeValue("class", "result_text").first();
        final Element a = result.getElementsByTag("a").first();
        final String url = Imdb.BASE_URL + a.attr("href");
        final String name = a.ownText();
        int titleCount = 0;
        final Matcher m = TITLE_COUNT_PATTERN.matcher(result.ownText());
        if(m.find())
            titleCount = Integer.parseInt(m.group(1));
        results.add(new Keyword(url, name, titleCount));
    }
}
