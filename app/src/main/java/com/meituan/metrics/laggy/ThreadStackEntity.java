// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.laggy;

public class ThreadStackEntity
{

    public ThreadStackEntity(long l, StackTraceElement astacktraceelement[])
    {
        ts = l;
        stackTraceElements = astacktraceelement;
    }

    public StackTraceElement stackTraceElements[];
    public long ts;
}
