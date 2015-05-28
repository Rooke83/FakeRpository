package com.example.baldwin.webviewtutorial;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    public static final String SHARED_PREF = "myPrefFile";
    private WebView wv;
    private EditText addressBar;
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);

        wv = (WebView) findViewById(R.id.webView);
        wv.setWebViewClient(new WebViewClient());
        addressBar = (EditText) findViewById(R.id.url_box);

        String hp = settings.getString("hp", null);
        if(hp == null) {
            addressBar.setText("http://i.reddit.com");
        } else {
            addressBar.setText(hp);
        }
        wv.loadUrl(addressBar.getText().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case(R.id.set_hp) :
                //Save current page to shared preferences
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("hp", addressBar.getText().toString());
                editor.apply();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {
            wv.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void go(View view) {

        wv.loadUrl(addressBar.getText().toString());

    }
}
