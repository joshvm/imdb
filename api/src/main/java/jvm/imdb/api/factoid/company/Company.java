package jvm.imdb.api.factoid.company;

import jvm.imdb.api.factoid.Factoid;

import java.util.Objects;

public class Company extends Factoid {

    private final String area;
    private final String category;

    private int hash;
    private String string;
    private String displayString;

    public Company(final String url, final String name, final String area, final String category) {
        super(url, name);
        this.area = area;
        this.category = category;
    }

    public String area() {
        return area;
    }

    public String category() {
        return category;
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
        if(!(o instanceof Company))
            return false;
        final Company cr = (Company) o;
        return Objects.equals(url, cr.url) && Objects.equals(name, cr.name) && Objects.equals(area, cr.area) && Objects.equals(category, cr.category);
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

    public static String string(final Company cr) {
        return String.format("%s(url=%s, name=%s, area=%s, category=%s)", Company.class.getSimpleName(), cr.url, cr.name, cr.area, cr.category);
    }

    public static int hash(final Company cr) {
        return Objects.hash(cr.url, cr.name, cr.area, cr.category);
    }

    public static String displayString(final Company c) {
        return String.format("Type: %s%nUrl: %s%nName: %s%nArea: %s%nCategory: %s", Company.class.getSimpleName(), c.url, c.name, c.area, c.category);
    }
}
