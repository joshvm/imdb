package jvm.imdb.api.factoid;

import jvm.imdb.api.utils.NetUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class FactoidEngine<F extends Factoid> {

    protected FactoidEngine() {
    }

    public List<F> search(final String query, final int options, final int timeout) throws IOException {
        final List<F> results = new ArrayList<>();
        final Document doc = NetUtils.doc(url(query, options), timeout);
        parse(query, options, doc, results);
        return results;
    }

    public List<F> search(final String query, final int options) throws IOException {
        return search(query, options, NetUtils.timeout);
    }

    public List<F> search(final String query) throws IOException {
        return search(query, 0);
    }

    public List<F> trySearch(final String query, final int options, final int timeout) {
        try{
            return search(query, options, timeout);
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public List<F> trySearch(final String query, final int options) {
        try{
            return search(query, options);
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public List<F> trySearch(final String query) {
        try{
            return search(query);
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    protected String tableClass() {
        return "findList";
    }

    protected String rowPattern() {
        return "findResult (odd|even)";
    }

    protected void parse(final String query, final int options, final Document doc, final List<F> results) {
        final Elements tables = doc.getElementsByAttributeValue("class", tableClass());
        if(tables.isEmpty())
            return;
        final Element table = tables.first();
        final Elements tbodies = table.getElementsByTag("tbody");
        if(tbodies.isEmpty())
            return;
        final Element tbody = tbodies.first();
        for(final Element tr : tbody.getElementsByAttributeValueMatching("class", rowPattern()))
            parseRow(query, options, tr, results);
    }

    protected void parseRow(final String query, final int options, final Element tr, final List<F> results) {

    }

    protected abstract String url(final String query, final int options);

}
