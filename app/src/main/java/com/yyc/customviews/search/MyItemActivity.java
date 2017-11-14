package com.yyc.customviews.search;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yyc.customviews.R;
import com.yyc.plugslib.itemView.SettingItemView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyItemActivity extends AppCompatActivity {

    @BindView(R.id.set_view)
    SettingItemView setView;
    @BindView(R.id.tv)
    TextView tv;
    Context context;
    AlphaAnimation mHideAnimation;
    AlphaAnimation mShowAnimation;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.llyt)
    LinearLayout llyt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_item);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        setHidAnimation1(tv,1);
//        tv.startAnimation(AnimationUtils.loadAnimation(context, R.anim.top_out));
    }

    private void initView() {
        setView.setTitleText("公司名称");
        context = this;
//        setView.setValueText("北京分公司！");

    }

    @OnClick({R.id.set_view, R.id.btn, R.id.btn1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.set_view:
                break;
            case R.id.btn:
//                tv.startAnimation(AnimationUtils.loadAnimation(context, R.anim.bottom_in));
//                tv.setVisibility(View.VISIBLE);
//                tv1.startAnimation(AnimationUtils.loadAnimation(context, R.anim.bottom_in));

//                setHideAnimation(tv,2000);
                llyt.setVisibility(View.GONE);
//                s1();
                break;
            case R.id.btn1:
//                tv.startAnimation(AnimationUtils.loadAnimation(context,R.anim.bottom_in));
//                setShowAnimation1(tv, 2000);
                llyt.setVisibility(View.VISIBLE);
//                setShowAnimation(tv, 2000);
//                s2();
                break;
        }
    }

    public void s1() {// 隐藏动画


        // 显示动画
        TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, -0.0f);
        mShowAction.setRepeatMode(Animation.REVERSE);
        mShowAction.setDuration(300);

        tv.startAnimation(mShowAction);//开始动画
        tv.setVisibility(View.VISIBLE);


    }

    public void s2() {

        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f);
        mHiddenAction.setDuration(500);
        tv.startAnimation(mHiddenAction);//开始动画
        tv.setVisibility(View.GONE);
    }

    /**
     * View渐隐动画效果
     */
    public void setHideAnimation(View view, int duration) {
        if (null == view || duration < 0) {
            return;
        }

        if (null != mHideAnimation) {
            mHideAnimation.cancel();
        }
        // 监听动画结束的操作
        mHideAnimation = new AlphaAnimation(1.0f, 0.0f);
        mHideAnimation.setDuration(duration);
        mHideAnimation.setFillAfter(true);
        view.startAnimation(mHideAnimation);
    }

    public void setHidAnimation1(final View view, int duration) {
        if (null == view || duration < 0) {
            return;
        }

        Animation animation = new TranslateAnimation(0, 0, 0, -view.getHeight());
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                int top = view.getTop();
                int width = view.getWidth();
                int height = view.getHeight();
                view.clearAnimation();
                view.layout(0, top, 0 + width, top + height);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation.setDuration(duration);
        animation.setRepeatCount(0);//动画的重复次数
//        animation.setFillAfter(true);//设置为true，动画转化结束后被应用
        view.startAnimation(animation);//开始动画

    }

    public void setShowAnimation1(View view, int duration) {
        if (null == view || duration < 0) {
            return;
        }

        Animation animation = new TranslateAnimation(0, 0, -view.getHeight(), 0);
        animation.setDuration(duration);
        animation.setRepeatCount(0);//动画的重复次数
        animation.setFillAfter(true);//设置为true，动画转化结束后被应用
        view.startAnimation(animation);//开始动画
    }

    /**
     * View渐现动画效果
     */
    public void setShowAnimation(View view, int duration) {
        if (null == view || duration < 0) {
            return;
        }
        if (null != mShowAnimation) {
            mShowAnimation.cancel();
        }
        mShowAnimation = new AlphaAnimation(0.0f, 1.0f);
        mShowAnimation.setDuration(duration);
        mShowAnimation.setFillAfter(true);
        view.startAnimation(mShowAnimation);
    }

}
