package com.tweets;

import java.util.Date;

public class Tweets
{
	public Tweets(String user, String post, String urlImage, Date date) {
		this.user = user;
		this.post = post;
		this.urlImage = urlImage;
		this.date = date;
	}
	
	public Tweets(Tweets tw) {
		this.user = tw.getUser();
		this.post = tw.getPost();
		this.urlImage = tw.getUrlImage();
		this.date = tw.getDate();
	}
	
	private String user;
	private String post;
	private String urlImage;
	private Date date;
	
	public void setUser(String user) {
		this.user = user;
	}
	public String getUser() {
		return user;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getPost() {
		return post;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate() {
		return date;
	}
	
}