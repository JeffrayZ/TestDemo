package com.test.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.test.R;

import java.util.List;

/**
 * Created by admin on 2016/11/25.
 */

public class ConstraintlayoutAdapter extends BaseQuickAdapter<Integer> {

    public ConstraintlayoutAdapter(View contentView, List<Integer> data) {
        super(contentView, data);
    }

    public ConstraintlayoutAdapter(List<Integer> data) {
        super(data);
    }

    public ConstraintlayoutAdapter(int layoutResId, List<Integer> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Integer integer) {
        baseViewHolder.setImageResource(R.id.imageView4,R.drawable.android);
    }
}
