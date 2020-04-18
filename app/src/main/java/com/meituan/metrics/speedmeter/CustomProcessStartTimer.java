// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.speedmeter;

import android.os.SystemClock;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;

public class CustomProcessStartTimer
{

    public CustomProcessStartTimer()
    {
    }

    public static void initProcessStartTime()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "20a2aa75526b9dfd39b4851e35a163f3", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "20a2aa75526b9dfd39b4851e35a163f3");
            return;
        } else
        {
            PROCESS_START_ELAPSED_TIME = SystemClock.elapsedRealtime();
            return;
        }
    }

    public static void setProcessStartTime(long l)
    {
        PROCESS_START_ELAPSED_TIME = l;
    }

    public static long PROCESS_START_ELAPSED_TIME;
    public static ChangeQuickRedirect changeQuickRedirect;
}
