// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.traffic;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.*;
import android.text.TextUtils;
import com.meituan.android.cipstorage.d;
import com.meituan.android.cipstorage.f;
import com.meituan.metrics.Metrics;
import com.meituan.metrics.cache.MetricsCacheManager;
import com.meituan.metrics.config.MetricsRemoteConfigManager;
import com.meituan.metrics.config.MetricsRemoteConfigV2;
import com.meituan.metrics.util.*;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import java.io.File;
import java.util.*;

// Referenced classes of package com.meituan.metrics.traffic:
//            TrafficRecord, TrafficEvent

public class TrafficRecordProcessHandler extends Handler
{

    public TrafficRecordProcessHandler(Looper looper)
    {
        super(looper);
        Object aobj[] = new Object[1];
        aobj[0] = looper;
        looper = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, looper, false, "33eaa7321a2b2223a6f4c1e1579028ca", 0x6000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, looper, false, "33eaa7321a2b2223a6f4c1e1579028ca");
            return;
        } else
        {
            SP_NORMAL_PARAMER = "metrics_traffic_paramer";
            todayTotalTraffic = new BasicTrafficUnit();
            uploadedDays = new HashSet();
            obtainMessage(1002).sendToTarget();
            return;
        }
    }

    private String getDownSPKeyByType(int i)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "4758a266f03b41c97d58669ec34cde47", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "4758a266f03b41c97d58669ec34cde47");
        if(i != 4)
            switch(i)
            {
            default:
                return "mobile.traffic.daily.other.downstream";

            case 2: // '\002'
                return "mobile.traffic.daily.res.downstream";

            case 1: // '\001'
                return "mobile.traffic.daily.web.downstream";

            case 0: // '\0'
                return "mobile.traffic.daily.api.downstream";
            }
        else
            return "mobile.traffic.daily.total.downstream";
    }

    private int getTrafficConfigFrom()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "7db05304095f9b999c4c16d0f2a68598", 0x4000000000000000L))
            return ((Integer)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "7db05304095f9b999c4c16d0f2a68598")).intValue();
        return MetricsRemoteConfigManager.getInstance().getRemoteConfigV2().isTrafficDailyTotalEnable() ? 3 : 1;
    }

    private String getUpSPKeyByType(int i)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "907f7d6f5c9a0ccb4421e26a829cf579", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "907f7d6f5c9a0ccb4421e26a829cf579");
        if(i != 4)
            switch(i)
            {
            default:
                return "mobile.traffic.daily.other.upstream";

            case 2: // '\002'
                return "mobile.traffic.daily.res.upstream";

            case 1: // '\001'
                return "mobile.traffic.daily.web.upstream";

            case 0: // '\0'
                return "mobile.traffic.daily.api.upstream";
            }
        else
            return "mobile.traffic.daily.total.upstream";
    }

    private void handleNewRecord(Context context, TrafficRecord trafficrecord)
    {
        Object aobj[] = new Object[2];
        aobj[0] = context;
        aobj[1] = trafficrecord;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "0274bf9e111069878a6c02f814bd5cf8", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "0274bf9e111069878a6c02f814bd5cf8");
            return;
        }
        if(trafficrecord != null)
        {
            if(uploadedDays.contains(trafficrecord.date))
                return;
            String s = getUpSPKeyByType(trafficrecord.type);
            String s1 = getDownSPKeyByType(trafficrecord.type);
            Object obj = new StringBuilder("metrics_traffic_");
            ((StringBuilder) (obj)).append(trafficrecord.date);
            obj = d.a(context, ((StringBuilder) (obj)).toString(), 2);
            long l = ((d) (obj)).b(s, 0L, f.e) + trafficrecord.txBytes;
            long l1 = ((d) (obj)).b(s1, 0L, f.e) + trafficrecord.rxBytes;
            updateTotalTraffic(((d) (obj)), trafficrecord);
            ((d) (obj)).a(s, l, f.e);
            ((d) (obj)).a(s1, l1, f.e);
            if(Metrics.debug)
                LogUtil.i("TrafficProcessor", new Object[] {
                    "save record ", trafficrecord, " current:", s, ":", Long.valueOf(l), " ", s1, ":", Long.valueOf(l1)
                });
            updateRecordDateSet(context, trafficrecord.date);
            return;
        } else
        {
            return;
        }
    }

    private void initTotalTrafficMeter()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "4632bae7cb5fa3666bedfbe665b14936", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "4632bae7cb5fa3666bedfbe665b14936");
            return;
        }
        currentTotalTraffic = NetUtils.getTotalTraffic();
        if(!currentTotalTraffic.isValid())
        {
            useSystemTrafficStats = false;
            LogUtil.e("TrafficProcessor", new Object[] {
                "read TrafficStats from system failed, use total traffic sum instead"
            });
            return;
        } else
        {
            useSystemTrafficStats = true;
            LogUtil.i("TrafficProcessor", new Object[] {
                "initial total traffic ", currentTotalTraffic
            });
            return;
        }
    }

    private boolean reportRecord(Context context, String s)
    {
label0:
        {
            Object aobj[] = new Object[2];
            aobj[0] = context;
            aobj[1] = s;
            ChangeQuickRedirect changequickredirect = changeQuickRedirect;
            if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "338ef917f0ab3d9d7b18eff75124cbe7", 0x4000000000000000L))
                return ((Boolean)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "338ef917f0ab3d9d7b18eff75124cbe7")).booleanValue();
            Object obj = new StringBuilder("metrics_traffic_");
            ((StringBuilder) (obj)).append(s);
            obj = ((StringBuilder) (obj)).toString();
            s = new TrafficEvent(context.getSharedPreferences(((String) (obj)), 0).getAll(), s);
            s.configFrom = getTrafficConfigFrom();
            MetricsCacheManager.getInstance().addToCache(s);
            boolean flag;
            try
            {
                if(android.os.Build.VERSION.SDK_INT >= 24)
                    return context.deleteSharedPreferences(((String) (obj)));
                context = new File(context.getFilesDir().getParent(), "shared_pref");
                s = new StringBuilder();
                s.append(((String) (obj)));
                s.append(".xml");
                s = new File(context, s.toString());
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append(((String) (obj)));
                stringbuilder.append(".xml.bak");
                context = new File(context, stringbuilder.toString());
                if(!s.delete())
                    break label0;
                flag = context.delete();
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                LogUtil.e("TrafficProcessor", "delete sp file failed", context);
                return false;
            }
            if(flag)
                return true;
        }
        return false;
    }

    private boolean reportRecord_mmkv(Context context, String s)
    {
        Object obj = new StringBuilder("metrics_traffic_");
        ((StringBuilder) (obj)).append(s);
        context = d.a(context, ((StringBuilder) (obj)).toString(), 2);
        obj = context.b(f.e);
        if(obj != null && ((Map) (obj)).size() > 0)
        {
            s = new TrafficEvent(((Map) (obj)), s);
            s.configFrom = getTrafficConfigFrom();
            MetricsCacheManager.getInstance().addToCache(s);
        }
        context.c();
        return true;
    }

    private void updateRecordDateSet(Context context, String s)
    {
        Object aobj[] = new Object[2];
        aobj[0] = context;
        aobj[1] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "f65c2049e1d1bb77ca7a68833139c3e2", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "f65c2049e1d1bb77ca7a68833139c3e2");
            return;
        }
        d d1 = d.a(context, "metrics_traffic_paramer", 2);
        context = d1.b("record_days", null, f.e);
        if(context == null)
        {
            context = Collections.singleton(s);
        } else
        {
            if(context.contains(s))
                return;
            context = new HashSet(context);
            context.add(s);
        }
        d1.a("record_days", context, f.e);
    }

    private void updateTotalTraffic(d d1, TrafficRecord trafficrecord)
    {
        Object aobj[] = new Object[2];
        aobj[0] = d1;
        aobj[1] = trafficrecord;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "99ca917262ed2232a29966f170626263", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "99ca917262ed2232a29966f170626263");
            return;
        }
        long l;
        long l1;
        if(useSystemTrafficStats)
        {
            trafficrecord = NetUtils.getTotalTraffic();
            l = ((BasicTrafficUnit) (trafficrecord)).rxBytes - currentTotalTraffic.rxBytes;
            l1 = ((BasicTrafficUnit) (trafficrecord)).txBytes - currentTotalTraffic.txBytes;
            currentTotalTraffic = trafficrecord;
        } else
        {
            l = trafficrecord.rxBytes;
            l1 = trafficrecord.txBytes;
        }
        if(l > 0L || l1 > 0L)
        {
            long l2 = d1.b("mobile.traffic.daily.total.downstream", 0L, f.e) + l;
            long l3 = d1.b("mobile.traffic.daily.total.upstream", 0L, f.e) + l1;
            d1.a("mobile.traffic.daily.total.downstream", l2, f.e);
            d1.a("mobile.traffic.daily.total.upstream", l3, f.e);
            todayTotalTraffic.rxBytes = l2;
            todayTotalTraffic.txBytes = l3;
            if(Metrics.debug)
                LogUtil.i("TrafficProcessor", new Object[] {
                    "save total traffic record  tx:", Long.valueOf(l1), " rx:", Long.valueOf(l), "current tx: ", Long.valueOf(l3), " rx: ", Long.valueOf(l2)
                });
        }
    }

    private void uploadRecords(Context context)
    {
        Object aobj[] = new Object[1];
        boolean flag = false;
        aobj[0] = context;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "a8302fd162d79cfe621e3d615a2308f8", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "a8302fd162d79cfe621e3d615a2308f8");
            return;
        }
        d d1 = d.a(context, "metrics_traffic_paramer", 2);
        boolean flag1 = d1.b("sp_has_upload", false, f.e);
        boolean flag2 = flag1;
        if(!flag1)
        {
            SharedPreferences sharedpreferences = context.getSharedPreferences("metrics_traffic_date_set", 0);
            Set set1 = sharedpreferences.getStringSet("record_days", null);
            if(set1 != null && set1.size() > 0)
            {
                String s1 = TimeUtil.currentDate();
                Iterator iterator1 = set1.iterator();
                do
                {
                    if(!iterator1.hasNext())
                        break;
                    String s3 = (String)iterator1.next();
                    if(!TextUtils.equals(s1, s3) && reportRecord(context, s3))
                        uploadedDays.add(s3);
                } while(true);
                if(set1.contains(s1))
                    d1.a("record_days", Collections.singleton(s1), f.e);
                else
                    sharedpreferences.edit().remove("record_days").commit();
            } else
            {
                flag1 = true;
            }
            d1.a("sp_has_upload", true, f.e);
            flag2 = flag1;
        }
        if(flag2)
        {
            Set set = d1.b("record_days", null, f.e);
            if(set != null && set.size() > 0)
            {
                String s = TimeUtil.currentDate();
                Iterator iterator = set.iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    String s2 = (String)iterator.next();
                    if(!TextUtils.equals(s, s2))
                    {
                        if(reportRecord_mmkv(context, s2))
                            uploadedDays.add(s2);
                        flag = true;
                    }
                } while(true);
                if(!flag)
                    return;
                if(set.contains(s))
                {
                    d1.a("record_days", Collections.singleton(s), f.e);
                    return;
                }
                d1.b("record_days", f.e);
            }
        }
    }

    public BasicTrafficUnit getTodayTotalTraffic()
    {
        return todayTotalTraffic;
    }

    public void handleMessage(Message message)
    {
        Object aobj[] = new Object[1];
        aobj[0] = message;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "e6ee0fb1cb6b03092648809d4bdd6c28", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "e6ee0fb1cb6b03092648809d4bdd6c28");
            return;
        }
        Context context = Metrics.getInstance().getContext();
        if(context == null)
            return;
        if(message.what == 1000 && (message.obj instanceof TrafficRecord))
        {
            handleNewRecord(context, (TrafficRecord)message.obj);
            return;
        }
        if(message.what == 1001)
        {
            uploadRecords(context);
            return;
        }
        if(message.what == 1002)
            initTotalTrafficMeter();
    }

    public static final int INIT_TRAFFIC_BYTES = 1002;
    public static final int NEW_TRAFFIC_RECORD = 1000;
    private static final String SP_KEY_RECORD_DAYS = "record_days";
    private static final String SP_NAME_DATE_SET = "metrics_traffic_date_set";
    private static final String SP_NAME_PREFIX = "metrics_traffic_";
    private static final String TAG = "TrafficProcessor";
    public static final int UPLOAD_OLD_RECORDS = 1001;
    public static ChangeQuickRedirect changeQuickRedirect;
    private final String SP_NORMAL_PARAMER;
    private BasicTrafficUnit currentTotalTraffic;
    private final BasicTrafficUnit todayTotalTraffic;
    private Set uploadedDays;
    private boolean useSystemTrafficStats;
}
