import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by leral on 03.05.2017.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket mySocket = null;
        mySocket = new Socket("localhost", 3000);
        BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
        PrintWriter out = new PrintWriter(mySocket.getOutputStream(), true);
        BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));

        String input, output;
        System.out.println("Connection established!");
        while ((input = userIn.readLine()) != null){
            out.println(input);
            output = in.readLine();
            System.out.println(output);
            if (input.equalsIgnoreCase("exit")) break;
        }

        out.close();
        in.close();
        userIn.close();
        mySocket.close();
    }
}
