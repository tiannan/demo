// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.lifecycle;

import android.app.Activity;

public interface MetricsActivityLifecycleCallback
{

    public abstract void onActivityCreated(Activity activity);

    public abstract void onActivityPaused(Activity activity);

    public abstract void onActivityResumed(Activity activity);

    public abstract void onActivityStopped(Activity activity);
}
