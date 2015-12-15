package jvm.imdb.api.factoid.company;

import jvm.imdb.api.Imdb;
import jvm.imdb.api.factoid.FactoidEngine;
import jvm.imdb.api.utils.OptionUtils;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Companies extends FactoidEngine<Company> {

    public static final int EXACT_MATCH = 1 << 1;

    private static final Pattern INFO_PATTERN = Pattern.compile("\\[(.+)\\]\\s*(?:\\((.+)\\))?");

    @Override
    protected String url(final String query, final int options) {
        if(OptionUtils.option(options, EXACT_MATCH))
            return String.format("http://www.imdb.com/find?q=%s&s=co&exact=true&ref_=fn_co_ex", query);
        return String.format("http://www.imdb.com/find?q=%s&s=co&ref_=fn_co", query);
    }

    @Override
    protected void parseRow(final String query, final int options, final Element tr, final List<Company> results) {
        final Element result = tr.getElementsByAttributeValue("class", "result_text").first();
        final Element a = result.getElementsByTag("a").first();
        final String url = Imdb.BASE_URL + a.attr("href");
        final String name = a.ownText();
        String area = null;
        String category = null;
        final Matcher m = INFO_PATTERN.matcher(result.ownText());
        if(m.find()){
            area = m.group(1);
            category = m.group(2);
        }
        results.add(new Company(url, name, area, category));
    }
}
