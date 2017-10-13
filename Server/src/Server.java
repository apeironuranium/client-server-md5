import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by leral on 02.05.2017.
 */
public class Server {
    public static void main(String[] args) throws IOException{
        BufferedReader in = null;
        PrintWriter out = null;
        ServerSocket myServerSocket = null;
        Socket mySocket = null;
        List<Integer> inputNumbers = new ArrayList<>();
        int inp = -1;

        try {
            myServerSocket = new ServerSocket(3000);
        } catch (IOException e) {
            System.out.println("Port already taken");
            System.exit(-1);
        }
        System.out.println("Waiting for user...");

        try {
            mySocket = myServerSocket.accept();
        } catch (IOException e) {
            System.out.println("Failed to connect user");
            System.exit(-1);
        }
        System.out.println("User connected!");

        try {
            in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Couldn't get input stream");
            System.exit(-1);
    }

        try {
            out = new PrintWriter(mySocket.getOutputStream(), true);
        } catch (IOException e){
            System.out.println("Couldn't get output stream");
            System.exit(-1);
        }

        String input, output;

        while((input = in.readLine()) != null){
            if (input.equalsIgnoreCase("exit")) break;
            try {
                inp = Integer.parseInt(input);
            } catch (NumberFormatException e){
                out.println("Non-number entered!");
                inp = -1;
            }
            if (inp > 0){
                inputNumbers.add(inp);
                out.println("Entered: " + input);
            }
            if (inp == 0 && inputNumbers.size() > 0){
                out.println("Max: " + Collections.max(inputNumbers) + " Min: " + Collections.min(inputNumbers));
                inputNumbers.clear();
            }
            System.out.println(input);
        }

        in.close();
        out.close();
        myServerSocket.close();
        mySocket.close();
    }
}
