
#pragma once

["java:package:org.example"]
module SOUP
{

    interface MusiqueReceiver
    {
        string getSongs();
        void select(string song);
        void play();
        void pause();
        void stop();
    }
}