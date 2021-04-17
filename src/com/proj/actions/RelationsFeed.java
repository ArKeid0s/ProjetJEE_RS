package com.proj.actions;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.proj.dao.PostDAO;
import com.proj.post.Post;
import com.proj.user.User;
import com.proj.util.SessionUtils;

@Named
@SessionScoped
public class RelationsFeed implements Serializable
{

	private static final long serialVersionUID = 782115604435416963L;
	private List<Post> allPosts;
	
	
	public List<Post> getAllPosts() {
		this.initPosts();
		return allPosts;
	}

	public void setAllPosts(List<Post> allPosts) {
		this.allPosts = allPosts;
	}
	
	public void initPosts() {
		User user;
		if((user=SessionUtils.getUser())!=null) {
			this.setAllPosts(PostDAO.getRelationsPostsOf(user));
		}
	}
	
	public void refreshPosts() {
		this.initPosts();
	}
	

}