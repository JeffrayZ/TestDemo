package com.test.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.test.R;
import com.test.bean.Status;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class QuickAdapter extends BaseQuickAdapter<Status> {

    public QuickAdapter(View contentView, List<Status> data) {
        super(contentView, data);
    }

    public QuickAdapter(List<Status> data) {
        super(data);
    }

    public QuickAdapter(int layoutResId, List<Status> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {
        helper.setText(R.id.tv_test_footer, item.getContent());
    }
}
