package jvm.imdb.api.factoid.plot;

import jvm.imdb.api.factoid.Factoid;
import jvm.imdb.api.factoid.title.Title;

import java.util.Objects;

public class PlotSummary extends Factoid {

    protected final Title title;
    protected final String text;

    private int hash;
    private String string;
    private String displayString;

    public PlotSummary(final String url, final String name, final Title title, final String text) {
        super(url, name);
        this.title = title;
        this.text = text;
    }

    public Title title() {
        return title;
    }

    public String text() {
        return text;
    }

    public String displayString() {
        if(displayString == null)
            displayString = displayString(this);
        return displayString;
    }

    @Override
    public boolean equals(final Object o) {
        if(o == this)
            return true;
        if(!(o instanceof PlotSummary))
            return false;
        final PlotSummary ps = (PlotSummary) o;
        return Objects.equals(url, ps.url) && Objects.equals(name, ps.url) && Objects.equals(text, ps.text);
    }

    @Override
    public int hashCode() {
        if(hash == 0)
            hash = hash(this);
        return hash;
    }

    @Override
    public String toString() {
        if(string == null)
            string = string(this);
        return string;
    }

    public static int hash(final PlotSummary ps) {
        return Objects.hash(ps.url, ps.name, ps.text);
    }

    public static String string(final PlotSummary ps) {
        return String.format("%s(url=%s, name=%s, title=%s, text=%s)", PlotSummary.class.getSimpleName(), ps.url, ps.name, ps.title, ps.text);
    }

    public static String displayString(final PlotSummary ps) {
        return String.format("Type: %s%nUrl: %s%nName: %s%nTitle: %s%nText: %s", PlotSummary.class.getSimpleName(), ps.url, ps.name, ps.title.displayString(), ps.text);
    }
}
