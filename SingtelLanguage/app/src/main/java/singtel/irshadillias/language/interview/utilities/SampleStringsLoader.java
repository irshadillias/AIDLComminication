package singtel.irshadillias.language.interview.utilities;

import com.ice.restring.Restring;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleStringsLoader implements Restring.StringsLoader {

    @Override
    public List<String> getLanguages() {
        return Arrays.asList("en", "de", "fa");
    }

    @Override
    public Map<String, String> getStrings(String language) {
        Map<String, String> map = new HashMap<>();
        switch (language) {
            case "en": {
                map.put("title", "This is title (from restring).");
                map.put("subtitle", "This is subtitle (from restring).");
                break;
            }
            case "de": {
                map.put("title", "Das ist Titel (from restring).");
                map.put("subtitle", "Das ist Untertitel (from restring).");
                break;
            }
            case "fa": {
                map.put("title", "In sarkhat ast (from restring).");
                map.put("subtitle", "In matn ast (from restring).");
                break;
            }
        }
        return map;
    }
}