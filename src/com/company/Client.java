package com.company;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        try{
            Socket socket=new Socket("LocalHost",2020);
            ObjectOutputStream outputStream=new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream=new ObjectInputStream(socket.getInputStream());
            System.out.println("Insert your name:");
            String name=in.next();
            while(true)
            {
                System.out.println("Press 1 to write message:"+"\n"+"Press 0 to exit:");
                String choice=in.next();
                if(choice.equals("1"))
                {
                    System.out.println("Write message: ");
                    String message=in.next();
                    outputStream.writeObject(name+": "+message);

                }
                else if (choice.equals("0"))
                {  break;
                }
                String message="";
                if((message=(String)inputStream.readObject())!=null)
                {
                    System.out.println(message);
                }
            }
            socket.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}