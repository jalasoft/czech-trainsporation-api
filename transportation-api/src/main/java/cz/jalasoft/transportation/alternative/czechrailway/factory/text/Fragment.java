package cz.jalasoft.transportation.alternative.czechrailway.factory.text;


import cz.jalasoft.transportation.alternative.czechrailway.factory.text.converter.Converter;
import cz.jalasoft.transportation.alternative.czechrailway.factory.text.converter.ConverterException;
import cz.jalasoft.transportation.alternative.czechrailway.factory.text.converter.StringConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Honza Lastovicka on 17.4.15.
 */
public abstract class Fragment<T extends Fragment<T>> {

    /**
     * Creates a new fragment from plain text.
     *
     * @param text a source of a new fragment, must not be null or empty.
     * @return never null
     * @throws java.lang.IllegalArgumentException if text is null or empty.
     */
    public static TextFragment fromText(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text fragment must not be null or empty.");
        }
        return new TextFragment(text);
    }

    public static TextFragment fromInputStream(InputStream input) throws IOException {
        if (input == null) {
            throw new IllegalArgumentException("Input stream must not be null.");
        }

        InputStreamReader reader = new InputStreamReader(input);
        BufferedReader buffReader = new BufferedReader(reader);

        String line;
        StringBuilder bldr = new StringBuilder();

        while((line = buffReader.readLine()) != null) {
            bldr.append(line);
        }
        return fromText(bldr.toString());
    }

    /**
     * Gets a string representation of this fragment.
     *
     * @return never null or empty;
     */
    public abstract String text();

    /**
     * Converts this fragment to a value that is distinguished by
     * a simpleValueConverter.
     *
     * @param converter must not be null
     * @param <T> resulting type from th simpleValueConverter.
     * @return nvr null
     * @throws ConverterException if an error occurs during conversion
     * @throws java.lang.IllegalArgumentException if simpleValueConverter isnull
     */
    public <T> T convertValue(StringConverter<T> converter) throws ConverterException {
        return converter.convert(text());
    }

    public <U> U convert(Converter<T, U> converter) throws ConverterException {
        return converter.convert(getThis());
    }

    abstract T getThis();

    public Optional<RegexFragment> asMatchingFragment(Pattern pattern) {
        Matcher matcher = pattern.matcher(text());

        if (!matcher.matches()) {
            return Optional.empty();
        }

        return Optional.of(new RegexFragment(matcher));
    }
}
