package com.example.wuyu20180601;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.wuyu20180601.adapter.MyAdapter;
import com.example.wuyu20180601.adapter.XrvListAdapter;
import com.example.wuyu20180601.base.BaseActivity;
import com.example.wuyu20180601.bean.AdBean;
import com.example.wuyu20180601.contract.AdContract;
import com.example.wuyu20180601.contract.AdPresenter;
import com.example.wuyu20180601.moudle.HttpMoudle;
import com.example.wuyu20180601.utils.GlideImageLoader;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;

import java.util.List;

public class ShowActivity extends BaseActivity<AdPresenter> implements AdContract.View {

    private SimpleDraweeView iv;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
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
        iv = findViewById(R.id.iv);
        tv = findViewById(R.id.tvTitle);


        mPresenter.getAd();

    }

    @Override
    public void onAdSuccess(AdBean adBean) {
        List<AdBean.ResultBean.DataBean> data = adBean.getResult().getData();
        for (int i=0;i<data.size();i++){
            iv.setImageURI(data.get(i).getAlbums().get(0));
             tv.setText(data.get(i).getTags());
        }

    }
}