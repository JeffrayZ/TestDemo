package com.test.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.test.R;
import com.test.bean.DataBean;
import com.test.bean.JsonBean;
import com.test.util.HttpUtils;
import com.test.view.NoScrollGridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TestJsonActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    List<JsonBean> list = new ArrayList<JsonBean>();
    NoScrollGridView nsgv;
    MyAdapter adapter;
    List<String> nameList = new ArrayList<>();
    LinearLayout llCheckBox;
    CheckBox[] strArray = new CheckBox[4];
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_json);

        initJsonData(this);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initJsonData(final TestJsonActivity testJsonActivity) {
        RequestParams params = new RequestParams();
        params.put("product_category_id","29");
        HttpUtils.doHttpGetWithParams(testJsonActivity
                , "http://us.modsdom.com/weiye/index.php/WechatPub/ElecCommence/Html/ProductAjax/getProductFilters"
                , params
                , new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            String code = response.getString("result");
                            if("200".equals(code)){
                                JSONArray jsonArray = response.getJSONArray("data");
                                JSONObject jsonObject;
                                JsonBean bean;
                                for (int i = 0; i < jsonArray.length(); i++){
                                    bean = new JsonBean();
                                    jsonObject = jsonArray.getJSONObject(i);
                                    bean.setPropertyId(jsonObject.getString("property_id_1"));
                                    bean.setPropertyName(jsonObject.getString("property_name_2"));
                                    bean.setPropertyNameTitle(jsonObject.getString("property_name_1"));
                                    bean.setShowInTopfilter(jsonObject.getString("show_in_topfilter_is"));
                                    list.add(bean);
                                }

                                List<JsonBean> all = new ArrayList<JsonBean>();
                                all.add(list.get(0));
                                all.add(list.get(1));
                                all.add(list.get(4));
                                analysisOfData(list);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    }
                });
    }

    PopupWindow pop;
    private void initPop(final TestJsonActivity testJsonActivity) {
        View view = getLayoutInflater().inflate(R.layout.popupwindow,null,false);

        nsgv = (NoScrollGridView) view.findViewById(R.id.nsgv_json);
        adapter = new MyAdapter();

        pop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        pop.setOutsideTouchable(false);
        pop.setFocusable(true);
        pop.setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        pop.showAsDropDown(llCheckBox);

        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Toast.makeText(testJsonActivity,"消失吧！", Toast.LENGTH_SHORT).show();
                strArray[position].setChecked(false);
            }
        });

        nsgv.setAdapter(adapter);
    }

    CheckBox cbOne;
    CheckBox cbTwo;
    CheckBox cbThree;
    CheckBox cbFour;
    private void initView() {
        llCheckBox = (LinearLayout) findViewById(R.id.ll_checkbox);

        cbOne = (CheckBox) findViewById(R.id.cb_one);
        cbOne.setOnCheckedChangeListener(this);
        cbTwo = (CheckBox) findViewById(R.id.cb_two);
        cbTwo.setOnCheckedChangeListener(this);
        cbThree = (CheckBox) findViewById(R.id.cb_three);
        cbThree.setOnCheckedChangeListener(this);
        cbFour = (CheckBox) findViewById(R.id.cb_four);
        cbFour.setOnCheckedChangeListener(this);

        strArray[0] = cbOne;
        strArray[1] = cbTwo;
        strArray[2] = cbThree;
        strArray[3] = cbFour;
    }

    List<DataBean> dbList = new ArrayList<>();
    public void analysisOfData(List<JsonBean> list){
        String curId;
        String lastId = null;
        String title = null;
        List<String> strList = null;
        DataBean dataBean = null;

        for (JsonBean bean : list) {
            if("1".equals(bean.getShowInTopfilter())){
                curId = bean.getPropertyId();
                if(curId.equals(lastId)){
                    strList.add(bean.getPropertyName());
                } else {
                    if(strList != null && strList.size() > 0){
                        dataBean.setName(strList);
                        dataBean.setTitle(title);
                        dataBean.setId(lastId);
                        dbList.add(dataBean);
                    }
                    dataBean = new DataBean();
                    strList = new ArrayList<>();
                    lastId = curId;
                    title = bean.getPropertyNameTitle();
                    strList.add(bean.getPropertyName());
                }
            }
        }

        // 添加最后一条数据
        if(strList != null && strList.size() > 0){
            dataBean.setName(strList);
            dataBean.setTitle(title);
            dataBean.setId(lastId);
            dbList.add(dataBean);
        }
        Toast.makeText(TestJsonActivity.this,dbList.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        nameList.clear();
        switch (buttonView.getId()){
            case R.id.cb_one:
                if(isChecked){
                    nameList.addAll(dbList.get(0).getName());
                    if(pop == null){
                        initPop(TestJsonActivity.this);
                    } else {
                        pop.showAsDropDown(llCheckBox);
                        adapter.notifyDataSetChanged();
                    }
                }

                position = 0;
                break;
            case R.id.cb_two:
                if(isChecked){
                    nameList.addAll(dbList.get(1).getName());
                    if(pop == null){
                        initPop(TestJsonActivity.this);
                    } else {
                        pop.showAsDropDown(llCheckBox);
                        adapter.notifyDataSetChanged();
                    }
                }

                position = 1;
                break;
            case R.id.cb_three:
                if(isChecked){
                    nameList.addAll(dbList.get(2).getName());
                    if(pop == null){
                        initPop(TestJsonActivity.this);
                    } else {
                        pop.showAsDropDown(llCheckBox);
                        adapter.notifyDataSetChanged();
                    }
                }

                position = 2;
                break;
            case R.id.cb_four:
                if(isChecked){
                    nameList.addAll(dbList.get(3).getName());
                    if(pop == null){
                        initPop(TestJsonActivity.this);
                    } else {
                        pop.showAsDropDown(llCheckBox);
                        adapter.notifyDataSetChanged();
                    }
                }

                position = 3;
                break;
            default:
                break;
        }
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return nameList.size();
        }

        @Override
        public Object getItem(int position) {
            return nameList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyViewHolder holder;
            if(convertView == null){
                holder = new MyViewHolder();
                convertView = View.inflate(TestJsonActivity.this,android.R.layout.simple_list_item_1,null);
                holder.tvName = (TextView) convertView.findViewById(android.R.id.text1);
                convertView.setTag(holder);
            } else {
                holder = (MyViewHolder) convertView.getTag();
            }

            holder.tvName.setText(nameList.get(position));
            return convertView;
        }

        class MyViewHolder {
            TextView tvName;
        }
    }
}
