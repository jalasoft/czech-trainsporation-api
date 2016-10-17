package cz.jalasoft.transportation.alternative.czechrailway.factory.text;

import cz.jalasoft.transportation.alternative.czechrailway.factory.text.converter.Converter;
import cz.jalasoft.transportation.alternative.czechrailway.factory.text.converter.ConverterException;

/**
 * Created by honzales on 9.6.15.
 */
public interface FragmentConverters {

    /*
    public static <T extends Fragment<T>> Converter<T, TextFragment> withoutCharacters(final CharMatcher matcher) {
        return new Converter<T, TextFragment>() {
            @Override
            public TextFragment convert(T from) throws ConverterException {
                String newText = matcher.removeFrom(from.text());
                return Fragment.fromText(newText);
            }

            @Override
            public Class<T> sourceType() {
                return
            }

            @Override
            public Class<TextFragment> targetType() {
                return TextFragment.class;
            }
        };
    }*/

    static Converter<TextFragment, TextFragment> toHtmlEscaped() {

        return new Converter<TextFragment, TextFragment>() {
            @Override
            public TextFragment convert(TextFragment from) throws ConverterException {
                String text = from.text();
                String newText = text.replace("&amp;", "&");
                return Fragment.fromText(newText);
            }

            @Override
            public Class<TextFragment> sourceType() {
                return TextFragment.class;
            }

            @Override
            public Class<TextFragment> targetType() {
                return TextFragment.class;
            }
        };
    }
}
