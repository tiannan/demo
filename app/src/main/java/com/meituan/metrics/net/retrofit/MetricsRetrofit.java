package com.meituan.metrics.net.retrofit;

import com.meituan.metrics.net.report.ReportResponse;
import com.sankuai.meituan.retrofit2.Call;
import com.sankuai.meituan.retrofit2.RequestBody;
import com.sankuai.meituan.retrofit2.Retrofit;
import com.sankuai.meituan.retrofit2.Retrofit.Builder;
import com.sankuai.meituan.retrofit2.converter.gson.GsonConverterFactory;
import com.sankuai.meituan.retrofit2.http.Body;

/* compiled from: ProGuard */
public class MetricsRetrofit {
    private static final String BASE_URL = "https://dreport.meituan.net/";
    private static volatile MetricsRetrofit sInstance;
    private Retrofit retrofit;

    public MetricsRetrofit() {

            this.retrofit = new Builder().baseUrl(BASE_URL).callFactory(MetricsCallFactory.getCallFactory()).addConverterFactory(GsonConverterFactory.create()).build();

    }

    public static MetricsRetrofit getInstance() {

        if (sInstance == null) {
            synchronized (MetricsRetrofit.class) {
                if (sInstance == null) {
                    sInstance = new MetricsRetrofit();
                }
            }
        }
        return sInstance;
    }

    public Call<ReportResponse> postMetricsData(@Body RequestBody requestBody) {
      return  ((MetricsRetrofitService) this.retrofit.create(MetricsRetrofitService.class)).postMetricsData(BASE_URL, requestBody);
    }
}