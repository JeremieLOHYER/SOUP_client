package org.example;//
// Copyright (c) ZeroC, Inc. All rights reserved.
//

import com.sun.jna.StringArray;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;
import org.example.SOUP.MusiqueReceiverPrx;

import java.net.*;
import java.util.function.Consumer;

import uk.co.caprica.vlcj.binding.internal.libvlc_media_player_t;
import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.media.*;
import uk.co.caprica.vlcj.binding.internal.libvlc_instance_t;
import uk.co.caprica.vlcj.binding.lib.LibVlc;
import uk.co.caprica.vlcj.player.base.ControlsApi;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.renderer.RendererItem;

import javax.swing.*;

public class Client
{

    private Communicator communicator;
    MusiqueReceiverPrx musiqueReceiver = null;

    public Client() {
        communicator = com.zeroc.Ice.Util.initialize();
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

        test.setGetSongsCallBack(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("my callBack said : \n" + s);
            }
        });




//        MediaPlayer mediaSender = new MediaPlayerFactory().mediaPlayers().newMediaPlayer();
//
//        mediaSender.audio().setVolume(0);
//
//        mediaSender.media().play("D:\\musique\\Billie Eilish - No Time To Die (Audio).mp3","--sout rtp/ts://localhost:8554/");




        MediaPlayer mediaPlayer = new MediaPlayerFactory().mediaPlayers().newMediaPlayer();

        mediaPlayer.audio().setVolume(100);

        mediaPlayer.media().play("rtp://localhost:8554");


        monClient.initClient("192.168.1.6", test);


        try {
            Thread.sleep(1000);
            monClient.musiqueReceiver.getSongs();

            monClient.musiqueReceiver.select("pop/chipichipi.mp3");
            Thread.sleep(2000);
            monClient.musiqueReceiver.play();
            Thread.sleep(15000);
            monClient.musiqueReceiver.pause();
            Thread.sleep(2000);
            monClient.musiqueReceiver.play();
            Thread.sleep(15000);
            monClient.musiqueReceiver.stop();
        } catch (InterruptedException e) {
            System.out.println("je peux pas dormir");
            monClient.musiqueReceiver.stop();
        }
        monClient.disconnect();
    }
}
