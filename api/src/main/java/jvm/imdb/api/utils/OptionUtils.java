package jvm.imdb.api.utils;

public final class OptionUtils {

    private OptionUtils() {
    }

    public static boolean option(final int options, final int option) {
        return (options & option) != 0;
    }

    public static int options(final int... options) {
        int sum = 0;
        for(final int opt : options)
            sum |= opt;
        return sum;
    }

    public static boolean allOptions(final int options, final int... checkOptions) {
        for(final int checkOpt : checkOptions)
            if(!option(options, checkOpt))
                return false;
        return true;
    }

    public static boolean anyOptions(final int options, final int... checkOptions) {
        for(final int checkOpt : checkOptions)
            if(option(options, checkOpt))
                return true;
        return false;
    }
}
