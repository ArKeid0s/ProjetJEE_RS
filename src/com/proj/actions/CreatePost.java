package com.proj.actions;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.proj.dao.PostDAO;
import com.proj.post.Post;
import com.proj.user.User;
import com.proj.util.SessionUtils;

@Named
@SessionScoped
public class CreatePost implements Serializable
{

	private static final long serialVersionUID = 782115604435416963L;

	/* TODO: Replace string with user object */

	private int author;
	private String title;
	private String content;
	private long time;

	private String error="";



	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	// Validate login
	public String validateCreationRequest()
	{
		if(this.title.isEmpty()) {
			//FacesContext.getCurrentInstance().addMessage("myForm:newPassword1", new FacesMessage("Error empty title"));
			error="Error empty title";
			return null;
		}
		else if(this.content.isEmpty()) {
			//FacesContext.getCurrentInstance().addMessage("myForm:newPassword1", new FacesMessage("Error empty content"));
			error="Error empty content";
			return null;
		}
		else
		{
			User user;
			if((user = SessionUtils.getUser())!=null) {
				int author = user.getId();
				//int author = 0;

				Post post = new Post(author, title, content, System.currentTimeMillis());

				PostDAO.insert(post);
				
				error="Post "+title+" created !";
				
				this.title="";
				this.content="";
				
				return "createPost";
			}			
			else {
				return "login";
			}
		}
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
