package com.yyc.plugslib.itemView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.yyc.plugslib.R;

/**
 * Created by yaoyongchao on 17-11-1.
 * Dscription: http://www.cnblogs.com/whoislcj/p/5714760.html
 */

public class SettingItemView extends ConstraintLayout {
    private ImageView imgLeft;
    private TextView tvTitle;


    public SettingItemView(Context context) {
        this(context,null);
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initViews(context,attrs);
    }

    /**
     * 初始化
     */
    private void initViews(Context context,AttributeSet attrs) {
        imgLeft = new ImageView(context);
        tvTitle = new TextView(context);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SearchView);
        if (attributes != null) {
            //处理imgLeft图片
            int imgLeftDrawable = attributes.getResourceId(R.styleable.SettingItemView_imgleft_drawable,R.drawable.ic_defalt);
//            imgLeft.setImageDrawable(imgLeftDrawable);


        }


    }



}
