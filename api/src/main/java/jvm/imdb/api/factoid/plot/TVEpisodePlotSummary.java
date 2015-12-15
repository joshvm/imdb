package jvm.imdb.api.factoid.plot;

import jvm.imdb.api.factoid.title.tv.TV;
import jvm.imdb.api.factoid.title.tv.TVEpisode;

import java.util.Objects;

public class TVEpisodePlotSummary extends PlotSummary {

    private final TVEpisode episode;

    private int hash;
    private String string;
    private String displayString;

    public TVEpisodePlotSummary(final String url, final String name, final TV tv, final TVEpisode episode, final String summary) {
        super(url, name, tv, summary);
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
        if(!(o instanceof TVEpisodePlotSummary))
            return false;
        final TVEpisodePlotSummary tveps = (TVEpisodePlotSummary) o;
        return Objects.equals(url, tveps.url) && Objects.equals(name, tveps.name) && Objects.equals(title, tveps.title) && Objects.equals(episode, tveps.episode) && Objects.equals(text, tveps.text);
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

    public static int hash(final TVEpisodePlotSummary tveps) {
        return Objects.hash(PlotSummary.hash(tveps), tveps.episode);
    }

    public static String string(final TVEpisodePlotSummary tveps) {
        return String.format("%s(url=%s, name=%s, tv=%s, episode=%s, text=%s)", TVEpisodePlotSummary.class.getSimpleName(), tveps.url, tveps.name, tveps.title, tveps.episode, tveps.text);
    }

    public static String displayString(final TVEpisodePlotSummary tveps) {
        return String.format("Type: %s%nUrl: %s%nName: %s%nTV: %s%nEpisode: %s%nText: %s", TVEpisodePlotSummary.class.getSimpleName(), tveps.url, tveps.name, tveps.title.displayString(), tveps.episode.displayString(), tveps.text);
    }
}
