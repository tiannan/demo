// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.sys;

import android.content.Context;
import android.text.TextUtils;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import java.io.*;
import java.math.BigDecimal;

public class MemoryStatus
{

    public MemoryStatus()
    {
    }

    private String obtainAppMemory(Context context)
    {
        Object aobj[] = new Object[1];
        aobj[0] = context;
        context = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, context, false, "27ec0da7741353cebaadbf50bff15e06", 0x4000000000000000L))
        {
            return (String)PatchProxy.accessDispatch(aobj, this, context, false, "27ec0da7741353cebaadbf50bff15e06");
        } else
        {
            context = new StringBuilder();
            context.append(Runtime.getRuntime().maxMemory());
            return context.toString();
        }
    }

    private String obtainPhoneMemory(Context context)
    {
        Object aobj[] = new Object[1];
        aobj[0] = context;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "1a0c9b368a26e268e2f09f433226e306", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "1a0c9b368a26e268e2f09f433226e306");
        else
            return getTotalMemory(context);
    }

    public String getTotalMemory(Context context)
    {
        Object aobj[] = new Object[1];
        int i = 0;
        aobj[0] = context;
        context = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, context, false, "12c1f663fa529f8a5fb304b4c5e9ac27", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, context, false, "12c1f663fa529f8a5fb304b4c5e9ac27");
        int j;
        BufferedReader bufferedreader;
        String as[];
        BigDecimal bigdecimal;
        String s;
        StringBuilder stringbuilder;
        try
        {
            context = new FileReader("/proc/meminfo");
            bufferedreader = new BufferedReader(context, 8192);
            as = bufferedreader.readLine().split("\\s+");
            j = as.length;
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            return "N/A";
        }
        if(i >= j)
            break; /* Loop/switch isn't completed */
        s = as[i];
        stringbuilder = new StringBuilder();
        stringbuilder.append(s);
        stringbuilder.append("\t");
        i++;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_86;
_L1:
        if(TextUtils.isEmpty(as[1]))
            return "N/A";
        bigdecimal = (new BigDecimal(as[1])).multiply(new BigDecimal(1024));
        bufferedreader.close();
        context.close();
        context = new StringBuilder();
        context.append(bigdecimal.longValue());
        return context.toString();
    }

    public String queryAppMemory(Context context)
    {
        Object aobj[] = new Object[1];
        aobj[0] = context;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "498dac6c974e76922b37caa2c9361abc", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "498dac6c974e76922b37caa2c9361abc");
        if(TextUtils.isEmpty(memoryPerApp))
            memoryPerApp = obtainAppMemory(context);
        return memoryPerApp;
    }

    public String queryPhoneMemory(Context context)
    {
        Object aobj[] = new Object[1];
        aobj[0] = context;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "2fab5f470e7725315d7356d6988dca2f", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "2fab5f470e7725315d7356d6988dca2f");
        if(TextUtils.isEmpty(memoryPerPhone))
            memoryPerPhone = obtainPhoneMemory(context);
        return memoryPerPhone;
    }

    public static ChangeQuickRedirect changeQuickRedirect;
    private static String memoryPerApp;
    private static String memoryPerPhone;
}
