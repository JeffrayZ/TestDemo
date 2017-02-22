package cn.pjcare.www.listview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MyListAdapter extends BaseAdapter {
	private Context mContext;
	private ListView mListView;

	private String[] mStrings = { 
			"Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam",
			"Abondance", "Ackawi", "Acorn",
			"Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag",
			"Airedale", "Aisy Cendre", 
			"Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese",
			"Ami du Chambertin", "Anejo Enchilado",
			"Anneau du Vic-Bilh", "Anthoriro"};
	
	
	public MyListAdapter(Context context, ListView list) {
		mContext = context;
		mListView = list;
	}
 
	public int getCount() {
		return mStrings.length;
	}

	@Override
	public boolean areAllItemsEnabled() {
		return false;
	}

	@Override
	public boolean isEnabled(int position) {
		return !mStrings[position].startsWith("-");
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		TextView tv;
		if (convertView == null) {
			tv = (TextView) LayoutInflater.from(mContext).inflate(
					android.R.layout.simple_expandable_list_item_1, parent,
					false);
		} else {
			tv = (TextView) convertView;
		}
		tv.setText(mStrings[position]);
		updateBackground(position , tv);
		return tv;
	}

	@SuppressLint("NewApi")
	public void updateBackground(int position, View view) {
		int backgroundId;
		if (mListView.isItemChecked(position)) {
			backgroundId = R.drawable.list_selected_holo_light;
		} else {
			backgroundId = R.drawable.conversation_item_background_read;
		}
		Drawable background = mContext.getResources().getDrawable(backgroundId);
		view.setBackground(background);
	}
 
}
