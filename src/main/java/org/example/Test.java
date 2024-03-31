package org.example;

import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;

public class Test {
    public static void main(String[] args) {
        // Utilisation de NativeDiscovery pour trouver les bibliothèques natives de VLC
        new NativeDiscovery().discover();

        // Création de la factory pour les lecteurs multimédia
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();

        // Création d'un lecteur multimédia
        MediaPlayer mediaPlayer = mediaPlayerFactory.mediaPlayers().newMediaPlayer();

        // Configuration du volume
        mediaPlayer.audio().setVolume(0);

        // Chemin du fichier audio
        String filePath = "D:\\musique\\Billie Eilish - No Time To Die (Audio).mp3";

        // Paramètres pour la diffusion en RTP
        String[] options = {":sout=#rtp{dst=localhost,port=5000,mux=ts}", ":no-sout-rtp-sap", ":no-sout-standard-sap", ":sout-all", ":sout-keep"};

        // Lecture du fichier audio avec les options de diffusion
        mediaPlayer.media().play(filePath, options);

        // Attendre jusqu'à ce que la lecture soit terminée ou que l'utilisateur appuie sur une touche
        System.out.println("Lecture en cours. Appuyez sur une touche pour quitter.");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Arrêt du lecteur multimédia et libération des ressources
        mediaPlayer.controls().stop();
        mediaPlayer.release();
        mediaPlayerFactory.release();
    }
}