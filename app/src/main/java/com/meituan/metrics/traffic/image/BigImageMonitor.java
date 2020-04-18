// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.traffic.image;

import android.text.TextUtils;
import com.meituan.metrics.cache.MetricsCacheManager;
import com.meituan.metrics.lifecycle.MetricsActivityLifecycleManager;
import com.meituan.metrics.traffic.MetricsNetworkInterceptor;
import com.meituan.metrics.traffic.TrafficRecord;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import java.util.*;

// Referenced classes of package com.meituan.metrics.traffic.image:
//            BigImageEvent

public class BigImageMonitor
    implements MetricsNetworkInterceptor
{

    public BigImageMonitor(int i)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "104c5008a2a0130275587ff248909ee1", 0x6000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "104c5008a2a0130275587ff248909ee1");
            return;
        } else
        {
            threshold = i << 10;
            return;
        }
    }

    public void onNetworkTraffic(TrafficRecord trafficrecord)
    {
        Object aobj[] = new Object[1];
        aobj[0] = trafficrecord;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "e15e933b2dfacc3ec0fb703f02592c19", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "e15e933b2dfacc3ec0fb703f02592c19");
            return;
        }
        if(trafficrecord == null)
            return;
        try
        {
            Iterator iterator = trafficrecord.getResponseHeaders().entrySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                if(!TextUtils.equals((CharSequence)entry.getKey(), "Content-Type") || ((List)entry.getValue()).size() <= 0)
                    continue;
                if(((String)((List)entry.getValue()).get(0)).startsWith("image/") && trafficrecord.getResponseBodySize() >= (long)threshold)
                {
                    MetricsCacheManager.getInstance().addToCache(new BigImageEvent(trafficrecord.getUrl(), trafficrecord.getResponseBodySize(), 0, 0, MetricsActivityLifecycleManager.currentActivity));
                    return;
                }
                break;
            } while(true);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(TrafficRecord trafficrecord)
        {
            trafficrecord.printStackTrace();
            return;
        }
    }

    public static ChangeQuickRedirect changeQuickRedirect;
    private final int threshold;
}
