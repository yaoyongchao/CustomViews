package com.yyc.customviews.search;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;

import com.yyc.customviews.R;
import com.yyc.customviews.badgeview.BadgeView;
import com.yyc.plugslib.itemView.SettingItemView;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.edt_search)
    EditText edtSearch;

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.st)
    SettingItemView st;

    private BadgeView badgeView;
    private BadgeView badgeView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initViews();

    }

    private void initViews() {
        removeBorder(searchView);
        setSearch();

        badgeView = new BadgeView(this);
        badgeView.setTargetView(edtSearch);
        badgeView.setBadgeText("999");
        badgeView.setBadgeGravity(Gravity.TOP | Gravity.LEFT);
        badgeView1 = new BadgeView(this);
        badgeView1.setTargetView(img);
        badgeView1.setBadgeText("9");
        Log.e(TAG, "is number:" + isNumeric("901"));
    }

    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    private void setSearch() {
        View v = searchView.getChildAt(0);
        Log.e(TAG, "v---" + v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "你摸到我了");
            }
        });

    }


    /**
     * Remove the SearchView bottom border
     */
    private void removeBorder(SearchView searchView) {
        if (searchView != null) {
            try {        //--拿到字节码
                Class<?> argClass = searchView.getClass();
                //--指定某个私有属性,mSearchPlate是搜索框父布局的名字
                Field ownField = argClass.getDeclaredField("mSearchPlate");
                //--暴力反射,只有暴力反射才能拿到私有属性
                ownField.setAccessible(true);
                View mView = (View) ownField.get(searchView);
                //--设置背景
                mView.setBackgroundColor(Color.TRANSPARENT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick(R.id.st)
    public void onViewClicked() {
        Log.e("aa","我是好人！");
    }
}
