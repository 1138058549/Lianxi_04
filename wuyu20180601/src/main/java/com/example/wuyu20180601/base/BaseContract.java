package com.example.wuyu20180601.base;

public interface BaseContract {
    interface BasePresenter<T extends BaseView>{
        void attchView(T view);

        void detachView();
    }

    interface BaseView{
        void showLoading();

        void dismissLoading();
    }
}
