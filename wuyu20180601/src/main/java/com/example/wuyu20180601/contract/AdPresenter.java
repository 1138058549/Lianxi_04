package com.example.wuyu20180601.contract;

import com.example.wuyu20180601.base.BasePresenter;
import com.example.wuyu20180601.bean.AdBean;
import com.example.wuyu20180601.net.AdApi;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdPresenter extends BasePresenter<AdContract.View> implements AdContract.Presenter {
    private AdApi adApi;
   @Inject
    public AdPresenter(AdApi adApi) {
        this.adApi = adApi;
    }

    @Override
    public void getAd() {
     adApi.getAd()
             .observeOn(AndroidSchedulers.mainThread())
             .subscribeOn(Schedulers.io())
             .subscribe(new Observer<AdBean>() {
                 @Override
                 public void onSubscribe(Disposable d) {

                 }

                 @Override
                 public void onNext(AdBean adBean) {
                       mView.onAdSuccess(adBean);
                 }

                 @Override
                 public void onError(Throwable e) {

                 }

                 @Override
                 public void onComplete() {

                 }
             });
    }
}
