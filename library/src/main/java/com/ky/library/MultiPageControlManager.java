package com.ky.library;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 */
public class MultiPageControlManager {
    public static final int NO_LAYOUT_ID = 0;

    public static int BASE_RETRY_LAYOUT_ID = NO_LAYOUT_ID;
    public static int BASE_EMPTY_LAYOUT_ID = NO_LAYOUT_ID;
    public static int BASE_LOADING_LAYOUT_ID = NO_LAYOUT_ID;

    public MultiPageControlLayout mLoadingAndRetryLayout;



    private MultiPageControlManager() {


    }

    public static MultiPageControlManager getInstance() {
        return new MultiPageControlManager();
    }

    /**
     * 设置默认布局(建议在Application中设置)
     *
     * @param loadingId 加载布局
     * @param retryId   重试布局
     * @param emptyId   空白布局(没有数据)
     */
    public static void defaultLayout(int loadingId, int retryId, int emptyId) {
        BASE_LOADING_LAYOUT_ID = loadingId;
        BASE_RETRY_LAYOUT_ID = retryId;
        BASE_EMPTY_LAYOUT_ID = emptyId;
    }


    public MultiPageControlManager init(View view) {
        if (BASE_LOADING_LAYOUT_ID == NO_LAYOUT_ID || BASE_RETRY_LAYOUT_ID == NO_LAYOUT_ID || BASE_EMPTY_LAYOUT_ID == NO_LAYOUT_ID) {
            throw new IllegalArgumentException(" No default layout or call is set init(Object activityOrFragmentOrView, int loadingId, int retryId, int emptyId)");
        }
        return init(view, BASE_LOADING_LAYOUT_ID, BASE_RETRY_LAYOUT_ID, BASE_EMPTY_LAYOUT_ID);
    }

    public MultiPageControlManager init(View view, int loadingId, int retryId, int emptyId) {

        ViewGroup contentParent = null;
        Context context;
        int index = 0;//旧的 主要布局的位置
        View oldContent;//这是旧的 主要的布局


        contentParent = (ViewGroup) (view.getParent());//拿到父容器布局
        context = view.getContext();

        int childCount = contentParent.getChildCount();//父容器布局的个数
        //get contentParent
        oldContent = (View) view;
        for (int i = 0; i < childCount; i++) {
            if (contentParent.getChildAt(i) == oldContent) {
                index = i;
                break;
            }
        }

        contentParent.removeView(oldContent);//先把主布局移除
        //setup content layout
        mLoadingAndRetryLayout = new MultiPageControlLayout(context);
        ViewGroup.LayoutParams lp = oldContent.getLayoutParams();
        contentParent.addView(mLoadingAndRetryLayout, index, lp);//把包含4种布局的容器 加入到最顶级布局
        /*4种布局*/
        mLoadingAndRetryLayout.setContentView(oldContent);
        mLoadingAndRetryLayout.setEmptyView(emptyId);
        mLoadingAndRetryLayout.setLoadingView(loadingId);
        mLoadingAndRetryLayout.setRetryView(retryId);

        return this;
    }

    public void setOnRetryClickListener(final OnLoadingAndRetryListener onRetryClickListener) {
        if (mLoadingAndRetryLayout == null) {
            throw new IllegalArgumentException("Please initialize it first init()");
        }
        mLoadingAndRetryLayout.getRetryView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRetryClickListener.onClick();
            }
        });
    }

    public void setOnEmptyClickListener(final OnLoadingAndRetryListener onEmptyClickListener) {
        if (mLoadingAndRetryLayout == null) {
            throw new IllegalArgumentException("Please initialize it first init()");
        }
        mLoadingAndRetryLayout.getEmptyView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmptyClickListener.onClick();
            }
        });
    }

    public void setOnEmptyChildClickListener(int id, final OnLoadingAndRetryListener onEmptyChildClickListener) {
        if (mLoadingAndRetryLayout == null) {
            throw new IllegalArgumentException("Please initialize it first init()");
        }
        mLoadingAndRetryLayout.getEmptyView().findViewById(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmptyChildClickListener.onClick();
            }
        });
    }

    public void setOnRetryChildClickListener(int id, final OnLoadingAndRetryListener onRetryChildClickListener) {
        if (mLoadingAndRetryLayout == null) {
            throw new IllegalArgumentException("Please initialize it first init()");
        }
        mLoadingAndRetryLayout.getRetryView().findViewById(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRetryChildClickListener.onClick();
            }
        });
    }


    public void showLoading() {
        if (mLoadingAndRetryLayout == null) {
            throw new IllegalArgumentException("Please initialize it first init()");
        }
        mLoadingAndRetryLayout.showLoading();
    }

    public void showRetry() {
        if (mLoadingAndRetryLayout == null) {
            throw new IllegalArgumentException("Please initialize it first init()");
        }
        mLoadingAndRetryLayout.showRetry();
    }

    public void showContent() {
        if (mLoadingAndRetryLayout == null) {
            throw new IllegalArgumentException("Please initialize it first init()");
        }
        mLoadingAndRetryLayout.showContent();
    }

    public void showEmpty() {
        if (mLoadingAndRetryLayout == null) {
            throw new IllegalArgumentException("Please initialize it first init()");
        }
        mLoadingAndRetryLayout.showEmpty();
    }


    public interface OnLoadingAndRetryListener {
        void onClick();
    }
}
