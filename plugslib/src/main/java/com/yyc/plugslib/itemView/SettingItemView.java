package com.yyc.plugslib.itemView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.yyc.plugslib.R;


/**
 * Created by yaoyongchao on 17-11-1.
 * Dscription: http://www.cnblogs.com/whoislcj/p/5714760.html
 * 回家继续搞。
 */

public class SettingItemView extends ConstraintLayout {
    private static final String TAG = "SettingItemView";
    private static final boolean isLog = true;
    private static final int IMGLEFT_ID = 0x1000;
    private static final int TVTITLE_ID = 0x1001;
    private static final int BORDER_TOP_ID = 0x1002;
    private static final int BORDER_BOTTOM_ID = 0x1003;
    private static final int TVLEFT_ID = 0x1004;
    private static final int CHK_ID = 0x1005;
    private static final String BORDER_COLOR = "#CCCCCC";
    //    private static final String BORDER_COLOR = "#FF0000";
    private  int sBorderHeight = 1;
    private static final int  IMGLEFTMARGIN = 15;
    private static final int  TVTITLEMARGIN = 10;
    private static final int  TVLEFTMARGIN = 15;
    private ImageView imgLeft;
    private TextView tvTitle;
    private float goneMarginLeft = 20;
    private float tvTitleSize = 16;
    private ConstraintSet set;
    private float imgWidthDefault;
    private float imgHeightDefault;

    private TextView borderTop;
    private TextView borderBottom;
    //    private TextView tvValue;
    private boolean isShowBorder = true;
    private boolean isShowBorderTop = false;
    private boolean isShowBorderBottom = true;
    private ImageView imgArrows ;

    private CheckBox checkBox;
    private boolean checkable = false;
    private boolean isShowCheck = true;
    private boolean isShowCheckBtn = true;
    private boolean isChecked = false;
    private int  chkBtnbg = R.drawable.ic_arrows_left;
    private String chkText = "";
    private static final int CHKMARGIN = 5;
    private float chkTvSize = 16;
    private float chkMargin = 15;
    private float chkMarginRight = chkMargin;

    private int titleTextColor = Color.parseColor("#000000");
    private int chkTextColor = Color.parseColor("#808080");



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

    /**
     * 初始化
     */
    private void initViews(Context context, AttributeSet attrs) {
        log("initViews");
        set = new ConstraintSet();
        set.clone(this);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SettingItemView);

        if (attributes != null) {
            initBorder(set,context,attributes);
            initImgLeft(set,context,attributes);
            initTvTitle(set,context,attributes);
            initChk(set,context,attributes);

            attributes.recycle();
        }


    }

    private void initChk(ConstraintSet set, Context context, TypedArray attributes) {
        log("initImaArrows");
        isShowCheck = attributes.getBoolean(R.styleable.SettingItemView_is_show_check, isChecked);
        if (!isShowCheck)
            return;

        checkBox = new CheckBox(context);
        checkBox.setId(CHK_ID);
        addView(checkBox);

        set.connect(checkBox.getId(),ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP);
        set.connect(checkBox.getId(),ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM);
        set.connect(checkBox.getId(),ConstraintSet.RIGHT,ConstraintSet.PARENT_ID,ConstraintSet.RIGHT);
        set.constrainHeight(checkBox.getId(), ConstraintSet.WRAP_CONTENT);
        set.constrainWidth(checkBox.getId(), ConstraintSet.WRAP_CONTENT);

        //set margin
        chkMarginRight = attributes.getDimension(R.styleable.SettingItemView_chk_marginRight, dip2Px(CHKMARGIN));
        set.setMargin(checkBox.getId(),ConstraintSet.END, (int) chkMarginRight);

        checkable = attributes.getBoolean(R.styleable.SettingItemView_checkable, checkable);
//        isShowCheckText = attributes.getBoolean(R.styleable.SettingItemView_is_show_checktext, isShowCheckText);
        isShowCheckBtn = attributes.getBoolean(R.styleable.SettingItemView_is_show_checkbtn, isShowCheckBtn);
        isChecked = attributes.getBoolean(R.styleable.SettingItemView_isChecked, isChecked);
        chkBtnbg = attributes.getResourceId(R.styleable.SettingItemView_chk_drawable, chkBtnbg);
        chkText = attributes.getString(R.styleable.SettingItemView_chk_text);
        chkTvSize = attributes.getDimension(R.styleable.SettingItemView_chk_textSize,dip2Px(chkTvSize));
        chkTextColor = attributes.getResourceId(R.styleable.SettingItemView_chk_textColor, chkTextColor);

        //set CheckBox is Checkable
        checkBox.setClickable(checkable);
        checkBox.setChecked(isChecked);
        //set checkBox buttonDrawable and Set CheckBox is show Drawable
        if (isShowCheckBtn == true) {
            checkBox.setButtonDrawable(chkBtnbg);
            Log.d("isBtn" ,"--ture" );
        } else {
            checkBox.setButtonDrawable(null);
            Log.d("isBtn" ,"--false" );
        }
        //set checkBox text
        checkBox.getPaint().setTextSize(chkTvSize);
        checkBox.setTextColor(chkTextColor);
        checkBox.setText(chkText);

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

        Log.i(TAG,"sBorderHeight:" + sBorderHeight);

        isShowBorder = attributes.getBoolean(R.styleable.SettingItemView_is_show_border,isShowBorder);
        isShowBorderTop = attributes.getBoolean(R.styleable.SettingItemView_is_show_border_top,isShowBorderTop);
        isShowBorderBottom = attributes.getBoolean(R.styleable.SettingItemView_is_show_border_bottom,isShowBorderBottom);
        Log.i(TAG,"isShowBorder:" + isShowBorder);
        set.setVisibility(borderTop.getId(),((isShowBorder && isShowBorderTop) ? View.VISIBLE : View.GONE));
        set.setVisibility(borderBottom.getId(),((isShowBorder && isShowBorderBottom) ? View.VISIBLE : View.GONE));
//
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
//        set.setMargin(tvTitle.getId(),ConstraintSet.START,dip2Px(TVTITLEMARGIN));
        set.setMargin(tvTitle.getId(),ConstraintSet.START, (int) attributes.getDimension(R.styleable.SettingItemView_title_text_marginleft,dip2Px(TVTITLEMARGIN)));
        set.setGoneMargin(tvTitle.getId(),ConstraintSet.START, (int) attributes.getDimension(R.styleable.SettingItemView_title_goneMarginLeft, dip2Px(goneMarginLeft)));

        String strTitle = attributes.getString(R.styleable.SettingItemView_title_text);
        tvTitleSize = attributes.getDimension(R.styleable.SettingItemView_title_textSize,  dip2Px(tvTitleSize));
        titleTextColor = attributes.getResourceId(R.styleable.SettingItemView_title_textColor, titleTextColor);
//        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,tvTitleSize);
        tvTitle.getPaint().setTextSize(tvTitleSize);
        tvTitle.setTextColor(titleTextColor);
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

        int imgLeftDrawable = attributes.getResourceId(R.styleable.SettingItemView_imgleft_drawable, -1);
        if (imgLeftDrawable == -1) {
            imgLeft.setVisibility(View.GONE);
        } else {
            set.connect(imgLeft.getId(),ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP);
            set.connect(imgLeft.getId(),ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM);
            set.connect(imgLeft.getId(),ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT);
            set.constrainHeight(imgLeft.getId(), ConstraintSet.WRAP_CONTENT);
            set.constrainWidth(imgLeft.getId(), ConstraintSet.WRAP_CONTENT);
            set.setMargin(imgLeft.getId(),ConstraintSet.START,dip2Px(IMGLEFTMARGIN));

            imgLeft.setVisibility(View.VISIBLE);
            imgLeft.setImageResource(imgLeftDrawable);
//            imgLeft.setBackgroundColor(Color.GREEN);
            imgHeightDefault = attributes.getDimension(R.styleable.SettingItemView_imgleft_height, 0);
            imgWidthDefault = attributes.getDimension(R.styleable.SettingItemView_imgleft_width, 0);
            set.constrainHeight(imgLeft.getId(), (int) imgHeightDefault);
            set.constrainWidth(imgLeft.getId(), (int) imgWidthDefault);
        }
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

    public void setRightImg(Drawable drawable){
        if (checkBox != null)
            checkBox.setButtonDrawable(drawable);
    }

    public void setRigntText(int resId) {
        if (checkBox != null)
            checkBox.setText(resId);
    }

    public void setRightText(CharSequence text) {
        if (checkBox != null)
            checkBox.setText(text);
    }

    public void setRightTextSize(float size) {
        if (checkBox != null)
            checkBox.setTextSize(size);
    }

    public void setRightTextColor(int color) {
        if (checkBox != null)
            checkBox.setTextColor(color);
    }

    public void setRigntImgVisiable(int visibility){
        if (checkBox != null)
            checkBox.setButtonDrawable(visibility == View.VISIBLE ? chkBtnbg : null);
    }

    public CheckBox getCheckBox() {
        if (checkBox != null)
            return checkBox;
        return  null;
    }

    public ImageView getLeftImg() {
        if (imgLeft != null)
            return imgLeft;
        return  null;
    }

    public TextView getTvTitle() {
        if (tvTitle != null)
            return  tvTitle;
        return null;
    }

    public CharSequence getTvTitleText() {
        if (tvTitle != null)
            return  tvTitle.getText();
        return null;
    }

    public CharSequence getRightText() {
        if (checkBox != null)
            return  checkBox.getText();
        return null;
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        if (checkBox != null)
            checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
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
