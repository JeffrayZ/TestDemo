package com.test.activity;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.test.R;

public class TestBottomSheetActivity extends AppCompatActivity {

    private View bottomSheet;
    private BottomSheetBehavior behavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_bottom_sheet);

        bottomSheet = findViewById(R.id.bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheet);
        /**
         * Bottom Sheet 一共有五个状态回调：

             STATE_COLLAPSED
             折叠状态。可通过app:behavior_peekHeight来设置默认显示的高度。

             STATE_SETTING
             拖拽松开之后到达终点位置（collapsed or expanded）前的状态。

             STATE_EXPANDED
             完全展开的状态。

             STATE_HIDDEN
             隐藏状态。默认是false，可通过app:behavior_hideable属性设置。

             STATE_DRAGGING
             被拖拽状态
         * */
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            public boolean hasRequest;

            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                String state = "null";
                switch (newState) {
                    case 1:
                        state = "STATE_DRAGGING";
                        break;
                    case 2:
                        state = "STATE_SETTLING";
                        break;
                    case 3:
                        state = "STATE_EXPANDED";
                        break;
                    case 4:
                        state = "STATE_COLLAPSED";
                        break;
                    case 5:
                        state = "STATE_HIDDEN";
                        break;
                }
                Log.d("TAG", "newState:" + state);
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
                Log.d("TAG", "slideOffset:" + slideOffset);
//                if (!hasRequest && behavior.getPeekHeight() == 0 && slideOffset > 0) {
//                    hasRequest = true;
//                    updateOffsets(bottomSheet);
//                }
            }
        });

        Button dialogBtn = (Button) findViewById(R.id.bottom_sheet_dialog_btn);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog dialog = new BottomSheetDialog(TestBottomSheetActivity.this);
                dialog.setContentView(R.layout.bottom_sheet_dialog_layout);
                dialog.show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
    }
}
