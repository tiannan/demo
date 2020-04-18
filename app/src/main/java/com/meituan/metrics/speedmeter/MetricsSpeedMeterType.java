// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.speedmeter;

import com.meituan.robust.ChangeQuickRedirect;
import java.lang.annotation.Annotation;

public class MetricsSpeedMeterType
{
    public static interface MetricsSpeedMeter
        extends Annotation
    {
    }


    public MetricsSpeedMeterType()
    {
    }

    public static final int CUSTOM = 3;
    public static final int LAUNCH = 1;
    public static final int PAGE = 2;
    public static ChangeQuickRedirect changeQuickRedirect;
}
