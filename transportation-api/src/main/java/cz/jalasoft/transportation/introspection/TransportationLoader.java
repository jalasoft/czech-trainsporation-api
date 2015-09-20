package cz.jalasoft.transportation.introspection;

import cz.jalasoft.transportation.Carrier;
import cz.jalasoft.transportation.Transportation;
import cz.jalasoft.transportation.introspection.ClassPathIntrospection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by honzales on 27.6.15.
 */
public final class TransportationLoader {

    public static Map<Carrier, Transportation> loadTransporations() {
        Iterable<Class<Transportation>> classes = introspectClassPath();
        return load(classes);
    }

    private static Iterable<Class<Transportation>> introspectClassPath() {
        try {
            return ClassPathIntrospection.introspect();
        } catch (IOException exc) {
            throw new RuntimeException("An error occurred during introspecting a classpath.", exc);
        }
    }

    private static Map<Carrier, Transportation> load(Iterable<Class<Transportation>> transportationClasses) {
        Map<Carrier, Transportation> result = new HashMap<>();

        for(Class<Transportation> transClass : transportationClasses) {
            Transportation transportation = load(transClass);
            result.put(transportation.carrier(), transportation);
        }

        return result;
    }

    private static Transportation load(Class<Transportation> transportationClass) {
        try {
            return transportationClass.newInstance();
        } catch (ReflectiveOperationException exc) {
            throw new RuntimeException("An error occurred during instantiation of a Transport.", exc);
        }
    }
}
