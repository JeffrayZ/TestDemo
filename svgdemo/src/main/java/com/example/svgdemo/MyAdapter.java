/**
 * Copyright (C), 2019-2019, 上海溢米教育科技有限公司
 * FileName: MyAdapter
 * Author: Jeffray
 * Date: 2019/5/5 15:05
 * Description: recyclerview适配器
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.example.svgdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @ProjectName: TestDemo
 * @Package: com.example.svgdemo
 * @ClassName: MyAdapter
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2019/5/5 15:05
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/5 15:05
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private String[] array;
    private Context context;

    public MyAdapter(String[] array,Context con) {
        this.array = array;
        context = con;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.content,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tv.setText(array[i]);

        viewHolder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,array[i],Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
