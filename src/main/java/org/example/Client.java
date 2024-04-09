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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client{

    private MediaPlayer mediaPlayer;
    private Communicator communicator;
    MusiqueReceiverPrx musiqueReceiver = null;

    int nbBlocs = 1;

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

        test.setGetSongsCallBack((s) -> System.out.println("my callBack said : \n" + s));
        test.setGetCompletionCallBack((val) -> {
            double percentage = (val / (double) monClient.nbBlocs) * 100;
            System.out.println("upload : " + String.format("%.1f", percentage) + "%");
        });

        monClient.initClient("10.126.5.252", test);

        new CLI(monClient).listen();

//        Thread.sleep(1000);
//        monClient.getSongs();
//
//        monClient.upload("pop", "D:/musique/Billie Eilish - No Time To Die (Audio).mp3");
//
//        monClient.select("pop/Billie Eilish - No Time To Die (Audio).mp3");
//        Thread.sleep(2000);
//        monClient.play();
//        Thread.sleep(15000);
//        monClient.pause();
//        Thread.sleep(2000);
//        monClient.play();
//        Thread.sleep(20000);
//        monClient.stop();

        monClient.stop();
        monClient.disconnect();
    }


    public void getSongs() {
        musiqueReceiver.getSongs();
    }

    public void getSongsByName(String songName) {
        musiqueReceiver.getSongsByName(songName);
    }
    public void getSongsByAuthor(String author) {
        musiqueReceiver.getSongsByAuthor(author);
    }

    public void upload(String styleName, String filePath) {
        String songName = filePath.substring(filePath.lastIndexOf("/") + 1);

        // Créez un objet File pour le fichier spécifié
        File file = new File(filePath);
        // Obtenez la taille du fichier pour calculer le nombre de blocs nécessaires
        long fileSize = file.length();
        int blockSize = 8192 * 32; // Taille du bloc, vous pouvez ajuster cette valeur selon vos besoins
        nbBlocs = (int) Math.ceil((double) fileSize / blockSize);

        musiqueReceiver.prepareUpload(styleName, songName, nbBlocs);
        // Préparez l'envoi de la chanson au serveur

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buffer = new byte[blockSize];
            int bytesRead;
            int blocId = 0;
            // Lisez le fichier par blocs et envoyez-les au serveur
            while ((bytesRead = bis.read(buffer)) != -1) {
                // Envoyez le bloc de données au serveur
                musiqueReceiver.upload(blocId, buffer);
                blocId++;
            }
        } catch (IOException e) {
            // Gérez les erreurs d'E/S ici
            e.printStackTrace();
        }

    }

    public void select(String song) {
        musiqueReceiver.select(song);
    }

    public void play() {
        musiqueReceiver.play();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
