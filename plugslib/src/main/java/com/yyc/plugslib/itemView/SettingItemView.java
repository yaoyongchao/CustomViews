package com.yyc.plugslib.itemView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
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
    private ImageView imgLeft;
    private TextView tvTitle;
    private ConstraintSet set;
    private float imgWidthDefault;
    private float imgHeightDefault;
    private int imgMarginLeft = 10;
    private int imgMarginTop = 10;
    private int imgMarginRignt = 10;
    private int imgMarginBotton = 10;


    public SettingItemView(Context context) {
        this(context,null);
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        Log.i(TAG,"SettingItemView");
        initViews1(context,attrs);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e(TAG,"onMeasure" );
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }



    private void initViews(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_setting_item, this, true);
        imgLeft = (ImageView) findViewById(R.id.img);
        tvTitle = (TextView) findViewById(R.id.tv);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SettingItemView);
        if (attributes != null) {
            if (attributes != null) {
                Log.i(TAG, "赋值img");
                //处理imgLeft图片
                int imgLeftDrawable = attributes.getResourceId(R.styleable.SettingItemView_imgleft_drawable, R.drawable.ic_defalt);
                Log.i(TAG, "赋值img--" + imgLeftDrawable);
                imgLeft.setImageResource(imgLeftDrawable);

                String strTitle = attributes.getString(R.styleable.SettingItemView_title_text);
                if (!TextUtils.isEmpty(strTitle)) {
                    Log.i(TAG, "赋值Title");
                    tvTitle.setText(strTitle);
                } else {
                    Log.i(TAG, "赋值Title为空");
                    tvTitle.setText("赋值Title为空");
                }
            }

        }

        attributes.recycle();
    }

    /**
     * 初始化
     */
    private void initViews1(Context context,AttributeSet attrs) {
        Log.i(TAG,"initViews" + "---" );
        set = new ConstraintSet();
        set.clone(this);
        imgLeft = new ImageView(context);
        imgLeft.setId(100);
        addView(imgLeft);

        set.connect(imgLeft.getId(),ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP);
        set.connect(imgLeft.getId(),ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM);
        set.constrainHeight(imgLeft.getId(), ConstraintSet.WRAP_CONTENT);
        set.constrainWidth(imgLeft.getId(), ConstraintSet.WRAP_CONTENT);
        set.setMargin(imgLeft.getId(),ConstraintSet.START,dip2Px(1));
        set.applyTo(this);

        tvTitle = new TextView(context);
        tvTitle.setId(101);
        addView(tvTitle);
        set.connect(tvTitle.getId(),ConstraintSet.LEFT,imgLeft.getId(),ConstraintSet.RIGHT);
        set.connect(tvTitle.getId(),ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP);
        set.connect(tvTitle.getId(),ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM);
        set.constrainHeight(tvTitle.getId(), ConstraintSet.WRAP_CONTENT);
        set.constrainWidth(tvTitle.getId(), ConstraintSet.WRAP_CONTENT);
        set.applyTo(this);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SettingItemView);
        if (attributes != null) {
            Log.i(TAG,"赋值img");
            //处理imgLeft图片
            int imgLeftDrawable = attributes.getResourceId(R.styleable.SettingItemView_imgleft_drawable, R.drawable.ic_defalt);
            Log.i(TAG,"赋值img--" + imgLeftDrawable);
            imgLeft.setImageResource(imgLeftDrawable);
            imgLeft.setBackgroundColor(Color.GREEN);

            imgHeightDefault = attributes.getDimension(R.styleable.SettingItemView_imgleft_height, 0);
            imgWidthDefault = attributes.getDimension(R.styleable.SettingItemView_imgleft_width, 0);
            Log.i(TAG, "img widht-" + imgWidthDefault + "-- img height--" + imgHeightDefault);
            set.constrainHeight(imgLeft.getId(), (int) imgHeightDefault);
            set.constrainWidth(imgLeft.getId(), (int) imgWidthDefault);
//            set.constrainHeight(imgLeft.getId(), ConstraintSet.MATCH_CONSTRAINT);
//            set.constrainWidth(imgLeft.getId(), ConstraintSet.MATCH_CONSTRAINT_WRAP);
            set.applyTo(this);




            String strTitle = attributes.getString(R.styleable.SettingItemView_title_text);
            if (!TextUtils.isEmpty(strTitle)) {
                Log.i(TAG, "赋值Title");
                tvTitle.setText(strTitle);
            } else {
                Log.i(TAG, "赋值Title为空");
                tvTitle.setText("赋值Title为空");
            }

        }


    }


    /*
     * converts dip to px
     */
    private int dip2Px(float dip) {
        return (int) (dip * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }

}
