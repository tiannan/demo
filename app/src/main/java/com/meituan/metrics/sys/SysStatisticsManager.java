// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.sys;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.meituan.android.common.babel.Babel;
import com.meituan.metrics.Environment;
import com.meituan.metrics.Metrics;
import com.meituan.metrics.util.LogUtil;
import com.meituan.metrics.util.NetUtils;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import java.util.*;
import org.json.JSONObject;

// Referenced classes of package com.meituan.metrics.sys:
//            CommonStatus, MemoryStatus, CpuStatus, StorageStatus

public class SysStatisticsManager
{

    public SysStatisticsManager()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "12d3f04b0a2e74a683fa3f5f0564472d", 0x6000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "12d3f04b0a2e74a683fa3f5f0564472d");
            return;
        } else
        {
            isReported = false;
            status = new CommonStatus();
            memory = new MemoryStatus();
            cpu = new CpuStatus();
            return;
        }
    }

    private String convert(String as[])
    {
        Object aobj[] = new Object[1];
        int i = 0;
        aobj[0] = as;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "4601398c18a8592a20f46f60f4bac2db", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "4601398c18a8592a20f46f60f4bac2db");
        StringBuilder stringbuilder = new StringBuilder(64);
        for(int j = as.length; i < j; i++)
        {
            stringbuilder.append(as[i]);
            stringbuilder.append(",");
        }

        stringbuilder.deleteCharAt(stringbuilder.length() - 1);
        return stringbuilder.toString();
    }

    public static SysStatisticsManager getInstance()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "f04871597b78937fa73e680c156081d5", 0x4000000000000000L))
            return (SysStatisticsManager)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "f04871597b78937fa73e680c156081d5");
        if(mInstance != null)
            break MISSING_BLOCK_LABEL_74;
        com/meituan/metrics/sys/SysStatisticsManager;
        JVM INSTR monitorenter ;
        if(mInstance == null)
            mInstance = new SysStatisticsManager();
        com/meituan/metrics/sys/SysStatisticsManager;
        JVM INSTR monitorexit ;
        break MISSING_BLOCK_LABEL_74;
        Exception exception;
        exception;
        com/meituan/metrics/sys/SysStatisticsManager;
        JVM INSTR monitorexit ;
        throw exception;
        return mInstance;
    }

    private boolean isMainProcess(Context context)
    {
        Object aobj[] = new Object[1];
        aobj[0] = context;
        Object obj1 = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, ((ChangeQuickRedirect) (obj1)), false, "0e1c189da8f4370897ef066c97e73d81", 0x4000000000000000L))
            return ((Boolean)PatchProxy.accessDispatch(aobj, this, ((ChangeQuickRedirect) (obj1)), false, "0e1c189da8f4370897ef066c97e73d81")).booleanValue();
        int i;
        boolean flag;
        Object obj;
        try
        {
            obj = (ActivityManager)context.getSystemService("activity");
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            return false;
        }
        if(obj == null)
            return false;
        obj = ((ActivityManager) (obj)).getRunningAppProcesses();
        if(obj == null)
            return false;
        context = context.getPackageName();
        i = Process.myPid();
        obj = ((List) (obj)).iterator();
_L1:
        do
        {
            if(!((Iterator) (obj)).hasNext())
                break MISSING_BLOCK_LABEL_148;
            obj1 = (android.app.ActivityManager.RunningAppProcessInfo)((Iterator) (obj)).next();
        } while(((android.app.ActivityManager.RunningAppProcessInfo) (obj1)).pid != i);
        flag = context.equals(((android.app.ActivityManager.RunningAppProcessInfo) (obj1)).processName);
        if(flag)
            return true;
          goto _L1
        return false;
    }

    private void reportSysData(Context context)
    {
        this;
        JVM INSTR monitorenter ;
        Object aobj[] = new Object[1];
        aobj[0] = context;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(!PatchProxy.isSupport(aobj, this, changequickredirect, false, "3c003b7fdb61e4668c6de489f3ecda19", 0x4000000000000000L))
            break MISSING_BLOCK_LABEL_50;
        PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "3c003b7fdb61e4668c6de489f3ecda19");
        this;
        JVM INSTR monitorexit ;
        return;
        Environment environment = Metrics.getEnvironment();
        if(context == null || environment == null) goto _L2; else goto _L1
_L1:
        if(!TextUtils.isEmpty(environment.getUuid())) goto _L3; else goto _L2
_L3:
        if(!isReported)
            break MISSING_BLOCK_LABEL_93;
        LogUtil.d("\u7CFB\u7EDF\u53C2\u6570\u5DF2\u4E0A\u62A5\u6210\u529F \u4E0D\u91CD\u590D\u4E0A\u62A5");
        this;
        JVM INSTR monitorexit ;
        return;
        com.meituan.android.common.kitefly.Log.Builder builder;
        HashMap hashmap;
        builder = new com.meituan.android.common.kitefly.Log.Builder("env");
        builder.tag("env");
        builder.reportChannel("fe_perf_report");
        builder.ts(System.currentTimeMillis());
        hashmap = new HashMap();
        hashmap.put("cpu_core_nums", Integer.valueOf(cpu.queryCpuCores()));
        hashmap.put("cpu_max_freq", cpu.queryCpuMaxFreq());
        hashmap.put("cpu_min_freq", cpu.queryCpuMinFreq());
        hashmap.put("memory_per_app", memory.queryAppMemory(context));
        hashmap.put("memory_per_phone", memory.queryPhoneMemory(context));
        hashmap.put("screen_resolution_width", status.querySrceenResolutionW());
        hashmap.put("screen_resolution_height", status.querySrceenResolutionH());
        hashmap.put("screen_density", status.queryDensity());
        hashmap.put("build_manu", status.queryBuildManufacturer());
        hashmap.put("build_abi", convert(status.queryBuildAbi()));
        hashmap.put("build_brand", status.queryBuildBrand());
        hashmap.put("build_root", Boolean.valueOf(status.isRoot()));
        hashmap.put("build_arch", Integer.valueOf(status.queryBuildArch()));
        hashmap.put("network", NetUtils.obtainNetworkType(context));
        context = null;
        Object obj;
        long l = StorageStatus.getInternalTotalStorageSize();
        long l1 = StorageStatus.getInternalAvailableStorageSize();
        hashmap.put("storage_capacity", Long.valueOf(l));
        obj = new JSONObject();
        ((JSONObject) (obj)).put("storage_free", l1);
        obj = ((JSONObject) (obj)).toString();
        context = ((Context) (obj));
_L5:
        StringBuilder stringbuilder = new StringBuilder("\u4E0A\u62A5\u7CFB\u7EDF\u4FE1\u606F");
        stringbuilder.append(hashmap.toString());
        LogUtil.d(stringbuilder.toString());
        builder.optional(hashmap);
        if(!TextUtils.isEmpty(context))
            builder.details(context);
        if(!TextUtils.isEmpty(environment.getToken()))
            builder.token(environment.getToken());
        Babel.log(builder.build());
        isReported = true;
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        LogUtil.d("\u6CA1\u6709uuid \u4E0D\u4E0A\u62A5\u7CFB\u7EDF\u53C2\u6570");
        this;
        JVM INSTR monitorexit ;
        return;
        context;
        throw context;
        Throwable throwable;
        throwable;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public boolean isReported()
    {
        return isReported;
    }

    public void reportSysDataOnce(Context context)
    {
        Object aobj[] = new Object[1];
        aobj[0] = context;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "e73972ca47f1790a11f03fb2f11f4426", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "e73972ca47f1790a11f03fb2f11f4426");
            return;
        }
        if(context == null)
            return;
        if(!isMainProcess(context))
            return;
        Context context1 = context.getApplicationContext();
        if(context1 != null)
            context = context1;
        reportSysData(context);
    }

    public static ChangeQuickRedirect changeQuickRedirect;
    private static volatile SysStatisticsManager mInstance;
    private final CpuStatus cpu;
    private volatile boolean isReported;
    private final MemoryStatus memory;
    private final CommonStatus status;
}
