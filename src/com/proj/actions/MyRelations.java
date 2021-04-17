package com.proj.actions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.proj.dao.RelationDAO;
import com.proj.user.User;
import com.proj.util.SessionUtils;

@Named
@SessionScoped
public class MyRelations implements Serializable
{

	private static final long serialVersionUID = 782115604435416963L;

	private List<User> allRelations;

	private List<User> currentDisplay;

	private int amountPerRefresh = 10;

	private int page=0;

	private boolean hasNextPage=true;

	public MyRelations() {
		this.initRelations();
	}

	public List<User> getAllRelations() {
		return allRelations;
	}

	public void setAllRelations(List<User> allRelations) {
		this.allRelations = allRelations;
	}

	public void initRelations() {
		User user;
		if((user=SessionUtils.getUser())!=null) {
			this.setAllRelations(RelationDAO.getRelationsOf(user));
			currentDisplay = new ArrayList<>();
			updateCurrentDisplay(page);
			hasNextPage = allRelations.size() >= (page+1)*amountPerRefresh;
		}
		else {
			allRelations = new ArrayList<>();
			currentDisplay = new ArrayList<>();
		}

	}
	
	public void backToStartRelations() {
		page=0;
		updateCurrentDisplay(page);
		hasNextPage = allRelations.size() >= (page+1)*amountPerRefresh;
	}

	public void refreshRelations() {
		this.initRelations();
	}

	public void nextPageRelations() {
		page++;
		updateCurrentDisplay(page);
		hasNextPage = allRelations.size() >= (page+1)*amountPerRefresh;
	}

	public void updateCurrentDisplay(int page) {
		currentDisplay.clear();
		for(int i= page*amountPerRefresh; i < (page+1)*amountPerRefresh; i++) {
			if(allRelations.size()<=i) break;
			else {
				currentDisplay.add(allRelations.get(i));
			}
		}
	}

	public List<User> getCurrentDisplay() {
		this.refreshRelations();
		return currentDisplay;
	}

	public void setCurrentDisplay(List<User> currentDisplay) {
		this.currentDisplay = currentDisplay;
	}

	public boolean getHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public void removeRelation(String targetUserIDStr) {
		User user;
		if((user=SessionUtils.getUser())==null) return;

		try {
			int targetUserID = Integer.valueOf(targetUserIDStr);
			RelationDAO.remove(user.getId(), targetUserID);
			
			this.deleteRelationWith(targetUserID);
			updateCurrentDisplay(page);
			hasNextPage = allRelations.size() >= (page+1)*amountPerRefresh;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		
	}
	
	public void deleteRelationWith(int targetUserID) {
		User toDelete = null;
		for(User u : allRelations) {
			if(u.getId()==targetUserID) {
				toDelete=u;
				break;
			}
		}
		if(toDelete!=null) allRelations.remove(toDelete);
	}




}
