package jvm.imdb.api.factoid.title;

import jvm.imdb.api.factoid.Factoid;

import java.util.Objects;

public class Title extends Factoid {

    public enum Type {

        MOVIE,
        TV,
        TV_EPISODE,
        VIDEO_GAME;

        public static Type fromString(final String typeStr) {
            if(typeStr.contains("TV Series") || typeStr.contains("TV Movie"))
                return Title.Type.TV;
            else if(typeStr.contains("TV Episode"))
                return Title.Type.TV_EPISODE;
            else if(typeStr.contains("Video Game"))
                return Title.Type.VIDEO_GAME;
            else
                return MOVIE;
        }
    }

    private final Title.Type type;
    private final String thumbUrl;
    private final String year;

    private int hash;
    private String string;
    private String displayString;

    protected Title(final Title.Type type, final String url, final String thumbUrl, final String name, final String year) {
        super(url, name);
        this.type = type;
        this.thumbUrl = thumbUrl;
        this.year = year;
    }

    public Title.Type type() {
        return type;
    }

    public String thumbUrl() {
        return thumbUrl;
    }

    public String year() {
        return year;
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
        if(!(o instanceof Title))
            return false;
        final Title tr = (Title) o;
        return Objects.equals(type, tr.type) && Objects.equals(url, tr.url) && Objects.equals(thumbUrl, tr.thumbUrl) && Objects.equals(name, tr.name) && Objects.equals(year, tr.year);
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

    public static String string(final Title tr) {
        return String.format("%s(type=%s, url=%s, thumbUrl=%s, name=%s, year=%s)", Title.class.getSimpleName(), tr.type, tr.url, tr.thumbUrl, tr.name, tr.year);
    }

    public static int hash(final Title tr) {
        return Objects.hash(tr.type, tr.url, tr.thumbUrl, tr.name, tr.year);
    }

    public static String displayString(final Title t) {
        return String.format("Type: %s%nUrl: %s%nThumbUrl: %s%nName: %s%nYear: %s", t.type, t.url, t.thumbUrl, t.name, t.year);
    }
}
