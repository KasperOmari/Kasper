package com.company;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static Socket c;
    public Client(){
        c=null;
    }
    public static void main(String[] args) {
        try {
            c=new Socket(InetAddress.getLocalHost().getHostAddress()/*Change it to Server's ip address*/,8888);

            Scanner sc=new Scanner(System.in);
            System.out.print("Enter File Name : ");
            String fname=sc.nextLine();


            FileInputStream in=new FileInputStream(fname);
            BufferedInputStream bin=new BufferedInputStream(in);


            PrintWriter pr=new PrintWriter(c.getOutputStream());
            pr.println(fname);
            pr.flush();
            System.out.println("File Name Sent");

            OutputStream out=c.getOutputStream();
            BufferedOutputStream bout=new BufferedOutputStream(out);
            System.out.println("Sending Content of the file ...");
            while(true){
                int b=bin.read();
                if(b==-1)break;
                bout.write(b);
            }
            bout.flush();

            System.out.println("Content of the file Sent");

            receive(fname);// to read the sent file from the server on the socket
        } catch (IOException e) {
            System.err.println("Error in Client Side");
        }
    }

    public static void receive(String name){
        try {
            BufferedInputStream in = new BufferedInputStream(c.getInputStream());
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(name.substring(0,name.length()-4)+"2.zip"));
            while(true){
                int b=in.read();
                if(b==-1)break;
                out.write(b);
            }
            out.flush();
            System.out.println("Compressed file received");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
