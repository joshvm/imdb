package jvm.imdb.api.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public final class NetUtils {

    public static String userAgent = "Mozilla/5.0";
    public static int timeout = 10000;

    private NetUtils() {
    }

    public static Document doc(final String url, final int timeout) throws IOException {
        return Jsoup.connect(url).timeout(timeout).userAgent(userAgent).get();
    }

    public static Document doc(final String url) throws IOException {
        return doc(url, timeout);
    }
}
