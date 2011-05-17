package com.tweets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;




public class TwitterApp extends Activity {
    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button button = (Button) findViewById(R.id.ok);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	EditText txtBox = (EditText) findViewById(R.id.entry);
                FillListView(txtBox.getText().toString());
            }
        });
	}
	
	public List<Tweets> listTweets = new ArrayList<Tweets>();
	
	void FillListView(String info)
	{
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		EditText txtBox = (EditText) findViewById(R.id.entry);
		imm.hideSoftInputFromWindow(txtBox.getWindowToken(), 0);
		LoadTweets(info);
		
		ListView list = (ListView)findViewById(R.id.listView1);
		
		ListViewAdapter adapter = new ListViewAdapter(this, listTweets);
		list.setAdapter(adapter);
	}
	
	public void LoadTweets(String info) {
			TwitterFactory twitterFactory = new TwitterFactory();
			Twitter twitter = twitterFactory.getInstance();
	 
			Query query = new Query(info);
	 
			QueryResult res = null;
	 
			try {
				res = twitter.search(query);
	 
				List<Tweet> listaTweets = res.getTweets();
	 
				Iterator<Tweet> itTweet = listaTweets.iterator();
				listTweets.clear();
	 
				while (itTweet.hasNext()) {
					Tweet tweet = itTweet.next();
					Tweets tw = new Tweets(tweet.getFromUser(), tweet.getText(), tweet.getProfileImageUrl());
					listTweets.add(tw);
					
				}
	 
			} catch (TwitterException e) {
				e.printStackTrace();
			}		
	}
}