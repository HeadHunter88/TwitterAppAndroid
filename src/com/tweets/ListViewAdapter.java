package com.tweets;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class ListViewAdapter extends BaseAdapter {

	private Context context;

	private List<Tweets> Tweets;
	
    public ListViewAdapter(Context context, List<Tweets> tweets) {
        this.context = context;
        this.Tweets = tweets;
    }

	public int getCount() {
        return Tweets.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
    	
    	String entry = Tweets.get(position).getUser();
    	String urlImage = Tweets.get(position).getUrlImage();
    	String info = Tweets.get(position).getPost();
    	
    	
    	
    	
    	if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
        }
    	
    	TextView tweetUser = (TextView) convertView.findViewById(R.id.Cell);
    	tweetUser.setText(entry);
    	TextView tweetInfo = (TextView) convertView.findViewById(R.id.info);
    	tweetInfo.setText(info);
    	ImageView imgViewer = (ImageView) convertView.findViewById(R.id.imgView);
    	imgViewer.setImageDrawable(TwitterApp.img.get(urlImage));
    	
		return convertView;
    }

     
}