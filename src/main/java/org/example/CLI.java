package org.example;

import java.util.Scanner;

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
        if (command.contains("get songs")) {
            client.getSongs();
        } else if (command.contains("select ")) {
            client.select(command.substring(command.indexOf("select ") + 7));
        } else if (command.contains("play")) {
            client.play();
        } else if (command.contains("pause")) {
            client.pause();
        } else if (command.contains("stop")) {
            client.stop();
        } else if (command.contains("upload ")) {
            String[] datas = command.substring(command.indexOf("upload ") + 7).split("°");
            if (datas.length < 2) {
                System.out.println("invalid data provided, should be like [styleName]°[songPath] ");
                return;
            }
            client.upload(datas[0],datas[1]);
        } else {
            System.out.println("unknown command : " + command);
        }
    }
}
