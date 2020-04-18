// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.laggy.anr;

import java.util.List;

public interface AnrCallback {

    void onAnrEvent(long l, String s, List list);
}
