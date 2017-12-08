package com.recyclerview.util;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 自定义SwipeLayout
 * <p/>
 * ViewDragHelper使用三步曲：
 * 1、构造时创建一个ViewDragHelper实例；
 * 2、接管ViewGroup的事件处理；
 * 3、自定义Callback，处理拖拽逻辑。
 * <p/>
 * 作者：余天然 on 16/7/15 上午10:32
 */
public class MySwipeLayout extends FrameLayout {
    private ViewDragHelper dragHelper;
    private View contentView;
    private View menuView;
    private int height;
    private int width;
    private int dragRange;//最大可拖拽距离
    private int curDistance;// 当前拖拽距离
    private Mode mode = Mode.Drag;//显示模式

    enum Mode {
        overlay,//覆盖模式
        Drag//拖动模式
    }


    public MySwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //1、构造时创建一个ViewDragHelper实例
        dragHelper = ViewDragHelper.create(this, callback);
    }

    //获取两个View
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 2) {
            throw new RuntimeException("必须有两个子视图");
        }
        menuView = getChildAt(0);//侧滑菜单
        contentView = getChildAt(1);//内容区域
    }

    //获取尺寸信息
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = contentView.getMeasuredHeight();
        width = contentView.getMeasuredWidth();
        dragRange = menuView.getMeasuredWidth();
    }

    //重写布局
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        menuView.layout(width, 0, width + dragRange, height);
        contentView.layout(0, 0, width, height);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //2、接管ViewGroup的事件处理
        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        return true;
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (dragHelper.continueSettling(true)) {
            postInvalidate();
        }
    }

    private void open() {

    }

    private void close() {

    }

    //3、自定义Callback，处理拖拽逻辑。
    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == contentView;
        }

        // 限制水平拖拽范围(-dragRange,0)
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            left = Math.max(-dragRange, left);
            left = Math.min(0, left);
            return left;
        }

        // 竖直方向始终为 0
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            curDistance = left;
            contentView.offsetLeftAndRight(dx);
            menuView.offsetLeftAndRight(dx);
            postInvalidate();
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            int finalOffset;
            //快速滑动
            if (xvel > 0) {
                finalOffset = 0;
            } else if (xvel < 0) {
                finalOffset = -dragRange;
            }
//            //拖动
//            else {
//                if (1.0f * Math.abs(curDistance) / dragRange > 0.5) {
//                    finalOffset = -dragRange;
//                } else {
//                    finalOffset = 0;
//                }
//            }
//            dragHelper.smoothSlideViewTo(releasedChild, finalOffset, releasedChild.getTop());
//            postInvalidate();

        }

        //子View如果是clickable，必须重写的方法
        public int getViewHorizontalDragRange(View child) {
            return 1;
        }

        public int getViewVerticalDragRange(View child) {
            return 1;
        }
    };

}
