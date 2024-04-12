package org.example;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public interface VLCAdapter {

    void play(String address);

    void pause();

    void stop();
}
