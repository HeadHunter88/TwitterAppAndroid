package com.tweets;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TweetsInfo extends Activity {

    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tweet_info);
		Button button = (Button) findViewById(R.id.btnBack);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	setResult(RESULT_OK);
            	finish();
            }
        });
        LoadInfo();
        
	}
	
	public Tweets tw = new Tweets(TwitterApp.SelectedTweet);
	
	private void LoadInfo() {
		TextView txtUser = (TextView) findViewById(R.id.userName);
		TextView txtContacto = (TextView) findViewById(R.id.date);
		TextView txtDate = (TextView) findViewById(R.id.postInfo);
		txtUser.setText(tw.getUser());
		txtContacto.setText(tw.getDate().toString());
		txtDate.setText(tw.getPost());
		ImageView img = (ImageView) findViewById(R.id.imgUser);
		img.setImageDrawable(TwitterApp.img.get(tw.getUrlImage()));
	}
}