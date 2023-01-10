import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.stream.Stream;

public class Client {
    public static void main(String[] args){
        try {
            System.out.println("server connecting...");
            Socket clientSocket = new Socket("127.0.0.1", 2525);
            System.out.println("connection established...");
            Scanner canal = new Scanner(System.in);
            ObjectOutputStream clientOutput = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream clientInput = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("Enter number N to send to server \n\t('0' - program terminate)");
            int clientMessage = canal.nextInt();
            System.out.println("Entered: " + clientMessage);
            while (clientMessage != 0) {
                clientOutput.writeObject(clientMessage);
                System.out.println("~server~:" + clientInput.readObject().toString());
                Stream.generate(() -> " _").limit(20).forEach(System.out::print);
                System.out.println(" ");
                clientMessage = canal.nextInt();
                System.out.println("Entered:" + clientMessage);
            }
            clientOutput.close();
            clientInput.close();
            clientSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
