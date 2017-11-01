package com.yyc.customviews.search;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.yyc.customviews.R;


public class ClearEditText extends EditText implements View.OnTouchListener , View.OnFocusChangeListener{

    final Drawable mClear = getResources().getDrawable(R.drawable.ic_search_clear);
    /**
     * @param context
     */
    public ClearEditText(Context context) {
        super(context);
        initClearEditTextRef();
    }

    /**
     * @param context
     * @param attrs
     */
    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initClearEditTextRef();
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initClearEditTextRef();
    }

    private void initClearEditTextRef() {

        mClear.setBounds(0, 0, mClear.getIntrinsicHeight(), mClear.getIntrinsicHeight());

        doClearDrawable();
        setHeight(mClear.getIntrinsicHeight() + 5 * getResources().getDimensionPixelSize(R.dimen.search_top));
        setOnTouchListener(this);
        addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                doClearDrawable();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                    int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        setOnFocusChangeListener(this);
    }

    /**
     * 
     */
    private void doClearDrawable() {
        if("".equals(getText().toString()) || !isFocused()) {
            setClearDrawableNull();
            return;
        }
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], mClear, getCompoundDrawables()[3]);
    }

    /**
     * 
     */
    private void setClearDrawableNull() {
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], null, getCompoundDrawables()[3]);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if(getCompoundDrawables()[2] == null) {
            return false;
        }

        if((event.getAction() != MotionEvent.ACTION_UP)
                || event.getX() <= (getWidth() - getPaddingRight() - mClear.getIntrinsicWidth())) {
            return false;

        }
        getText().clear();

        return false;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        doClearDrawable();
    }
}