package com.test.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/********
 *
 * @author： Jeffray zhanfeifei1991@163.com
 *
 * @time： 2016/4/21 11:09
 *
 * @description： 自定义的不可滑动的scrollview
 *
 * @Copyright： 2016 www.modosdom.com Inc. All rights reserved.
 ********/
public class NoScrollGridView extends GridView { 
    public NoScrollGridView(Context context, AttributeSet attrs) { 
        super(context, attrs); 
    } 

    public NoScrollGridView(Context context) { 
        super(context); 
    }

    public NoScrollGridView(Context context, AttributeSet attrs, int defStyle) { 
        super(context, attrs, defStyle); 
    } 

    @Override 
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { 

        int expandSpec = MeasureSpec.makeMeasureSpec( 
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST); 
        super.onMeasure(widthMeasureSpec, expandSpec); 
    } 
}
