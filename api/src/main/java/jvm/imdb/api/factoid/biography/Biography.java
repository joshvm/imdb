package jvm.imdb.api.factoid.biography;

import jvm.imdb.api.factoid.Factoid;

import java.util.Objects;

public class Biography extends Factoid {

    private final String text;

    private int hash;
    private String string;
    private String displayString;

    public Biography(final String url, final String name, final String text) {
        super(url, name);
        this.text = text;
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
        if(!(o instanceof Biography))
            return false;
        final Biography b = (Biography) o;
        return Objects.equals(url, b.url) && Objects.equals(name, b.name) && Objects.equals(text, b.text);
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

    public static int hash(final Biography b) {
        return Objects.hash(b.url, b.name, b.text);
    }

    public static String string(final Biography b) {
        return String.format("%s(url=%s, name=%s, text=%s)", Biography.class.getSimpleName(), b.url, b.name, b.text);
    }

    public static String displayString(final Biography b) {
        return String.format("Type: %s%nUrl: %s%nName: %s%nText: %s", Biography.class.getSimpleName(), b.url, b.name, b.text);
    }
}
