package com.tweets;

public class Tweets
{
	public Tweets(String user, String post, String urlImage) {
		this.user = user;
		this.post = post;
		this.urlImage = urlImage;
	}
	
	private String user;
	private String post;
	private String urlImage;
	
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
	
}