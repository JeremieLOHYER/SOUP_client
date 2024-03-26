
#pragma once

["java:package:org.example"]
module SOUP
{

    interface MusiqueReceiver
    {
        void addClient(string adress, string port);
        void getSongs();
        void select(string song);
        void play();
        void pause();
        void stop();
    };
    interface MusiqueSender
    {
        void responseGetSongs(string songs);
    }
}