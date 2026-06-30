package Client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 6000);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String username = scanner.nextLine();

        Client client = new Client(socket, username);

        client.listenForMessage();
        client.sendMessage();




    }


}
