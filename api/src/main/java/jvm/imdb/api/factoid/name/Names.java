package jvm.imdb.api.factoid.name;

import jvm.imdb.api.Imdb;
import jvm.imdb.api.factoid.FactoidEngine;
import jvm.imdb.api.factoid.ref.Reference;
import jvm.imdb.api.utils.OptionUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class Names extends FactoidEngine<Name> {

    public static final int EXACT_MATCH = 1 << 1;

    @Override
    protected String url(final String query, final int options) {
        if(OptionUtils.option(options, EXACT_MATCH))
            return String.format("http://www.imdb.com/find?q=%s&s=nm&exact=true&ref_=fn_nm_ex", query);
        return String.format("http://www.imdb.com/find?q=%s&s=nm&ref_=fn_nm", query);
    }

    @Override
    protected void parseRow(final String query, final int options, final Element tr, final List<Name> results) {
        final String thumbnailUrl = tr.getElementsByAttributeValue("class", "primary_photo").first().getElementsByTag("img").first().attr("src");
        final Element r = tr.getElementsByAttributeValue("class", "result_text").first();
        final Element a = r.getElementsByTag("a").first();
        final String url = Imdb.BASE_URL + a.attr("href");
        final String name = a.ownText();
        String job = "";
        Reference ref = null;
        final Elements smalls = r.getElementsByTag("small");
        if(!smalls.isEmpty()){
            final String refUrl = Imdb.BASE_URL + smalls.first().getElementsByTag("a").first().attr("href");
            String desc = smalls.first().text();
            if(desc.startsWith("(") && desc.endsWith(")"))
                desc = desc.substring(1, desc.length() - 1);
            final int comma = desc.indexOf(',');
            if(comma != -1){
                job = desc.substring(0, comma).trim();
                ref = new Reference(refUrl, desc.substring(comma + 1).trim());
            }else{
                if(desc.matches(".+\\(\\d+\\)"))
                    ref = new Reference(refUrl, desc.substring(comma + 1).trim());
                else
                    job = desc;
            }
        }
        results.add(new Name(url, thumbnailUrl, name, job, ref));
    }
}
