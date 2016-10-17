package cz.jalasoft.transportation.alternative.czechrailway.factory.text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by honzales on 18.4.15.
 */
public final class RegexFragment extends Fragment<RegexFragment> {

    private final Pattern pattern;
    private final String text;
    private final List<TextFragment> groups;

    public RegexFragment(Matcher matcher) {
        pattern = matcher.pattern();
        text = matcher.group();
        groups = initGroups(matcher);
    }

    private List<TextFragment> initGroups(Matcher matcher) {
        List<TextFragment> groups = new ArrayList<>();

        for(int i=1; i <= matcher.groupCount(); i++) {
            String group = matcher.group(i);
            groups.add(new TextFragment(group));
        }
        return groups;
    }

    @Override
    public String text() {
        return text;
    }

    public TextFragment asTextFragment() {
        return new TextFragment(text);
    }

    public List<TextFragment> getGroupFragments() {
        return new ArrayList<TextFragment>(groups);
    }

    public TextFragment getGroupTextFragment(int index) {
        return groups.get(index);
    }

    public int getGroupCount() {
        return groups.size();
    }

    @Override
    RegexFragment getThis() {
        return this;
    }
}
