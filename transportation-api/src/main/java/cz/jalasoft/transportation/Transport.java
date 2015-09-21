package cz.jalasoft.transportation;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cz.jalasoft.util.ArgumentAssertion.mustNotBeNullOrEmpty;

/**
 * Created by honzales on 16.5.15.
 */
public final class Transport {

    private static final String TO_STRING_PATTERN = "Transport[%s][%s][%s]";

    public static Builder newTransport() {
        return new Builder();
    }

    private final String carrier;
    private final String code;
    private final Optional<String> maybeName;

    private Transport(Builder builder) {
        this.carrier = builder.carrier;
        this.code = builder.code;
        this.maybeName = builder.maybeName;
    }

    public String carrier() {
        return carrier;
    }

    public String code() {
        return code;
    }

    public Optional<String> name() {
        return maybeName;
    };

    public String fullName() {
        return code + " " + name().orElse("");
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_PATTERN, carrier, code(), name().orElse(""));
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

        private static final Pattern TRANSPORT_DESCRIPTOR_PATTERN = Pattern.compile("Transport\\[(.+)\\]\\[(.+)\\]\\[(.*)\\]");

        public static Transport fromString(String descriptor) {
            mustNotBeNullOrEmpty(descriptor, "Transport description");

            Matcher matcher = TRANSPORT_DESCRIPTOR_PATTERN.matcher(descriptor);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Input '" + descriptor + "' does not match expected pattern");
            }

            return new Builder()
                    .carrier(matcher.group(1))
                    .code(matcher.group(2))
                    .name(matcher.group(3))
                    .get();
        }

        String carrier;
        String code;
        Optional<String> maybeName = Optional.empty();

        private Builder() {}

        public Builder carrier(String carrier) {
            mustNotBeNullOrEmpty(carrier, "Provider");

            this.carrier = carrier;
            return this;
        }

        public Builder code(String code) {
            mustNotBeNullOrEmpty(code, "Transport code");

            this.code = code;
            return this;
        }

        public Builder name(String name) {
            this.maybeName = Optional.ofNullable(name);
            return this;
        }

        public Transport get() {
            return new Transport(this);
        }
    }
}
