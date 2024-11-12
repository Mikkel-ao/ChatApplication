package com.mikkel.chatapp.server;
import com.mikkel.chatapp.client.ClientHandler;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2000); // Port number 2000
        System.out.println("Server stated. Waiting for clients");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected" +  clientSocket);
            // Spawn new thread for client
            ClientHandler clientThread = new ClientHandler(clientSocket, clients);
            clients.add(clientThread);
            new Thread(clientThread).start();
        }
    }
}

