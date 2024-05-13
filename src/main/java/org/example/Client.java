package org.example;//
// Copyright (c) ZeroC, Inc. All rights reserved.
//

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;
import org.example.SOUP.MusiqueReceiverPrx;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.media.Media;
import uk.co.caprica.vlcj.media.MediaRef;
import uk.co.caprica.vlcj.player.base.MediaPlayer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {

    private Communicator communicator;
    private VLCAdapter vlcAdapter;

    private String serverAddress;
    MusiqueReceiverPrx musiqueReceiver = null;

    private int nbBlocs = 1;

    public Client(VLCAdapter vlcAdapter) {
        communicator = com.zeroc.Ice.Util.initialize();

        this.vlcAdapter = vlcAdapter;
    }

    public void initClient(String serverAddress) {
        initClient(serverAddress, null);
    }
    public void initClient(String serverAddress, ImplMusiqueSender musiqueSenderImplementation) {
        if(musiqueSenderImplementation == null) {
            musiqueSenderImplementation = new ImplMusiqueSender();
        }
        this.serverAddress = serverAddress;
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
            ObjectPrx proxy = communicator.stringToProxy("musiqueReceiver:default -h " + serverAddress + " -p 5001");
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

    public int getNbBlocs() {
        return nbBlocs;
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
        Client monClient = new Client(new VLCWindows());

        ImplMusiqueSender test = new ImplMusiqueSender();

        test.setGetSongsCallBack((s) -> System.out.println("my callBack said : \n" + s));
        test.setGetCompletionCallBack((val) -> {
            double percentage = (val / (double) monClient.getNbBlocs()) * 100;
            System.out.println("upload : " + String.format("%.1f", percentage) + "%");
        });

        monClient.initClient("192.168.1.46", test);

        new CLI(monClient).listen();

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
        int blockSize = 8192 * 96; // Taille du bloc, vous pouvez ajuster cette valeur selon vos besoins
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
        vlcAdapter.listen(serverAddress);
    }

    public void play() {
        musiqueReceiver.play();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        vlcAdapter.play();
    }

    public void pause() {
        musiqueReceiver.pause();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        vlcAdapter.pause();
    }

    public void stop() {
        musiqueReceiver.stop();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        vlcAdapter.stop();
    }

    static class VLCWindows implements VLCAdapter {

        private final MediaPlayer mediaPlayer;

        public VLCWindows() {
            mediaPlayer = new MediaPlayerFactory().mediaPlayers().newMediaPlayer();
            mediaPlayer.audio().setVolume(100);
        }

        public void listen(String address) {
            String option = "rtsp://" + getIP() + ":32470/";

            System.out.println("listening : " + option);

            mediaPlayer.media().play(option);
        }

        public void play() {
            mediaPlayer.controls().play();
        }

        public void pause() {
            mediaPlayer.controls().pause();
        }

        public void stop() {
            mediaPlayer.controls().stop();
        }
    }
}
