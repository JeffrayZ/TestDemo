package cn.pjcare.www.listview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void openChoiceModeMultiple(View v) {
		Intent Intent =new Intent(this, ChoiceModeMultipleActivity.class );
		startActivity(Intent);
	}
	
	public void openChoiceModeMultipleModal(View v) {
		Intent Intent =new Intent(this, ChoiceModeMultipleModalActivity.class );
		startActivity(Intent);
	}	
 
}
