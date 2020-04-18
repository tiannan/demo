// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.model;


import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AbstractEvent
{

    public AbstractEvent()
    {

            ts = System.currentTimeMillis();
            configFrom = 1;

    }

    public abstract void convertToJson(JSONObject jsonobject)
        throws JSONException;

    public abstract String getEventType();

    public abstract String getLocalEventType();

    public abstract double getMetricValue();

    public abstract String getPageName();

    public boolean isValid()
    {
        return true;
    }

    public final JSONObject toJson()
    {
         JSONObject jsonobject = new JSONObject();
        try
        {
            convertToJson(jsonobject);
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
            return jsonobject;
        }
        return jsonobject;
    }

    public String toString()
    {

            return toJson().toString();
    }

    public int configFrom;
    public Map optionTags;
    public String raw;
    public long ts;
}
