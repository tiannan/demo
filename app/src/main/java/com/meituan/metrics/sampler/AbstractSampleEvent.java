// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.sampler;

import com.meituan.metrics.model.AbstractEvent;
import com.meituan.robust.ChangeQuickRedirect;
import java.text.DecimalFormat;

public abstract class AbstractSampleEvent extends AbstractEvent
{

    public AbstractSampleEvent()
    {
    }

    public String getEventType()
    {
        return "default";
    }

    public String getLocalEventType()
    {
        return "default";
    }

    public static ChangeQuickRedirect changeQuickRedirect;
    protected static final DecimalFormat df = new DecimalFormat("#.##");

}
