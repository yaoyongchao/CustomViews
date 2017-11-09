package com.yyc.customviews.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.yyc.customviews.R;
import com.yyc.plugslib.itemView.SettingItemView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyItemActivity extends AppCompatActivity {

    @BindView(R.id.set_view)
    SettingItemView setView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_item);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setView.setTitleText("公司名称");
//        setView.setValueText("北京分公司！");
    }

    @OnClick(R.id.set_view)
    public void onViewClicked() {
        Log.e("aa","我是好人");
    }
}
