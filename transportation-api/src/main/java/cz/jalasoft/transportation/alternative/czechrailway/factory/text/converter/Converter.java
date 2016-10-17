package cz.jalasoft.transportation.alternative.czechrailway.factory.text.converter;

/**
 * A functional interface that does a conversion from
 * generic type F to generic type T.
 *
 * Created by Honza Lastovicka on 18.4.15.
 */
public interface Converter<F, T> {

    /**
     * Converts from F to T
     * @param from must not be null
     * @return never null
     * @throws ConverterException if a problem occurred during the conversion
     * @throws java.lang.IllegalArgumentException if from is null
     */
    T convert(F from) throws ConverterException;

    Class<F> sourceType();

    Class<T> targetType();
}
