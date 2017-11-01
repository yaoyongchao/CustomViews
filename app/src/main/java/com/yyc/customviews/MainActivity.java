package com.yyc.customviews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yyc.customviews.search.SearchActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 *自定义控件集合
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_search, R.id.btn_dialog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                doNext(SearchActivity.class);
                break;
            case R.id.btn_dialog:
                break;
        }
    }

    private void doNext(Class cls) {
        startActivity(new Intent(this,cls));
    }
}
