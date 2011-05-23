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

    public class ViewHolder {
    	TextView TweetUser;
    	TextView TweetInfo;
    	ImageView imgUser;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
    	
    	ViewHolder holder = null;
    	
    	String entry = Tweets.get(position).getUser();
    	String urlImage = Tweets.get(position).getUrlImage();
    	String info = Tweets.get(position).getPost();
    	
    	
    	
    	
    	if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
            
            holder = new ViewHolder();
            holder.TweetUser = (TextView) convertView.findViewById(R.id.Cell);
            holder.TweetInfo = (TextView) convertView.findViewById(R.id.info);
            holder.imgUser = (ImageView) convertView.findViewById(R.id.imgView);
            
            convertView.setTag(holder);
        }
    	else {
    		holder = (ViewHolder) convertView.getTag();
    	}
    
    	holder.TweetUser.setText(entry);
    	holder.TweetInfo.setText(info);
    	holder.imgUser.setImageDrawable(TwitterApp.img.get(urlImage));
    	
		return convertView;
    }

    /*
     * static class ViewHolder {
    	TextView TweetUser;
    	TextView TweetInfo;
    	ImageView imgUser;
    }
    
    
    public View getView(int position, View convertView, ViewGroup parent) {
    	ViewHolder holder;
    	
    	String entry = Tweets.get(position).getUser();
    	String urlImage = Tweets.get(position).getUrlImage();
    	String info = Tweets.get(position).getPost();

    	if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
            
            holder = new ViewHolder();
            holder.TweetUser = (TextView) convertView.findViewById(R.id.Cell);
            holder.TweetInfo = (TextView) convertView.findViewById(R.id.info);
            holder.imgUser = (ImageView) convertView.findViewById(R.id.imgView);
        }
    	else {
    		holder = (ViewHolder) convertView.getTag();
    	}
    	
    	holder.TweetUser.setText(entry);
    	holder.TweetInfo.setText(info);
    	holder.imgUser.setImageDrawable(TwitterApp.img.get(urlImage));
    	
		return convertView;
    } 
     * */
     
}