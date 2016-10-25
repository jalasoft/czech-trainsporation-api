package cz.jalasoft.transportation.alternative.czechrailway.page.loader;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-10-06.
 */
public interface PageLoader extends Closeable {

    String postForm(String url, List<FormParameter> parameters) throws IOException;

    String get(String url) throws IOException;

    //------------------------------------------------------------
    //POST FORM PARAMETER CLASS
    //------------------------------------------------------------

    final class FormParameter {

        private String name;
        private String value;

        public FormParameter(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String name() {
            return name;
        }

        public String value() {
            return value;
        }
    }
}
