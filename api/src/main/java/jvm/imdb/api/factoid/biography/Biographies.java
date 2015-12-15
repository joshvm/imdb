package jvm.imdb.api.factoid.biography;

import jvm.imdb.api.Imdb;
import jvm.imdb.api.factoid.FactoidEngine;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.stream.Collectors;

public class Biographies extends FactoidEngine<Biography> {

    @Override
    protected String url(final String query, final int options) {
        return String.format("http://www.imdb.com/search/text?realm=name&&field=bio&&q=%s&ref_=fn_bi", query);
    }

    @Override
    protected String tableClass() {
        return "results";
    }

    @Override
    protected String rowPattern() {
        return "(odd|even)";
    }

    @Override
    protected void parseRow(final String query, final int options, final Element tr, final List<Biography> results) {
        final Element td = tr.getElementsByTag("td").first();
        final Element a = td.getElementsByTag("a").first();
        final String url = Imdb.BASE_URL + a.attr("href");
        final String name = a.ownText();
        final Element div = td.getElementsByTag("div").first();
        final String text = div.getElementsByTag("p").stream().map(Element::ownText).collect(Collectors.joining(System.lineSeparator()));
        results.add(new Biography(url, name, text));
    }
}
