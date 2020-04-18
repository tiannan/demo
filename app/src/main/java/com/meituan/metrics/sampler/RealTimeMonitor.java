// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.sampler;


public interface RealTimeMonitor
{

    public abstract void destroy();

    public abstract void onCpu(double d);

    public abstract void onFPS(double d);

    public abstract void onMemory(double d);
}
