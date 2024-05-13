package org.example;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public interface VLCAdapter {

    void listen(String address);

    void play();

    void pause();

    void stop();
}
