package org.example;//
// Copyright (c) ZeroC, Inc. All rights reserved.
//

import com.zeroc.Ice.*;

public class Server
{
    public void init() {
        try (Communicator communicator = Util.initialize()) {
            // Shut down the communicator on Ctrl+C
            Runtime.getRuntime().addShutdownHook(new Thread(() -> communicator.shutdown()));

            ObjectAdapter adapter =
                    communicator.createObjectAdapterWithEndpoints("MusiqueSender", "default -h localhost -p 11000");
            adapter.add(new ImplMusiqueSender(), Util.stringToIdentity("musiqueSender"));
            adapter.activate();

            communicator.waitForShutdown();
        }
    }
    public static void main(String[] args)
    {
        new Server().init();
    }
}
