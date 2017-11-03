package com.yyc.plugslib.itemView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.yyc.plugslib.R;


/**
 * Created by yaoyongchao on 17-11-1.
 * Dscription: http://www.cnblogs.com/whoislcj/p/5714760.html
 */

public class SettingItemView extends ConstraintLayout {
    private static final String TAG = "SettingItemView";
    private static final boolean isLog = true;
    private static final int IMGLEFT_ID = 0x1000;
    private static final int TVTITLE_ID = 0x1001;
    private static final int BORDER_TOP_ID = 0x1002;
    private static final int BORDER_BOTTOM_ID = 0x1003;
    private static final int TVLEFT_ID = 0x1004;
    private static final int IMGARROWS_ID = 0x1005;
    private static final String BORDER_COLOR = "#CCCCCC";
//    private static final String BORDER_COLOR = "#FF0000";
    private static  int sBorderHeight = 1;
    private static final int  IMGLEFTMARGIN = 15;
    private static final int  TVTITLEMARGIN = 10;
    private static final int  TVLEFTMARGIN = 15;
    private ImageView imgLeft;
    private TextView tvTitle;
    private ConstraintSet set;
    private float imgWidthDefault;
    private float imgHeightDefault;
    private int imgMarginLeft = 10;
    private int imgMarginTop = 10;
    private int imgMarginRignt = 10;
    private int imgMarginBotton = 10;

    private TextView borderTop;
    private TextView borderBottom;
    private TextView tvValue;
    private boolean isShowBorder = true;
    private boolean isShowBorderTop = true;
    private boolean isShowBorderBottom = true;
    private ImageView imgArrows ;
    private int  imgArrowsDrawable = R.drawable.ic_arrows_left;


    public SettingItemView(Context context) {
        this(context,null);
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        Log.i(TAG,"SettingItemView");
        initViews(context,attrs);
    }

//    private void initViews(Context context, AttributeSet attrs) {
//        LayoutInflater.from(context).inflate(R.layout.view_setting_item, this, true);
//        imgLeft = (ImageView) findViewById(R.id.img);
//        tvTitle = (TextView) findViewById(R.id.tv);
//
//        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SettingItemView);
//        if (attributes != null) {
//            if (attributes != null) {
//                Log.i(TAG, "赋值img");
//                //处理imgLeft图片
//                int imgLeftDrawable = attributes.getResourceId(R.styleable.SettingItemView_imgleft_drawable, R.drawable.ic_defalt);
//                Log.i(TAG, "赋值img--" + imgLeftDrawable);
//                imgLeft.setImageResource(imgLeftDrawable);
//
//                String strTitle = attributes.getString(R.styleable.SettingItemView_title_text);
//                if (!TextUtils.isEmpty(strTitle)) {
//                    Log.i(TAG, "赋值Title");
//                    tvTitle.setText(strTitle);
//                } else {
//                    Log.i(TAG, "赋值Title为空");
//                    tvTitle.setText("赋值Title为空");
//                }
//            }
//
//        }
//
//        attributes.recycle();
//    }

    /**
     * 初始化
     */
    private void initViews(Context context,AttributeSet attrs) {
        log("initViews");
        set = new ConstraintSet();
        set.clone(this);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SettingItemView);

        if (attributes != null) {
            initBorder(set,context,attributes);
            initImgLeft(set,context,attributes);
            initTvTitle(set,context,attributes);
            initTvValue(set,context,attributes);
            initImgArrows(set,context,attributes);

            attributes.recycle();
        }


    }

    private void initImgArrows(ConstraintSet set, Context context, TypedArray attributes) {
        log("initImaArrows");
        imgArrows = new ImageView(context);
        imgArrows.setId(IMGARROWS_ID);
        addView(imgArrows);

        set.connect(imgArrows.getId(),ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP);
        set.connect(imgArrows.getId(),ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM);
        set.connect(imgArrows.getId(),ConstraintSet.RIGHT,ConstraintSet.PARENT_ID,ConstraintSet.RIGHT);
        set.constrainHeight(imgArrows.getId(), ConstraintSet.WRAP_CONTENT);
        set.constrainWidth(imgArrows.getId(), ConstraintSet.WRAP_CONTENT);
//        set.setMargin(imgArrows.getId(),ConstraintSet.END,dip2Px(IMGLEFTMARGIN));

        int imgArr = attributes.getResourceId(R.styleable.SettingItemView_imgarrows_drawable, imgArrowsDrawable);
        imgArrows.setImageResource(imgArr);

        set.applyTo(this);
    }

    private void initTvValue(ConstraintSet set, Context context, TypedArray attributes) {
        log("initTvValue");
        tvValue = new TextView(context);
        tvValue.setId(TVLEFT_ID);
        addView(tvValue);
        set.connect(tvValue.getId(),ConstraintSet.RIGHT,ConstraintSet.PARENT_ID,ConstraintSet.RIGHT);
        set.connect(tvValue.getId(),ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP);
        set.connect(tvValue.getId(),ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM);
        set.constrainHeight(tvValue.getId(), ConstraintSet.WRAP_CONTENT);
        set.constrainWidth(tvValue.getId(), ConstraintSet.WRAP_CONTENT);
        set.setMargin(tvValue.getId(),ConstraintSet.END,dip2Px(TVLEFTMARGIN));

        String str = attributes.getString(R.styleable.SettingItemView_left_text);
        if (!TextUtils.isEmpty(str)) {
            tvValue.setText(str);
        } else {
            Log.i(TAG, "赋值Title为空");
        }

        set.applyTo(this);
    }

    private void initBorder(ConstraintSet set, Context context, TypedArray attributes) {
        log("initBorder");
        borderTop = new TextView(context);
        borderTop.setId(BORDER_TOP_ID);
        borderTop.setBackgroundColor(Color.parseColor(BORDER_COLOR));
        addView(borderTop);

        sBorderHeight = (int) attributes.getDimension(R.styleable.SettingItemView_border_height,dip2Px(sBorderHeight));

        set.connect(borderTop.getId(),ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT);
        set.connect(borderTop.getId(),ConstraintSet.RIGHT,ConstraintSet.PARENT_ID,ConstraintSet.RIGHT);
        set.connect(borderTop.getId(),ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP);
        set.constrainWidth(borderTop.getId(),ConstraintSet.MATCH_CONSTRAINT_SPREAD);
        set.constrainHeight(borderTop.getId(),sBorderHeight);
        set.applyTo(this);

        borderBottom = new TextView(context);
        borderBottom.setId(BORDER_BOTTOM_ID);
        borderBottom.setBackgroundColor(Color.parseColor(BORDER_COLOR));
        addView(borderBottom);
        set.connect(borderBottom.getId(),ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT);
        set.connect(borderBottom.getId(),ConstraintSet.RIGHT,ConstraintSet.PARENT_ID,ConstraintSet.RIGHT);
        set.connect(borderBottom.getId(),ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM);
        set.constrainWidth(borderBottom.getId(),ConstraintSet.MATCH_CONSTRAINT_SPREAD);
        set.constrainHeight(borderBottom.getId(),sBorderHeight);

        isShowBorder = attributes.getBoolean(R.styleable.SettingItemView_is_show_border,isShowBorder);
        isShowBorderTop = attributes.getBoolean(R.styleable.SettingItemView_is_show_border_top,isShowBorderTop);
        isShowBorderBottom = attributes.getBoolean(R.styleable.SettingItemView_is_show_border_bottom,isShowBorderBottom);
        Log.i(TAG,"isShowBorder:" + isShowBorder);
        set.setVisibility(borderTop.getId(),((isShowBorder && isShowBorderTop) ? View.VISIBLE : View.GONE));
        set.setVisibility(borderBottom.getId(),((isShowBorder && isShowBorderBottom) ? View.VISIBLE : View.GONE));
//
//        if (isShowBorder) {
//            Log.i(TAG,"isShowBorder--显示" );
//            if (isShowBorderTop)
//                set.setVisibility(borderTop.getId(),View.VISIBLE);
//            if (isShowBorderBottom)
//                set.setVisibility(borderTop.getId(),View.VISIBLE);
//        } else {
//            Log.i(TAG,"isShowBorder:--隐藏");
//            set.setVisibility(borderTop.getId(),View.GONE);
//            set.setVisibility(borderBottom.getId(),View.GONE);
//        }

        set.applyTo(this);
    }

    private void initTvTitle(ConstraintSet set, Context context, TypedArray attributes) {
        log("initTVTitle");

        tvTitle = new TextView(context);
        tvTitle.setId(TVTITLE_ID);
        addView(tvTitle);
        set.connect(tvTitle.getId(),ConstraintSet.LEFT,imgLeft.getId(),ConstraintSet.RIGHT);
        set.connect(tvTitle.getId(),ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP);
        set.connect(tvTitle.getId(),ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM);
        set.constrainHeight(tvTitle.getId(), ConstraintSet.WRAP_CONTENT);
        set.constrainWidth(tvTitle.getId(), ConstraintSet.WRAP_CONTENT);
        set.setMargin(tvTitle.getId(),ConstraintSet.START,dip2Px(TVTITLEMARGIN));
        set.setMargin(tvTitle.getId(),ConstraintSet.START, (int) attributes.getDimension(R.styleable.SettingItemView_title_text_marginleft,dip2Px(TVTITLEMARGIN)));

        String strTitle = attributes.getString(R.styleable.SettingItemView_title_text);
        if (!TextUtils.isEmpty(strTitle)) {
            tvTitle.setText(strTitle);
        } else {
            Log.i(TAG, "赋值Title为空");
        }

        set.applyTo(this);
    }

    private void initImgLeft(ConstraintSet set, Context context, TypedArray attributes) {
        log("initImgLeft");
        imgLeft = new ImageView(context);
        imgLeft.setId(IMGLEFT_ID);
        addView(imgLeft);

        set.connect(imgLeft.getId(),ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP);
        set.connect(imgLeft.getId(),ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM);
        set.connect(imgLeft.getId(),ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT);
        set.constrainHeight(imgLeft.getId(), ConstraintSet.WRAP_CONTENT);
        set.constrainWidth(imgLeft.getId(), ConstraintSet.WRAP_CONTENT);
        set.setMargin(imgLeft.getId(),ConstraintSet.START,dip2Px(IMGLEFTMARGIN));

        int imgLeftDrawable = attributes.getResourceId(R.styleable.SettingItemView_imgleft_drawable, -1);
        if (imgLeftDrawable != -1)
            imgLeft.setImageResource(imgLeftDrawable);
        imgLeft.setBackgroundColor(Color.GREEN);

        imgHeightDefault = attributes.getDimension(R.styleable.SettingItemView_imgleft_height, 0);
        imgWidthDefault = attributes.getDimension(R.styleable.SettingItemView_imgleft_width, 0);
        set.constrainHeight(imgLeft.getId(), (int) imgHeightDefault);
        set.constrainWidth(imgLeft.getId(), (int) imgWidthDefault);
        set.applyTo(this);
    }

    public void setTitleText(CharSequence text) {
        log("setTitleText");
        if (null != tvTitle)
            tvTitle.setText(text);
    }

    public void setTitleSize(float size) {
        log("setTitleSize");
        if (null != tvTitle)
            tvTitle.setTextSize(size);
    }
    public void setTitleColor(@ColorInt int color) {
        log("setTitleColor");
        if (null != tvTitle)
            tvTitle.setTextColor(color);
    }
   /* public void setTitleMargin(float left, float top, float right, float bottom){
        log("setTitleMargin");
        if (null != set)
            return;
        set.setMargin(tvTitle.getId(),ConstraintSet.START,dip2Px(left));
//        set.setMargin(tvTitle.getId(),ConstraintSet.TOP,dip2Px(top));
//        set.setMargin(tvTitle.getId(),ConstraintSet.END,dip2Px(right));
//        set.setMargin(tvTitle.getId(),ConstraintSet.BOTTOM,dip2Px(bottom));
        set.applyTo(this);
        invalidate();

    }*/
    public void setValueText(CharSequence text) {
        log("setValueText");
        if (null != tvValue)
            tvValue.setText(text);
    }


    /*
     * converts dip to px
     */
    private int dip2Px(float dip) {
        return (int) (dip * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }

    private void log(String str){
        if (isLog)
            Log.i(TAG,str);

    }
}
