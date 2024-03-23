//package org.example.client;
//
//import com.zeroc.Ice.Communicator;
//import com.zeroc.Ice.ObjectAdapter;
//import com.zeroc.Ice.Util;
//
//public class ClientServer {
//    public ClientServer(String port) {
//
//        ClientServer parent = this;
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try (Communicator communicator = Util.initialize()) {
//                    // Shut down the communicator on Ctrl+C
//                    Runtime.getRuntime().addShutdownHook(new Thread(() -> communicator.shutdown()));
//
//                    ObjectAdapter adapter =
//                            communicator.createObjectAdapterWithEndpoints("MusiqueReceiver", "default -h localhost -p " + port);
//                    adapter.add(new ClientMusiqueReader(parent), Util.stringToIdentity("MusiqueReceiver"));
//                    adapter.activate();
//
//                    communicator.waitForShutdown();
//                }
//            }
//        });
//
//        thread.start();
//    }
//    public static void main(String[] args)
//    {
//        ClientServer clientTest = new ClientServer("5000");
//    }
//}
