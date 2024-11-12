package com.mikkel.chatapp.client;
import java.io.*;
import java.net.*;
import java.util.function.Consumer;

public class ChatClient {
    private Socket socket = null;
    private BufferedReader in;
    private PrintWriter out;
    private Consumer<String> onMessageReceived;

    public ChatClient(String serverAddress, int serverPort, Consumer<String>onMessageReceived) throws IOException {
        this.socket = new Socket(serverAddress, serverPort);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.onMessageReceived = onMessageReceived;
    }

    public void sendMessage(String msg) {
        out.println(msg);
    }

    public void startClient() {
        new Thread(() -> { // lambda expression
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    onMessageReceived.accept(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start(); // Starts the thread immediately after.
    }




    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000); // Connect to server on port 5000
        System.out.println("Connect to server.");

    }
}
