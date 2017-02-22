package cn.pjcare.www.listview;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ChoiceModeMultipleActivity extends Activity {
	ListView mListView = null;
	MyListAdapter mAdapter;
    private View mMultiSelectActionBarView;
    private TextView mSelectedCount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		setContentView(R.layout.activity_list);
		
		mListView = (ListView)findViewById(R.id.list);
		mAdapter = new MyListAdapter(this,mListView);
		mListView.setAdapter(mAdapter);

		mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);	

		mListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				mAdapter.notifyDataSetChanged();
				updateSeletedCount();
			} 
		});
		
        if (mMultiSelectActionBarView == null) {
            mMultiSelectActionBarView = LayoutInflater.from(ChoiceModeMultipleActivity.this)
                .inflate(R.layout.list_multi_select_actionbar, null);

            mSelectedCount =
                (TextView)mMultiSelectActionBarView.findViewById(R.id.selected_conv_count);
        }
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM,  
                ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME |  
                ActionBar.DISPLAY_SHOW_TITLE); 
        getActionBar().setCustomView(mMultiSelectActionBarView);
        ((TextView)mMultiSelectActionBarView.findViewById(R.id.title)).setText(R.string.select_item);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
 
		getMenuInflater().inflate(R.menu.multi_select_menu, menu);
		return true;
	}

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
 
        MenuItem mItem = menu.findItem(R.id.action_slelect);
        if(mListView.getCheckedItemCount() == mAdapter.getCount()){
        	mItem.setTitle(R.string.action_deselect_all);
        }else{
        	mItem.setTitle(R.string.action_select_all);
        }       
        return super.onPrepareOptionsMenu(menu);
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
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
		return super.onOptionsItemSelected(item);
	}
	
	public void selectedAll(){
		for(int i= 0; i< mAdapter.getCount(); i++){
			mListView.setItemChecked(i, true);
		}
		updateSeletedCount();
	}
	
	public void unSelectedAll(){
		mListView.clearChoices();
		updateSeletedCount();
	}
 
    public void updateSeletedCount(){
    	mSelectedCount.setText(Integer.toString(mListView.getCheckedItemCount()));
    }	
}
