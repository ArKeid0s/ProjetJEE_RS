package com.proj.actions;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.proj.dao.PostDAO;
import com.proj.post.Post;

@Named
@SessionScoped
public class GeneralFeed implements Serializable
{

	private static final long serialVersionUID = 782115604435416963L;

	private List<Post> allPosts;
	
	public GeneralFeed() {
		this.initPosts();
	}

	public List<Post> getAllPosts() {
		this.initPosts();
		return allPosts;
	}

	public void setAllPosts(List<Post> allPosts) {
		this.allPosts = allPosts;
	}
	
	public void initPosts() {
		this.setAllPosts(PostDAO.getLastPosts(100));
	}
	
	public void refreshPosts() {
		this.initPosts();
	}
	

}
