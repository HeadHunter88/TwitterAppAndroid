package com.tweets;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;




public class TwitterApp extends Activity{
	
	public static Hashtable<String, Drawable> img = new Hashtable<String, Drawable>();
	
    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState){
		
	
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.main);
		setProgressBarIndeterminateVisibility(false);
		Button button = (Button) findViewById(R.id.ok);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	setProgressBarIndeterminateVisibility(true);
            	InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				EditText txtBox = (EditText) findViewById(R.id.entry);
				imm.hideSoftInputFromWindow(txtBox.getWindowToken(), 0);
				info = txtBox.getText().toString();
				pt = ProgressDialog.show(TwitterApp.this, null, "Loading Tweets!");
                new GetTweets().execute();
            }
        });
        ListView lv = (ListView) findViewById(R.id.listView1);
        lv.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		SelectedTweet = new Tweets(
        				listTweets.get(position).getUser(),
        				listTweets.get(position).getPost(), 
        				listTweets.get(position).getUrlImage(),
        				listTweets.get(position).getDate());
        		Intent it = new Intent(TwitterApp.this, TweetsInfo.class);
        		startActivityForResult(it, 0);
        	}
        });
	}
	
	public List<Tweets> listTweets = new ArrayList<Tweets>();
	public static Tweets SelectedTweet;
	public static String info;
	
	public ProgressDialog pt;
	
	public void FillListView()
	{
        LoadTweets();
		list = (ListView)findViewById(R.id.listView1);
    	adapter = new ListViewAdapter(this, listTweets);
	}
	
	public Drawable LoadImage(String url)
    {
         try
         {
             InputStream stream = (InputStream) new URL(url).getContent();
             Drawable bmp = Drawable.createFromStream(stream, "Twitter");
             return bmp;
         }catch (Exception e) {
     		Resources res = getResources();
     		Drawable drawable = res.getDrawable(R.drawable.ic_menu_block);
        	 return drawable;
         }
     }
	
	public void LoadTweets() {
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
					Tweets tw = new Tweets(tweet.getFromUser(), tweet.getText(), tweet.getProfileImageUrl(), tweet.getCreatedAt());
					
					if(!img.containsKey(tweet.getProfileImageUrl()))
			    	{
			    		Drawable drawable = LoadImage(tweet.getProfileImageUrl());
			    		if(drawable == null)
			    		{
			    			Resources resourc = getResources();
			         		drawable = resourc.getDrawable(R.drawable.ic_menu_block);
			    		}
			    		img.put(tweet.getProfileImageUrl(), drawable);
			    	}
					
					listTweets.add(tw);
					
				}
	 
			} catch (TwitterException e) {
				e.printStackTrace();
			}		
	}
	
	public ListView list;
	public ListViewAdapter adapter;
	
	private class GetTweets extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Void... arg0) {
			FillListView();
			return true;
		}
		@Override
		protected void onPostExecute(Boolean result) {
			setProgressBarIndeterminateVisibility(false);
			pt.dismiss();
			list.setAdapter(adapter);
		}
	}
}