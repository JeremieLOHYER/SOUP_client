package org.example;//
// Copyright (c) ZeroC, Inc. All rights reserved.
//

import com.zeroc.Ice.Current;
import org.example.SOUP.MusiqueSender;

public class ImplMusiqueSender implements MusiqueSender
{
    @Override
    public void responseGetSongs(String songs, Current current) {
        System.out.println("songs : \n" + songs);
    }
}
