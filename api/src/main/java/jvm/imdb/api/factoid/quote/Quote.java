package jvm.imdb.api.factoid.quote;

import jvm.imdb.api.factoid.Factoid;
import jvm.imdb.api.factoid.title.Title;

import java.util.Objects;

public class Quote extends Factoid {

    protected final Title title;
    protected final String text;

    private int hash;
    private String string;
    private String displayString;

    public Quote(final String url, final String name, final Title title, final String text) {
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
        if(o == null)
            return false;
        if(o == this)
            return true;
        if(!(o instanceof Quote))
            return false;
        final Quote q = (Quote) o;
        return Objects.equals(url, q.url) && Objects.equals(name, q.name) && Objects.equals(title, q.title) && Objects.equals(text, q.text);
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

    public static int hash(final Quote q) {
        return Objects.hash(q.url, q.name, q.title, q.text);
    }

    public static String string(final Quote q) {
        return String.format("%s(url=%s, name=%s, title=%s, text=%s)", Quote.class.getSimpleName(), q.url, q.name, q.title, q.text);
    }

    public static String displayString(final Quote q) {
        return String.format("Type: %s%nUrl: %s%nName: %s%nTitle: %s%nText: %s", Quote.class.getSimpleName(), q.url, q.name, q.title.displayString(), q.text);
    }
}
