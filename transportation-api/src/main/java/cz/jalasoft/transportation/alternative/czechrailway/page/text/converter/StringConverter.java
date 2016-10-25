package cz.jalasoft.transportation.alternative.czechrailway.page.text.converter;

/**
 * Created by honzales on 18.4.15.
 */
public abstract class StringConverter<T> implements Converter<String, T> {

    @Override
    public final Class<String> sourceType() {
        return String.class;
    }

    @Override
    public abstract Class<T> targetType();
}
