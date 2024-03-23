package org.example;//
// Copyright (c) ZeroC, Inc. All rights reserved.
//

import com.zeroc.Ice.ObjectPrx;
import org.example.SOUP.MusiqueReceiverPrx;

public class Client
{

    MusiqueReceiverPrx musiqueReceiver = null;

    public void init() {
        com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize();
        communicator.getProperties().setProperty("Ice.Default.Package", "com.zeroc.demos.Ice.minimal");
        ObjectPrx proxy = communicator.stringToProxy("musiqueReceiver:default -h localhost -p 5000 -t 5000");
        musiqueReceiver = MusiqueReceiverPrx.checkedCast(proxy);
    }
    public static void main(String[] args)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Client monClient = new Client();
                monClient.init();
                //System.out.println(monClient.musiqueReceiver.getSongs());
                monClient.musiqueReceiver.select("pop/chipichipi.mp3");
                monClient.musiqueReceiver.play();
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    System.out.println("je peux pas dormir");
                }
                monClient.musiqueReceiver.stop();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Server().init();
            }
        }).start();
    }
}
