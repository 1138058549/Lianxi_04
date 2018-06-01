package com.example.wuyu20180601.net;

import com.example.wuyu20180601.bean.AdBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface AdApiService {
    @GET("cook/query?key=584396a671dbaecd225c296ce7f42097&menu=西红柿&rn=10&pn=3")
    Observable<AdBean> getAd();

}
