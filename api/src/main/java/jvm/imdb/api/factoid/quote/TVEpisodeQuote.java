package jvm.imdb.api.factoid.quote;

import jvm.imdb.api.factoid.title.tv.TV;
import jvm.imdb.api.factoid.title.tv.TVEpisode;

import java.util.Objects;

public class TVEpisodeQuote extends Quote {

    private final TVEpisode episode;

    private int hash;
    private String string;
    private String displayString;

    public TVEpisodeQuote(final String url, final String name, final TV tv, final TVEpisode episode, final String text) {
        super(url, name, tv, text);
        this.episode = episode;
    }

    public TV tv() {
        return (TV) title;
    }

    public TVEpisode episode() {
        return episode;
    }

    public String displayString() {
        if(displayString == null)
            displayString = displayString(this);
        return displayString;
    }

    @Override
    public boolean equals(final Object o) {
        if(o == null)
            return false;
        if(o == this)
            return true;
        if(!(o instanceof TVEpisodeQuote))
            return false;
        final TVEpisodeQuote tveq = (TVEpisodeQuote) o;
        return Objects.equals(url, tveq.url) && Objects.equals(name, tveq.name) && Objects.equals(title, tveq.title) && Objects.equals(episode, tveq.episode) && Objects.equals(text, tveq.text);
    }

    public static int hash(final TVEpisodeQuote tveq) {
        return Objects.hash(Quote.hash(tveq), tveq);
    }

    public static String string(final TVEpisodeQuote tveq) {
        return String.format("%s(url=%s, name=%s, tv=%s, episode=%s, text=%s)", TVEpisodeQuote.class.getSimpleName(), tveq.url, tveq.name, tveq.title, tveq.episode, tveq.text);
    }

    public static String displayString(final TVEpisodeQuote tveq) {
        return String.format("Type: %s%nUrl: %s%nName: %s%nTV: %s%nEpisode: %s%nText: %s", TVEpisodeQuote.class.getSimpleName(), tveq.url, tveq.name, tveq.title.displayString(), tveq.episode.displayString(), tveq.text);
    }
}
