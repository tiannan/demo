// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.speedmeter;

import com.meituan.metrics.sampler.AbstractSampleEvent;
import com.meituan.metrics.util.JSONUtils;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import java.util.HashMap;
import java.util.Map;
import org.json.*;

public class SpeedMeterEvent extends AbstractSampleEvent
{

    public SpeedMeterEvent(String s, long l, int i, String s1)
    {
        Object aobj[] = new Object[4];
        aobj[0] = s;
        aobj[1] = new Long(l);
        aobj[2] = Integer.valueOf(i);
        aobj[3] = s1;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "0c993c8109ee2c2085059289b7e88da5", 0x6000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "0c993c8109ee2c2085059289b7e88da5");
            return;
        } else
        {
            step = s;
            duration = l;
            type = i;
            id = s1;
            return;
        }
    }

    public SpeedMeterEvent(String s, long l, int i, String s1, int j)
    {
        this(s, l, i, s1);
        Object aobj[] = new Object[5];
        aobj[0] = s;
        aobj[1] = new Long(l);
        aobj[2] = Integer.valueOf(i);
        aobj[3] = s1;
        aobj[4] = Integer.valueOf(j);
        s = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, s, false, "4bc5f02b4a4e85edd190c2f65794f8d8", 0x6000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, s, false, "4bc5f02b4a4e85edd190c2f65794f8d8");
            return;
        } else
        {
            configFrom = j;
            return;
        }
    }

    public SpeedMeterEvent(String s, long l, Map map, int i, String s1)
    {
        this(s, l, i, s1);
        Object aobj[] = new Object[5];
        aobj[0] = s;
        aobj[1] = new Long(l);
        aobj[2] = map;
        aobj[3] = Integer.valueOf(i);
        aobj[4] = s1;
        s1 = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, s1, false, "f8fa684b1d74801fcf40dc86c24e1ebb", 0x6000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, s1, false, "f8fa684b1d74801fcf40dc86c24e1ebb");
            return;
        } else
        {
            steps = new HashMap();
            steps.put(s, Long.valueOf(l));
            steps.putAll(map);
            return;
        }
    }

    public void convertToJson(JSONObject jsonobject)
        throws JSONException
    {
        Object aobj[] = new Object[1];
        aobj[0] = jsonobject;
        Object obj = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, ((ChangeQuickRedirect) (obj)), false, "b196f0c719ac217f00ce363b47f763d5", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, ((ChangeQuickRedirect) (obj)), false, "b196f0c719ac217f00ce363b47f763d5");
            return;
        }
        JSONArray jsonarray = new JSONArray();
        obj = new JSONObject();
        ((JSONObject) (obj)).put("step", step);
        switch(type)
        {
        case 3: // '\003'
            ((JSONObject) (obj)).put("key", id);
            jsonarray.put(JSONUtils.buildLogUnit("mobile.view.load.custom", Long.valueOf(duration), ((JSONObject) (obj)), ts));
            break;

        case 2: // '\002'
            ((JSONObject) (obj)).put("pageName", id);
            jsonarray.put(JSONUtils.buildLogUnit("mobile.view.load.page", Long.valueOf(duration), ((JSONObject) (obj)), ts));
            break;

        case 1: // '\001'
            jsonarray.put(JSONUtils.buildLogUnit("mobile.view.load.homepage", Long.valueOf(duration), ((JSONObject) (obj)), ts));
            break;
        }
        jsonobject.put("metrics", jsonarray);
    }

    public long getDuration()
    {
        return duration;
    }

    public String getId()
    {
        return id;
    }

    public String getLocalEventType()
    {
        switch(type)
        {
        default:
            return "default";

        case 3: // '\003'
            return "mobile.view.load.custom";

        case 2: // '\002'
            return "mobile.view.load.page";

        case 1: // '\001'
            return "mobile.view.load.homepage";
        }
    }

    public double getMetricValue()
    {
        return (double)duration;
    }

    public String getPageName()
    {
        return id;
    }

    public String getStep()
    {
        return step;
    }

    public Map getSteps()
    {
        return steps;
    }

    public int getType()
    {
        return type;
    }

    public static ChangeQuickRedirect changeQuickRedirect;
    private final long duration;
    private final String id;
    private final String step;
    private Map steps;
    private final int type;
}
