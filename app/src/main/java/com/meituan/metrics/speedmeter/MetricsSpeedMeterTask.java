// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.speedmeter;

import android.app.Activity;
import android.app.Fragment;
import android.text.TextUtils;
import com.meituan.metrics.cache.MetricsCacheManager;
import com.meituan.metrics.config.MetricsRemoteConfigManager;
import com.meituan.metrics.util.TimeUtil;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import com.sankuai.meituan.takeoutnew.util.aop.d;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package com.meituan.metrics.speedmeter:
//            SpeedMeterEvent

public class MetricsSpeedMeterTask
{

    public MetricsSpeedMeterTask(int i, String s)
    {
        this(i, s, TimeUtil.elapsedTimeMillis());
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = s;
        s = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, s, false, "c0892060c6dac2f414938c69096d6c78", 0x6000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, s, false, "c0892060c6dac2f414938c69096d6c78");
            return;
        } else
        {
            return;
        }
    }

    public MetricsSpeedMeterTask(int i, String s, long l)
    {
        Object aobj[] = new Object[3];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = s;
        aobj[2] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "34b2fc3fda7fd53d8b93624b6af6c049", 0x6000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "34b2fc3fda7fd53d8b93624b6af6c049");
            return;
        }
        mMiddleSteps = new ConcurrentHashMap(10);
        speedMeterType = i;
        mStartTime = l;
        id = s;
        if(i != 1)
        {
            if(!TextUtils.isEmpty(s))
                return;
            else
                throw new IllegalArgumentException("meterTaskId\u4E0D\u80FD\u4E3A\u7A7A\uFF0C\u9875\u9762\u548C\u81EA\u5B9A\u4E49\u6D4B\u901F\u5FC5\u987B\u6307\u5B9A\u540D\u79F0");
        } else
        {
            return;
        }
    }

    private static MetricsSpeedMeterTask _createPageSpeedMeterTask(Object obj)
    {
        return createPageSpeedMeterTask(obj.getClass().getName());
    }

    private void addStep(String s, long l)
    {
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "e09f9e9f95db5152aa9bde2b78bbc073", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "e09f9e9f95db5152aa9bde2b78bbc073");
            return;
        }
        if(l < 0L)
        {
            disable();
            return;
        } else
        {
            mMiddleSteps.put(s, Long.valueOf(l));
            mLastStepDuration = l;
            return;
        }
    }

    public static MetricsSpeedMeterTask createCustomSpeedMeterTask(String s)
    {
        Object aobj[] = new Object[1];
        aobj[0] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "3336338ef874720f4a8bc341f3dee4c0", 0x4000000000000000L))
            return (MetricsSpeedMeterTask)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "3336338ef874720f4a8bc341f3dee4c0");
        else
            return new MetricsSpeedMeterTask(3, s);
    }

    public static MetricsSpeedMeterTask createCustomSpeedMeterTask(String s, long l)
    {
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "9a111b16117e939d0fa14870f794d5da", 0x4000000000000000L))
            return (MetricsSpeedMeterTask)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "9a111b16117e939d0fa14870f794d5da");
        else
            return new MetricsSpeedMeterTask(3, s, l);
    }

    public static MetricsSpeedMeterTask createCustomSpeedMeterTask(String s, boolean flag)
    {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }

    public static MetricsSpeedMeterTask createLaunchSpeedMeterTask()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "8153beb020690ad13316263dbdd32446", 0x4000000000000000L))
            return (MetricsSpeedMeterTask)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "8153beb020690ad13316263dbdd32446");
        else
            return new MetricsSpeedMeterTask(1, null, TimeUtil.processStartElapsedTimeMillis());
    }

    public static MetricsSpeedMeterTask createLaunchSpeedMeterTask(long l)
    {
        Object aobj[] = new Object[1];
        aobj[0] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "3593458a2ce471b8d86b1c66a91216d8", 0x4000000000000000L))
            return (MetricsSpeedMeterTask)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "3593458a2ce471b8d86b1c66a91216d8");
        else
            return new MetricsSpeedMeterTask(1, null, l);
    }

    public static MetricsSpeedMeterTask createPageSpeedMeterTask(Activity activity)
    {
        Object aobj[] = new Object[1];
        aobj[0] = activity;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "5c6bb32ad9814887db8b72fdbe59fbce", 0x4000000000000000L))
            return (MetricsSpeedMeterTask)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "5c6bb32ad9814887db8b72fdbe59fbce");
        else
            return _createPageSpeedMeterTask(activity);
    }

    public static MetricsSpeedMeterTask createPageSpeedMeterTask(Activity activity, long l)
    {
        Object aobj[] = new Object[2];
        aobj[0] = activity;
        aobj[1] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "c0aa6c2681e2ca2bf7543021377f7863", 0x4000000000000000L))
            return (MetricsSpeedMeterTask)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "c0aa6c2681e2ca2bf7543021377f7863");
        else
            return new MetricsSpeedMeterTask(2, activity.getClass().getName(), l);
    }

    public static MetricsSpeedMeterTask createPageSpeedMeterTask(Fragment fragment)
    {
        Object aobj[] = new Object[1];
        aobj[0] = fragment;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "8dc3796762ed00e92a26c508baf5284f", 0x4000000000000000L))
            return (MetricsSpeedMeterTask)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "8dc3796762ed00e92a26c508baf5284f");
        else
            return _createPageSpeedMeterTask(fragment);
    }

    public static MetricsSpeedMeterTask createPageSpeedMeterTask(Fragment fragment, long l)
    {
        Object aobj[] = new Object[2];
        aobj[0] = fragment;
        aobj[1] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "c6fc06eef3e4032a66e4cf62811e0768", 0x4000000000000000L))
            return (MetricsSpeedMeterTask)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "c6fc06eef3e4032a66e4cf62811e0768");
        else
            return new MetricsSpeedMeterTask(2, fragment.getClass().getName(), l);
    }

    public static MetricsSpeedMeterTask createPageSpeedMeterTask(android.support.v4.app.Fragment fragment)
    {
        Object aobj[] = new Object[1];
        aobj[0] = fragment;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "227e99172d90e6f2c886e00a3d5da5b4", 0x4000000000000000L))
            return (MetricsSpeedMeterTask)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "227e99172d90e6f2c886e00a3d5da5b4");
        else
            return _createPageSpeedMeterTask(fragment);
    }

    public static MetricsSpeedMeterTask createPageSpeedMeterTask(android.support.v4.app.Fragment fragment, long l)
    {
        Object aobj[] = new Object[2];
        aobj[0] = fragment;
        aobj[1] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "d4cdfa83b522832a8697a3fba38de7dc", 0x4000000000000000L))
            return (MetricsSpeedMeterTask)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "d4cdfa83b522832a8697a3fba38de7dc");
        else
            return new MetricsSpeedMeterTask(2, fragment.getClass().getName(), l);
    }

    public static MetricsSpeedMeterTask createPageSpeedMeterTask(String s)
    {
        Object aobj[] = new Object[1];
        aobj[0] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "57d1285fc75eca4a2aa73b3d7d382a4d", 0x4000000000000000L))
            return (MetricsSpeedMeterTask)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "57d1285fc75eca4a2aa73b3d7d382a4d");
        else
            return new MetricsSpeedMeterTask(2, s);
    }

    public static MetricsSpeedMeterTask createPageSpeedMeterTask(String s, long l)
    {
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "e82ca0e74ac87b0eea1b824ef3a1d9ab", 0x4000000000000000L))
            return (MetricsSpeedMeterTask)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "e82ca0e74ac87b0eea1b824ef3a1d9ab");
        else
            return new MetricsSpeedMeterTask(2, s, l);
    }

    public static void disableCustomTask(String s)
    {
        Object aobj[] = new Object[1];
        aobj[0] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "5cef54a4c735e43f37040bea713ef481", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "5cef54a4c735e43f37040bea713ef481");
            return;
        }
        s = getCustomSpeedMeterTask(s);
        if(s != null)
            s.disable();
    }

    public static MetricsSpeedMeterTask getCustomSpeedMeterTask(String s)
    {
        Object aobj[] = new Object[1];
        aobj[0] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "859603a407ef13e46218ecf45c19e4e7", 0x4000000000000000L))
            return (MetricsSpeedMeterTask)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "859603a407ef13e46218ecf45c19e4e7");
        if(TextUtils.isEmpty(s))
            return null;
        else
            return (MetricsSpeedMeterTask)customSpeedMeterTaskMap.get(s);
    }

    public static MetricsSpeedMeterTask recordCustomTaskStep(String s, String s1)
    {
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = s1;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "6cbe91d9f1a181dde992a29b96ead199", 0x4000000000000000L))
            return (MetricsSpeedMeterTask)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "6cbe91d9f1a181dde992a29b96ead199");
        else
            return recordCustomTaskStep(s, s1, -1L);
    }

    public static MetricsSpeedMeterTask recordCustomTaskStep(String s, String s1, long l)
    {
        Object aobj[] = new Object[3];
        aobj[0] = s;
        aobj[1] = s1;
        aobj[2] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "76efe1642b9cb00fcb3214245290caa3", 0x4000000000000000L))
            return (MetricsSpeedMeterTask)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "76efe1642b9cb00fcb3214245290caa3");
        s = getCustomSpeedMeterTask(s);
        if(s != null)
        {
            if(l > 0L)
            {
                s.recordStep(s1, l);
                return s;
            }
            s.recordStep(s1);
        }
        return s;
    }

    public static MetricsSpeedMeterTask removeCustomSpeedMeterTask(String s)
    {
        Object aobj[] = new Object[1];
        aobj[0] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "64a84bd1b7ea804bcc03fd03c153155c", 0x4000000000000000L))
            return (MetricsSpeedMeterTask)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "64a84bd1b7ea804bcc03fd03c153155c");
        if(TextUtils.isEmpty(s))
            return null;
        else
            return (MetricsSpeedMeterTask)customSpeedMeterTaskMap.remove(s);
    }

    public static void reportCustomTask(String s, Map map, String s1)
    {
        Object aobj[] = new Object[3];
        aobj[0] = s;
        aobj[1] = map;
        aobj[2] = s1;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "c15af544ac01765fee2c8b9954cf8db0", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "c15af544ac01765fee2c8b9954cf8db0");
            return;
        }
        MetricsSpeedMeterTask metricsspeedmetertask = getCustomSpeedMeterTask(s);
        if(metricsspeedmetertask != null)
        {
            d.a(metricsspeedmetertask, map, s1);
            removeCustomSpeedMeterTask(s);
        }
    }

    private void reportForConfigNew(Map map, String s)
    {
        Object aobj[] = new Object[2];
        aobj[0] = map;
        aobj[1] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "43b37c70a88261070c0d4ec435b9716f", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "43b37c70a88261070c0d4ec435b9716f");
            return;
        } else
        {
            MetricsCacheManager metricscachemanager = MetricsCacheManager.getInstance();
            SpeedMeterEvent speedmeterevent = new SpeedMeterEvent("total", mLastStepDuration, mMiddleSteps, speedMeterType, id);
            speedmeterevent.configFrom = 2;
            speedmeterevent.optionTags = map;
            speedmeterevent.raw = s;
            metricscachemanager.addToCache(speedmeterevent);
            return;
        }
    }

    private void reportForConfigOld()
    {
        Object aobj[] = new Object[0];
        Object obj = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, ((ChangeQuickRedirect) (obj)), false, "c335c34ede5352bf5ac34e1ec9cfd42f", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, ((ChangeQuickRedirect) (obj)), false, "c335c34ede5352bf5ac34e1ec9cfd42f");
            return;
        }
        MetricsCacheManager metricscachemanager = MetricsCacheManager.getInstance();
        obj = new SpeedMeterEvent("total", mLastStepDuration, speedMeterType, id);
        obj.configFrom = 1;
        metricscachemanager.addToCache(((com.meituan.metrics.model.AbstractEvent) (obj)));
        String s;
        for(Iterator iterator = mMiddleSteps.keySet().iterator(); iterator.hasNext(); metricscachemanager.addToCache(new SpeedMeterEvent(s, ((Long)mMiddleSteps.get(s)).longValue(), speedMeterType, id, 1)))
            s = (String)iterator.next();

    }

    public void disable()
    {
        isInvokeReport = true;
    }

    public MetricsSpeedMeterTask recordStep(String s)
    {
        Object aobj[] = new Object[1];
        aobj[0] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "d0569d1d8124940764b37020a8bfbf78", 0x4000000000000000L))
            return (MetricsSpeedMeterTask)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "d0569d1d8124940764b37020a8bfbf78");
        if(!isInvokeReport)
        {
            if(mStartTime <= 0L)
            {
                return this;
            } else
            {
                addStep(s, TimeUtil.elapsedTimeMillis() - mStartTime);
                return this;
            }
        } else
        {
            return this;
        }
    }

    public MetricsSpeedMeterTask recordStep(String s, long l)
    {
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "3b1dc5102f5cb0f0ae9244888d44427c", 0x4000000000000000L))
            return (MetricsSpeedMeterTask)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "3b1dc5102f5cb0f0ae9244888d44427c");
        if(!isInvokeReport)
        {
            if(mStartTime <= 0L)
                return this;
            long l1 = TimeUtil.elapsedTimeMillis() - mStartTime;
            if(l1 < l)
                addStep(s, l1);
            return this;
        } else
        {
            return this;
        }
    }

    public MetricsSpeedMeterTask recordStepUseDefined(String s, long l)
    {
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "66fb156a3ec1ca29262af7c3fbb5677f", 0x4000000000000000L))
            return (MetricsSpeedMeterTask)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "66fb156a3ec1ca29262af7c3fbb5677f");
        if(!isInvokeReport && mStartTime > 0L)
        {
            if(l < mStartTime)
            {
                return this;
            } else
            {
                addStep(s, l - mStartTime);
                return this;
            }
        } else
        {
            return this;
        }
    }

    public void report()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "7f5a6043d3dca98784b1c47e535c7970", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "7f5a6043d3dca98784b1c47e535c7970");
            return;
        } else
        {
            d.a(this, null, null);
            return;
        }
    }

    public void report(Map map)
    {
        Object aobj[] = new Object[1];
        aobj[0] = map;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "4e248e011814d1fec266e67fa30b1353", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "4e248e011814d1fec266e67fa30b1353");
            return;
        } else
        {
            d.a(this, map, null);
            return;
        }
    }

    public void report(Map map, String s)
    {
        Object aobj[] = new Object[2];
        aobj[0] = map;
        aobj[1] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "16922fcf9095f62fba2027a88c8f3f30", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "16922fcf9095f62fba2027a88c8f3f30");
            return;
        }
        if(isInvokeReport)
            return;
        isInvokeReport = true;
        if(MetricsRemoteConfigManager.getInstance().getRemoteConfig() == null)
            return;
        int i;
        if(speedMeterType == 2)
        {
            int j = MetricsRemoteConfigManager.getInstance().getLoadPageConfig(id);
            i = j;
            if(j == -1)
                return;
        } else
        if(speedMeterType == 3)
        {
            int k = MetricsRemoteConfigManager.getInstance().getLoadCustomConfig(id);
            i = k;
            if(k == -1)
                return;
        } else
        if(speedMeterType == 1)
        {
            int l = MetricsRemoteConfigManager.getInstance().getAppStartupConfig();
            i = l;
            if(l == -1)
                return;
        } else
        {
            i = -1;
        }
        if(mStartTime > 0L)
        {
            if(i == 1)
            {
                reportForConfigOld();
                return;
            }
            if(i == 2)
            {
                reportForConfigNew(map, s);
                return;
            }
            if(i == 3)
            {
                reportForConfigOld();
                reportForConfigNew(map, s);
            }
        }
    }

    public static ChangeQuickRedirect changeQuickRedirect;
    private static final Map customSpeedMeterTaskMap = new ConcurrentHashMap();
    private final String id;
    private boolean isInvokeReport;
    private long mLastStepDuration;
    private final Map mMiddleSteps;
    private final long mStartTime;
    private final int speedMeterType;

}
