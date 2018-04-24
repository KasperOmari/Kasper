package com.company;

public class Main {

    public static void main(String[] args) {
        Server s=new Server();
        Thread sth=new Thread(s);
        sth.start();

        Client c=new Client();
        Thread cth=new Thread(c);
        cth.start();
    }
}
