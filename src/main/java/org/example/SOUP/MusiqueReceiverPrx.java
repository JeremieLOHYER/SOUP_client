//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.10
//
// <auto-generated>
//
// Generated from file `MusiqueReceiver.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package org.example.SOUP;

public interface MusiqueReceiverPrx extends com.zeroc.Ice.ObjectPrx
{
    default void addClient(String adress, String port)
    {
        addClient(adress, port, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void addClient(String adress, String port, java.util.Map<String, String> context)
    {
        _iceI_addClientAsync(adress, port, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> addClientAsync(String adress, String port)
    {
        return _iceI_addClientAsync(adress, port, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> addClientAsync(String adress, String port, java.util.Map<String, String> context)
    {
        return _iceI_addClientAsync(adress, port, context, false);
    }

    /**
     * @hidden
     * @param iceP_adress -
     * @param iceP_port -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_addClientAsync(String iceP_adress, String iceP_port, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "addClient", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeString(iceP_adress);
                     ostr.writeString(iceP_port);
                 }, null);
        return f;
    }

    default void getSongs()
    {
        getSongs(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void getSongs(java.util.Map<String, String> context)
    {
        _iceI_getSongsAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> getSongsAsync()
    {
        return _iceI_getSongsAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> getSongsAsync(java.util.Map<String, String> context)
    {
        return _iceI_getSongsAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_getSongsAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "getSongs", null, sync, null);
        f.invoke(false, context, null, null, null);
        return f;
    }

    default void getSongsByName(String songName)
    {
        getSongsByName(songName, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void getSongsByName(String songName, java.util.Map<String, String> context)
    {
        _iceI_getSongsByNameAsync(songName, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> getSongsByNameAsync(String songName)
    {
        return _iceI_getSongsByNameAsync(songName, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> getSongsByNameAsync(String songName, java.util.Map<String, String> context)
    {
        return _iceI_getSongsByNameAsync(songName, context, false);
    }

    /**
     * @hidden
     * @param iceP_songName -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_getSongsByNameAsync(String iceP_songName, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "getSongsByName", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeString(iceP_songName);
                 }, null);
        return f;
    }

    default void getSongsByAuthor(String author)
    {
        getSongsByAuthor(author, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void getSongsByAuthor(String author, java.util.Map<String, String> context)
    {
        _iceI_getSongsByAuthorAsync(author, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> getSongsByAuthorAsync(String author)
    {
        return _iceI_getSongsByAuthorAsync(author, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> getSongsByAuthorAsync(String author, java.util.Map<String, String> context)
    {
        return _iceI_getSongsByAuthorAsync(author, context, false);
    }

    /**
     * @hidden
     * @param iceP_author -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_getSongsByAuthorAsync(String iceP_author, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "getSongsByAuthor", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeString(iceP_author);
                 }, null);
        return f;
    }

    default void prepareUpload(String style, String songName, int nbBlocs)
    {
        prepareUpload(style, songName, nbBlocs, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void prepareUpload(String style, String songName, int nbBlocs, java.util.Map<String, String> context)
    {
        _iceI_prepareUploadAsync(style, songName, nbBlocs, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> prepareUploadAsync(String style, String songName, int nbBlocs)
    {
        return _iceI_prepareUploadAsync(style, songName, nbBlocs, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> prepareUploadAsync(String style, String songName, int nbBlocs, java.util.Map<String, String> context)
    {
        return _iceI_prepareUploadAsync(style, songName, nbBlocs, context, false);
    }

    /**
     * @hidden
     * @param iceP_style -
     * @param iceP_songName -
     * @param iceP_nbBlocs -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_prepareUploadAsync(String iceP_style, String iceP_songName, int iceP_nbBlocs, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "prepareUpload", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeString(iceP_style);
                     ostr.writeString(iceP_songName);
                     ostr.writeInt(iceP_nbBlocs);
                 }, null);
        return f;
    }

    default void upload(int blocId, byte[] data)
    {
        upload(blocId, data, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void upload(int blocId, byte[] data, java.util.Map<String, String> context)
    {
        _iceI_uploadAsync(blocId, data, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> uploadAsync(int blocId, byte[] data)
    {
        return _iceI_uploadAsync(blocId, data, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> uploadAsync(int blocId, byte[] data, java.util.Map<String, String> context)
    {
        return _iceI_uploadAsync(blocId, data, context, false);
    }

    /**
     * @hidden
     * @param iceP_blocId -
     * @param iceP_data -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_uploadAsync(int iceP_blocId, byte[] iceP_data, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "upload", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeInt(iceP_blocId);
                     ostr.writeByteSeq(iceP_data);
                 }, null);
        return f;
    }

    default String getStyle()
    {
        return getStyle(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default String getStyle(java.util.Map<String, String> context)
    {
        return _iceI_getStyleAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.lang.String> getStyleAsync()
    {
        return _iceI_getStyleAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.String> getStyleAsync(java.util.Map<String, String> context)
    {
        return _iceI_getStyleAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.lang.String> _iceI_getStyleAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.String> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "getStyle", null, sync, null);
        f.invoke(true, context, null, null, istr -> {
                     String ret;
                     ret = istr.readString();
                     return ret;
                 });
        return f;
    }

    default void select(String song)
    {
        select(song, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void select(String song, java.util.Map<String, String> context)
    {
        _iceI_selectAsync(song, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> selectAsync(String song)
    {
        return _iceI_selectAsync(song, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> selectAsync(String song, java.util.Map<String, String> context)
    {
        return _iceI_selectAsync(song, context, false);
    }

    /**
     * @hidden
     * @param iceP_song -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_selectAsync(String iceP_song, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "select", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeString(iceP_song);
                 }, null);
        return f;
    }

    default void play()
    {
        play(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void play(java.util.Map<String, String> context)
    {
        _iceI_playAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> playAsync()
    {
        return _iceI_playAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> playAsync(java.util.Map<String, String> context)
    {
        return _iceI_playAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_playAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "play", null, sync, null);
        f.invoke(false, context, null, null, null);
        return f;
    }

    default void pause()
    {
        pause(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void pause(java.util.Map<String, String> context)
    {
        _iceI_pauseAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> pauseAsync()
    {
        return _iceI_pauseAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> pauseAsync(java.util.Map<String, String> context)
    {
        return _iceI_pauseAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_pauseAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "pause", null, sync, null);
        f.invoke(false, context, null, null, null);
        return f;
    }

    default void stop()
    {
        stop(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void stop(java.util.Map<String, String> context)
    {
        _iceI_stopAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> stopAsync()
    {
        return _iceI_stopAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> stopAsync(java.util.Map<String, String> context)
    {
        return _iceI_stopAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_stopAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "stop", null, sync, null);
        f.invoke(false, context, null, null, null);
        return f;
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static MusiqueReceiverPrx checkedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, ice_staticId(), MusiqueReceiverPrx.class, _MusiqueReceiverPrxI.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static MusiqueReceiverPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, context, ice_staticId(), MusiqueReceiverPrx.class, _MusiqueReceiverPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static MusiqueReceiverPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, ice_staticId(), MusiqueReceiverPrx.class, _MusiqueReceiverPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static MusiqueReceiverPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, context, ice_staticId(), MusiqueReceiverPrx.class, _MusiqueReceiverPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @return A proxy for this type.
     **/
    static MusiqueReceiverPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, MusiqueReceiverPrx.class, _MusiqueReceiverPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    static MusiqueReceiverPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, facet, MusiqueReceiverPrx.class, _MusiqueReceiverPrxI.class);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the per-proxy context.
     * @param newContext The context for the new proxy.
     * @return A proxy with the specified per-proxy context.
     **/
    @Override
    default MusiqueReceiverPrx ice_context(java.util.Map<String, String> newContext)
    {
        return (MusiqueReceiverPrx)_ice_context(newContext);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the adapter ID.
     * @param newAdapterId The adapter ID for the new proxy.
     * @return A proxy with the specified adapter ID.
     **/
    @Override
    default MusiqueReceiverPrx ice_adapterId(String newAdapterId)
    {
        return (MusiqueReceiverPrx)_ice_adapterId(newAdapterId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoints.
     * @param newEndpoints The endpoints for the new proxy.
     * @return A proxy with the specified endpoints.
     **/
    @Override
    default MusiqueReceiverPrx ice_endpoints(com.zeroc.Ice.Endpoint[] newEndpoints)
    {
        return (MusiqueReceiverPrx)_ice_endpoints(newEndpoints);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator cache timeout.
     * @param newTimeout The new locator cache timeout (in seconds).
     * @return A proxy with the specified locator cache timeout.
     **/
    @Override
    default MusiqueReceiverPrx ice_locatorCacheTimeout(int newTimeout)
    {
        return (MusiqueReceiverPrx)_ice_locatorCacheTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the invocation timeout.
     * @param newTimeout The new invocation timeout (in seconds).
     * @return A proxy with the specified invocation timeout.
     **/
    @Override
    default MusiqueReceiverPrx ice_invocationTimeout(int newTimeout)
    {
        return (MusiqueReceiverPrx)_ice_invocationTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for connection caching.
     * @param newCache <code>true</code> if the new proxy should cache connections; <code>false</code> otherwise.
     * @return A proxy with the specified caching policy.
     **/
    @Override
    default MusiqueReceiverPrx ice_connectionCached(boolean newCache)
    {
        return (MusiqueReceiverPrx)_ice_connectionCached(newCache);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoint selection policy.
     * @param newType The new endpoint selection policy.
     * @return A proxy with the specified endpoint selection policy.
     **/
    @Override
    default MusiqueReceiverPrx ice_endpointSelection(com.zeroc.Ice.EndpointSelectionType newType)
    {
        return (MusiqueReceiverPrx)_ice_endpointSelection(newType);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for how it selects endpoints.
     * @param b If <code>b</code> is <code>true</code>, only endpoints that use a secure transport are
     * used by the new proxy. If <code>b</code> is false, the returned proxy uses both secure and
     * insecure endpoints.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default MusiqueReceiverPrx ice_secure(boolean b)
    {
        return (MusiqueReceiverPrx)_ice_secure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the encoding used to marshal parameters.
     * @param e The encoding version to use to marshal request parameters.
     * @return A proxy with the specified encoding version.
     **/
    @Override
    default MusiqueReceiverPrx ice_encodingVersion(com.zeroc.Ice.EncodingVersion e)
    {
        return (MusiqueReceiverPrx)_ice_encodingVersion(e);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its endpoint selection policy.
     * @param b If <code>b</code> is <code>true</code>, the new proxy will use secure endpoints for invocations
     * and only use insecure endpoints if an invocation cannot be made via secure endpoints. If <code>b</code> is
     * <code>false</code>, the proxy prefers insecure endpoints to secure ones.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default MusiqueReceiverPrx ice_preferSecure(boolean b)
    {
        return (MusiqueReceiverPrx)_ice_preferSecure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the router.
     * @param router The router for the new proxy.
     * @return A proxy with the specified router.
     **/
    @Override
    default MusiqueReceiverPrx ice_router(com.zeroc.Ice.RouterPrx router)
    {
        return (MusiqueReceiverPrx)_ice_router(router);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator.
     * @param locator The locator for the new proxy.
     * @return A proxy with the specified locator.
     **/
    @Override
    default MusiqueReceiverPrx ice_locator(com.zeroc.Ice.LocatorPrx locator)
    {
        return (MusiqueReceiverPrx)_ice_locator(locator);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for collocation optimization.
     * @param b <code>true</code> if the new proxy enables collocation optimization; <code>false</code> otherwise.
     * @return A proxy with the specified collocation optimization.
     **/
    @Override
    default MusiqueReceiverPrx ice_collocationOptimized(boolean b)
    {
        return (MusiqueReceiverPrx)_ice_collocationOptimized(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses twoway invocations.
     * @return A proxy that uses twoway invocations.
     **/
    @Override
    default MusiqueReceiverPrx ice_twoway()
    {
        return (MusiqueReceiverPrx)_ice_twoway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses oneway invocations.
     * @return A proxy that uses oneway invocations.
     **/
    @Override
    default MusiqueReceiverPrx ice_oneway()
    {
        return (MusiqueReceiverPrx)_ice_oneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch oneway invocations.
     * @return A proxy that uses batch oneway invocations.
     **/
    @Override
    default MusiqueReceiverPrx ice_batchOneway()
    {
        return (MusiqueReceiverPrx)_ice_batchOneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses datagram invocations.
     * @return A proxy that uses datagram invocations.
     **/
    @Override
    default MusiqueReceiverPrx ice_datagram()
    {
        return (MusiqueReceiverPrx)_ice_datagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch datagram invocations.
     * @return A proxy that uses batch datagram invocations.
     **/
    @Override
    default MusiqueReceiverPrx ice_batchDatagram()
    {
        return (MusiqueReceiverPrx)_ice_batchDatagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, except for compression.
     * @param co <code>true</code> enables compression for the new proxy; <code>false</code> disables compression.
     * @return A proxy with the specified compression setting.
     **/
    @Override
    default MusiqueReceiverPrx ice_compress(boolean co)
    {
        return (MusiqueReceiverPrx)_ice_compress(co);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection timeout setting.
     * @param t The connection timeout for the proxy in milliseconds.
     * @return A proxy with the specified timeout.
     **/
    @Override
    default MusiqueReceiverPrx ice_timeout(int t)
    {
        return (MusiqueReceiverPrx)_ice_timeout(t);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection ID.
     * @param connectionId The connection ID for the new proxy. An empty string removes the connection ID.
     * @return A proxy with the specified connection ID.
     **/
    @Override
    default MusiqueReceiverPrx ice_connectionId(String connectionId)
    {
        return (MusiqueReceiverPrx)_ice_connectionId(connectionId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except it's a fixed proxy bound
     * the given connection.@param connection The fixed proxy connection.
     * @return A fixed proxy bound to the given connection.
     **/

    static String ice_staticId()
    {
        return "::SOUP::MusiqueReceiver";
    }
}
