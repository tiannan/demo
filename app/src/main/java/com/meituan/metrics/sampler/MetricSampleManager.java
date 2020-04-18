// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.sampler;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.*;
import android.text.TextUtils;
import com.meituan.metrics.Metrics;
import com.meituan.metrics.config.MetricsRemoteConfig;
import com.meituan.metrics.config.MetricsRemoteConfigManager;
import com.meituan.metrics.lifecycle.*;
import com.meituan.metrics.sampler.cpu.MetricsCpuSampler;
import com.meituan.metrics.sampler.fps.MetricsFpsSampler;
import com.meituan.metrics.sampler.fps.MetricsFpsSamplerImpl;
import com.meituan.metrics.sampler.memory.MetricsMemorySampler;
import com.meituan.metrics.util.AppUtils;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

// Referenced classes of package com.meituan.metrics.sampler:
//            SamplingThread, MetricsSampler, RealTimeMonitor

public class MetricSampleManager
    implements MetricsActivityLifecycleCallback, MetricsAppMonitorCallback, SamplingThread.SamplingCallback
{

    public MetricSampleManager()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "f09d1d357f97d04d905181627083ee41", 0x6000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "f09d1d357f97d04d905181627083ee41");
            return;
        } else
        {
            samplers = new ConcurrentLinkedQueue();
            return;
        }
    }

    public static MetricSampleManager getInstance()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "a928ebe98c246998087d9fb3c1e509bb", 0x4000000000000000L))
            return (MetricSampleManager)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "a928ebe98c246998087d9fb3c1e509bb");
        if(sInstance != null)
            break MISSING_BLOCK_LABEL_74;
        com/meituan/metrics/sampler/MetricSampleManager;
        JVM INSTR monitorenter ;
        if(sInstance == null)
            sInstance = new MetricSampleManager();
        com/meituan/metrics/sampler/MetricSampleManager;
        JVM INSTR monitorexit ;
        break MISSING_BLOCK_LABEL_74;
        Exception exception;
        exception;
        com/meituan/metrics/sampler/MetricSampleManager;
        JVM INSTR monitorexit ;
        throw exception;
        return sInstance;
    }

    private boolean isFpsCustomEnable(String s)
    {
        Object aobj[] = new Object[1];
        aobj[0] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "c3a15da6e06091bf16e71e701d2a3ff7", 0x4000000000000000L))
            return ((Boolean)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "c3a15da6e06091bf16e71e701d2a3ff7")).booleanValue();
        return MetricsRemoteConfigManager.getInstance().getFpsCustomConfig(s) != -1;
    }

    private boolean isFpsScrollEnable(Activity activity)
    {
        Object aobj[] = new Object[1];
        aobj[0] = activity;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "bf4b9f0e59926fd705de3fd6d1ff01c3", 0x4000000000000000L))
            return ((Boolean)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "bf4b9f0e59926fd705de3fd6d1ff01c3")).booleanValue();
        activity = AppUtils.getPageName(activity, MetricsActivityLifecycleManager.currentActivity);
        return MetricsRemoteConfigManager.getInstance().getFpsScrollConfig(activity) != -1;
    }

    private void startRealTimeMonitor()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "13c788fa81c62fbc8a608ead4250f7e9", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "13c788fa81c62fbc8a608ead4250f7e9");
            return;
        } else
        {
            (new Handler(Looper.getMainLooper())).post(new Runnable() {

                public void run()
                {
                    Object obj;
                    Object aobj1[] = new Object[0];
                    ChangeQuickRedirect changequickredirect1 = changeQuickRedirect;
                    if(PatchProxy.isSupport(aobj1, this, changequickredirect1, false, "4aa974182b56fc1c429e5610ac7489c6", 0x4000000000000000L))
                    {
                        PatchProxy.accessDispatch(aobj1, this, changequickredirect1, false, "4aa974182b56fc1c429e5610ac7489c6");
                        return;
                    }
                    if(monitorImpl != null)
                        return;
                    obj = Metrics.getInstance().getContext();
                    obj = ((Context) (obj)).getPackageManager().getApplicationInfo(((Context) (obj)).getPackageName(), 128).metaData.getString("com.meituan.metrics.sampler.RealTimeMonitor");
                    if(!TextUtils.isEmpty(((CharSequence) (obj))))
                    {
                        monitorImpl = (RealTimeMonitor)Class.forName(((String) (obj))).newInstance();
                        return;
                    }
                    try
                    {
                        throw new RuntimeException("Metrics can't find the implementation class of com.meituan.metrics.sampler.RealTimeMonitor in meta-data, please add dependency com.meituan.metrics:realtime-monitor:0.7.14 or put your own implementation in AndroidManifest.xml");
                    }
                    catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
                    {
                        namenotfoundexception.printStackTrace();
                    }
                    catch(IllegalAccessException illegalaccessexception)
                    {
                        illegalaccessexception.printStackTrace();
                        return;
                    }
                    catch(InstantiationException instantiationexception)
                    {
                        instantiationexception.printStackTrace();
                        return;
                    }
                    catch(ClassNotFoundException classnotfoundexception)
                    {
                        classnotfoundexception.printStackTrace();
                        return;
                    }
                    catch(Exception exception)
                    {
                        exception.printStackTrace();
                        return;
                    }
                    return;
                }

                public static ChangeQuickRedirect changeQuickRedirect;
                public final MetricSampleManager this$0;

            public 
            {
                this$0 = MetricSampleManager.this;
                super();
            }
            }
);
            return;
        }
    }

    public void addRNSample(MetricsFpsSampler metricsfpssampler)
    {
        this;
        JVM INSTR monitorenter ;
        Object aobj[] = new Object[1];
        aobj[0] = metricsfpssampler;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(!PatchProxy.isSupport(aobj, this, changequickredirect, false, "7dda2e97cd99bbf25efb6ffb01618cf3", 0x4000000000000000L))
            break MISSING_BLOCK_LABEL_43;
        PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "7dda2e97cd99bbf25efb6ffb01618cf3");
        this;
        JVM INSTR monitorexit ;
        return;
        if(metricsfpssampler == null)
            return;
        rnSampler = metricsfpssampler;
        if(samplingThread == null)
            samplingThread = new SamplingThread(this, 1L);
        if(samplers == null)
            samplers = new ConcurrentLinkedQueue();
        if(MetricsRemoteConfigManager.getInstance().isFpsCustomEnable() && android.os.Build.VERSION.SDK_INT >= 16)
            samplers.add(metricsfpssampler);
        if(samplers.size() > 0)
        {
            samplingThread.startSampleTimer();
            MetricsActivityLifecycleManager.getInstance().register(this);
            MetricsActivityLifecycleManager.getInstance().registerAppMonitor(this);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        metricsfpssampler;
        throw metricsfpssampler;
    }

    public void changeToFragment(Object obj)
    {
        Object aobj[] = new Object[1];
        aobj[0] = obj;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "259578bf9e5ee399b12ac625b33687f0", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "259578bf9e5ee399b12ac625b33687f0");
            return;
        }
        if(obj != null && fpsSampler != null && samplingThread != null)
            samplingThread.sendMessage(8, new Object[] {
                obj
            });
    }

    public void enableRealTimeMonitor(boolean flag)
    {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }

    public void init(MetricsRemoteConfig metricsremoteconfig, boolean flag)
    {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.useAs(TypeTransformer.java:868)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:668)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }

    public void onActivityCreated(Activity activity)
    {
        Object aobj[] = new Object[1];
        aobj[0] = activity;
        activity = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, activity, false, "e8090705c151a33d9523120814b2c9d7", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, activity, false, "e8090705c151a33d9523120814b2c9d7");
            return;
        } else
        {
            return;
        }
    }

    public void onActivityPaused(Activity activity)
    {
        Object aobj[] = new Object[1];
        aobj[0] = activity;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "f13889e8035fda61f922e91ee7ea12c2", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "f13889e8035fda61f922e91ee7ea12c2");
            return;
        }
        if(samplingThread != null)
            samplingThread.sendMessage(4, new Object[] {
                activity
            });
    }

    public void onActivityResumed(Activity activity)
    {
        Object aobj[] = new Object[1];
        aobj[0] = activity;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "dc9162f771085b2e0e6bd2f6988677cc", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "dc9162f771085b2e0e6bd2f6988677cc");
            return;
        }
        if(samplingThread != null)
        {
            samplingThread.sendMessage(3, new Object[] {
                activity
            });
            samplingThread.startSampleOnce();
        }
    }

    public void onActivityStopped(Activity activity)
    {
    }

    public void onBackground()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "e4f57afb22bce36ac5e66a1cb7fe0dc8", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "e4f57afb22bce36ac5e66a1cb7fe0dc8");
            return;
        }
        if(samplingThread != null)
            samplingThread.stopSampleTimer();
        if(fpsSampler instanceof MetricsFpsSamplerImpl)
            ((MetricsFpsSamplerImpl)fpsSampler).reset();
    }

    public void onForeground()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "bdf7b3339c76c488c90665c540c181be", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "bdf7b3339c76c488c90665c540c181be");
            return;
        }
        if(samplingThread != null)
            samplingThread.startSampleTimer();
    }

    public transient void onSamplingEvent(int i, Object aobj[])
    {
        Object aobj1[] = new Object[2];
        aobj1[0] = Integer.valueOf(i);
        aobj1[1] = ((Object) (aobj));
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj1, this, changequickredirect, false, "c81fc2280f4c9ad0079b356db949bc19", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj1, this, changequickredirect, false, "c81fc2280f4c9ad0079b356db949bc19");
            return;
        }
        if(i == 1)
        {
            if(samplers != null && samplers.size() > 0)
            {
                for(aobj = samplers.iterator(); ((Iterator) (aobj)).hasNext(); ((MetricsSampler)((Iterator) (aobj)).next()).doSample());
                if(monitorImpl != null && samplers != null)
                {
                    aobj = samplers.iterator();
                    do
                    {
                        if(!((Iterator) (aobj)).hasNext())
                            break;
                        MetricsSampler metricssampler = (MetricsSampler)((Iterator) (aobj)).next();
                        if(metricssampler instanceof MetricsCpuSampler)
                            monitorImpl.onCpu(metricssampler.getRealTimeValue());
                        else
                        if(metricssampler instanceof MetricsFpsSampler)
                            monitorImpl.onFPS(metricssampler.getRealTimeValue());
                        else
                        if(metricssampler instanceof MetricsMemorySampler)
                            monitorImpl.onMemory(metricssampler.getRealTimeValue());
                    } while(true);
                    return;
                }
            }
        } else
        if(i == 2)
        {
            if(fpsSampler != null)
            {
                fpsSampler.doSample();
                return;
            }
        } else
        {
            Object obj1 = null;
            Object obj2 = null;
            Object obj3 = null;
            Object obj4 = null;
            Object obj = null;
            if(i == 3)
            {
                if(samplers != null && samplers.size() > 0)
                {
                    Activity activity = obj;
                    if(aobj != null)
                    {
                        activity = obj;
                        if(aobj.length == 1)
                        {
                            activity = obj;
                            if(aobj[0] instanceof Activity)
                                activity = (Activity)aobj[0];
                        }
                    }
                    MetricsSampler metricssampler1;
                    for(aobj = samplers.iterator(); ((Iterator) (aobj)).hasNext(); metricssampler1.doSample())
                    {
                        metricssampler1 = (MetricsSampler)((Iterator) (aobj)).next();
                        metricssampler1.pageEnter(activity);
                    }

                    return;
                }
            } else
            if(i == 4)
            {
                if(samplers != null && samplers.size() > 0)
                {
                    Activity activity1 = obj1;
                    if(aobj != null)
                    {
                        activity1 = obj1;
                        if(aobj.length == 1)
                        {
                            activity1 = obj1;
                            if(aobj[0] instanceof Activity)
                                activity1 = (Activity)aobj[0];
                        }
                    }
                    for(aobj = samplers.iterator(); ((Iterator) (aobj)).hasNext(); ((MetricsSampler)((Iterator) (aobj)).next()).pageExit(activity1));
                    return;
                }
            } else
            if(i == 5)
            {
                if(fpsSampler instanceof MetricsFpsSamplerImpl)
                {
                    Activity activity2 = obj2;
                    if(aobj != null)
                    {
                        activity2 = obj2;
                        if(aobj.length == 1)
                        {
                            activity2 = obj2;
                            if(aobj[0] instanceof Activity)
                                activity2 = (Activity)aobj[0];
                        }
                    }
                    if(activity2 != null && isFpsScrollEnable(activity2))
                        ((MetricsFpsSamplerImpl)fpsSampler).startCustomScrollFPS(activity2);
                    return;
                }
            } else
            if(i == 6)
            {
                if(fpsSampler instanceof MetricsFpsSamplerImpl)
                {
                    Activity activity3 = obj3;
                    if(aobj != null)
                    {
                        activity3 = obj3;
                        if(aobj.length == 1)
                        {
                            activity3 = obj3;
                            if(aobj[0] instanceof Activity)
                                activity3 = (Activity)aobj[0];
                        }
                    }
                    if(activity3 != null && isFpsScrollEnable(activity3))
                        ((MetricsFpsSamplerImpl)fpsSampler).stopCustomScrollFPS(activity3);
                    return;
                }
            } else
            if(i == 7)
            {
                if(fpsSampler instanceof MetricsFpsSamplerImpl)
                {
                    Activity activity4 = obj4;
                    if(aobj != null)
                    {
                        activity4 = obj4;
                        if(aobj.length == 1)
                        {
                            activity4 = obj4;
                            if(aobj[0] instanceof Activity)
                                activity4 = (Activity)aobj[0];
                        }
                    }
                    if(activity4 != null)
                        ((MetricsFpsSamplerImpl)fpsSampler).setScrollEntityCustom(activity4);
                    return;
                }
            } else
            if(i == 8 && aobj != null && aobj.length == 1 && aobj[0] != null)
            {
                aobj = ((Object []) (aobj[0]));
                if(fpsSampler != null)
                    fpsSampler.changeToFragment(((Object) (aobj)));
            }
        }
    }

    public void setScrollEntityCustom(Activity activity)
    {
        Object aobj[] = new Object[1];
        aobj[0] = activity;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "ab660fe6f3b4bbe228df278f1207e572", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "ab660fe6f3b4bbe228df278f1207e572");
            return;
        }
        if(isFpsScrollEnable(activity) && samplingThread != null)
            samplingThread.sendMessage(7, new Object[] {
                activity
            });
    }

    public void start(MetricsRemoteConfig metricsremoteconfig, boolean flag)
    {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }

    public void startCustomFPS(String s)
    {
        Object aobj[] = new Object[1];
        aobj[0] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "104d66e0a41131347c61c286f9cac260", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "104d66e0a41131347c61c286f9cac260");
            return;
        }
        if(isFpsCustomEnable(s) && fpsSampler != null)
            fpsSampler.startCustomRecordFps(s);
    }

    public void startCustomScrollFPS(Activity activity)
    {
        Object aobj[] = new Object[1];
        aobj[0] = activity;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "f2aced45725657be3c8b49bc6f610bfb", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "f2aced45725657be3c8b49bc6f610bfb");
            return;
        }
        if(isFpsScrollEnable(activity) && samplingThread != null)
            samplingThread.sendMessage(5, new Object[] {
                activity
            });
    }

    public void startRNCustomFPS(String s)
    {
        Object aobj[] = new Object[1];
        aobj[0] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "402cba07d912845b3e581b3edfdaa710", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "402cba07d912845b3e581b3edfdaa710");
            return;
        }
        if(isFpsCustomEnable(s) && rnSampler != null)
            rnSampler.startCustomRecordFps(s);
    }

    public void startSample()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "10588237d536b26188dc4b27f9b5fb28", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "10588237d536b26188dc4b27f9b5fb28");
            return;
        }
        if(samplingThread != null)
            samplingThread.startSampleTimer();
    }

    public void stopCustomFPS(String s)
    {
        Object aobj[] = new Object[1];
        aobj[0] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "33c31479c9209a847a7533f593745d57", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "33c31479c9209a847a7533f593745d57");
            return;
        } else
        {
            stopCustomFPS(s, null);
            return;
        }
    }

    public void stopCustomFPS(String s, Map map)
    {
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = map;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "ee585cfcbbeff6ed296e971a54bb6715", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "ee585cfcbbeff6ed296e971a54bb6715");
            return;
        }
        if(isFpsCustomEnable(s) && fpsSampler != null)
            fpsSampler.stopCustomRecordFps(s, map);
    }

    public void stopCustomScrollFPS(Activity activity)
    {
        Object aobj[] = new Object[1];
        aobj[0] = activity;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "155a9ac4d0a2f7c313163e5acfad0f97", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "155a9ac4d0a2f7c313163e5acfad0f97");
            return;
        }
        if(isFpsScrollEnable(activity) && samplingThread != null)
            samplingThread.sendMessage(6, new Object[] {
                activity
            });
    }

    public void stopRNCustomFPS(String s)
    {
        Object aobj[] = new Object[1];
        aobj[0] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "ae4c619a211809e2d58c7f5b393a9eb8", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "ae4c619a211809e2d58c7f5b393a9eb8");
            return;
        }
        if(isFpsCustomEnable(s) && rnSampler != null)
            rnSampler.stopCustomRecordFps(s, null);
    }

    public void stopSample()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "919b23ae34b47b5eee99ccf29cadedbc", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "919b23ae34b47b5eee99ccf29cadedbc");
            return;
        }
        if(samplingThread != null)
            samplingThread.stopSampleTimer();
    }

    public static final int SAMPLE_TIME_INTERVAL = 1;
    private static final boolean USE_FRAME_METRICS_API = false;
    public static ChangeQuickRedirect changeQuickRedirect;
    private static volatile MetricSampleManager sInstance;
    private MetricsRemoteConfig config;
    private MetricsCpuSampler cpuSampler;
    private MetricsFpsSampler fpsSampler;
    private boolean initialized;
    private MetricsMemorySampler memorySampler;
    private RealTimeMonitor monitorImpl;
    private boolean realTimeMonitor;
    private MetricsFpsSampler rnSampler;
    private Collection samplers;
    private SamplingThread samplingThread;



/*
    public static RealTimeMonitor access$002(MetricSampleManager metricsamplemanager, RealTimeMonitor realtimemonitor)
    {
        metricsamplemanager.monitorImpl = realtimemonitor;
        return realtimemonitor;
    }

*/
}
