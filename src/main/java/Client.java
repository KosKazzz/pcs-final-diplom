import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8989)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Введите - какое слово ищем :");
            String fromClient = reader.readLine();
            out.write(fromClient+ "\n");
            out.flush();
            String serverRequest = in.readLine();
            System.out.println(serverRequest);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Connection closed");
    }
}
