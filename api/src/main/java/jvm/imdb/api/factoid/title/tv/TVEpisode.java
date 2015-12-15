package jvm.imdb.api.factoid.title.tv;

import jvm.imdb.api.factoid.title.Title;

public class TVEpisode extends Title {

    public TVEpisode(final String url, final String thumbUrl, final String name, final String year) {
        super(Type.TV_EPISODE, url, thumbUrl, name, year);
    }
}
