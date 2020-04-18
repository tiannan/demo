// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.interceptor;

import com.meituan.metrics.model.AbstractEvent;

public interface MetricsInterceptor
{

    public abstract void onNewEvent(AbstractEvent abstractevent);

    public abstract void onReportEvent(AbstractEvent abstractevent);
}
