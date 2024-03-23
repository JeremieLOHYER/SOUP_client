//package org.example.client;
//
//import com.zeroc.Ice.Current;
//import org.example.SOUP.MusiqueReceiver;
//import uk.co.caprica.vlcj.binding.internal.libvlc_instance_t;
//import uk.co.caprica.vlcj.player.base.MediaPlayer;
//
//import javax.sound.sampled.*;
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.IOException;
//import java.util.Calendar;
//
//public class ClientMusiqueReader implements MusiqueReceiver {
//
//
//    private final long start;
//
//    private byte[] data;
//    ClientServer client;
//
//    public ClientMusiqueReader(ClientServer client) {
//        this.client = client;
//        this.start = Calendar.getInstance().toInstant().toEpochMilli();
//        this.data = new byte[10000];
//
//        //readFile(new File("C:\\Users\\jeremie\\Documents\\cours\\M1-S2\\middleware\\ice-demo-python\\src\\styles\\pop\\chipichipi.wav"));
//    }
//
//    public void readFile(File file) {
//        AudioInputStream audioInputStream = null;
//        try {
//            //obtention d'un flux audio à partir d'un fichier (objet File)
//            audioInputStream = AudioSystem.getAudioInputStream(file);
//
//        } catch (UnsupportedAudioFileException e) {
//            e.printStackTrace();
//            return;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return;
//        }
//
//        //Il est nécessaire de connaître le format audio du fichier
//        // d'entrée
//        // pour permettre à java de créer l'objet DataLine adéquat
//        //AudioFormat audioFormat = audioInputStream.getFormat();
//        //System.out.println(audioFormat.getEncoding() + "\n" + audioFormat.getSampleRate() + "\n" + audioFormat.getSampleSizeInBits() + "\n" + audioFormat.getChannels() + "\n" + audioFormat.getFrameSize() + "\n" + audioFormat.getFrameRate() + "\n" + audioFormat.isBigEndian());
//        AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
//
//        // En plus du format du flux audio d'entrée il est nécessaire de
//        // spécifier le type de DataLine qu'on veut
//        // ici le DataLine qu'on souhaite est un SourceDataLine qui permet
//        // la
//        // lecture (targetDataLine permet l'enregistrement).
//
//        DataLine.Info info = new DataLine.Info(SourceDataLine.class,
//                audioFormat);
//
//        // On récupère le DataLine adéquat et on l'ouvre
//        SourceDataLine line;
//        try {
//            line = (SourceDataLine) AudioSystem.getLine(info);
//
//        } catch (LineUnavailableException e) {
//            e.printStackTrace();
//            return;
//        }
//
//
//        try {
//            line.open(audioFormat);
//        } catch (LineUnavailableException e) {
//            e.printStackTrace();
//            return;
//        }
//
//        line.start();
//
//        try {
//            byte bytes[] = new byte[1024];
//            int bytesRead = 0;
//            while (((bytesRead = audioInputStream.read(bytes, 0, bytes.length)) != -1)
//                    && !false) {
//                line.write(bytes, 0, bytesRead);
//            }
//        } catch (IOException io) {
//            io.printStackTrace();
//            return;
//        }
//
//
//        line.close();
//    }
//
//    public void read() {
//        Clip clip = null;
//        try {
//            clip = AudioSystem.getClip();
//            ByteArrayInputStream fakeInputStream = new ByteArrayInputStream(data);
//            AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
//            AudioInputStream inputStream = new AudioInputStream(fakeInputStream, audioFormat, data.length);
//            clip.open(inputStream);
//            clip.start();
//        } catch (LineUnavailableException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static void main(String[] args) {
//
//
//        MediaPlayer player = new MediaPlayer(new libvlc_instance_t());
//        player.media().play("std{access=udp,mux=ts,dst=239.255.0.1:1234}","");
//    }
//}
