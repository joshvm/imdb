package jvm.imdb.api.factoid.character;

import jvm.imdb.api.factoid.Factoid;
import jvm.imdb.api.factoid.ref.Reference;

import java.util.Objects;

public class Character extends Factoid {

    private final Reference reference;

    private int hash;
    private String string;
    private String displayString;

    public Character(final String url, final String name, final Reference reference) {
        super(url, name);
        this.reference = reference;
    }

    public Reference reference() {
        return reference;
    }

    public String displayString() {
        if(displayString == null)
            displayString = displayString(this);
        return displayString;
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

    public static String string(final Character cr) {
        return String.format("%s(url=%s, name=%s, reference=%s)", Character.class.getSimpleName(), cr.url, cr.name, cr.reference);
    }

    public static int hash(final Character cr) {
        return Objects.hash(cr.url, cr.name, cr.reference);
    }

    public static String displayString(final Character c) {
        return String.format("Type: %s%nUrl: %s%nName: %s%nReference: %s", Character.class.getSimpleName(), c.url, c.name, c.reference.displayString());
    }
}
