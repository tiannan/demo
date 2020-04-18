// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import com.meituan.metrics.cache.MetricsCacheManager;
import com.meituan.metrics.config.MetricsConfig;
import com.meituan.metrics.config.MetricsLocalSwitchConfig;
import com.meituan.metrics.config.MetricsLocalSwitchConfigManager;
import com.meituan.metrics.config.MetricsRemoteConfig;
import com.meituan.metrics.config.MetricsRemoteConfigManager;
import com.meituan.metrics.config.MetricsRemoteConfigV2;
import com.meituan.metrics.interceptor.MetricsInterceptor;
import com.meituan.metrics.laggy.MetricsLaggyManager;
import com.meituan.metrics.net.report.MetricsReportManager;
import com.meituan.metrics.sampler.MetricSampleManager;
import com.meituan.metrics.speedmeter.MetricsSpeedMeterTask;
import com.meituan.metrics.traffic.MetricsNetworkInterceptor;
import com.meituan.metrics.traffic.MetricsTrafficManager;
import com.meituan.metrics.traffic.image.BigImageMonitor;
import com.meituan.metrics.util.AppUtils;
import com.meituan.metrics.util.LogUtil;
import com.meituan.metrics.util.thread.ThreadManager;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import com.meituan.snare.a;
import com.meituan.snare.h;
import com.sankuai.meituan.takeoutnew.util.aop.d;
import com.sankuai.meituan.takeoutnew.util.aop.f;
import java.util.Map;

// Referenced classes of package com.meituan.metrics:
//            MetricsInterceptorChain, Environment

public class Metrics
{

    public Metrics()
    {

            interceptorChain = new MetricsInterceptorChain();
            launchSpeedTask = MetricsSpeedMeterTask.createLaunchSpeedMeterTask();

    }

    public static Environment getEnvironment()
    {
        return environment;
    }

    public static Metrics getInstance()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "4943d5118179a48c406f0bef381feeb8", 0x4000000000000000L))
            return (Metrics)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "4943d5118179a48c406f0bef381feeb8");
        if(sInstance != null)
            break MISSING_BLOCK_LABEL_74;
        com/meituan/metrics/Metrics;
        JVM INSTR monitorenter ;
        if(sInstance == null)
            sInstance = new Metrics();
        com/meituan/metrics/Metrics;
        JVM INSTR monitorexit ;
        break MISSING_BLOCK_LABEL_74;
        Exception exception;
        exception;
        com/meituan/metrics/Metrics;
        JVM INSTR monitorexit ;
        throw exception;
        return sInstance;
    }

    private void initMetricWithRemoteConfig()
    {
        Object aobj[] = new Object[0];
        Object obj = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, ((ChangeQuickRedirect) (obj)), false, "149b1222f41f22e3cec5798e75b74218", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, ((ChangeQuickRedirect) (obj)), false, "149b1222f41f22e3cec5798e75b74218");
            return;
        }
        MetricsRemoteConfig metricsremoteconfig = MetricsRemoteConfigManager.getInstance().getRemoteConfig();
        if(metricsremoteconfig == null)
            return;
        obj = MetricsRemoteConfigManager.getInstance().getRemoteConfigV2();
        MetricsCacheManager.getInstance().setConfig(metricsremoteconfig, ((MetricsRemoteConfigV2) (obj)));
        if(mLocalConfig.isLagEnable() && ((MetricsRemoteConfigV2) (obj)).isLagEnable() || mLocalConfig.isAnrEnable() && ((MetricsRemoteConfigV2) (obj)).isAnrEnable())
        {
            MetricsLaggyManager metricslaggymanager = MetricsLaggyManager.getInstance();
            boolean flag;
            if(mLocalConfig.isLagEnable() && ((MetricsRemoteConfigV2) (obj)).isLagEnable())
                flag = true;
            else
                flag = false;
            int i = ((MetricsRemoteConfigV2) (obj)).lagThreshold;
            int j = ((MetricsRemoteConfigV2) (obj)).maxReportCallstackTimes;
            boolean flag2;
            if(mLocalConfig.isAnrEnable() && ((MetricsRemoteConfigV2) (obj)).isAnrEnable())
                flag2 = true;
            else
                flag2 = false;
            metricslaggymanager.init(flag, i, j, flag2);
        } else
        {
            MetricsLaggyManager metricslaggymanager1 = MetricsLaggyManager.getInstance();
            boolean flag1;
            if(mLocalConfig.isLagEnable() && ((MetricsRemoteConfigV2) (obj)).isLagEnable())
                flag1 = true;
            else
                flag1 = false;
            metricslaggymanager1.setLagConfig(flag1, ((MetricsRemoteConfigV2) (obj)).lagThreshold, ((MetricsRemoteConfigV2) (obj)).maxReportCallstackTimes);
        }
        if(mLocalConfig.isBigImageEnable() && metricsremoteconfig.isBigImageEnable() && metricsremoteconfig.bigImageThreshold > 0)
            MetricsTrafficManager.getInstance().addInterceptor(new BigImageMonitor(metricsremoteconfig.bigImageThreshold));
        if((!mLocalConfig.isStartupTimerEnable() || !MetricsRemoteConfigManager.getInstance().isAppStartupEnable()) && launchSpeedTask != null)
            launchSpeedTask.disable();
        if(mLocalConfig.isSampleEnable())
            MetricSampleManager.getInstance().init(metricsremoteconfig, mLocalConfig.isNativeFPSSampleEnable());
        if(debug)
        {
            StringBuilder stringbuilder = new StringBuilder("initialized with ");
            stringbuilder.append(metricsremoteconfig.toString());
            LogUtil.i("Metrics", new Object[] {
                stringbuilder.toString()
            });
        }
    }

    public static void storeCrash(String s, int i, String s1, boolean flag, boolean flag1)
    {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.useAs(TypeTransformer.java:868)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:668)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }

    public static void storeCrash(Throwable throwable, int i, String s, boolean flag)
    {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.useAs(TypeTransformer.java:868)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:668)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }

    public Metrics addInterceptor(MetricsInterceptor metricsinterceptor)
    {
        Object aobj[] = new Object[1];
        aobj[0] = metricsinterceptor;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "13034dab7caaef1d4f1879ee0b12d555", 0x4000000000000000L))
            return (Metrics)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "13034dab7caaef1d4f1879ee0b12d555");
        if(metricsinterceptor != null)
            interceptorChain.addInterceptor(metricsinterceptor);
        return this;
    }

    public Metrics addNetworkInterceptor(MetricsNetworkInterceptor metricsnetworkinterceptor)
    {
        Object aobj[] = new Object[1];
        aobj[0] = metricsnetworkinterceptor;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "c1bf3baf3e212258ff80444ce6801f5f", 0x4000000000000000L))
        {
            return (Metrics)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "c1bf3baf3e212258ff80444ce6801f5f");
        } else
        {
            MetricsTrafficManager.getInstance().addInterceptor(metricsnetworkinterceptor);
            return this;
        }
    }

    public void changeToFragment(Fragment fragment)
    {
        Object aobj[] = new Object[1];
        aobj[0] = fragment;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "e8e67072624e6d5d7f90377b51db181f", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "e8e67072624e6d5d7f90377b51db181f");
            return;
        } else
        {
            MetricSampleManager.getInstance().changeToFragment(fragment);
            return;
        }
    }

    public void changeToFragment(android.support.v4.app.Fragment fragment)
    {
        Object aobj[] = new Object[1];
        aobj[0] = fragment;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "3dc7f16c9b4c7254778a2a7ab3e5ceaa", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "3dc7f16c9b4c7254778a2a7ab3e5ceaa");
            return;
        } else
        {
            MetricSampleManager.getInstance().changeToFragment(fragment);
            return;
        }
    }

    public MetricsLocalSwitchConfig createLocalSwitchConfig(Activity activity)
    {
        Object aobj[] = new Object[1];
        aobj[0] = activity;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "9d4bd00dfe025fa3a378c50d63c0ef0b", 0x4000000000000000L))
            return (MetricsLocalSwitchConfig)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "9d4bd00dfe025fa3a378c50d63c0ef0b");
        else
            return new MetricsLocalSwitchConfig(AppUtils.getPageName(activity), MetricsLocalSwitchConfigManager.getInstance().getCommonLocalSw());
    }

    public Metrics disableLaunchSpeedMeter()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "8685b508fc956c9507f1b16d9a15f71a", 0x4000000000000000L))
            return (Metrics)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "8685b508fc956c9507f1b16d9a15f71a");
        if(launchSpeedTask != null)
            launchSpeedTask.disable();
        return this;
    }

    public void disableRealTimeMonitor()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "f43b1927a80d1ce98b93a1f46193ad16", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "f43b1927a80d1ce98b93a1f46193ad16");
            return;
        } else
        {
            MetricSampleManager.getInstance().enableRealTimeMonitor(false);
            return;
        }
    }

    public void enableRealTimeMonitor()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "4cfdd844ded3cdfe2f9722f383b86ac3", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "4cfdd844ded3cdfe2f9722f383b86ac3");
            return;
        } else
        {
            MetricSampleManager.getInstance().enableRealTimeMonitor(true);
            return;
        }
    }

    public MetricsConfig getAppConfig()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "ceac6c88cd9befaba9ebf01c7531b248", 0x4000000000000000L))
            return (MetricsConfig)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "ceac6c88cd9befaba9ebf01c7531b248");
        if(mLocalConfig == null)
            mLocalConfig = new MetricsConfig() {

                public String getChannel()
                {
                    return "";
                }

                public h getReportStrategy()
                {
                    Object aobj1[] = new Object[0];
                    ChangeQuickRedirect changequickredirect1 = changeQuickRedirect;
                    if(PatchProxy.isSupport(aobj1, this, changequickredirect1, false, "87d10be010a9248de8d5a1d716494b44", 0x4000000000000000L))
                        return (h)PatchProxy.accessDispatch(aobj1, this, changequickredirect1, false, "87d10be010a9248de8d5a1d716494b44");
                    else
                        return new a();
                }

                public String getToken()
                {
                    return "";
                }

                public String getUuid()
                {
                    return "";
                }

                public static ChangeQuickRedirect changeQuickRedirect;
                public final Metrics this$0;

            public 
            {
                this$0 = Metrics.this;
                super();
            }
            }
;
        return mLocalConfig;
    }

    public Context getContext()
    {
        return context;
    }

    public MetricsInterceptorChain getInterceptorChain()
    {
        return interceptorChain;
    }

    public Metrics init(Context context1, MetricsConfig metricsconfig)
    {
        Object aobj[] = new Object[2];
        aobj[0] = context1;
        aobj[1] = metricsconfig;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "62927001d845b72505eb884fb4c51166", 0x4000000000000000L))
            return (Metrics)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "62927001d845b72505eb884fb4c51166");
        if(context != null)
        {
            LogUtil.e("Metrics", new Object[] {
                "Metrics already initialized."
            });
            return this;
        } else
        {
            context1 = context1.getApplicationContext();
            context = context1;
            ThreadManager.getInstance().prepare();
            MetricsTrafficManager.getInstance().prepare();
            Babel.init(context1);
            mLocalConfig = metricsconfig;
            environment = new Environment(context1.getApplicationContext(), metricsconfig);
            MetricsCacheManager.getInstance().init(context1);
            f.a(AsyncTask.THREAD_POOL_EXECUTOR, new Runnable() {

                public void run()
                {
                    Object aobj1[] = new Object[0];
                    ChangeQuickRedirect changequickredirect1 = changeQuickRedirect;
                    if(PatchProxy.isSupport(aobj1, this, changequickredirect1, false, "a31f7d35bb7e65ebf801d65a4ed56765", 0x4000000000000000L))
                    {
                        PatchProxy.accessDispatch(aobj1, this, changequickredirect1, false, "a31f7d35bb7e65ebf801d65a4ed56765");
                        return;
                    } else
                    {
                        MetricsTrafficManager.getInstance().start();
                        initMetricWithRemoteConfig();
                        MetricsReportManager.getInstance().startReportRegular();
                        return;
                    }
                }

                public static ChangeQuickRedirect changeQuickRedirect;
                public final Metrics this$0;

            public 
            {
                this$0 = Metrics.this;
                super();
            }
            }
);
            return this;
        }
    }

    public Metrics recordLaunchStep(String s)
    {
        Object aobj[] = new Object[1];
        aobj[0] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "b4ae6dcdf9cdf8e4a3a6a291d83588c4", 0x4000000000000000L))
            return (Metrics)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "b4ae6dcdf9cdf8e4a3a6a291d83588c4");
        if(launchSpeedTask != null)
            launchSpeedTask.recordStep(s);
        return this;
    }

    public Metrics recordLaunchStep(String s, long l)
    {
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "67cb95a9e7ed318ef2c2728f6bc57289", 0x4000000000000000L))
            return (Metrics)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "67cb95a9e7ed318ef2c2728f6bc57289");
        if(launchSpeedTask != null)
            launchSpeedTask.recordStep(s, l);
        return this;
    }

    public Metrics reportLaunchSteps()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "ffb2bc64bfce5d55826de269bae6764d", 0x4000000000000000L))
            return (Metrics)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "ffb2bc64bfce5d55826de269bae6764d");
        else
            return reportLaunchSteps(null, null);
    }

    public Metrics reportLaunchSteps(Map map)
    {
        Object aobj[] = new Object[1];
        aobj[0] = map;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "ed7e1bae26fbd36b34f4eb6141a15d86", 0x4000000000000000L))
            return (Metrics)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "ed7e1bae26fbd36b34f4eb6141a15d86");
        else
            return reportLaunchSteps(map, null);
    }

    public Metrics reportLaunchSteps(Map map, String s)
    {
        Object aobj[] = new Object[2];
        aobj[0] = map;
        aobj[1] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "b2388deedd8fea9684d8ab9fd4bf726f", 0x4000000000000000L))
            return (Metrics)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "b2388deedd8fea9684d8ab9fd4bf726f");
        if(launchSpeedTask != null)
            d.a(launchSpeedTask, map, s);
        return this;
    }

    public void reportSpeedMeterTask(MetricsSpeedMeterTask metricsspeedmetertask)
    {
        Object aobj[] = new Object[1];
        aobj[0] = metricsspeedmetertask;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "e75503e3a6a476a264fd590a31d25c7b", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "e75503e3a6a476a264fd590a31d25c7b");
            return;
        } else
        {
            d.a(metricsspeedmetertask);
            return;
        }
    }

    public void resetLaunchTask(long l)
    {
        Object aobj[] = new Object[1];
        aobj[0] = new Long(l);
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "785bf3a7ea9e54da17963f8250babaf2", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "785bf3a7ea9e54da17963f8250babaf2");
            return;
        } else
        {
            launchSpeedTask = MetricsSpeedMeterTask.createLaunchSpeedMeterTask(l);
            return;
        }
    }

    public Metrics setDebug(boolean flag)
    {
        debug = flag;
        return this;
    }

    public Metrics setLocalSwitch(boolean flag)
    {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.useAs(TypeTransformer.java:868)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:668)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }

    public Metrics setReportCategory(String s)
    {
        Object aobj[] = new Object[1];
        aobj[0] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "a43fbd09f75ca91783149a3216fb9e23", 0x4000000000000000L))
        {
            return (Metrics)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "a43fbd09f75ca91783149a3216fb9e23");
        } else
        {
            MetricsReportManager.getInstance().setCategory(s);
            return this;
        }
    }

    public void setScrollCustom(Activity activity)
    {
        Object aobj[] = new Object[1];
        aobj[0] = activity;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "9e5d64d56af401fae76ededaa0ba7128", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "9e5d64d56af401fae76ededaa0ba7128");
            return;
        } else
        {
            MetricSampleManager.getInstance().setScrollEntityCustom(activity);
            return;
        }
    }

    public Metrics startCustomFPS(String s)
    {
        Object aobj[] = new Object[1];
        aobj[0] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "25a665fa4cd8ed720d8f05e1c0a3e25c", 0x4000000000000000L))
        {
            return (Metrics)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "25a665fa4cd8ed720d8f05e1c0a3e25c");
        } else
        {
            MetricSampleManager.getInstance().startCustomFPS(s);
            return this;
        }
    }

    public void startCustomScrollFPS(Activity activity)
    {
        Object aobj[] = new Object[1];
        aobj[0] = activity;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "3ec9a094f91098410f05ab2a573952b8", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "3ec9a094f91098410f05ab2a573952b8");
            return;
        } else
        {
            MetricSampleManager.getInstance().startCustomScrollFPS(activity);
            return;
        }
    }

    public Metrics stopCustomFPS(String s)
    {
        Object aobj[] = new Object[1];
        aobj[0] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "b244ce68ad03bd963405857d5a722845", 0x4000000000000000L))
        {
            return (Metrics)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "b244ce68ad03bd963405857d5a722845");
        } else
        {
            MetricSampleManager.getInstance().stopCustomFPS(s, null);
            return this;
        }
    }

    public Metrics stopCustomFPS(String s, Map map)
    {
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = map;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "3b03f2a2ae58b144bf5cf35cd65c9f19", 0x4000000000000000L))
        {
            return (Metrics)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "3b03f2a2ae58b144bf5cf35cd65c9f19");
        } else
        {
            MetricSampleManager.getInstance().stopCustomFPS(s, map);
            return this;
        }
    }

    public void stopCustomScrollFPS(Activity activity)
    {
        Object aobj[] = new Object[1];
        aobj[0] = activity;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "52e81de21e35c4be571352a9b62c0e45", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "52e81de21e35c4be571352a9b62c0e45");
            return;
        } else
        {
            MetricSampleManager.getInstance().stopCustomScrollFPS(activity);
            return;
        }
    }

    private static final String TAG = "Metrics";
    public static ChangeQuickRedirect changeQuickRedirect;
    public static boolean debug;
    private static Environment environment;
    private static Metrics sInstance;
    private Context context;
    private final MetricsInterceptorChain interceptorChain;
    private MetricsSpeedMeterTask launchSpeedTask;
    private MetricsConfig mLocalConfig;

}
