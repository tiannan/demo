// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.meituan.metrics.common;


public interface Constants
{

    public static final String ANDROIRD = "Android";
    public static final String ANR = "anr";
    public static final String BIG_IMAGE = "mobile.large.image";
    public static final String CATCH_EXCEPTION_NAME = "safeRun";
    public static final String COLD_LAUNCH_METER = "mobile.view.load.homepage";
    public static final String CPU = "cpu";
    public static final String CPU_AVG = "mobile.cpu.avg";
    public static final String CPU_MAX = "mobile.cpu.max";
    public static final String CPU_MAX_V2 = "cpuMax";
    public static final String CPU_V2 = "mobile.cpu.v2";
    public static final String CUSTOM_METER_ID = "key";
    public static final String CUSTOM_SPEED_METER = "mobile.view.load.custom";
    public static final char ENTER = 10;
    public static final String FPS_CUSTOM = "fps_custom";
    public static final String FPS_CUSTOM_AVG = "mobile.fps.custom.avg.v2";
    public static final String FPS_CUSTOM_MIN = "mobile.fps.custom.min.v2";
    public static final String FPS_PAGE = "fps_page";
    public static final String FPS_PAGE_AVG = "mobile.fps.page.avg.v2";
    public static final String FPS_PAGE_MIN = "mobile.fps.page.min.v2";
    public static final String FPS_SCROLL = "fps_scroll";
    public static final String FPS_SCROLL_AVG = "mobile.fps.scroll.avg.v2";
    public static final String FPS_SCROLL_MIN = "mobile.fps.scroll.min.v2";
    public static final String FPS_TYPE_CUSTOM = "custom";
    public static final String FPS_TYPE_PAGE = "page";
    public static final String FPS_TYPE_SCROLL = "scroll";
    public static final String IMAGE_URL = "url";
    public static final String LAG_EVENT_TYPE = "event_type";
    public static final String LAG_LOG = "lag_log";
    public static final String LAG_THRESHOLD = "threshold";
    public static final String MEMORY = "memory";
    public static final String MEMORY_AVG = "mobile.memory.avg";
    public static final String MEMORY_MAX = "mobile.memory.max";
    public static final String MEMORY_V2 = "mobile.memory.v2";
    public static final String METRICS = "metrics";
    public static final String PAGE_LOAD_AUTO = "mobile.view.load.page.auto";
    public static final String PAGE_LOAD_METER = "mobile.view.load.page";
    public static final String PAGE_NAME = "pageName";
    public static final char SPACE = 32;
    public static final String SPEED_METER_STEP = "step";
    public static final String SPLITTER = "\n--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---\n";
    public static final String TAGS = "tags";
    public static final String TOTAL_TIME = "total";
    public static final String TRAFFIC_DAILY_API_DOWNSTREAM = "mobile.traffic.daily.api.downstream";
    public static final String TRAFFIC_DAILY_API_UPSTREAM = "mobile.traffic.daily.api.upstream";
    public static final String TRAFFIC_DAILY_OTHER_DOWNSTREAM = "mobile.traffic.daily.other.downstream";
    public static final String TRAFFIC_DAILY_OTHER_UPSTREAM = "mobile.traffic.daily.other.upstream";
    public static final String TRAFFIC_DAILY_RES_DOWNSTREAM = "mobile.traffic.daily.res.downstream";
    public static final String TRAFFIC_DAILY_RES_UPSTREAM = "mobile.traffic.daily.res.upstream";
    public static final String TRAFFIC_DAILY_TOTAL_DOWNSTREAM = "mobile.traffic.daily.total.downstream";
    public static final String TRAFFIC_DAILY_TOTAL_STREAM = "mobile.traffic.daily.total";
    public static final String TRAFFIC_DAILY_TOTAL_UPSTREAM = "mobile.traffic.daily.total.upstream";
    public static final String TRAFFIC_DAILY_WEB_DOWNSTREAM = "mobile.traffic.daily.web.downstream";
    public static final String TRAFFIC_DAILY_WEB_UPSTREAM = "mobile.traffic.daily.web.upstream";
    public static final String TRAFFIC_TAG_DATE = "date";
    public static final int TRAFFIC_TYPE_API = 0;
    public static final int TRAFFIC_TYPE_OTHER = 3;
    public static final int TRAFFIC_TYPE_RES = 2;
    public static final int TRAFFIC_TYPE_TOTAL = 4;
    public static final int TRAFFIC_TYPE_UNKNOWN = -1;
    public static final int TRAFFIC_TYPE_WEB = 1;
    public static final int TRAFFIC_WRAPPER_MAX_FAILED_COUNT = 3;
    public static final String TYPE_DEFAULT = "default";
    public static final String TYPE_LAG = "lag_log";
}
