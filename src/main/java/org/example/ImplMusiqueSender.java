package org.example;//
// Copyright (c) ZeroC, Inc. All rights reserved.
//

import com.zeroc.Ice.Current;
import org.example.SOUP.MusiqueSender;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class ImplMusiqueSender implements MusiqueSender
{
    public Consumer<String> getSongsCallBack = new Consumer<String>() {
        @Override
        public void accept(String s) {
            System.out.println("songs : \n" + s);
        }
    };

    public void setGetSongsCallBack(Consumer<String> callBack) {
        getSongsCallBack = callBack;
    }
    @Override
    public void responseGetSongs(String songs, Current current) {
        getSongsCallBack.accept(songs);
    }
}
