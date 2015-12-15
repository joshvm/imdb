package jvm.imdb.api.factoid.character;

import jvm.imdb.api.Imdb;
import jvm.imdb.api.factoid.FactoidEngine;
import jvm.imdb.api.factoid.ref.Reference;
import jvm.imdb.api.utils.OptionUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class Characters extends FactoidEngine<Character> {

    public static final int EXACT_MATCH = 1 << 1;

    @Override
    protected String url(final String query, final int options) {
        if(OptionUtils.option(options, EXACT_MATCH))
            return String.format("http://www.imdb.com/find?q=%s&s=ch&exact=true&ref_=fn_ch_ex", query);
        return String.format("http://www.imdb.com/find?ref_=nv_sr_fn&q=%s&s=ch", query);
    }

    @Override
    protected void parseRow(final String query, final int options, final Element tr, final List<Character> results) {
        final Element result = tr.getElementsByAttributeValue("class", "result_text").first();
        final Element a = result.getElementsByTag("a").first();
        final String name = a.ownText();
        final String url = Imdb.BASE_URL + a.attr("href");
        Reference ref = null;
        final Elements smalls = result.getElementsByTag("small");
        if(!smalls.isEmpty()){
            final Element small = smalls.first();
            String refName = small.text();
            if(refName.startsWith("(") && refName.endsWith(")"))
                refName = refName.substring(1, refName.length() - 1);
            final String refUrl = Imdb.BASE_URL + small.getElementsByTag("a").first().attr("href");
            ref = new Reference(refUrl, refName);
        }
        results.add(new Character(url, name, ref));
    }
}
