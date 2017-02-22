package cn.pjcare.www.listview;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ChoiceModeMultipleModalActivity extends Activity {
	ListView mListView = null;
	MyListAdapter mAdapter;
	ModeCallback mCallback;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		mListView = (ListView)findViewById(R.id.list);
		mAdapter = new MyListAdapter(this,mListView);
		mListView.setAdapter(mAdapter);
		
		mCallback = new ModeCallback();
		mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);	
		mListView.setMultiChoiceModeListener(mCallback);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
                   Toast.makeText(ChoiceModeMultipleModalActivity.this, "ѡ����һ��item", 300).show();
			} 
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.action_choice){
			//����ʹ����һ�㼼����ʵ�ִ���ѡ��״̬ ����0��item ��ѡ��
			mListView.setItemChecked(0,true);
			mListView.clearChoices();
			mCallback.updateSeletedCount();
		}
		return super.onOptionsItemSelected(item);
	}
	
	private class ModeCallback implements ListView.MultiChoiceModeListener {
        private View mMultiSelectActionBarView;
        private TextView mSelectedCount;
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // actionmode�Ĳ˵�����
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.multi_select_menu, menu);
            if (mMultiSelectActionBarView == null) {
                mMultiSelectActionBarView = LayoutInflater.from(ChoiceModeMultipleModalActivity.this)
                    .inflate(R.layout.list_multi_select_actionbar, null);

                mSelectedCount =
                    (TextView)mMultiSelectActionBarView.findViewById(R.id.selected_conv_count);
            }
            mode.setCustomView(mMultiSelectActionBarView);
            ((TextView)mMultiSelectActionBarView.findViewById(R.id.title)).setText(R.string.select_item);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            if (mMultiSelectActionBarView == null) {
                ViewGroup v = (ViewGroup)LayoutInflater.from(ChoiceModeMultipleModalActivity.this)
                    .inflate(R.layout.list_multi_select_actionbar, null);
                mode.setCustomView(v);
                mSelectedCount = (TextView)v.findViewById(R.id.selected_conv_count);
            }            
            //���²˵���״̬
            MenuItem mItem = menu.findItem(R.id.action_slelect);
            if(mListView.getCheckedItemCount() == mAdapter.getCount()){
            	mItem.setTitle(R.string.action_deselect_all);
            }else{
            	mItem.setTitle(R.string.action_select_all);
            }
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_slelect:
                    if(mListView.getCheckedItemCount() == mAdapter.getCount()){
                    	unSelectedAll();
                    }else{
                    	selectedAll();
                    }
                    mAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
        	mListView.clearChoices();
        }

        @Override
        public void onItemCheckedStateChanged(ActionMode mode,
                int position, long id, boolean checked) {
            updateSeletedCount();
            mode.invalidate();
            mAdapter.notifyDataSetChanged();
        }
        
        public void updateSeletedCount(){
        	mSelectedCount.setText(Integer.toString(mListView.getCheckedItemCount()));
        }
    }

	public void selectedAll(){
		for(int i= 0; i< mAdapter.getCount(); i++){
			mListView.setItemChecked(i, true);
		}
		mCallback.updateSeletedCount();
	}
	
	public void unSelectedAll(){
		mListView.clearChoices();
		mListView.setItemChecked(0,false);
		mCallback.updateSeletedCount();
	}
}
