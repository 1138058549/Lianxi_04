package com.example.wuyu20180601.contract;

import com.example.wuyu20180601.base.BaseContract;
import com.example.wuyu20180601.bean.AdBean;

public interface AdContract {
    interface View extends BaseContract.BaseView{
        void onAdSuccess(AdBean adBean);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getAd();
    }
}
