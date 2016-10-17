package cz.jalasoft.transportation.alternative.czechrailway.factory.text.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Created by honzales on 18.4.15.
 */
public interface StringConverters {

    static StringConverter<String> identity() {
        return new StringConverter<String>() {
            @Override
            public String convert(String from) throws ConverterException {
                return from;
            }

            @Override
            public Class<String> targetType() {
                return String.class;
            }
        };
    }

    static StringConverter<Byte> toByte() {
        return new StringConverter<Byte>() {
            @Override
            public Class targetType() {
                return Byte.class;
            }

            @Override
            public Byte convert(String from) throws ConverterException {
                try {
                    return Byte.valueOf(from);
                } catch (NumberFormatException exc) {
                    throw new ConverterException(from, String.class, Byte.class);
                }
            }
        };
    }

    static StringConverter<Float> toFloat() {
        return new StringConverter<Float>() {
            @Override
            public Class targetType() {
                return Float.class;
            }

            @Override
            public Float convert(String from) throws ConverterException {
                try {
                    return Float.valueOf(from);
                } catch (NumberFormatException exc) {
                    throw new ConverterException(from, String.class, Float.class);
                }
            }
        };
    }

    static StringConverter<Double> toDouble() {
        return new StringConverter<Double>() {
            @Override
            public Double convert(String from) throws ConverterException {
                try {
                    return Double.valueOf(from);
                } catch (NumberFormatException exc) {
                    throw new ConverterException(from, String.class, Double.class);
                }
            }

            @Override
            public Class<Double> targetType() {
                return Double.class;
            }
        };
    }

    static StringConverter<Short> toShort() {
        return new StringConverter<Short>() {
            @Override
            public Class<Short> targetType() {
                return Short.class;
            }

            @Override
            public Short convert(String from) throws ConverterException {
                try {
                    return Short.valueOf(from);
                } catch (NumberFormatException exc) {
                    throw new ConverterException(from, String.class, Short.class);
                }
            }
        };
    }

    static StringConverter<Integer> toInteger() {
        return new StringConverter<Integer>() {
            @Override
            public Integer convert(String from) throws ConverterException {
                try {
                    return Integer.valueOf(from);
                } catch (NumberFormatException exc) {
                    throw new ConverterException(from, String.class, Integer.class);
                }
            }

            @Override
            public Class<Integer> targetType() {
                return Integer.class;
            }
        };
    }

    static StringConverter<Long> toLong() {
        return new StringConverter<Long>() {
            @Override
            public Long convert(String from) throws ConverterException {
                try {
                    return Long.parseLong(from);
                } catch (NumberFormatException exc) {
                    throw new ConverterException(from, String.class, Long.class);
                }
            }

            @Override
            public Class<Long> targetType() {
                return Long.class;
            }
        };
    }

    static StringConverter<Boolean> toBoolean() {
        return new StringConverter<Boolean>() {
            @Override
            public Boolean convert(String from) throws ConverterException {
                try {
                    return Boolean.parseBoolean(from);
                } catch (NumberFormatException exc) {
                    throw new ConverterException(from, String.class, Boolean.class);
                }
            }

            @Override
            public Class<Boolean> targetType() {
                return Boolean.class;
            }
        };
    }

    static StringConverter<Float> toFloatPrimitive() {
        return new StringConverter<Float>() {
            @Override
            public Float convert(String from) throws ConverterException {
                try {
                    return Float.parseFloat(from);
                } catch (NumberFormatException exc) {
                    throw new ConverterException(from, String.class, float.class);
                }
            }

            @Override
            public Class<Float> targetType() {
                return float.class;
            }
        };
    }

    static StringConverter<Double> toDoublePrimitive() {
        return new StringConverter<Double>() {
            @Override
            public Double convert(String from) throws ConverterException {
                try {
                    return Double.parseDouble(from);
                } catch (NumberFormatException exc) {
                    throw new ConverterException(from, String.class, Double.class);
                }
            }

            @Override
            public Class<Double> targetType() {
                return double.class;
            }
        };
    }

    static StringConverter<Boolean> toBooleanPrimitive() {
        return new StringConverter<Boolean>() {
            @Override
            public Boolean convert(String from) throws ConverterException {
                try {
                    return Boolean.parseBoolean(from);
                } catch (NumberFormatException exc) {
                    throw new ConverterException(from, String.class, boolean.class);
                }
            }

            @Override
            public Class<Boolean> targetType() {
                return boolean.class;
            }
        };
    }

    static StringConverter<Byte> toBytePrimitive() {
        return new StringConverter<Byte>() {
            @Override
            public Byte convert(String from) throws ConverterException {
                try {
                    return Byte.parseByte(from);
                } catch (NumberFormatException exc) {
                    throw new ConverterException(from, String.class, byte.class);
                }
            }

            @Override
            public Class<Byte> targetType() {
                return byte.class;
            }
        };
    }

    static StringConverter<Short> toShortPrimitive() {
        return new StringConverter<Short>() {
            @Override
            public Short convert(String from) throws ConverterException {
                try {
                    return Short.parseShort(from);
                } catch (NumberFormatException exc) {
                    throw new ConverterException(from, String.class, Short.class);
                }
            }

            @Override
            public Class<Short> targetType() {
                return short.class;
            }
        };
    }

    static StringConverter<Integer> toIntPrimitive() {
        return new StringConverter<Integer>() {
            @Override
            public Integer convert(String from) throws ConverterException {
                try {
                    return Integer.parseInt(from);
                } catch (NumberFormatException exc) {
                    throw new ConverterException(from, String.class, Integer.class);
                }
            }

            @Override
            public Class<Integer> targetType() {
                return int.class;
            }
        };
    }

    static StringConverter<Long> toLongPrimitive() {
        return new StringConverter<Long>() {
            @Override
            public Long convert(String from) throws ConverterException {
                try {
                    return Long.parseLong(from);
                } catch (NumberFormatException exc) {
                    throw new ConverterException(from, String.class, Long.class);
                }
            }

            @Override
            public Class<Long> targetType() {
                return long.class;
            }
        };
    }

    static StringConverter<Integer> toIntegerOr(final StringConverter<Integer> alternative) {
        return new StringConverter<Integer>() {
            @Override
            public Integer convert(String from) throws ConverterException {
                try {
                    return Integer.parseInt(from);
                } catch (NumberFormatException exc) {
                    return alternative.convert(from);
                }
            }

            @Override
            public Class<Integer> targetType() {
                return Integer.class;
            }
        };
    }

    static StringConverter<LocalDateTime> toLocalDateTime(String pattern) {
        return new StringConverter<LocalDateTime>() {
            @Override
            public LocalDateTime convert(String from) throws ConverterException {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDateTime.parse(from, formatter);
            }

            @Override
            public Class<LocalDateTime> targetType() {
                return LocalDateTime.class;
            }
        };
    }

    static StringConverter<LocalDateTime> toISOLocalDateTime() {
        return new StringConverter<LocalDateTime>() {
            @Override
            public Class<LocalDateTime> targetType() {
                return LocalDateTime.class;
            }

            @Override
            public LocalDateTime convert(String from) throws ConverterException {
                try {
                    return LocalDateTime.parse(from, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                } catch (DateTimeParseException exc) {
                    throw new ConverterException(from, String.class, LocalDateTime.class);
                }
            }
        };
    }
}
