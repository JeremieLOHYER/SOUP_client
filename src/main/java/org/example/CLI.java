package org.example;

import com.zeroc.IceInternal.Time;

import java.util.*;

public class CLI {

    private Client client;
    public CLI(Client client) {
        this.client = client;
    }

    public void listen() {
        boolean done = false;
        Scanner scanner = new Scanner(System.in);

        while (!done) {
            // Affichez le prompt de commande
            System.out.print(">> ");

            // Lire la commande de l'utilisateur
            String command = scanner.nextLine();

            // Parsez et exécutez la commande
            if (command.equals("exit")) {
                done = true;
            } else {
                executeCommand(command);
            }
        }

        // Fermez le scanner une fois que vous avez terminé
        scanner.close();
    }

    // Méthode pour exécuter les commandes
    public void executeCommand(String command) {
        if (call(command,"get songs")) {
            client.getSongs();
        } else if (call(command,"select ")) {
            client.select(command.substring(command.indexOf("select ") + 7));
        } else if (call(command,"play")) {
            client.play();
        } else if (call(command,"pause")) {
            client.pause();
        } else if (call(command,"stop")) {
            client.stop();
        } else if (call(command,"upload ")) {
            //Map<String,String> data = decompose(command);

            String[] data = command.substring(command.indexOf("upload ") + 7).split("\\|");
            String styleName = "unknown";
            if (data.length > 1) {
                styleName = data[0];
            }

            //upload shrek°C:/Users/jeremie/Documents/shreksophone.mp3
            client.upload(styleName,data[data.length - 1]);
        } else if (call(command, "get song ")){
            client.getSongsByName(command.substring(command.indexOf("get song ") + 9));
        } else if (call(command, "get author ")){
            client.getSongsByAuthor(command.substring(command.indexOf("get author ") + 11));
        } else {
            System.out.println("unknown command : " + command);
        }
    }

    public Boolean call(String command, String balise) {
        return command.indexOf(balise) == 0;
    }

    public Map<String,String> decompose(String command) {
        int indexSearch = 0;
        List<String> elementsEntreGuillemets = new ArrayList<>();
        Map<String,String> elementActuel = new HashMap<>();
        while ((indexSearch = command.indexOf("\"",indexSearch) + 1) != 0) {
            int indexFin = command.indexOf("\"", indexSearch);
            elementsEntreGuillemets.add(command.substring(indexSearch - 1, indexFin + 1));
            indexSearch = indexFin + 1;
        }

        for (String element :
                elementsEntreGuillemets) {
            System.out.println("element : " + element);
            String remplace = new RandomName().get(8);
            elementActuel.put(remplace,element);
            command = command.replaceFirst(elementActuel.get(remplace),remplace);
        }

        for (String key :
                elementActuel.keySet()) {
            System.out.println("cle : " + key + " -> valeur '" + elementActuel.get(key) + "'");
        }

        System.out.println("codé : " + command);


        for (String key :
                elementActuel.keySet()) {
            command = command.replaceAll(key, elementActuel.get(key));
        }

        System.out.println("décodé : " + command);



        Map<String,String> ret = new HashMap<>();

        ret.put("base", command.substring(command.lastIndexOf(" ")));

        return ret;
    }

    //  upload test "un" " deur " di"oh"i"diu"ih"uihdiu"hu"i"

    private static class RandomName {
        private static char[] list = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

        private Random rng;
        public RandomName(){
            rng = new Random();
            rng.setSeed(Time.currentMonotonicTimeMillis());
        }

        public String get(int size) {
            String ret = "°";
            for (int element = 0; element < size; element++) {
                ret += list[Math.abs(rng.nextInt()) % list.length];
            }
            return ret + "°";
        }
    }
}
