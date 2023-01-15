import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(8989)) {
            BooleanSearchEngine engine = new BooleanSearchEngine(new File("pdfs"));
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    System.out.println("Server started");
                    String fromClient = in.readLine();
                    System.out.println(fromClient);
                    String toClient = JsonForServ.pageEntryToJson(engine.search(fromClient));
                    out.write(toClient);
                    out.flush();
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
