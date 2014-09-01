package com.example.webcall;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import org.apache.cordova.*;

import com.example.webcall.R;
public class MainActivity extends DroidGap{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		super.setIntegerProperty("loadUrlTimeoutValue", 70000);// to increase the Timeout limit 
		super.loadUrl("file:///android_asset/www/index.html");
		//super.loadUrl("file:///android_asset/www/contact.html");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
