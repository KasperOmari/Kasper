package com.company;

import java.io.*;
import java.net.*;

public class Main {

    public ServerSocket ss;

    public Main(int port){
        try {
            ss=new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Error in Server port");
        }
    }
    public static void ResiveFile(Socket clientFile){
        try {
            DataInputStream din=new DataInputStream(clientFile.getInputStream());
            BufferedInputStream bin=new BufferedInputStream(din);

            FileOutputStream out=new FileOutputStream("Test.txt");
            BufferedOutputStream bout=new BufferedOutputStream(out);

            while(true){
                int b=bin.read();
                if(b==-1)break;
                bout.write(b);
            }
            bin.close();
            bout.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Main server=new Main(5641);
        System.out.println("Lestening ...");

        Socket client = server.ss.accept();

        System.out.println("Connected with "+client.getInetAddress().getHostName());

        server.ResiveFile(client);

    }
}
