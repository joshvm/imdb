package jvm.imdb.api.factoid.quote;

import jvm.imdb.api.Imdb;
import jvm.imdb.api.factoid.FactoidEngine;
import jvm.imdb.api.factoid.title.Title;
import jvm.imdb.api.factoid.title.game.VideoGame;
import jvm.imdb.api.factoid.title.movie.Movie;
import jvm.imdb.api.factoid.title.tv.TV;
import jvm.imdb.api.factoid.title.tv.TVEpisode;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

public class Quotes extends FactoidEngine<Quote> {

    @Override
    protected String url(final String query, final int options) {
        return String.format("http://www.imdb.com/search/text?realm=title&&field=quotes&&q=%s&ref_=fn_qu", query);
    }

    @Override
    protected String tableClass() {
        return "results";
    }

    @Override
    protected String rowPattern() {
        return "(odd|even)";
    }

    @Override
    protected void parseRow(final String query, final int options, final Element tr, final List<Quote> results) {
        final Element titleELement = tr.getElementsByAttributeValue("class", "title").first();
        final Element a = titleELement.getElementsByTag("a").first();
        final String titleUrl = Imdb.BASE_URL + a.attr("href");
        final String titleName = a.ownText();
        final String desc = titleELement.getElementsByAttributeValue("class", "year_type").first().ownText();
        final String year = desc.split(" ")[0].trim().replaceAll("[()]", "");
        final Title.Type type = Title.Type.fromString(desc);
        Title title = null;
        switch(type){
            case MOVIE:
                title = new Movie(titleUrl, null, titleName, year);
                break;
            case TV:
                title = new TV(titleUrl, null, titleName, year);
                break;
            case VIDEO_GAME:
                title = new VideoGame(titleUrl, null, titleName, year);
                break;
            case TV_EPISODE:
                title = new TVEpisode(titleUrl, null, titleName, year);
                break;
        }
        final String text = titleELement.getElementsByTag("div").stream().map(Element::ownText).collect(Collectors.joining(System.lineSeparator()));
        final Elements episodes = titleELement.getElementsByAttributeValue("class", "episode");
        if(!episodes.isEmpty()){
            final Element episode = episodes.first();
            final Element episodeA = episode.getElementsByTag("a").first();
            final String episodeUrl = Imdb.BASE_URL + episodeA.attr("href");
            final String episodeName = episodeA.ownText();
            final String episodeYear = episode.ownText().replaceFirst("Episode:", "").trim().replaceAll("[()]", "");
            final TVEpisode tvEpisode = new TVEpisode(episodeUrl, null, episodeName, episodeYear);
            results.add(new TVEpisodeQuote(titleUrl, titleName, (TV) title, tvEpisode, text));
        }else{
            results.add(new Quote(titleUrl, titleName, title, text));
        }
    }
}
