package com.wxk.mytitlebar;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.wxk.mytitlebar.ITitleBar.AbsTitleBar;

/**
 * Created by Administrator on 2017/3/30
 */

public class CommonTitleBar extends AbsTitleBar<CommonTitleBar.Builder.CommonTitleBarParams>{

    public CommonTitleBar(CommonTitleBar.Builder.CommonTitleBarParams params) {
        super(params);
    }

    @Override
    public int getLayoutId() {
        return R.layout.title_bar;
    }

    @Override
    public void bindView() {

        setText(R.id.title, getParams().mTitle);
        setText(R.id.right_text, getParams().mRightText);

        setOnClickListener(R.id.right_text, getParams().mRightClickListener);
        setOnClickListener(R.id.back, getParams().mLeftClickListener);

        setRightRes(R.id.right_text, getParams().mRightRes);
    }

    public static class Builder extends AbsTitleBar.Builder{

        public CommonTitleBarParams P;

        //指定要加载的布局
        public Builder(Context context, ViewGroup parent) {
            super(context, parent);
            P = new CommonTitleBarParams(context, parent);
        }

        //使用默认布局
        public Builder(Context context) {
            super(context, null);
            P = new CommonTitleBarParams(context, null);
        }

        //设置所有效果
        public CommonTitleBar.Builder setTitle(String title){

            P.mTitle = title;
            return this;
        }

        public CommonTitleBar.Builder setRightTitle(String rightTitle){

            P.mRightText = rightTitle;
            return this;
        }

        public CommonTitleBar.Builder setRightIcon(int rightRes){

            P.mRightRes = rightRes;
            return this;
        }

        public CommonTitleBar.Builder setRightClickListener(View.OnClickListener rightClickListener){

            P.mRightClickListener = rightClickListener;
            return this;
        }

        public CommonTitleBar.Builder setLeftClickListener(View.OnClickListener leftClickListener){

            P.mLeftClickListener = leftClickListener;
            return this;
        }

        @Override
        public CommonTitleBar build() {
            CommonTitleBar titleBar = new CommonTitleBar(P);
            return titleBar;
        }

        public static class CommonTitleBarParams extends AbsTitleBar.Builder.TitleBarParams{

            //标题
            public String mTitle;
            //左边view的文本
            public String mRightText;
            //左边view的icon
            public int mRightRes;
            //左边view的点击事件
            public View.OnClickListener mRightClickListener;
            //左边view的点击事件，默认为关闭当前activity
            public View.OnClickListener mLeftClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ((Activity)mContext).finish();
                }
            };

            public CommonTitleBarParams(Context context, ViewGroup parent) {
                super(context, parent);
            }
        }
    }
}
