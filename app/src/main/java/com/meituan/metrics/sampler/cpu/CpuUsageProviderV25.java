package com.meituan.metrics.sampler.cpu;


import com.meituan.robust.common.StringUtil;
import com.sankuai.common.utils.IOUtils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* compiled from: ProGuard */
public class CpuUsageProviderV25 implements ICpuUsageProvider {
    private long mAppCpuTimeLast;
    private long mTotalLast;

    public CpuUsageProviderV25() {

        this.mTotalLast = 0;
        this.mAppCpuTimeLast = 0;
        this.mTotalLast = 0;
        this.mAppCpuTimeLast = 0;
    }

    public double getCpuUsagePercent() throws Throwable {
        Throwable th;

        Closeable closeable = null;
        Closeable bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/stat")), 1000);
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    IOUtils.close(bufferedReader);
                    IOUtils.close(null);
                    return -1.0d;
                }
                Closeable bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/self/stat")), 1000);
                try {
                    double parseCpuRate = (double) parseCpuRate(readLine, bufferedReader2.readLine());
                    IOUtils.close(bufferedReader);
                    IOUtils.close(bufferedReader2);
                    return parseCpuRate;
                } catch (Throwable th2) {
                    th = th2;
                    closeable = bufferedReader2;
                    IOUtils.close(bufferedReader);
                    IOUtils.close(closeable);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                IOUtils.close(bufferedReader);
                IOUtils.close(closeable);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
            IOUtils.close(bufferedReader);
            IOUtils.close(closeable);
            throw th;
        }
    }

    private long parseCpuRate(String str, String str2) {

        String[] split = str.split(StringUtil.SPACE);
        long j = -1;
        if (split.length < 9) {
            return -1;
        }
        long parseLong = Long.parseLong(split[2]);
        long parseLong2 = Long.parseLong(split[3]);
        long parseLong3 = Long.parseLong(split[4]);
        parseLong = (((((parseLong + parseLong2) + parseLong3) + Long.parseLong(split[5])) + Long.parseLong(split[6])) + Long.parseLong(split[7])) + Long.parseLong(split[8]);
        split = str2.split(StringUtil.SPACE);
        if (split.length < 17) {
            return -1;
        }
        parseLong2 = ((Long.parseLong(split[13]) + Long.parseLong(split[14])) + Long.parseLong(split[15])) + Long.parseLong(split[16]);
        parseLong3 = 0;
        if (this.mTotalLast != 0) {
            long j2 = parseLong - this.mTotalLast;
            if (j2 > 0) {
                j = ((parseLong2 - this.mAppCpuTimeLast) * 100) / j2;
            }
            parseLong3 = j;
        }
        this.mTotalLast = parseLong;
        this.mAppCpuTimeLast = parseLong2;
        return parseLong3;
    }
}