package com.proj.actions;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.proj.dao.RelationDAO;
import com.proj.user.User;
import com.proj.util.SessionUtils;

@Named
@SessionScoped
public class NewRelations implements Serializable
{

	private static final long serialVersionUID = 782115604435416963L;
	private int newRelationsPerRefresh = 10;
	private List<User> allNewRelations;

	public List<User> getAllNewRelations() {
		this.initNewRelations();
		return allNewRelations;
	}

	public void setAllNewRelations(List<User> allNewRelations) {
		this.allNewRelations = allNewRelations;
	}
	
	public void initNewRelations() {
		User user;
		if((user=SessionUtils.getUser())!=null) {
			this.setAllNewRelations(RelationDAO.getRandomNewRelationsFor(user, newRelationsPerRefresh));
		}
	}
	
	public void refreshNewRelations() {
		this.initNewRelations();
	}
	
	public void createRelation(String targetUserIDStr) {
		User user;
		if((user=SessionUtils.getUser())==null) return;
		
		try {
			int targetUserID = Integer.valueOf(targetUserIDStr);
			RelationDAO.insert(user.getId(), targetUserID);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
