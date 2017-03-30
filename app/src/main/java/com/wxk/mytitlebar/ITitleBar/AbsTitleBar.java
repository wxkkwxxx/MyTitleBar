package com.wxk.mytitlebar.ITitleBar;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/3/30
 */

public abstract class AbsTitleBar<P extends AbsTitleBar.Builder.TitleBarParams> implements ITitleBar{

    private View mTitleBarView;
    private P mParams;

    public AbsTitleBar(P params) {
        this.mParams = params;
        createAndBindView();
    }

    private void createAndBindView() {

        //判断是否制定了父布局,如果未指定则获取系统布局R.layout.screen_simple
        //这里不获取android.R.id.content主要是因为如果activity的根布局是RelativeLayout会出现重叠的效果
        if(mParams.mParent == null){

            ViewGroup root = (ViewGroup) ((Activity)mParams.mContext).getWindow().getDecorView();
            mParams.mParent = (ViewGroup) root.getChildAt(0);
        }

        mTitleBarView = LayoutInflater.from(mParams.mContext).inflate(getLayoutId(), mParams.mParent, false);
        mParams.mParent.addView(mTitleBarView, 0);

        //绑定参数
        bindView();
    }

    protected void setText(int viewId, CharSequence text){

        TextView tv =getView(viewId);
        if (!TextUtils.isEmpty(text)) {
            tv.setVisibility(View.VISIBLE);
            tv.setText(text);
        }
    }

    protected void setOnClickListener(int viewId, View.OnClickListener listener){

        View view = getView(viewId);
        view.setOnClickListener(listener);
    }

    protected void setRightRes(int viewId, int rightRes){

        View view = getView(viewId);
        if(rightRes != 0){
            view.setVisibility(View.VISIBLE);
            view.setBackgroundResource(rightRes);
        }
    }

    private <V extends View> V getView(int viewId){

        View view = mTitleBarView.findViewById(viewId);
        return (V) view;
    }

    public P getParams() {
        return mParams;
    }

    public abstract static class Builder{

        public Builder(Context context, ViewGroup parent) {
        }

        public abstract AbsTitleBar build();

        public static class TitleBarParams{

            public Context mContext;
            public ViewGroup mParent;

            public TitleBarParams(Context context, ViewGroup parent) {
                this.mContext = context;
                this.mParent = parent;
            }
        }
    }
}
