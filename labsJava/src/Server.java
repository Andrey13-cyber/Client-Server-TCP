import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {
    public static void main(String[] args){
        ServerSocket serverSocket = null;
        Socket clientAccepted = null;
        ObjectInputStream serverInput = null;
        ObjectOutputStream serverOutput = null;
        try {
            System.out.println("server starting...");
            serverSocket = new ServerSocket(2525);
            clientAccepted = serverSocket.accept();
            System.out.println("connection is established...");

            serverInput = new ObjectInputStream(clientAccepted.getInputStream());
            serverOutput = new ObjectOutputStream(clientAccepted.getOutputStream());
            int clientMessageReceived = (Integer) serverInput.readObject();

            while (clientMessageReceived != 0){
                System.out.println("message received:" + clientMessageReceived + " ");
                int[] array;
                array = new int[clientMessageReceived];
                for (int i = 0; i < array.length ; i++) {
                    array[i] = (int)(Math.random() * clientMessageReceived) + 1;
                    System.out.println(array[i] + " ");
                }

                serverOutput.writeObject(Arrays.toString(array));

                clientMessageReceived = (Integer) serverInput.readObject();
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert serverInput != null;
                serverInput.close();
                assert serverOutput != null;
                serverOutput.close();
                clientAccepted.close();
                serverSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
