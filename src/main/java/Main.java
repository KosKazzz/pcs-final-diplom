import java.io.File;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        //BooleanSearchEngine engine = new BooleanSearchEngine(new File("pdfs"));
        //System.out.println(engine.search("бизнес"));
        //System.out.println(JsonForServ.pageEntryToJson(engine.search("бизнес")));

        Server server = new Server();
        server.start();
    }
}