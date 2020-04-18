// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.traffic.image;

import com.meituan.metrics.model.AbstractEvent;
import com.meituan.metrics.util.JSONUtils;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import org.json.*;

public class BigImageEvent extends AbstractEvent
{

    public BigImageEvent(String s, long l, int i, int j, String s1)
    {
        Object aobj[] = new Object[5];
        aobj[0] = s;
        aobj[1] = new Long(l);
        aobj[2] = Integer.valueOf(i);
        aobj[3] = Integer.valueOf(j);
        aobj[4] = s1;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "48fe03c84e015b53380d359f718ec797", 0x6000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "48fe03c84e015b53380d359f718ec797");
            return;
        } else
        {
            url = s;
            fileSize = l;
            page = s1;
            return;
        }
    }

    public void convertToJson(JSONObject jsonobject)
        throws JSONException
    {
        Object aobj[] = new Object[1];
        aobj[0] = jsonobject;
        ChangeQuickRedirect changequickredirect = changeQuickRedirect;
        if(PatchProxy.isSupport(aobj, this, changequickredirect, false, "8c18c44619d4f444214f45b4cb8e3444", 0x4000000000000000L))
        {
            PatchProxy.accessDispatch(aobj, this, changequickredirect, false, "8c18c44619d4f444214f45b4cb8e3444");
            return;
        } else
        {
            JSONArray jsonarray = new JSONArray();
            JSONObject jsonobject1 = new JSONObject();
            jsonobject1.put("pageName", page);
            jsonobject1.put("url", url);
            jsonarray.put(JSONUtils.buildLogUnit("mobile.large.image", Long.valueOf(fileSize), jsonobject1, ts));
            jsonobject.put("metrics", jsonarray);
            return;
        }
    }

    public String getEventType()
    {
        return "default";
    }

    public String getLocalEventType()
    {
        return "mobile.large.image";
    }

    public double getMetricValue()
    {
        return (double)fileSize;
    }

    public String getPageName()
    {
        return page;
    }

    public static ChangeQuickRedirect changeQuickRedirect;
    private final long fileSize;
    private final String page;
    private final String url;
}
