package Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private static ArrayList<String> chatHistory = new ArrayList<>();


    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;

    private String username;

    public ClientHandler(Socket socket) {
        try  {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = bufferedReader.readLine();

            clientHandlers.add(this);

            for (String oldMessage : chatHistory) {
                bufferedWriter.write(oldMessage);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();

            broadcastMessage("SERVER: " + username + " has entered the chat.");
        } catch (IOException e){
            closeEverything(socket, bufferedWriter, bufferedReader);

        }
    }

    private void closeEverything(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader) {
        clientHandlers.remove(this);
        broadcastMessage("SERVER: " + username + " has left the chat.");

        try{
            if (bufferedWriter != null) bufferedWriter.close();
            if(bufferedReader != null) bufferedReader.close();
            if (socket != null) socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void broadcastMessage(String message) {
        chatHistory.add(message);

        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler == this) continue;


            try{
                if(!clientHandler.socket.isClosed()){
                    clientHandler.bufferedWriter.write(message);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            }catch (IOException e){
                closeEverything(socket, bufferedWriter, bufferedReader);
            }
        }
    }

    @Override
    public void run() {
        String messageFromClient;
        while (!socket.isClosed()) {
            try{
                messageFromClient = bufferedReader.readLine();
                if (messageFromClient == null) break;
                broadcastMessage(messageFromClient);

            }catch (IOException e){
                break;
            }
        }
        closeEverything(socket, bufferedWriter, bufferedReader);
    }
}
