// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.traffic;

import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.meituan.metrics.Metrics;
import com.meituan.metrics.config.MetricsConfig;
import com.meituan.metrics.lifecycle.MetricsActivityLifecycleManager;
import com.meituan.metrics.lifecycle.MetricsAppMonitorCallback;
import com.meituan.metrics.util.BasicTrafficUnit;
import com.meituan.metrics.util.TimeUtil;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

// Referenced classes of package com.meituan.metrics.traffic:
//            TrafficRecordProcessHandler, TrafficRecord, MetricsNetworkInterceptor

public class MetricsTrafficManager
    implements MetricsAppMonitorCallback
{

    public MetricsTrafficManager()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "8825d1551cd59602d840c97d3829134b", 0x6000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "8825d1551cd59602d840c97d3829134b");
            return;
        } else
        {
            trafficRecords = new ConcurrentHashMap();
            networkInterceptors = new ArrayList();
            isPrepare = false;
            cleanUpTimeoutRequestRecords = new Runnable() {

                public void run()
                {
                    Object aobj1[] = new Object[0];
                    Object obj1 = changeQuickRedirect;
                    if(PatchProxy.isSupport(aobj1, this, ((ChangeQuickRedirect) (obj1)), false, "75d2b054fdb90234f3cf0c97be6f0ef6", 0x4000000000000000L))
                    {
                        PatchProxy.accessDispatch(aobj1, this, ((ChangeQuickRedirect) (obj1)), false, "75d2b054fdb90234f3cf0c97be6f0ef6");
                        return;
                    }
                    long l = TimeUtil.currentTimeMillis();
                    Object obj = new ArrayList();
                    obj1 = trafficRecords.keySet().iterator();
                    do
                    {
                        if(!((Iterator) (obj1)).hasNext())
                            break;
                        Integer integer1 = (Integer)((Iterator) (obj1)).next();
                        TrafficRecord trafficrecord = (TrafficRecord)trafficRecords.get(integer1);
                        if(trafficrecord != null && l - trafficrecord.startTime >= 0x1b7740L)
                            ((ArrayList) (obj)).add(integer1);
                    } while(true);
                    Integer integer;
                    for(obj = ((ArrayList) (obj)).iterator(); ((Iterator) (obj)).hasNext(); trafficRecords.remove(integer))
                        integer = (Integer)((Iterator) (obj)).next();

                }

                public static ChangeQuickRedirect changeQuickRedirect;
                public final MetricsTrafficManager this$0;

            public 
            {
                this$0 = MetricsTrafficManager.this;
                super();
            }
            }
;
            init = false;
            return;
        }
    }

    public static MetricsTrafficManager getInstance()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "c86879341c47eb75dd4d131de6be68f7", 0x4000000000000000L))
            return (MetricsTrafficManager)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "c86879341c47eb75dd4d131de6be68f7");
        if(sInstance != null)
            break MISSING_BLOCK_LABEL_74;
        com/meituan/metrics/traffic/MetricsTrafficManager;
        JVM INSTR monitorenter ;
        if(sInstance == null)
            sInstance = new MetricsTrafficManager();
        com/meituan/metrics/traffic/MetricsTrafficManager;
        JVM INSTR monitorexit ;
        break MISSING_BLOCK_LABEL_74;
        Exception exception;
        exception;
        com/meituan/metrics/traffic/MetricsTrafficManager;
        JVM INSTR monitorexit ;
        throw exception;
        if(!sInstance.init && sInstance.isPrepare)
            sInstance.init();
        return sInstance;
    }

    private int getTrafficType(String s, Map map)
    {
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = map;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "068c70a002db23ab9982e34da114d786", 0x4000000000000000L))
            return ((Integer)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "068c70a002db23ab9982e34da114d786")).intValue();
        if(TextUtils.isEmpty(s))
            return -1;
        MetricsConfig metricsconfig = Metrics.getInstance().getAppConfig();
        if(metricsconfig == null)
            return 3;
        if(Pattern.matches(metricsconfig.getTrafficWebUrlPattern(), s))
        {
            Object obj = null;
            String s1 = obj;
            if(map != null)
            {
                map = (List)map.get("Content-Type");
                s1 = obj;
                if(map != null)
                {
                    s1 = obj;
                    if(map.size() > 0)
                        s1 = (String)map.get(0);
                }
            }
            if(!TextUtils.isEmpty(s1) && (s1.contains("text/css") || s1.contains("text/html") || s1.contains("application/x-javascript") || s1.contains("application/javascript")))
                return 1;
        }
        if(Pattern.matches(metricsconfig.getTrafficApiUrlPattern(), s))
            return 0;
        return !Pattern.matches(metricsconfig.getTrafficResUrlPattern(), s) ? 3 : 2;
    }

    public void addInterceptor(MetricsNetworkInterceptor metricsnetworkinterceptor)
    {
        Object aobj[] = new Object[1];
        aobj[0] = metricsnetworkinterceptor;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "bf6a545f4adf815f86be8871a87c8a02", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "bf6a545f4adf815f86be8871a87c8a02");
            return;
        } else
        {
            networkInterceptors.add(metricsnetworkinterceptor);
            return;
        }
    }

    public int getNextRequestId()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "df8c9e65406189cfa831ce4b70f62be5", 0x4000000000000000L))
            return ((Integer)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "df8c9e65406189cfa831ce4b70f62be5")).intValue();
        else
            return idGenerator.incrementAndGet();
    }

    public BasicTrafficUnit getTodayTotalTraffic()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "a7ca42ba4d42c2831229d975af18e734", 0x4000000000000000L))
            return (BasicTrafficUnit)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "a7ca42ba4d42c2831229d975af18e734");
        if(processorHandler != null)
            return processorHandler.getTodayTotalTraffic();
        else
            return null;
    }

    public void init()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "76a9a75b4e810bfa898b97acc8f325be", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "76a9a75b4e810bfa898b97acc8f325be");
            return;
        } else
        {
            processorHandler = new TrafficRecordProcessHandler(trafficRecordProcessThread.getLooper());
            MetricsActivityLifecycleManager.getInstance().registerAppMonitor(this);
            init = true;
            return;
        }
    }

    public void onBackground()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "2b96491c71537ccb683c49cd4ae21b26", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "2b96491c71537ccb683c49cd4ae21b26");
            return;
        }
        if(processorHandler != null)
        {
            processorHandler.removeCallbacks(cleanUpTimeoutRequestRecords);
            processorHandler.post(cleanUpTimeoutRequestRecords);
        }
    }

    public void onForeground()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "d35131d7a63c4d852e3f284b39ac4528", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "d35131d7a63c4d852e3f284b39ac4528");
            return;
        } else
        {
            start();
            return;
        }
    }

    public void onPostRequest(int i, long l)
    {
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "4ad061280be68fb4690aeb006ec32961", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "4ad061280be68fb4690aeb006ec32961");
            return;
        }
        TrafficRecord trafficrecord = (TrafficRecord)trafficRecords.get(Integer.valueOf(i));
        if(trafficrecord == null)
        {
            return;
        } else
        {
            trafficrecord.setRequestBodySize(Math.max(0L, l));
            return;
        }
    }

    public void onPreRequest(int i, String s)
    {
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "e36ae7c5910a1e6dd3533313079f4878", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "e36ae7c5910a1e6dd3533313079f4878");
            return;
        } else
        {
            trafficRecords.put(Integer.valueOf(i), new TrafficRecord(s));
            return;
        }
    }

    public void onRequest(int i, String s, Map map)
    {
        Object aobj[] = new Object[3];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = s;
        aobj[2] = map;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "b719024214353f30149c2dc89bebfaa8", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "b719024214353f30149c2dc89bebfaa8");
            return;
        }
        TrafficRecord trafficrecord = (TrafficRecord)trafficRecords.get(Integer.valueOf(i));
        if(trafficrecord == null)
        {
            return;
        } else
        {
            trafficrecord.setRequestHeaders(s, map);
            return;
        }
    }

    public void onRequestFailed(int i)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "2e72ed028bb4499b45f2427e5aad7abb", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "2e72ed028bb4499b45f2427e5aad7abb");
            return;
        } else
        {
            trafficRecords.remove(Integer.valueOf(i));
            return;
        }
    }

    public void onRequestFinished(int i)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "15de955ae472717fc3dc5e893e048c88", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "15de955ae472717fc3dc5e893e048c88");
            return;
        }
        TrafficRecord trafficrecord = (TrafficRecord)trafficRecords.get(Integer.valueOf(i));
        if(trafficrecord == null)
            return;
        trafficRecords.remove(Integer.valueOf(i));
        if(Metrics.getInstance().getAppConfig().isTrafficStatDisabled())
            return;
        trafficrecord.duration = TimeUtil.currentTimeMillis() - trafficrecord.startTime;
        if(processorHandler != null)
            processorHandler.obtainMessage(1000, trafficrecord).sendToTarget();
        for(Iterator iterator = networkInterceptors.iterator(); iterator.hasNext(); ((MetricsNetworkInterceptor)iterator.next()).onNetworkTraffic(trafficrecord));
    }

    public void onResponse(int i, int j, String s, Map map)
    {
        Object aobj[] = new Object[4];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = Integer.valueOf(j);
        aobj[2] = s;
        aobj[3] = map;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "56c72c18f8717206501ed6dca8bc18fa", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "56c72c18f8717206501ed6dca8bc18fa");
            return;
        }
        TrafficRecord trafficrecord = (TrafficRecord)trafficRecords.get(Integer.valueOf(i));
        if(trafficrecord == null)
            return;
        if(trafficrecord.type == -1)
            trafficrecord.type = getTrafficType(trafficrecord.url, map);
        trafficrecord.setResponseCode(j);
        trafficrecord.setResponseHeaders(s, map);
    }

    public void onResponseBody(int i, long l)
    {
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "f641cc369b3d3b7251a248e469582ad3", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "f641cc369b3d3b7251a248e469582ad3");
            return;
        }
        TrafficRecord trafficrecord = (TrafficRecord)trafficRecords.get(Integer.valueOf(i));
        if(trafficrecord == null)
        {
            return;
        } else
        {
            trafficrecord.setResponseBodySize(Math.max(0L, l));
            return;
        }
    }

    public void prepare()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "3bf1e526c5adaccd9bde0685928b841c", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "3bf1e526c5adaccd9bde0685928b841c");
            return;
        }
        if(Metrics.getInstance().getAppConfig().isTrafficStatDisabled())
        {
            return;
        } else
        {
            trafficRecordProcessThread = new HandlerThread("metrics traffic processor");
            trafficRecordProcessThread.start();
            isPrepare = true;
            return;
        }
    }

    public void start()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "f0ccc2d62eeef52dc36dcd059fda7ac9", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "f0ccc2d62eeef52dc36dcd059fda7ac9");
            return;
        }
        if(processorHandler != null)
        {
            processorHandler.obtainMessage(1001).sendToTarget();
            processorHandler.postDelayed(cleanUpTimeoutRequestRecords, 0x1b7740L);
        }
    }

    private static final long CLEAN_UP_TIMEOUT = 0x1b7740L;
    public static ChangeQuickRedirect changeQuickRedirect;
    private static final AtomicInteger idGenerator = new AtomicInteger(0);
    private static volatile MetricsTrafficManager sInstance;
    private Runnable cleanUpTimeoutRequestRecords;
    private volatile boolean init;
    private boolean isPrepare;
    private final List networkInterceptors;
    private TrafficRecordProcessHandler processorHandler;
    private HandlerThread trafficRecordProcessThread;
    private final ConcurrentHashMap trafficRecords;


}
