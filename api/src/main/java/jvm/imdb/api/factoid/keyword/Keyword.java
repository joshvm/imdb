package jvm.imdb.api.factoid.keyword;

import jvm.imdb.api.factoid.Factoid;

import java.util.Objects;

public class Keyword extends Factoid {

    private final int titleCount;

    private int hash;
    private String string;
    private String displayString;

    public Keyword(final String url, final String name, final int titleCount) {
        super(url, name);
        this.titleCount = titleCount;
    }

    public int titleCount() {
        return titleCount;
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
        if(!(o instanceof Keyword))
            return false;
        final Keyword kr = (Keyword) o;
        return Objects.equals(url, kr.url) && Objects.equals(name, kr.name) && Objects.equals(titleCount, kr.titleCount);
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

    public static String string(final Keyword k) {
        return String.format("%s(url=%s, name=%s, titleCount=%,d)", Keyword.class.getSimpleName(), k.url, k.name, k.titleCount);
    }

    public static int hash(final Keyword k) {
        return Objects.hash(k.url, k.name, k.titleCount);
    }

    public static String displayString(final Keyword k) {
        return String.format("Type: %s%nUrl: %s%nName: %s%nTitle Count: %,d", Keyword.class.getSimpleName(), k.url, k.name, k.titleCount);
    }
}
