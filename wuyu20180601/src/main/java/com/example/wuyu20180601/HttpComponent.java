package com.example.wuyu20180601;

import com.example.wuyu20180601.moudle.HttpMoudle;

import dagger.Component;
import dagger.Module;

@Component(modules= HttpMoudle.class)
public interface HttpComponent {

    void inject(ListActivity listActivity);

    void inject(ShowActivity showActivity);

}
