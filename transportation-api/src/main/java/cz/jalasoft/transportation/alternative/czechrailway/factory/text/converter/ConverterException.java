package cz.jalasoft.transportation.alternative.czechrailway.factory.text.converter;


/**
 * An error that might appear during conversion.
 *
 * Created by Honza Lastovicka on 18.4.15.
 */
public class ConverterException extends Exception {

    private static final String MESSAGE_PATTERN = "An error occurred during converting value %s of type %s to type %s.";

    private final Object source;
    private final Class<?> sourceType;
    private final Class<?> targetType;
    private final String reason;

    public ConverterException(Object source, Class<?> sourceType, Class<?> targetType) {
        this.source = source;
        this.sourceType = sourceType;
        this.targetType = targetType;
        this.reason = null;
    }

    public ConverterException(Object source, Class<?> sourceType, Class<?> targetType, Exception cause) {
        super(cause);

        this.source = source;
        this.sourceType = sourceType;
        this.targetType = targetType;
        this.reason = null;
    }

    public ConverterException(Object source, Class<?> sourceType, Class<?> targetType, String reason) {

        this.source = source;
        this.sourceType = sourceType;
        this.targetType = targetType;
        this.reason = reason;
    }

    public Object getSource() {
        return source;
    }

    public Class<?> getSourceType() {
        return sourceType;
    }

    public Class<?> getTargetType() {
        return targetType;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE_PATTERN, source, sourceType, targetType) + (reason != null ? " Reason: " + reason : "");
    }
}
