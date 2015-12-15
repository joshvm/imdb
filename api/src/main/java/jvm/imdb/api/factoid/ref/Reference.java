package jvm.imdb.api.factoid.ref;

import jvm.imdb.api.factoid.Factoid;

import java.util.Objects;

public class Reference extends Factoid {

    private int hash;
    private String string;
    private String displayString;

    public Reference(final String url, final String name) {
        super(url, name);
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
        if(!(o instanceof Reference))
            return false;
        final Reference r = (Reference) o;
        return Objects.equals(url, r.url) && Objects.equals(name, r.name);
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

    public static String string(final Reference r) {
        return String.format("%s(url=%s, name=%s)", Reference.class.getSimpleName(), r.url, r.name);
    }

    public static int hash(final Reference r) {
        return Objects.hash(r.url, r.name);
    }

    public static String displayString(final Reference r) {
        return String.format("%s (%s)", r.name, r.url);
    }
}
