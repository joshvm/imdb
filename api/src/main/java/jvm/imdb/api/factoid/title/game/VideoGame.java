package jvm.imdb.api.factoid.title.game;

import jvm.imdb.api.factoid.title.Title;

public class VideoGame extends Title {

    public VideoGame(final String url, final String thumbUrl, final String name, final String year) {
        super(Type.VIDEO_GAME, url, thumbUrl, name, year);
    }
}
