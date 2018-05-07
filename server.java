package com.company;

import java.io.*;
import java.net.*;

public class Server{
    public static void main(String[] args) {
        ServerSocket ss=null;
        Socket c=null;
        try {
            ss = new ServerSocket(8888);
            while(true) {
                System.out.println("lestining .. ");
                c = ss.accept();
                System.out.println("Connected with " + c.getInetAddress().getHostAddress());

                //Here the Server is Connected with the client and compress thread will work
                Compress C=new Compress(c);
                Thread th=new Thread(C);
                th.start();
            }
        } catch (IOException e) {
            System.err.println("Error in Server Side");
        }
    }
}
