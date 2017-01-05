package com.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.EditText;

import com.test.R;

public class TestEditTextActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_edit_text);

        editText = (EditText) findViewById(R.id.et_link);

        // Make links in the EditText clickable
        editText.setMovementMethod(LinkMovementMethod.getInstance());

        // Setup my Spannable with clickable URLs
        Spannable spannable = new SpannableString("http://blog.danlew.net");
        Linkify.addLinks(spannable, Linkify.WEB_URLS);

        // The fix: Append a zero-width space to the Spannable
        CharSequence text = TextUtils.concat(spannable, "\u200B");

        // Use it!
        editText.setText(text);
    }
}
