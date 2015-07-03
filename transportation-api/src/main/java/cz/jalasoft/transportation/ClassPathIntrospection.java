package cz.jalasoft.transportation;

import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by honzales on 25.6.15.
 */
final class ClassPathIntrospection {

    private static final String DEFAULT_PACKAGE_NAME = ClassPathIntrospection.class.getPackage().getName();
    private static final String SYSTEM_PROPERTY_PACKAGE_KEY = "transport.package";

    private static final String CUSTOM_TRANSPORTATION_CLASS_SUFFIX = "Transportation";

    static Collection<Class<Transportation>> introspect() throws IOException {
        Iterable<String> packageNames = packageNames();
        Iterable<ClassPath.ClassInfo> classInfos = searchPackages(packageNames);

        Iterable<Class<?>> classes = loadTransformationClasses(classInfos);
        return filterTranformationClasses(classes);
    }

    private static Iterable<String> packageNames() {
        Set<String> result = new HashSet<>();

        result.add(DEFAULT_PACKAGE_NAME);

        String alternativePackageName = System.getProperty(SYSTEM_PROPERTY_PACKAGE_KEY);
        if (alternativePackageName != null) {
            result.add(alternativePackageName);
        }

        return result;
    }

    private static Iterable<ClassPath.ClassInfo> searchPackages(Iterable<String> packageNames) throws IOException {
        Set<ClassPath.ClassInfo> result = new HashSet<>();

        for(String packageName : packageNames) {
            Set<ClassPath.ClassInfo> classInfos = searchPackage(packageName);
            result.addAll(classInfos);
        }
        return result;
    }

    private static Set<ClassPath.ClassInfo> searchPackage(String packageName) throws IOException {
        return ClassPath.from(ClassPathIntrospection.class.getClassLoader()).getTopLevelClassesRecursive("cz.jalasoft.transportation");
    }

    private static Set<Class<?>> loadTransformationClasses(Iterable<ClassPath.ClassInfo> classInfos) {
        Set<Class<?>> result = new HashSet<>();

        for(ClassPath.ClassInfo classInfo : classInfos) {
            if (isTransportationClass(classInfo)) {
                Class<?> clazz = classInfo.load();
                result.add(clazz);
            }
        }

        return result;
    }

    private static boolean isTransportationClass(ClassPath.ClassInfo classInfo) {
        return classInfo.getName().endsWith(CUSTOM_TRANSPORTATION_CLASS_SUFFIX);
    }

    private static Collection<Class<Transportation>> filterTranformationClasses(Iterable<Class<?>> possibleTransformations) {
        Set<Class<Transportation>> result = new HashSet<>();

        for(Class<?> transportation : possibleTransformations) {
            boolean isChildOfTransportation = Transportation.class.isAssignableFrom(transportation);
            boolean isClass = !transportation.isInterface();

            if (isChildOfTransportation && isClass) {
                result.add((Class<Transportation>)transportation);
            }
        }

        return result;
    }
}
