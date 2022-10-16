import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {


    public static void main(String[] args) throws InterruptedException {

// запускаем подключение сокета по известным координатам и нициализируем приём сообщений с консоли клиента
        try(Socket socket = new Socket("localhost", 3345);
            BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            //DataInputStream ois = new DataInputStream(socket.getInputStream());
        )
        {
            System.out.println("Client connected to socket.");
            System.out.println();
            System.out.println("Client writing channel = oos & reading channel = ois initialized.");


            while(!socket.isOutputShutdown()){

                String pizzaName = "";
                System.out.println("\nChoose a pizza");
                System.out.println("Margarita: enter 1");
                System.out.println("Four cheeses: enter 2");
                System.out.println("Capriccioza: enter 3");
                System.out.println("Hawaian: enter 4");
                System.out.print("Please make a choice: ");
                int choice = new Scanner(System.in).nextInt();

                if(choice == 1){
                    pizzaName = "Margarita";
                }
                if(choice == 2){
                    pizzaName = "Four cheeses";
                }
                if(choice == 3){
                    pizzaName = "Capriccioza";
                }
                if(choice == 4){
                    pizzaName = "Hawaian";
                }

                System.out.println("Enter your Name, please");
                String clientName = br.readLine();

                System.out.println("Enter your email, please");
                String eMail = br.readLine();

                System.out.println("Enter your contact phone number, please");
                String phone = br.readLine();

                System.out.println("Enter address for delivery, please");
                String address = br.readLine();

                String zakaz = "pizza: " + pizzaName + "\nclient: " + clientName +
                        "\nemail: " + eMail + "\nphone number: " + phone +
                        "\naddress for delivery: " + address + "\n";

// пишем данные с консоли в канал сокета для сервера
                    oos.writeUTF(zakaz);
                    oos.flush();
                    System.out.println("Client send message\n" + zakaz + "to server.");
                    Thread.sleep(1000);

            }

        }  catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

