package com.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.recyclerview.superadapter.DataHolder;
import com.recyclerview.superadapter.LayoutWrapper;
import com.recyclerview.superadapter.MultiAdapter;
import com.recyclerview.superadapter.SingleAdapter;
import com.recyclerview.superadapter.SuperAdapter;
import com.recyclerview.superadapter.SuperViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        testSimpleAdapter();
//        testSingleAdapter();
//        testMultiAdapter();
//        testSuperAdapter();
        testSuperAdapter02();
    }

    //普通的Adapter
    private void testSimpleAdapter() {
        rvContent = (RecyclerView) findViewById(R.id.rv_content);
        rvContent.setLayoutManager(new LinearLayoutManager(this));

        SimpleAdapter adapter = new SimpleAdapter(this);
        rvContent.setAdapter(adapter);

        List<SimpleBean> data = new ArrayList<>();
        data.add(new SimpleBean(R.drawable.icon_renzheng, "认证服务"));
        data.add(new SimpleBean(R.drawable.icon_zhihuan, "置换"));
        data.add(new SimpleBean(R.drawable.icon_fenqi, "分期购车"));
        data.add(new SimpleBean(R.drawable.icon_chezhushou, "车助手"));
        adapter.setData(data);
    }

    //单布局的Adapter
    private void testSingleAdapter() {
        rvContent = (RecyclerView) findViewById(R.id.rv_content);
        rvContent.setLayoutManager(new LinearLayoutManager(this));

        SingleAdapter<SimpleBean> adapter = new SingleAdapter<SimpleBean>(this, R.layout.item_simple) {
            @Override
            protected void bindData(SuperViewHolder holder, SimpleBean item) {
                ImageView ivIcon = holder.getView(R.id.iv_icon);
                TextView tvName = holder.getView(R.id.tv_name);

                ivIcon.setImageResource(item.getIcon());
                tvName.setText(item.getName());
            }
        };
        rvContent.setAdapter(adapter);

        List<SimpleBean> data = new ArrayList<>();
        data.add(new SimpleBean(R.drawable.icon_renzheng, "认证服务"));
        data.add(new SimpleBean(R.drawable.icon_zhihuan, "置换"));
        data.add(new SimpleBean(R.drawable.icon_fenqi, "分期购车"));
        data.add(new SimpleBean(R.drawable.icon_chezhushou, "车助手"));
        adapter.setData(data);
    }

    //多布局单实体的Adapter
    private void testMultiAdapter() {
        rvContent = (RecyclerView) findViewById(R.id.rv_content);
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        int[] layoutIds = {R.layout.item_simple, R.layout.item_multi,};
        MultiAdapter<SimpleBean> adapter = new MultiAdapter<SimpleBean>(this, layoutIds) {
            @Override
            public int bindLayout(SimpleBean item, int position) {
                switch (item.getName()) {
                    case "认证服务":
                    case "分期购车":
                        return R.layout.item_simple;
                    case "置换":
                    case "车助手":
                        return R.layout.item_multi;
                }
                return 0;
            }

            @Override
            public void bindData(Context context, SuperViewHolder holder, SimpleBean item, int layoutId, int position) {
                switch (layoutId) {
                    case R.layout.item_simple:
                        ImageView ivIcon = holder.getView(R.id.iv_icon);
                        TextView tvName = holder.getView(R.id.tv_name);
                        ivIcon.setImageResource(item.getIcon());
                        tvName.setText(item.getName());
                        break;
                    case R.layout.item_multi:
                        ImageView ivImg = holder.getView(R.id.iv_img);
                        TextView tvTitle = holder.getView(R.id.tv_title);
                        ivImg.setImageResource(item.getIcon());
                        tvTitle.setText(item.getName());
                        break;
                }
            }
        };
        rvContent.setAdapter(adapter);
        List<SimpleBean> data = new ArrayList<>();
        data.add(new SimpleBean(R.drawable.icon_renzheng, "认证服务"));
        data.add(new SimpleBean(R.drawable.icon_zhihuan, "置换"));
        data.add(new SimpleBean(R.drawable.icon_fenqi, "分期购车"));
        data.add(new SimpleBean(R.drawable.icon_chezhushou, "车助手"));
        adapter.setData(data);
    }

    //多布局多实体的Adapter
    private void testSuperAdapter() {
        rvContent = (RecyclerView) findViewById(R.id.rv_content);
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        int[] layoutIds = {R.layout.item_simple, R.layout.item_super,};
        SuperAdapter adapter = new SuperAdapter(this, layoutIds);
        rvContent.setAdapter(adapter);

        DataHolder<SimpleBean> holderSimple = new DataHolder<SimpleBean>() {
            @Override
            public void bind(Context context, SuperViewHolder holder, SimpleBean item, int position) {
                ImageView ivIcon = holder.getView(R.id.iv_icon);
                TextView tvName = holder.getView(R.id.tv_name);
                ivIcon.setImageResource(item.getIcon());
                tvName.setText(item.getName());
            }
        };

        DataHolder<SuperBean> holderSuper = new DataHolder<SuperBean>() {
            @Override
            public void bind(Context context, SuperViewHolder holder, SuperBean item, int position) {
                TextView tvGroup = holder.getView(R.id.tv_group);
                TextView tvArrow = holder.getView(R.id.tv_arrow);
                tvGroup.setText(item.getName());
                tvArrow.setVisibility(item.isHasArrow() ? View.VISIBLE : View.GONE);
            }
        };

        List<LayoutWrapper> data = new ArrayList<>();
        data.add(new LayoutWrapper(R.layout.item_super, new SuperBean("车城头条", false), holderSuper));

        data.add(new LayoutWrapper(R.layout.item_simple, new SimpleBean(R.drawable.icon_zhihuan, "置换"), holderSimple));
        data.add(new LayoutWrapper(R.layout.item_simple, new SimpleBean(R.drawable.icon_chezhushou, "车助手"), holderSimple));

        data.add(new LayoutWrapper(R.layout.item_super, new SuperBean("车城服务", true), holderSuper));

        data.add(new LayoutWrapper(R.layout.item_simple, new SimpleBean(R.drawable.icon_renzheng, "认证服务"), holderSimple));
        data.add(new LayoutWrapper(R.layout.item_simple, new SimpleBean(R.drawable.icon_fenqi, "分期购车"), holderSimple));

        adapter.setData(data);
    }

    //多布局多实体多列的Adapter
    private void testSuperAdapter02() {
        rvContent = (RecyclerView) findViewById(R.id.rv_content);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        final List<LayoutWrapper> data = new ArrayList<>();
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return data.get(position).getSpanSize();
            }
        });
        rvContent.setLayoutManager(gridLayoutManager);
        int[] layoutIds = {R.layout.item_simple, R.layout.item_super,};
        SuperAdapter adapter = new SuperAdapter(this, layoutIds);
        rvContent.setAdapter(adapter);

        DataHolder<SimpleBean> holderSimple = new DataHolder<SimpleBean>() {
            @Override
            public void bind(Context context, SuperViewHolder holder, SimpleBean item, int position) {
                ImageView ivIcon = holder.getView(R.id.iv_icon);
                TextView tvName = holder.getView(R.id.tv_name);
                ivIcon.setImageResource(item.getIcon());
                tvName.setText(item.getName());
            }
        };

        DataHolder<SuperBean> holderSuper = new DataHolder<SuperBean>() {
            @Override
            public void bind(Context context, SuperViewHolder holder, SuperBean item, int position) {
                TextView tvGroup = holder.getView(R.id.tv_group);
                TextView tvArrow = holder.getView(R.id.tv_arrow);
                tvGroup.setText(item.getName());
                tvArrow.setVisibility(item.isHasArrow() ? View.VISIBLE : View.GONE);
            }
        };

        data.add(new LayoutWrapper(R.layout.item_super, 2, new SuperBean("车城头条", false), holderSuper));

        data.add(new LayoutWrapper(R.layout.item_simple, 2, new SimpleBean(R.drawable.icon_zhihuan, "置换"), holderSimple));
        data.add(new LayoutWrapper(R.layout.item_simple, 2, new SimpleBean(R.drawable.icon_chezhushou, "车助手"), holderSimple));

        data.add(new LayoutWrapper(R.layout.item_super, 2, new SuperBean("车城服务", true), holderSuper));

        data.add(new LayoutWrapper(R.layout.item_simple, 1, new SimpleBean(R.drawable.icon_renzheng, "认证服务"), holderSimple));
        data.add(new LayoutWrapper(R.layout.item_simple, 1, new SimpleBean(R.drawable.icon_fenqi, "分期购车"), holderSimple));

        adapter.setData(data);
    }


}
