import jvm.imdb.api.Imdb;
import jvm.imdb.api.factoid.Factoid;
import jvm.imdb.api.factoid.FactoidEngine;
import jvm.imdb.api.factoid.character.Characters;
import jvm.imdb.api.factoid.company.Companies;
import jvm.imdb.api.factoid.keyword.Keywords;
import jvm.imdb.api.factoid.name.Names;
import jvm.imdb.api.factoid.title.Titles;

import java.io.IOException;
import java.io.PrintStream;

public class ImdbFactoidsTest {

    public static void main(String[] args) throws IOException {
        System.setOut(new PrintStream("imdb_test_results.txt"));
        testTitles();
        testNames();
        testCharacters();
        testCompanies();
        testKeywords();
        testPlotSummaries();
        testBiographies();
        testQuotes();
        System.out.flush();
        System.out.close();
    }

    private static void test(final String name, final FactoidEngine<? extends Factoid> engine, final String query, final int options) {
        System.out.printf("***** \"%s\" %s *****%n", query, name);
        engine.trySearch(query, options).forEach(f -> System.out.println(f.displayString() + System.lineSeparator()));
        System.out.printf("***** \"%s\" END OF %s *****%n%n", query, name);
    }

    private static void testTitles() {
        test("TITLES", Imdb.titles(), "batman", 0);
        test("TITLES (EXACT)", Imdb.titles(), "batman", Titles.EXACT_MATCH);

        test("MOVIES", Imdb.titles(), "batman", Titles.MOVIE);
        test("MOVIES (EXACT)", Imdb.titles(), "batman", Titles.MOVIE | Titles.EXACT_MATCH);

        test("TV", Imdb.titles(), "batman", Titles.TV);
        test("TV (EXACT)", Imdb.titles(), "batman", Titles.TV | Titles.EXACT_MATCH);

        test("TV EPISODES", Imdb.titles(), "batman", Titles.TV_EPISODE);
        test("TV EPISODES (EXACT)", Imdb.titles(), "batman", Titles.TV_EPISODE | Titles.EXACT_MATCH);

        test("VIDEO GAMES", Imdb.titles(), "batman", Titles.VIDEO_GAME);
        test("VIDEO GAMES (EXACT)", Imdb.titles(), "batman", Titles.VIDEO_GAME | Titles.EXACT_MATCH);

        test("MOVIES AND TV", Imdb.titles(), "batman", Titles.MOVIE | Titles.TV);
        test("MOVIES AND TV (EXACT)", Imdb.titles(), "batman", Titles.MOVIE | Titles.TV | Titles.EXACT_MATCH);

        test("MOVIES AND TV EPISODES", Imdb.titles(), "batman", Titles.MOVIE | Titles.TV_EPISODE);
        test("MOVIES AND TV EPISODES (EXACT)", Imdb.titles(), "batman", Titles.MOVIE | Titles.TV_EPISODE | Titles.EXACT_MATCH);

        test("MOVIES AND VIDEO GAMES", Imdb.titles(), "batman", Titles.MOVIE | Titles.VIDEO_GAME);
        test("MOVIES AND VIDEO GAMES (EXACT)", Imdb.titles(), "batman", Titles.MOVIE | Titles.VIDEO_GAME | Titles.EXACT_MATCH);

        test("TV AND TV EPISODES", Imdb.titles(), "batman", Titles.TV | Titles.TV_EPISODE);
        test("TV AND TV EPISODES (EXACT)", Imdb.titles(), "batman", Titles.TV | Titles.TV_EPISODE | Titles.EXACT_MATCH);

        test("TV AND VIDEO GAMES", Imdb.titles(), "batman", Titles.TV | Titles.VIDEO_GAME);
        test("TV AND VIDEO GAMES (EXACT)", Imdb.titles(), "batman", Titles.TV | Titles.VIDEO_GAME | Titles.EXACT_MATCH);
    }

    private static void testNames() {
        test("NAMES", Imdb.names(), "samuel l. jackson", 0);
        test("NAMES (EXACT)", Imdb.names(), "samuel l. jackson", Names.EXACT_MATCH);
    }

    private static void testCharacters() {
        test("CHARACTERS", Imdb.characters(), "batman", 0);
        test("CHARACTERS (EXACT)", Imdb.characters(), "batman", Characters.EXACT_MATCH);
    }

    private static void testCompanies() {
        test("COMPANIES", Imdb.companies(), "pixar", 0);
        test("COMPANIES (EXACT)", Imdb.companies(), "pixar", Companies.EXACT_MATCH);
    }

    private static void testKeywords() {
        test("KEYWORDS", Imdb.keywords(), "ice", 0);
        test("KEYWORDS (EXACT)", Imdb.keywords(), "ice", Keywords.EXACT_MATCH);
    }

    private static void testPlotSummaries() {
        test("PLOT SUMMARIES", Imdb.plotSummaries(), "ice", 0);
        test("PLOT SUMMARIES (EXACT)", Imdb.plotSummaries(), "ice", Keywords.EXACT_MATCH);
    }

    private static void testBiographies() {
        test("BIOGRAPHIES", Imdb.biographies(), "ice", 0);
    }

    private static void testQuotes() {
        test("QUOTES", Imdb.quotes(), "ice", 0);
    }
}
