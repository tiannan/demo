// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.sys;

import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import java.io.*;

public class CommonStatus
{

    public CommonStatus()
    {
    }

    private boolean isExecutable(String s)
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object aobj[] = new Object[1];
        aobj[0] = s;
        obj1 = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, ((ChangeQuickRedirect) (obj1)), false, "4e1845cbd50a4a8702cfc2c3b47950a4", 0x4000000000000000L))
            return ((Boolean)PatchProxy.accessDispatch(aobj, this, ((ChangeQuickRedirect) (obj1)), false, "4e1845cbd50a4a8702cfc2c3b47950a4")).booleanValue();
        obj1 = null;
        obj2 = null;
        obj = obj2;
        Runtime runtime = Runtime.getRuntime();
        obj = obj2;
        StringBuilder stringbuilder = new StringBuilder("ls -l ");
        obj = obj2;
        stringbuilder.append(s);
        obj = obj2;
        s = runtime.exec(stringbuilder.toString());
        char c;
        try
        {
            obj = (new BufferedReader(new InputStreamReader(s.getInputStream()))).readLine();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj1)
        {
            break MISSING_BLOCK_LABEL_197;
        }
        if(obj == null)
            break MISSING_BLOCK_LABEL_161;
        if(((String) (obj)).length() < 4)
            break MISSING_BLOCK_LABEL_161;
        c = ((String) (obj)).charAt(3);
        if(c == 's' || c == 'x')
        {
            if(s != null)
                s.destroy();
            return true;
        }
        if(s != null)
        {
            s.destroy();
            return false;
        }
        break MISSING_BLOCK_LABEL_212;
        obj;
        break MISSING_BLOCK_LABEL_214;
        obj1;
        s = ((String) (obj));
        obj = obj1;
        break MISSING_BLOCK_LABEL_214;
        IOException ioexception;
        ioexception;
        s = ((String) (obj1));
        obj1 = ioexception;
        ioexception = s;
        ((IOException) (obj1)).printStackTrace();
        if(s != null)
            s.destroy();
        return false;
        if(s != null)
            s.destroy();
        throw ioexception;
    }

    public boolean isRoot()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "67232fa11308c691f5224a52e1f9f66e", 0x4000000000000000L))
            return ((Boolean)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "67232fa11308c691f5224a52e1f9f66e")).booleanValue();
        if((new File("/system/bin/su")).exists() && isExecutable("/system/bin/su"))
            return true;
        return (new File("/system/xbin/su")).exists() && isExecutable("/system/xbin/su");
    }

    public String[] queryBuildAbi()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "d663cb0b3b46524c586261f101f02d2a", 0x4000000000000000L))
            return (String[])PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "d663cb0b3b46524c586261f101f02d2a");
        else
            return (new String[] {
                Build.CPU_ABI, Build.CPU_ABI2
            });
    }

    public int queryBuildArch()
    {
        return android.os.Build.VERSION.SDK_INT < 21 || Build.SUPPORTED_64_BIT_ABIS.length <= 0 ? 32 : 64;
    }

    public String queryBuildBrand()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "56b6a808296c28fe4f146583c2ff3267", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "56b6a808296c28fe4f146583c2ff3267");
        if(TextUtils.isEmpty(build_brand))
            build_brand = Build.BRAND;
        return build_brand;
    }

    public String queryBuildManufacturer()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "b4506cb0a839186c14b8986a5274d0e2", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "b4506cb0a839186c14b8986a5274d0e2");
        if(TextUtils.isEmpty(build_manufacturer))
            build_manufacturer = Build.MANUFACTURER;
        return build_manufacturer;
    }

    public String queryBuildModel()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "448fdc397cd5d115124cf275821c0002", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "448fdc397cd5d115124cf275821c0002");
        if(TextUtils.isEmpty(build_model))
            build_model = Build.MODEL;
        return build_model;
    }

    public String queryDensity()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "5127ce5adb5e03974a1e7a1477ce7f46", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "5127ce5adb5e03974a1e7a1477ce7f46");
        if(TextUtils.isEmpty(screen_density))
        {
            DisplayMetrics displaymetrics = Resources.getSystem().getDisplayMetrics();
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(displaymetrics.density);
            screen_density = stringbuilder.toString();
        }
        return screen_density;
    }

    public String querySrceenResolutionH()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "05391b6debe90ae50538226aacca2708", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "05391b6debe90ae50538226aacca2708");
        if(TextUtils.isEmpty(screen_resolution_height))
        {
            DisplayMetrics displaymetrics = Resources.getSystem().getDisplayMetrics();
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(displaymetrics.heightPixels);
            screen_resolution_height = stringbuilder.toString();
        }
        return screen_resolution_height;
    }

    public String querySrceenResolutionW()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "6ea71fd73500c635b1a0ed9342f466c2", 0x4000000000000000L))
            return (String)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "6ea71fd73500c635b1a0ed9342f466c2");
        if(TextUtils.isEmpty(screen_resolution_width))
        {
            DisplayMetrics displaymetrics = Resources.getSystem().getDisplayMetrics();
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(displaymetrics.widthPixels);
            screen_resolution_width = stringbuilder.toString();
        }
        return screen_resolution_width;
    }

    public static ChangeQuickRedirect changeQuickRedirect;
    private String build_brand;
    private String build_manufacturer;
    private String build_model;
    private String screen_density;
    private String screen_resolution_height;
    private String screen_resolution_width;
}
