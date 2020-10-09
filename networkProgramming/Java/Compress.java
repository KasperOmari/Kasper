package com.company;

import java.io.*;
import java.net.*;
import java.util.zip.*;

public class Compress implements Runnable{
    public  static Socket c;
    static String fname;

    public Compress(Socket a){
        c=a;
        fname=null;
    }
    @Override
    public void run() {
        try {
            BufferedReader bin = new BufferedReader(new InputStreamReader(c.getInputStream()));

            fname = bin.readLine();
            String ComFileName = fname.substring(0, fname.length() - 4) + ".zip";//to convert file extension to .zip

            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(ComFileName));
            ZipOutputStream zout = new ZipOutputStream(bout);

            ZipEntry z = new ZipEntry(fname);
            zout.putNextEntry(z);

            while (true) {
                int b = bin.read();
                if (b == -1) break;
                zout.write(b);
            }
            zout.close();

            send(ComFileName);//send compressed file to client
        }catch (IOException e){
            System.err.println("Error in compressing");
        }

    }
    public static void send(String name)  {
        try {
            FileInputStream in=new FileInputStream(name);
            BufferedInputStream bf=new BufferedInputStream(in);
            BufferedOutputStream bout=new BufferedOutputStream(c.getOutputStream());
            while(true){
                int b=bf.read();
                bout.write(b);
                System.out.println(b);
                if(b==-1)break;
            }
            bout.flush();
        } catch (IOException e) {
            System.err.println("Error in Sending");
        }

    }
}
