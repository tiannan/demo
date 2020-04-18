// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.laggy.anr;



public class AnrEvent
{

    public AnrEvent()
    {
    }

    public String getActivity()
    {
        return activity;
    }

    public String getAnrVersion()
    {
        return anrVersion;
    }

    public String getApkHash()
    {
        return apkHash;
    }

    public String getCh()
    {
        return ch;
    }

    public long getCity()
    {
        return city;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    public String getGuid()
    {
        return guid;
    }

    public String getMainThread()
    {
        return mainThread;
    }

    public String getNet()
    {
        return net;
    }

    public String getOtherThread()
    {
        return otherThread;
    }

    public String getShortMst()
    {
        return shortMst;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public String getTraceFile()
    {
        return traceFile;
    }

    public String getUuid()
    {
        return uuid;
    }

    public String getcActivity()
    {
        return cActivity;
    }

    public void setActivity(String s)
    {
        activity = s;
    }

    public void setAnrVersion(String s)
    {
        anrVersion = s;
    }

    public void setApkHash(String s)
    {
        apkHash = s;
    }

    public void setCh(String s)
    {
        ch = s;
    }

    public void setCity(long l)
    {

        city = l;

    }

    public void setErrorMsg(String s)
    {
        errorMsg = s;
    }

    public void setGuid(String s)
    {
        guid = s;
    }

    public void setMainThread(String s)
    {
        mainThread = s;
    }

    public void setNet(String s)
    {
        net = s;
    }

    public void setOtherThread(String s)
    {
        otherThread = s;
    }

    public void setShortMst(String s)
    {
        shortMst = s;
    }

    public void setTimestamp(long l)
    {

            timestamp = l;

    }

    public void setTraceFile(String s)
    {
        traceFile = s;
    }

    public void setUuid(String s)
    {
        uuid = s;
    }

    public void setcActivity(String s)
    {
        cActivity = s;
    }

    public String toString()
    {

            StringBuilder stringbuilder = new StringBuilder("AnrEvent{, apkHash='");
            stringbuilder.append(apkHash);
            stringbuilder.append('\'');
            stringbuilder.append(",timestamp=");
            stringbuilder.append(getTimestamp());
            stringbuilder.append(", mainThread='");
            stringbuilder.append(getMainThread());
            stringbuilder.append('\'');
            stringbuilder.append(", otherThread='");
            stringbuilder.append(getOtherThread());
            stringbuilder.append('\'');
            stringbuilder.append(", tracesInfo='");
            stringbuilder.append(getTraceFile());
            stringbuilder.append('\'');
            stringbuilder.append(", guid='");
            stringbuilder.append(guid);
            stringbuilder.append('\'');
            stringbuilder.append(", activity='");
            stringbuilder.append(activity);
            stringbuilder.append('\'');
            stringbuilder.append(", anrVersion='");
            stringbuilder.append(anrVersion);
            stringbuilder.append('\'');
            stringbuilder.append(", errorInfo='");
            stringbuilder.append(errorMsg);
            stringbuilder.append('\'');
            stringbuilder.append(", desc='");
            stringbuilder.append(shortMst);
            stringbuilder.append('\'');
            stringbuilder.append('}');
            return stringbuilder.toString();

    }

    private String activity;
    public String allMainThreadStack;
    private String anrVersion;
    private String apkHash;
    private String cActivity;
    private String ch;
    private long city;
    private String errorMsg;
    private String guid;
    private String mainThread;
    private String net;
    private String otherThread;
    private String shortMst;
    private long timestamp;
    private String traceFile;
    private String uuid;
}
