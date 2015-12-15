package jvm.imdb.api.factoid.title;

import jvm.imdb.api.Imdb;
import jvm.imdb.api.factoid.FactoidEngine;
import jvm.imdb.api.factoid.title.game.VideoGame;
import jvm.imdb.api.factoid.title.movie.Movie;
import jvm.imdb.api.factoid.title.tv.TV;
import jvm.imdb.api.factoid.title.tv.TVEpisode;
import jvm.imdb.api.utils.OptionUtils;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Titles extends FactoidEngine<Title> {

    public static final int EXACT_MATCH = 1 << 1;

    public static final int MOVIE = 1 << 2;
    public static final int TV = 1 << 3;
    public static final int TV_EPISODE = 1 << 4;
    public static final int VIDEO_GAME = 1 << 5;

    private static final Pattern PARAM_PATTERN = Pattern.compile("\\(([\\w\\s\\d]+)\\)");

    @Override
    protected String url(final String query, final int options) {
        if(options == 0 || options == EXACT_MATCH || OptionUtils.allOptions(options, MOVIE, TV, TV_EPISODE, VIDEO_GAME)){
            if(OptionUtils.option(options, EXACT_MATCH))
                return String.format("http://www.imdb.com/find?q=%s&s=tt&exact=true&ref_=fn_tt_ex", query);
            else
                return String.format("http://www.imdb.com/find?q=%s&s=tt&ref_=fn_tt", query);
        }
        if(options == MOVIE || options == OptionUtils.options(MOVIE, EXACT_MATCH)){
            if(OptionUtils.option(options, EXACT_MATCH))
                return String.format("http://www.imdb.com/find?q=%s&s=tt&ttype=ft&exact=true&ref_=fn_tt_ex", query);
            else
                return String.format("http://www.imdb.com/find?q=%s&s=tt&ttype=ft&ref_=fn_ft", query);
        }
        if(options == TV || options == OptionUtils.options(TV, EXACT_MATCH)){
            if(OptionUtils.option(options, EXACT_MATCH))
                return String.format("http://www.imdb.com/find?q=%s&s=tt&ttype=tv&exact=true&ref_=fn_tt_ex", query);
            else
                return String.format("http://www.imdb.com/find?q=%s&s=tt&ttype=tv&ref_=fn_tv", query);
        }
        if(options == TV_EPISODE || options == OptionUtils.options(TV_EPISODE, EXACT_MATCH)){
            if(OptionUtils.option(options, EXACT_MATCH))
                return String.format("http://www.imdb.com/find?q=%s&s=tt&ttype=ep&exact=true&ref_=fn_tt_ex", query);
            else
                return String.format("http://www.imdb.com/find?q=%s&s=tt&ttype=ep&ref_=fn_ep", query);
        }
        if(options == VIDEO_GAME || options == OptionUtils.options(VIDEO_GAME, EXACT_MATCH)){
            if(OptionUtils.option(options, EXACT_MATCH))
                return String.format("http://www.imdb.com/find?q=%s&s=tt&ttype=vg&exact=true&ref_=fn_tt_ex", query);
            else
                return String.format("http://www.imdb.com/find?q=%s&s=tt&ttype=vg&ref_=fn_vg", query);
        }
        if(OptionUtils.option(options, EXACT_MATCH))
            return String.format("http://www.imdb.com/find?q=%s&s=tt&exact=true&ref_=fn_tt_ex", query);
        else
            return String.format("http://www.imdb.com/find?q=%s&s=tt&ref_=fn_tt", query);
    }

    @Override
    protected void parseRow(final String query, final int options, final Element tr, final List<Title> results) {
        final String thumbUrl = tr.getElementsByAttributeValue("class", "primary_photo").first().getElementsByTag("img").first().attr("src");
        final Element r = tr.getElementsByAttributeValue("class", "result_text").first();
        final Element a = r.getElementsByTag("a").first();
        final String url = Imdb.BASE_URL + a.attr("href");
        final String name = a.ownText();
        final String extras = r.text().replace(name, "").replaceAll("\\(I+\\)", "").replaceAll("aka .+", "").trim();
        final Matcher m = PARAM_PATTERN.matcher(extras);
        String year = m.find() ? m.group(1) : null;
        if(year != null)
            year = year.replaceAll("[()]", "");
        final Title.Type type = m.find() ? Title.Type.fromString(m.group(1)) : Title.Type.MOVIE;
        final boolean pass = options == 0 || options == EXACT_MATCH;
        switch(type){
            case MOVIE:
                if(pass || OptionUtils.option(options, MOVIE))
                    results.add(new Movie(url, thumbUrl, name, year));
                break;
            case TV:
                if(pass || OptionUtils.option(options, TV))
                    results.add(new TV(url, thumbUrl, name, year));
                break;
            case TV_EPISODE:
                if(pass || OptionUtils.option(options, TV_EPISODE))
                    results.add(new TVEpisode(url, thumbUrl, name, year));
                break;
            case VIDEO_GAME:
                if(pass || OptionUtils.option(options, VIDEO_GAME))
                    results.add(new VideoGame(url, thumbUrl, name, year));
                break;
        }
    }
}
