package jvm.imdb.api.factoid.title.tv;

import jvm.imdb.api.factoid.title.Title;

public class TV extends Title {

    public TV(final String url, final String thumbUrl, final String name, final String year) {
        super(Type.TV, url, thumbUrl, name, year);
    }
}
