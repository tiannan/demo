// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.interceptor;

import com.meituan.metrics.model.AbstractEvent;
import com.meituan.metrics.util.LogUtil;



public class MetricsDebugInterceptor implements MetricsInterceptor
{

    public MetricsDebugInterceptor()
    {
    }

    public void onNewEvent(AbstractEvent abstractevent)
    {

            LogUtil.i("Metrics.NewEvent", new Object[] {
                abstractevent.toString()
            });

    }

    public void onReportEvent(AbstractEvent abstractevent)
    {

            LogUtil.i("Metrics.ReportEvent", new Object[] {
                abstractevent.toString()
            });

    }

}
