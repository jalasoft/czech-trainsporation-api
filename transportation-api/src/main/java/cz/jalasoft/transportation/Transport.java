package cz.jalasoft.transportation;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cz.jalasoft.util.ArgumentAssertion.mustNotBeNullOrEmpty;

/**
 * Created by honzales on 16.5.15.
 */
public final class Transport {

    private static final Pattern TRANSPORT_DESCRIPTOR_PATTERN = Pattern.compile("Transport\\[(.+)\\]\\[((.+) (\\((.*)\\))?)\\]");

    public static Builder newTransport() {
        return new Builder();
    }

    public static Transport fromString(String descriptor) {
        mustNotBeNullOrEmpty(descriptor, "Transport description");

        return new Transport(descriptor);
    }

    private Transport(String descriptor) {
        Matcher matcher = TRANSPORT_DESCRIPTOR_PATTERN.matcher(descriptor);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Input '" + descriptor + "' does not match expected pattern");
        }
        this.matcher = matcher;
    }

    private final Matcher matcher;

    public String providerName() {
        return matcher.group(1);
    }

    public String code() {
        return matcher.group(3);
    }

    public Optional<String> name() {
        String maybeName = matcher.group(4);
        return Optional.ofNullable(maybeName);
    };

    public String fullName() {
        return matcher.group(2);
    }

    @Override
    public String toString() {
        return matcher.group();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Transport)) {
            return false;
        }

        Transport that = (Transport) obj;

        return this.toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 37 + toString().hashCode();
        return result;
    }


    //---------------------------------------------------------------------------
    //BUILDER
    //---------------------------------------------------------------------------

    public static final class Builder {

        String providerName;
        String transportCode;
        String transportName;

        private Builder() {}

        public Builder providedBy(String provider) {
            mustNotBeNullOrEmpty(provider, "Provider");

            this.providerName = provider;
            return this;
        }

        public Builder withCode(String code) {
            mustNotBeNullOrEmpty(code, "Transport code");

            this.transportCode = code;
            return this;
        }

        public Builder named(String name) {
            this.transportName = name;
            return this;
        }

        public Transport get() {
            StringBuilder bldr =
                    new StringBuilder("Transport[")
                            .append(providerName)
                            .append("][")
                            .append(transportCode)
                            .append(" ");

            if (transportName != null) {
                bldr
                        .append("(")
                        .append(transportName)
                        .append(")");
            }

            String descriptor = bldr
                    .append("]")
                    .toString();

            return new Transport(descriptor);
        }
    }
}
