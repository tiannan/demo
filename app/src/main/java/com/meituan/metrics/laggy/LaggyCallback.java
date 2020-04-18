// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.laggy;

import java.util.List;

public interface LaggyCallback
{

    public abstract void onLaggyEvent(long l, String s, List list);
}
