package com.example.wrathspectre.computercontrol;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionManagement extends Thread {
    static String destinationAddrss;
    static int destinationPort;

    String inMessage;
    String outMessage = "";
    boolean goOut = false;

    private static ConnectionManagement instance = null;

    public static ConnectionManagement getInstance(final String address, final int port) {
       if(instance == null) {
           destinationAddrss = address;
           destinationPort = port;
           instance = new ConnectionManagement();
       }

       return instance;
   }

    @Override
    public void run() {
        Socket socket = null;
        DataOutputStream dataOutputStream = null;
        BufferedReader in = null;
       // DataInputStream dataInputStream = null;

        try {
            socket = new Socket(destinationAddrss, destinationPort);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
           // dataInputStream = new DataInputStream(socket.getInputStream());
            //security measures

            while(!goOut) {
                if(!in.readLine().isEmpty()) {
                    Log.d("D", in.readLine().toString());
                    in.reset();
                    //dataInputStream.readUTF().toString()
                }

                if(!outMessage.equals("")) {
                    Log.d("MSG:  ", outMessage);
                    dataOutputStream.writeUTF(outMessage);
                    dataOutputStream.flush();
                    outMessage = "";
                }
            }
        } catch (UnknownHostException e) {

        } catch (IOException e) {

        } finally {
            if(socket != null) {
                try {
                    socket.close();

                } catch (IOException e) {

                }
            }

            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }

            if (in != null) {
                try {
                     in.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }

     //   return null;
    }

    public String setMessage(final String message) {
        return message;
    }

    public void getMessage(final String message) {
        this.outMessage = message;
    }

    private void disconnect() {
        this.goOut = true;
    }
}
