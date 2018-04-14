package com.company;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Main{
    public static Socket c;
    String file;
    public Main(String host,int port,String f){
        try {
            c=new Socket(host,port);
            file=f;//complete file path
            SendTheFile();
        } catch (IOException e) {
            System.out.println("Error in client host or port");
        }
    }
    public void SendTheFile() {
        try {
            DataOutputStream dout=new DataOutputStream(c.getOutputStream());
            BufferedOutputStream bout=new BufferedOutputStream(dout);
            FileInputStream in=new FileInputStream(file);
            BufferedInputStream bin=new BufferedInputStream(in);

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
    public static void main(String []args){
        try {
            Scanner in=new Scanner(System.in);
            System.out.println("Enter your file's path");
            String path=in.nextLine();
            Main t=new Main(InetAddress.getLocalHost().getHostName(),5641,path);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}

