// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics;

import java.util.Map;

public interface MetricsTagsProvider
{

    public abstract Map getTags(String s);
}
