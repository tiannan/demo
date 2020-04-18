package com.meituan.metrics.cache.db;

import android.text.TextUtils;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.meituan.android.cipstorage.d;
import com.meituan.android.cipstorage.f;
import com.meituan.metrics.util.TimeUtil;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import com.meituan.robust.common.CommonConstant.Symbol;
import com.meituan.robust.utils.RobustBitConfig;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: ProGuard */
public class PageReportCountDao {
    public static final String REPORT_COUNT = "reportcount";
    public static final String REPORT_COUNT_V2 = "reportcount_v2";
    public static final String REPORT_RECORD = "reportRecord_";
    public static final String REPORT_RECORD_V2 = "reportRecord_v2_";
    public static String recordJson;
    public static String recordV2Json;

    public static Map<String, Integer> getCurrentDayReportCount(String str, d dVar, Map<String, Integer> map) {

        if (dVar == null) {
            return map;
        }
        long dayStartMillis = TimeUtil.getDayStartMillis();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(dayStartMillis);
        String b = dVar.b(stringBuilder.toString(), "", f.e);
        if (REPORT_RECORD.equals(str)) {
            recordJson = b;
        } else {
            recordV2Json = b;
        }
        if (TextUtils.isEmpty(b)) {
            return map;
        }
        Map<String, Integer> map2 = (Map) new GsonBuilder().enableComplexMapKeySerialization().create().fromJson(b, new TypeToken<ConcurrentHashMap<String, Integer>>() {
        }.getType());
        if (map2 == null) {
            map2 = map;
        }
        return map2;
    }

    public static int getCurrentDayCount(String str, d dVar) {

        int parseInt;
        String b = dVar.b(str, "", f.e);
        if (!TextUtils.isEmpty(b)) {
            long dayStartMillis = TimeUtil.getDayStartMillis();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(dayStartMillis);
            if (b.contains(stringBuilder.toString())) {
                try {
                    parseInt = Integer.parseInt(b.substring(b.indexOf(Symbol.COLON) + 1));
                } catch (Exception unused) {
                }
                return parseInt;
            }
            dVar.b(str, f.e);
        }
        parseInt = 0;
        return parseInt;
    }

    public static void setCurrentDayLimit(String str, int i, d dVar) {

        long dayStartMillis = TimeUtil.getDayStartMillis();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dayStartMillis);
        stringBuilder.append(Symbol.COLON);
        stringBuilder.append(i);
        dVar.a(str, stringBuilder.toString(), f.e);
    }

    public static boolean addPageCountRecord(String str, Map<String, Integer> map, d dVar) {

        if (!(map == null || map.size() == 0)) {
            if (dVar != null) {
                long dayStartMillis = TimeUtil.getDayStartMillis();
                String toJson = new GsonBuilder().enableComplexMapKeySerialization().create().toJson((Object) map);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(dayStartMillis);
                return dVar.a(stringBuilder.toString(), toJson, f.e);
            }
        }
        return false;
    }

    public static void removeInvalidPageCountRecord(d dVar) {
       if (dVar != null) {
            long dayStartMillis = TimeUtil.getDayStartMillis();
            Map b = dVar.b(f.e);
            if (b != null && b.size() > 0) {
                for (String str2 : b.keySet()) {
                    if (!REPORT_COUNT.equals(str2)) {
                        StringBuilder stringBuilder = new StringBuilder(REPORT_RECORD);
                        stringBuilder.append(dayStartMillis);
                        if (!(stringBuilder.toString().equals(str2) || REPORT_COUNT_V2.equals(str2))) {
                            stringBuilder = new StringBuilder(REPORT_RECORD_V2);
                            stringBuilder.append(dayStartMillis);
                            if (!stringBuilder.toString().equals(str2)) {
                                dVar.b(str2, f.e);
                            }
                        }
                    }
                }
            }
        }
    }
}