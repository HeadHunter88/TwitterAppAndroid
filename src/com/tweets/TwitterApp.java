package com.tweets;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TwitterApp extends ListActivity {
    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		LoadTweets();
		
		  super.onCreate(savedInstanceState);

		  setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, COUNTRIES));

		  ListView lv = getListView();
		  lv.setTextFilterEnabled(true);
		  
		  
	}
	
	public void LoadTweets() {
			TwitterFactory twitterFactory = new TwitterFactory();
			Twitter twitter = twitterFactory.getInstance();
	 
			Query query = new Query();
	 
			QueryResult res = null;
			
			List<String> listTweets = new ArrayList<String>();
	 
			try {
				res = twitter.search(query);
	 
				List<Tweet> listaTweets = res.getTweets();
	 
				Iterator<Tweet> itTweet = listaTweets.iterator();
	 
				while (itTweet.hasNext()) {
					Tweet tweet = itTweet.next();
	 
					String infoTweet = (tweet.getFromUser() + tweet.getText());
					listTweets.add(infoTweets);
					
				}
	 
			} catch (TwitterException e) {
				e.printStackTrace();
			}		
	}
}