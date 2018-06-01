package com.example.wuyu20180601;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wuyu20180601.bean.Bean;
import com.example.wuyu20180601.bean.BeanDao;
import com.example.wuyu20180601.bean.DaoMaster;
import com.example.wuyu20180601.bean.DaoSession;
import com.example.wuyu20180601.view.MyTitleview;
import com.fynn.fluidlayout.FluidLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BeanDao beanDao;
    private Button btn_all;
    private MyTitleview main_mytitle;
    private FluidLayout fluidLayout;
    private List<Bean> stringList = new ArrayList<>();
    private Button btn_al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaoMaster.DevOpenHelper mHelper = new DaoMaster.DevOpenHelper
                (this, "sport-db", null);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        DaoMaster mDaoMaster = new DaoMaster(db);
        DaoSession daoSession = mDaoMaster.newSession();

        beanDao = daoSession.getBeanDao();
        //找到控件
        btn_all = findViewById(R.id.btn_all);
        main_mytitle = findViewById(R.id.main_mytitle);
        fluidLayout = findViewById(R.id.fluidLayout);
        btn_al = findViewById(R.id.btn_al);
        main_mytitle.setListened(new MyTitleview.Listened() {
            @Override
            public void toString(String editText) {
                if(!TextUtils.isEmpty(editText)){
                    //存入数据库
                    beanDao.insertOrReplaceInTx(new Bean(null,editText));

                    genTag();
                }else{
                    Toast.makeText(MainActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //清空按钮
        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除数据库中的所有数据
                beanDao.deleteAll();
                //查询数据库
                genTag();
            }
        });

        btn_al.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        genTag();


    }


    private void genTag() {
        List<Bean> beans = beanDao.loadAll();

        stringList.clear();
        stringList.addAll(beans);

        fluidLayout.removeAllViews();

        for (int x=0;x<beans.size();x++){
            TextView tv = new TextView(MainActivity.this);
            tv.setText(stringList.get(x).getName());
            tv.setTextSize(13);

            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            params.setMargins(12,12,12,12);

            fluidLayout.addView(tv,params);
        }

    }
}