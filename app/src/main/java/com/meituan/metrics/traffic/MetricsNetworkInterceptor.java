// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.traffic;


// Referenced classes of package com.meituan.metrics.traffic:
//            TrafficRecord

public interface MetricsNetworkInterceptor
{

    public abstract void onNetworkTraffic(TrafficRecord trafficrecord);
}
