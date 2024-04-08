package org.example;//
// Copyright (c) ZeroC, Inc. All rights reserved.
//

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;
import org.example.SOUP.MusiqueReceiverPrx;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client{

    private MediaPlayer mediaPlayer;
    private Communicator communicator;
    MusiqueReceiverPrx musiqueReceiver = null;

    public Client() {
        communicator = com.zeroc.Ice.Util.initialize();
        mediaPlayer = new MediaPlayerFactory().mediaPlayers().newMediaPlayer();

        mediaPlayer.audio().setVolume(100);
    }

    public void initClient(String serverAdress) {
        initClient(serverAdress, null);
    }
    public void initClient(String serverAdress, ImplMusiqueSender musiqueSenderImplementation) {
        if(musiqueSenderImplementation == null) {
            musiqueSenderImplementation = new ImplMusiqueSender();
        }
        communicator.getProperties().setProperty("Ice.Default.Package", "com.zeroc.demos.Ice.minimal");

        final String[] ip = {"localhost"};
        new Thread(new Runnable() {
            @Override
            public void run() {
                ip[0] = getIP();
            }
        }).start();

        while (ip[0].contains("localhost")) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("local IP : " + ip[0]);

        try {
            ObjectPrx proxy = communicator.stringToProxy("musiqueReceiver:default -h " + serverAdress + " -p 5000");
            musiqueReceiver = MusiqueReceiverPrx.checkedCast(proxy);
            System.out.println("connected");
            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("MusiqueSender", "default -h " + ip[0] + " -p 11000");
            adapter.add(musiqueSenderImplementation, Util.stringToIdentity("musiqueSender"));
            adapter.activate();
            System.out.println("server UP");

            musiqueReceiver.addClient(ip[0], "11000");

            System.out.println("client added");

        } catch (Error e) {
            System.out.println("feur");
        }
    }

    public MusiqueReceiverPrx getMusiqueReceiver() {
        return musiqueReceiver;
    }

    public void disconnect() {
        communicator.destroy();
    }

    public static String getIP() {
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            return socket.getLocalAddress().getHostAddress();
        } catch (UnknownHostException | SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args)
    {
        Client monClient = new Client();

        ImplMusiqueSender test = new ImplMusiqueSender();

        test.setGetSongsCallBack((s) -> {System.out.println("my callBack said : \n" + s);});

        monClient.initClient("10.126.5.252", test);

        try {
            Thread.sleep(1000);
            monClient.getSongs();

            monClient.select("pop/blingbang.mp3");
            Thread.sleep(2000);
            monClient.play();
            Thread.sleep(15000);
            monClient.pause();
            Thread.sleep(2000);
            monClient.play();
            Thread.sleep(15000);
            monClient.stop();
        } catch (InterruptedException e) {
            System.out.println("je peux pas dormir");
            monClient.stop();
        }
        monClient.disconnect();
    }


    public void getSongs() {
        musiqueReceiver.getSongs();
    }

    public void select(String song) {
        musiqueReceiver.select(song);
    }

    public void play() {
        musiqueReceiver.play();
        mediaPlayer.media().play("rtp://localhost:8554");
    }

    public void pause() {
        musiqueReceiver.pause();
        mediaPlayer.controls().stop();
    }

    public void stop() {
        musiqueReceiver.stop();
        mediaPlayer.controls().stop();
    }
}
