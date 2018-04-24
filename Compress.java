package com.company;

import java.io.*;
import java.net.*;
import java.util.zip.*;

public class Compress implements Runnable{
    public  static Socket c;
    String fname;

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

            send(ComFileName);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void send(String fname)  {
        try {
            FileInputStream in=new FileInputStream(fname);
            BufferedInputStream bf=new BufferedInputStream(in);
            PrintWriter pr = new PrintWriter(c.getOutputStream());
            while(true){
                int b=bf.read();
                if(b==-1)break;
                pr.print((byte)b);
            }
            pr.flush();

        } catch (IOException e) {
            System.out.println("Error in Sending");
        }

    }
}

