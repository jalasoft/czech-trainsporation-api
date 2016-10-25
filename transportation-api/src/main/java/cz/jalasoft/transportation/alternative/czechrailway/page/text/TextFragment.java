package cz.jalasoft.transportation.alternative.czechrailway.page.text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A fragment that represents a piece of text.
 *
 * Created by Honza Lastovicka on 17.4.15.
 */
public final class TextFragment extends Fragment<TextFragment> {

    private final String text;

    TextFragment(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }

    public FragmentList<TextFragment> findFragmentsBetweenText(String startString, String endString) {
        return findFragmentsBetween(escaped(startString), escaped(endString));
    }

    private String escaped(String string) {
        String escaped = string.replaceAll("([\\\\\\.\\[\\{\\(\\)\\}\\]\\*\\+\\?\\^\\$\\|])", "\\\\$1");
        return escaped;
    }

    public FragmentList<TextFragment> findFragmentsBetween(String startString, String endString) {
        Pattern startPattern = Pattern.compile(startString);
        Pattern endPattern = Pattern.compile(endString);

        return findFragmentsBetween(startPattern, endPattern);
    }

    public FragmentList<TextFragment> findFragmentsBetween(Pattern startPattern, Pattern endPattern) {
        List<TextFragment> result = new ArrayList<>();

        Matcher startMatcher = startPattern.matcher(text);
        Matcher endMatcher = endPattern.matcher(text);

        while(startMatcher.find()) {
            int startIndex = startMatcher.end();
            int endIndex = -1;

            while(endMatcher.find()) {
                endIndex = endMatcher.start();
                if (endIndex > startIndex) {
                    break;
                }
            }

            if (endIndex < 0) {
                break;
            }

            String newFragment = text.substring(startIndex, endIndex);
            result.add(new TextFragment(newFragment));
        }

        return new FragmentList(result);
    }

    public FragmentList<RegexFragment> findFragmentsMatching(String stringPattern) {
        Pattern pattern = Pattern.compile(stringPattern);
        return findFragmentsMatching(pattern);
    }

    public FragmentList<RegexFragment> findFragmentsMatching(Pattern pattern) {
        List<RegexFragment> fragments = new ArrayList<>();

        Matcher matcher = pattern.matcher(text);
        while(matcher.find()) {
            fragments.add(new RegexFragment(matcher));
        }
        return new FragmentList<>(fragments);
    }

    @Override
    TextFragment getThis() {
        return this;
    }

    /*
    public <U extends Fragment> U convert(Converter<TextFragment, U> simpleConverter) throws ConverterException {
        return simpleConverter.convert(this);
    }*/
}
