package com.expandable.listview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.pjcare.www.listview.R;

public class ExpandableListViewActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;

    private List<String> listGroup = new ArrayList<>();
    private List<ArrayList<String>> listChild = new ArrayList<>();
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);

        expandableListView = (ExpandableListView) findViewById(R.id.elv_listview);

        initDefaultData();

        final MyAdapter adapter = new MyAdapter(this);
        expandableListView.setAdapter(adapter);
    }

    private void initDefaultData() {
        for (int i = 1; i <= 30; i++){
            listGroup.add("群组" + i);
        }

        list = new ArrayList<>();
        list.add("成员" + 1);
        listChild.add(list);

        list = new ArrayList<>();
        list.add("成员" + 1);
        list.add("成员" + 2);
        listChild.add(list);

        list = new ArrayList<>();
        list.add("成员" + 1);
        list.add("成员" + 2);
        list.add("成员" + 3);
        listChild.add(list);

        list = new ArrayList<>();
        list.add("成员" + 1);
        list.add("成员" + 2);
        list.add("成员" + 3);
        list.add("成员" + 4);
        listChild.add(list);

        list = new ArrayList<>();
        list.add("成员" + 1);
        list.add("成员" + 2);
        list.add("成员" + 3);
        list.add("成员" + 4);
        list.add("成员" + 5);
        listChild.add(list);

        list = new ArrayList<>();
        list.add("成员" + 1);
        list.add("成员" + 2);
        list.add("成员" + 3);
        list.add("成员" + 4);
        list.add("成员" + 5);
        list.add("成员" + 6);
        listChild.add(list);

        list = new ArrayList<>();
        list.add("成员" + 1);
        list.add("成员" + 2);
        list.add("成员" + 3);
        list.add("成员" + 4);
        list.add("成员" + 5);
        list.add("成员" + 6);
        list.add("成员" + 7);
        listChild.add(list);

        list = new ArrayList<>();
        list.add("成员" + 1);
        list.add("成员" + 2);
        list.add("成员" + 3);
        list.add("成员" + 4);
        list.add("成员" + 5);
        list.add("成员" + 6);
        list.add("成员" + 7);
        list.add("成员" + 8);
        listChild.add(list);

        list = new ArrayList<>();
        list.add("成员" + 1);
        list.add("成员" + 2);
        list.add("成员" + 3);
        list.add("成员" + 4);
        list.add("成员" + 5);
        list.add("成员" + 6);
        list.add("成员" + 7);
        list.add("成员" + 8);
        list.add("成员" + 9);
        listChild.add(list);

        list = new ArrayList<>();
        list.add("成员" + 1);
        list.add("成员" + 2);
        list.add("成员" + 3);
        list.add("成员" + 4);
        list.add("成员" + 5);
        list.add("成员" + 6);
        list.add("成员" + 7);
        list.add("成员" + 8);
        list.add("成员" + 9);
        list.add("成员" + 10);
        listChild.add(list);

    }

    class MyAdapter extends BaseExpandableListAdapter{

        private Context mContext;

        public MyAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public int getGroupCount() {
            return listGroup.size();
        }

        @Override
        public int getChildrenCount(int i) {
            try {
                return listChild.get(i).size();
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
            return 0;
        }

        @Override
        public Object getGroup(int i) {
            return listGroup.get(i);
        }

        @Override
        public Object getChild(int i, int i1) {
            return listChild.get(i).get(i1);
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            ViewHolderGroup groupHolder;
            if(view == null){
                view = LayoutInflater.from(mContext).inflate(
                        R.layout.layout_group, viewGroup, false);
                groupHolder = new ViewHolderGroup();
                groupHolder.tv_group_name = (TextView) view.findViewById(R.id.tv_group);
                view.setTag(groupHolder);
            }else{
                groupHolder = (ViewHolderGroup) view.getTag();
            }
            groupHolder.tv_group_name.setText(listGroup.get(i));
            if(b){
                groupHolder.tv_group_name.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        getResources().getDrawable(R.mipmap.ic_up),
                        null);
            } else {
                groupHolder.tv_group_name.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        getResources().getDrawable(R.mipmap.ic_down),
                        null);
            }
            return view;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            ViewHolderItem itemHolder;
            if(view == null){
                view = LayoutInflater.from(mContext).inflate(
                        R.layout.layout_child, viewGroup, false);
                itemHolder = new ViewHolderItem();
                itemHolder.tv_name = (TextView) view.findViewById(R.id.tv_child);
                view.setTag(itemHolder);
            }else{
                itemHolder = (ViewHolderItem) view.getTag();
            }
            itemHolder.tv_name.setText(listChild.get(i).get(i1));
            return view;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }

        private class ViewHolderGroup{
            private TextView tv_group_name;
        }

        private class ViewHolderItem{
            private TextView tv_name;
        }
    }

}
