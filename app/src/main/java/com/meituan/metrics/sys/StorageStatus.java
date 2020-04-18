// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.sys;

import android.os.Environment;
import android.os.StatFs;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import java.io.File;

public class StorageStatus
{

    public StorageStatus()
    {
    }

    private static long getAvailableBlocks(StatFs statfs)
    {
        Object aobj[] = new Object[1];
        aobj[0] = statfs;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "78e8dd01d1f1f0e87bdefdd5fdcae878", 0x4000000000000000L))
            return ((Long)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "78e8dd01d1f1f0e87bdefdd5fdcae878")).longValue();
        if(android.os.Build.VERSION.SDK_INT >= 18)
            return statfs.getAvailableBlocksLong();
        else
            return (long)statfs.getAvailableBlocks();
    }

    private static long getBlockCount(StatFs statfs)
    {
        Object aobj[] = new Object[1];
        aobj[0] = statfs;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "f59a82fd3bec8199d726da46c7dd69db", 0x4000000000000000L))
            return ((Long)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "f59a82fd3bec8199d726da46c7dd69db")).longValue();
        if(android.os.Build.VERSION.SDK_INT >= 18)
            return statfs.getBlockCountLong();
        else
            return (long)statfs.getBlockCount();
    }

    private static long getBlockSize(StatFs statfs)
    {
        Object aobj[] = new Object[1];
        aobj[0] = statfs;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "f7705ff20ebc670b92abd7b6894902a0", 0x4000000000000000L))
            return ((Long)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "f7705ff20ebc670b92abd7b6894902a0")).longValue();
        if(android.os.Build.VERSION.SDK_INT >= 18)
            return statfs.getBlockSizeLong();
        else
            return (long)statfs.getBlockSize();
    }

    public static long getInternalAvailableStorageSize()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "07ddd76a741a0944f01e409584ba3f15", 0x4000000000000000L))
        {
            return ((Long)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "07ddd76a741a0944f01e409584ba3f15")).longValue();
        } else
        {
            StatFs statfs = new StatFs(Environment.getDataDirectory().getPath());
            long l = getBlockSize(statfs);
            return getAvailableBlocks(statfs) * l;
        }
    }

    public static long getInternalTotalStorageSize()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, null, changequickredirect, true, "2bcff76165aa588a65053a18eb4e88d6", 0x4000000000000000L))
        {
            return ((Long)PatchProxy.accessDispatch(aobj, null, changequickredirect, true, "2bcff76165aa588a65053a18eb4e88d6")).longValue();
        } else
        {
            StatFs statfs = new StatFs(Environment.getDataDirectory().getPath());
            long l = getBlockSize(statfs);
            return getBlockCount(statfs) * l;
        }
    }

    public static ChangeQuickRedirect changeQuickRedirect;
}
