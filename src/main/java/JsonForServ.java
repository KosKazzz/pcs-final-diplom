
import com.google.gson.Gson;
import java.util.List;

public class JsonForServ {
    public static String pageEntryToJson(List<PageEntry> pageEntries) {
        Gson gson = new Gson();
        StringBuilder result = new StringBuilder();
        for (PageEntry pe : pageEntries) {
           result.append(gson.toJson(pe));
        }
        return result.toString();
    }
}
