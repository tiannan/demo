// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.sampler;

import android.app.Activity;

public interface MetricsSampler
{

    public abstract void doSample();

    public abstract double getRealTimeValue();

    public abstract void pageEnter(Activity activity);

    public abstract void pageExit(Activity activity);
}
