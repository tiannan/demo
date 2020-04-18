// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.traffic;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public interface HttpConnectionTracker
{

    public abstract void disconnect();

    public abstract void error(String s);

    public abstract void reportRequestBody(long l);

    public abstract void reportResponseBody(long l);

    public abstract void trackRequest(String s, Map map);

    public abstract OutputStream trackRequestBody(OutputStream outputstream);

    public abstract void trackResponse(int i, String s, Map map);

    public abstract InputStream trackResponseBody(InputStream inputstream);
}
