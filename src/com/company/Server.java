package com.company;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        try{
            ServerSocket server = new ServerSocket(2020);

            System.out.println("Waiting a client");
            Socket socket = server.accept();
            System.out.println("Client connected");

            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

            String text = "";

            while((text=(String)inStream.readObject())!=null){

                System.out.println(text);
                if(text.toLowerCase().contains("hello"))
                {
                    outStream.writeObject("hello from server");
                }
                else
                {
                    outStream.writeObject(null);
                }


            }

            inStream.close();
            outStream.close();
            socket.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
}
