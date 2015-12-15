package jvm.imdb.api.factoid.name;

import jvm.imdb.api.factoid.Factoid;
import jvm.imdb.api.factoid.ref.Reference;

import java.util.Objects;

public class Name extends Factoid {

    private final String thumbUrl;
    private final String job;
    private final Reference reference;

    private int hash;
    private String string;
    private String displayString;

    public Name(final String url, final String thumbUrl, final String name, final String job, final Reference reference) {
        super(url, name);
        this.thumbUrl = thumbUrl;
        this.job = job;
        this.reference = reference;

        hash = hash(this);
        string = string(this);
    }

    public String thumbUrl() {
        return thumbUrl;
    }

    public String job() {
        return job;
    }

    public Reference reference() {
        return reference;
    }

    public String displayString() {
        if(displayString == null)
            displayString = displayString(this);
        return displayString;
    }

    @Override
    public boolean equals(final Object o) {
        if(o == this)
            return true;
        if(!(o instanceof Name))
            return false;
        final Name nr = (Name) o;
        return Objects.equals(url, nr.url) && Objects.equals(thumbUrl, nr.thumbUrl) && Objects.equals(name, nr.name) && Objects.equals(job, nr.job) && Objects.equals(reference, nr.reference);
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

    public static int hash(final Name nr) {
        return Objects.hash(nr.url, nr.thumbUrl, nr.name, nr.job, nr.reference);
    }

    public static String string(final Name nr) {
        return String.format("%s(url=%s, thumbUrl=%s, name=%s, job=%s, reference=%s)", Name.class.getSimpleName(), nr.url, nr.thumbUrl, nr.name, nr.job, nr.reference);
    }

    public static String displayString(final Name n) {
        return String.format("Type: %s%nUrl: %s%nThumbUrl: %s%nName: %s%nJob: %s%nReference: %s", Name.class.getSimpleName(), n.url, n.thumbUrl, n.name, n.job, n.reference != null ? n.reference.displayString() : "");
    }
}
