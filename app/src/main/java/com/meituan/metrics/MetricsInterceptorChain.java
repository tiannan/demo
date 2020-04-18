// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics;

import com.meituan.metrics.interceptor.MetricsInterceptor;
import com.meituan.metrics.model.AbstractEvent;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MetricsInterceptorChain
    implements MetricsInterceptor
{

    public MetricsInterceptorChain()
    {

            interceptors = new CopyOnWriteArrayList();

    }

    public void addInterceptor(MetricsInterceptor metricsinterceptor)
    {

            interceptors.add(metricsinterceptor);

    }

    @Override
    public void onNewEvent(AbstractEvent abstractevent)
    {

        if(interceptors != null && abstractevent != null)
        {
            Iterator iterator = interceptors.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                MetricsInterceptor metricsinterceptor = (MetricsInterceptor)iterator.next();
                if(metricsinterceptor != null)
                    metricsinterceptor.onNewEvent(abstractevent);
            } while(true);
        }
    }

    @Override
    public void onReportEvent(AbstractEvent abstractevent)
    {

        if(interceptors != null && abstractevent != null)
        {
            Iterator iterator = interceptors.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                MetricsInterceptor metricsinterceptor = (MetricsInterceptor)iterator.next();
                if(metricsinterceptor != null)
                    metricsinterceptor.onReportEvent(abstractevent);
            } while(true);
        }
    }

    private final List interceptors;
}
