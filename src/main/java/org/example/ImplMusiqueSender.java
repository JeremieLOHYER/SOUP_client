package org.example;//
// Copyright (c) ZeroC, Inc. All rights reserved.
//

import com.zeroc.Ice.Current;
import org.example.SOUP.MusiqueSender;

import java.util.function.Consumer;

public class ImplMusiqueSender implements MusiqueSender
{
    public Consumer<String> getSongsCallBack = (s) -> System.out.println("songs : \n" + s);

    public Consumer<Integer> getCompletionCallBack = (epoch) -> System.out.println("upload completion : " + epoch);

    public void setGetSongsCallBack(Consumer<String> callBack) {
        getSongsCallBack = callBack;
    }
    public void setGetCompletionCallBack(Consumer<Integer> callBack) {
        getCompletionCallBack = callBack;
    }
    @Override
    public void responseGetSongs(String songs, Current current) {
        getSongsCallBack.accept(songs);
    }

    @Override
    public void responseGetCompletion(int complete, Current current) {
        getCompletionCallBack.accept(complete);
    }
}
