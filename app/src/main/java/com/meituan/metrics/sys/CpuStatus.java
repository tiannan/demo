// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.sys;

import android.text.TextUtils;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import java.io.*;
import java.util.regex.Pattern;

public class CpuStatus
{

    public CpuStatus()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "58cb29b340ffa6705b0aa7fd657674f0", 0x6000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "58cb29b340ffa6705b0aa7fd657674f0");
            return;
        } else
        {
            isDebug = false;
            return;
        }
    }

    public static void main(String args[])
    {
        Object aobj[] = new Object[1];
        aobj[0] = args;
        args = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, args, true, "818bdae6ecac1c94a273c38d38c750a7", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, null, args, true, "818bdae6ecac1c94a273c38d38c750a7");
            return;
        } else
        {
            args = new CpuStatus();
            PrintStream printstream = System.out;
            StringBuilder stringbuilder = new StringBuilder(" c.queryCpuCores():");
            stringbuilder.append(args.queryCpuCores());
            printstream.println(stringbuilder.toString());
            printstream = System.out;
            stringbuilder = new StringBuilder(" c.queryCpuMinFreq():");
            stringbuilder.append(args.queryCpuMinFreq());
            printstream.println(stringbuilder.toString());
            printstream = System.out;
            stringbuilder = new StringBuilder(" c.queryCpuMaxFreq():");
            stringbuilder.append(args.queryCpuMaxFreq());
            printstream.println(stringbuilder.toString());
            return;
        }
    }

    private int obtainCoreNum()
    {
        Exception exception;
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "a38887a0336a3e15f0a20dae77b43955", 0x4000000000000000L))
            return ((Integer)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "a38887a0336a3e15f0a20dae77b43955")).intValue();
        int i;
        try
        {
            File afile[] = (new File("/sys/devices/system/cpu/")).listFiles(new FileFilter() {

                public boolean accept(File file)
                {
                    Object aobj1[] = new Object[1];
                    aobj1[0] = file;
                    ChangeQuickRedirect changequickredirect1 = changeQuickRedirect;
                    if(PatchProxy.isSupport(aobj1, this, changequickredirect1, false, "6881cb47d783b36c6d22038959627cf9", 0x4000000000000000L))
                        return ((Boolean)PatchProxy.accessDispatch(aobj1, this, changequickredirect1, false, "6881cb47d783b36c6d22038959627cf9")).booleanValue();
                    return Pattern.matches("cpu[0-9]", file.getName());
                }

                public static ChangeQuickRedirect changeQuickRedirect;
                public final CpuStatus this$0;

            public 
            {
                this$0 = CpuStatus.this;
                super();
            }
            }
);
            if(isDebug)
                (new StringBuilder("CPU Count: ")).append(afile.length);
            i = afile.length;
        }
        // Misplaced declaration of an exception variable
        catch(Exception exception)
        {
            return 1;
        }
        return i;
    }

    private String obtainMaxFreq()
    {
        int i;
        long l1;
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "ba09c2148519208cf9ec5764d123c020", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "ba09c2148519208cf9ec5764d123c020");
        l1 = 0L;
        i = 0;
_L1:
        Object obj;
        String s;
        if(i >= queryCpuCores())
            break MISSING_BLOCK_LABEL_179;
        obj = new StringBuilder("/sys/devices/system/cpu/cpu");
        ((StringBuilder) (obj)).append(i);
        ((StringBuilder) (obj)).append("/cpufreq/cpuinfo_max_freq");
        obj = new BufferedReader(new InputStreamReader((new ProcessBuilder(new String[] {
            "/system/bin/cat", ((StringBuilder) (obj)).toString()
        })).start().getInputStream()));
        s = ((BufferedReader) (obj)).readLine();
        long l;
        try
        {
            l = Long.valueOf(s).longValue();
        }
        // Misplaced declaration of an exception variable
        catch(NumberFormatException numberformatexception)
        {
            l = 0L;
        }
        long l2 = l1;
        if(l > l1)
            l2 = l;
        try
        {
            ((BufferedReader) (obj)).close();
        }
        // Misplaced declaration of an exception variable
        catch(IOException ioexception)
        {
            return "N/A";
        }
        i++;
        l1 = l2;
          goto _L1
        IOException ioexception;
        NumberFormatException numberformatexception;
        obj = new StringBuilder();
        ((StringBuilder) (obj)).append(l1);
        return ((StringBuilder) (obj)).toString();
    }

    private String obtainMinFreq()
    {
        int i;
        long l;
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "f0bd5d9f26865871dcf63880a7c8e180", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "f0bd5d9f26865871dcf63880a7c8e180");
        l = 0x7fffffffffffffffL;
        i = 0;
_L2:
        boolean flag;
        Object obj;
        String s;
        if(i >= queryCpuCores())
            break; /* Loop/switch isn't completed */
        obj = new StringBuilder("/sys/devices/system/cpu/cpu");
        ((StringBuilder) (obj)).append(i);
        ((StringBuilder) (obj)).append("/cpufreq/cpuinfo_min_freq");
        obj = new BufferedReader(new InputStreamReader((new ProcessBuilder(new String[] {
            "/system/bin/cat", ((StringBuilder) (obj)).toString()
        })).start().getInputStream()));
        s = ((BufferedReader) (obj)).readLine();
        flag = TextUtils.isEmpty(s);
        long l2 = l;
        if(flag)
            break MISSING_BLOCK_LABEL_186;
        long l1;
        try
        {
            l1 = Long.valueOf(s).longValue();
        }
        // Misplaced declaration of an exception variable
        catch(NumberFormatException numberformatexception)
        {
            l1 = 0L;
        }
        // Misplaced declaration of an exception variable
        catch(IOException ioexception)
        {
            return "N/A";
        }
        l2 = l;
        if(l1 < l)
            l2 = l1;
        ((BufferedReader) (obj)).close();
        i++;
        l = l2;
        if(true) goto _L2; else goto _L1
_L1:
        IOException ioexception;
        NumberFormatException numberformatexception;
        if(l == 0x7fffffffffffffffL)
        {
            return "N/A";
        } else
        {
            obj = new StringBuilder();
            ((StringBuilder) (obj)).append(l);
            return ((StringBuilder) (obj)).toString();
        }
    }

    public int queryCpuCores()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "9a032995753204ab04a7f70bbcbc855c", 0x4000000000000000L))
            return ((Integer)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "9a032995753204ab04a7f70bbcbc855c")).intValue();
        if(cpuCoreNums == -1)
            cpuCoreNums = obtainCoreNum();
        return cpuCoreNums;
    }

    public String queryCpuMaxFreq()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "1c577d64af5eaaf372a1ff3337828783", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "1c577d64af5eaaf372a1ff3337828783");
        if(TextUtils.isEmpty(cpuMaxFreq))
            cpuMaxFreq = obtainMaxFreq();
        return cpuMaxFreq;
    }

    public String queryCpuMinFreq()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "ddf967b2f5f9ecc0573908446ba3babe", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "ddf967b2f5f9ecc0573908446ba3babe");
        if(TextUtils.isEmpty(cpuMinFreq))
            cpuMinFreq = obtainMinFreq();
        return cpuMinFreq;
    }

    private static final String TAG = "CPUSTATUS";
    public static ChangeQuickRedirect changeQuickRedirect;
    private static int cpuCoreNums = -1;
    private static String cpuMaxFreq = "";
    private static String cpuMinFreq = "";
    private boolean isDebug;

}
