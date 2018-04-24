package com.company;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client implements Runnable{
    @Override
    public void run() {
        try {
            Socket c=new Socket(InetAddress.getLocalHost().getHostAddress()/*Change it to Server's ip address*/,8888);

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
            bout.close();
            bout.flush();
            System.out.println("Content of the file Sent");
        } catch (IOException e) {
            System.out.println("Error in Client Side");
        }
    }
}
