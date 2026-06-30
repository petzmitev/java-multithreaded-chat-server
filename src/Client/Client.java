package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private String username;


    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Client(Socket socket, String username) {
        try{
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
        }catch(Exception e){
            closeEverything();
        }
    }

    public void closeEverything(){
        try{
            if(bufferedReader != null) bufferedReader.close();
            if (bufferedWriter != null) bufferedWriter.close();
            if (socket != null) socket.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void sendMessage(){
        try{
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);

            while (!socket.isClosed()){
                String messageToSend = scanner.nextLine();
                bufferedWriter.write(this.username + ": " + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }catch(Exception e){
            closeEverything();
        }
    }

    public void listenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run(){
                String messageFromGroupChat;

                while (!socket.isClosed()){
                    try{
                        messageFromGroupChat = bufferedReader.readLine();
                        System.out.println(messageFromGroupChat);

                    }catch(Exception e){
                        closeEverything();

                    }
                }
            }
        }).start();
    }

}
