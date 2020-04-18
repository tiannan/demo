// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.traffic;

import com.meituan.metrics.model.AbstractEvent;
import com.meituan.metrics.util.JSONUtils;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import com.sankuai.common.utils.NumberUtils;
import java.util.*;
import org.json.*;

public class TrafficEvent extends AbstractEvent
{

    public TrafficEvent(Map map, String s)
    {
        Object aobj[] = new Object[2];
        aobj[0] = map;
        aobj[1] = s;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "640f4e755c68168983dcb5d66d777893", 0x6000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "640f4e755c68168983dcb5d66d777893");
            return;
        } else
        {
            traffic = map;
            date = s;
            value = NumberUtils.parseLong(String.valueOf(map.get("mobile.traffic.daily.total.upstream")), -1L) + NumberUtils.parseLong(String.valueOf(map.get("mobile.traffic.daily.total.downstream")), -1L);
            return;
        }
    }

    public void convertToJson(JSONObject jsonobject)
        throws JSONException
    {
        Object aobj[] = new Object[1];
        aobj[0] = jsonobject;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "a38846c5fdef11ad6901864667bb59a2", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "a38846c5fdef11ad6901864667bb59a2");
            return;
        } else
        {
            JSONArray jsonarray = new JSONArray();
            JSONObject jsonobject1 = new JSONObject(traffic);
            JSONObject jsonobject2 = new JSONObject();
            jsonobject2.put("date", date.replace("-", "/"));
            jsonobject.put("tags", jsonobject2);
            jsonarray.put(JSONUtils.buildTrafficLogUnit("mobile.traffic.daily.total", Double.valueOf(value), jsonobject1, jsonobject2, ts));
            jsonobject.put("metrics", jsonarray);
            return;
        }
    }

    public String getDate()
    {
        return date;
    }

    public Map getDetails()
    {
        return traffic;
    }

    public String getEventType()
    {
        return "default";
    }

    public String getLocalEventType()
    {
        return "mobile.traffic.daily.total";
    }

    public double getMetricValue()
    {
        return value;
    }

    public String getPageName()
    {
        return "";
    }

    public Map getTraffic()
    {
        return traffic;
    }

    public boolean isValid()
    {
        Object aobj[] = new Object[0];
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "892a1de92543465859b9802aa1eb84dc", 0x4000000000000000L))
            return ((Boolean)PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "892a1de92543465859b9802aa1eb84dc")).booleanValue();
        if(value < 0.0D)
            return false;
        for(Iterator iterator = traffic.values().iterator(); iterator.hasNext();)
        {
            Object obj = iterator.next();
            if(obj instanceof Long)
            {
                if(((Long)obj).longValue() < 0L)
                    return false;
            } else
            {
                return false;
            }
        }

        return true;
    }

    public static ChangeQuickRedirect changeQuickRedirect;
    private final String date;
    private final Map traffic;
    private double value;
}
