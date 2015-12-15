package jvm.imdb.api.factoid.title.movie;

import jvm.imdb.api.factoid.title.Title;

public class Movie extends Title {

    public Movie(final String url, final String thumbUrl, final String name, final String year) {
        super(Type.MOVIE, url, thumbUrl, name, year);
    }

}
