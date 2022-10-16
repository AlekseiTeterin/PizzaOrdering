import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws InterruptedException {


        try (ServerSocket server= new ServerSocket(3345)){

            Socket client = server.accept();

            System.out.print("Connection accepted.");

            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            System.out.println("DataOutputStream  created");

            DataInputStream in = new DataInputStream(client.getInputStream());
            System.out.println("DataInputStream created");


            while(!client.isClosed()){

                String entry = in.readUTF();
// после получения данных считывает их
                System.out.println("\nПоступил заказ\n"+ entry);

                out.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
