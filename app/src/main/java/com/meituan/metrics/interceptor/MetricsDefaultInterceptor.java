// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.interceptor;

import com.meituan.metrics.laggy.LaggyEvent;
import com.meituan.metrics.model.AbstractEvent;
import com.meituan.metrics.sampler.cpu.CpuEvent;
import com.meituan.metrics.sampler.fps.FpsEvent;
import com.meituan.metrics.sampler.memory.MemoryEvent;
import com.meituan.metrics.speedmeter.SpeedMeterEvent;
import com.meituan.metrics.traffic.TrafficEvent;



public class MetricsDefaultInterceptor
    implements MetricsInterceptor
{

    public MetricsDefaultInterceptor()
    {
    }

    public void onNewCpuEvent(CpuEvent cpuevent)
    {
    }

    public void onNewEvent(AbstractEvent abstractevent)
    {

        if(abstractevent instanceof CpuEvent)
        {
            onNewCpuEvent((CpuEvent)abstractevent);
            return;
        }
        if(abstractevent instanceof MemoryEvent)
        {
            onNewMemoryEvent((MemoryEvent)abstractevent);
            return;
        }
        if(abstractevent instanceof FpsEvent)
        {
            onNewFpsEvent((FpsEvent)abstractevent);
            return;
        }
        if(abstractevent instanceof SpeedMeterEvent)
        {
            onNewSpeedMeterEvent((SpeedMeterEvent)abstractevent);
            return;
        }
        if(abstractevent instanceof TrafficEvent)
        {
            onNewTrafficEvent((TrafficEvent)abstractevent);
            return;
        }
        if(abstractevent instanceof LaggyEvent)
            onNewLaggyEvent((LaggyEvent)abstractevent);
    }

    public void onNewFpsEvent(FpsEvent fpsevent)
    {
    }

    public void onNewLaggyEvent(LaggyEvent laggyevent)
    {
    }

    public void onNewMemoryEvent(MemoryEvent memoryevent)
    {
    }

    public void onNewSpeedMeterEvent(SpeedMeterEvent speedmeterevent)
    {
    }

    public void onNewTrafficEvent(TrafficEvent trafficevent)
    {
    }

    public void onReportCpuEvent(CpuEvent cpuevent)
    {
    }

    public void onReportEvent(AbstractEvent abstractevent)
    {

        if(abstractevent instanceof CpuEvent)
        {
            onReportCpuEvent((CpuEvent)abstractevent);
            return;
        }
        if(abstractevent instanceof MemoryEvent)
        {
            onReportMemoryEvent((MemoryEvent)abstractevent);
            return;
        }
        if(abstractevent instanceof FpsEvent)
        {
            onReportFpsEvent((FpsEvent)abstractevent);
            return;
        }
        if(abstractevent instanceof SpeedMeterEvent)
        {
            onReportSpeedMeterEvent((SpeedMeterEvent)abstractevent);
            return;
        }
        if(abstractevent instanceof TrafficEvent)
        {
            onReportTrafficEvent((TrafficEvent)abstractevent);
            return;
        }
        if(abstractevent instanceof LaggyEvent)
            onReportLaggyEvent((LaggyEvent)abstractevent);
    }

    public void onReportFpsEvent(FpsEvent fpsevent)
    {
    }

    public void onReportLaggyEvent(LaggyEvent laggyevent)
    {
    }

    public void onReportMemoryEvent(MemoryEvent memoryevent)
    {
    }

    public void onReportSpeedMeterEvent(SpeedMeterEvent speedmeterevent)
    {
    }

    public void onReportTrafficEvent(TrafficEvent trafficevent)
    {
    }

}
