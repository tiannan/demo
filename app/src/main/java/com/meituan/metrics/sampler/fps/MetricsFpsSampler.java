// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.sampler.fps;

import com.meituan.metrics.sampler.MetricsSampler;
import java.util.Map;

public interface MetricsFpsSampler
    extends MetricsSampler
{

    public abstract void changeToFragment(Object obj);

    public abstract void startCustomRecordFps(String s);

    public abstract void stopCustomRecordFps(String s, Map map);
}
