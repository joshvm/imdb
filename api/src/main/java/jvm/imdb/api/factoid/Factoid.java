package jvm.imdb.api.factoid;

public class Factoid {

    protected final String url;
    protected final String name;

    protected Factoid(final String url, final String name) {
        this.url = url;
        this.name = name;
    }

    public String url() {
        return url;
    }

    public String name() {
        return name;
    }

    public String displayString() {
        return null;
    }
}
