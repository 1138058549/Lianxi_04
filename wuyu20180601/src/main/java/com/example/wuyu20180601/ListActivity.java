package com.example.wuyu20180601;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.wuyu20180601.adapter.XrvListAdapter;
import com.example.wuyu20180601.base.BaseActivity;
import com.example.wuyu20180601.bean.AdBean;
import com.example.wuyu20180601.contract.AdContract;
import com.example.wuyu20180601.contract.AdPresenter;
import com.example.wuyu20180601.moudle.HttpMoudle;

import java.util.List;


public class ListActivity extends BaseActivity<AdPresenter> implements AdContract.View {

    private RecyclerView rrcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initView();
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_list;
    }

    @Override
    public void iniect() {
        DaggerHttpComponent.builder()
                .httpMoudle(new HttpMoudle())
                .build()
                .inject(this);

    }
    private void initView(){
        rrcv = findViewById(R.id.rcv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rrcv.setLayoutManager(layoutManager);
        mPresenter.getAd();

    }

    @Override
    public void onAdSuccess(AdBean adBean) {
        List<AdBean.ResultBean.DataBean> data = adBean.getResult().getData();
        XrvListAdapter xrvListAdapter = new XrvListAdapter(this, data);
        rrcv.setAdapter(xrvListAdapter);

    }
}
