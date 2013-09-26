package com.baranov.pft.fw;

import java.util.List;

import com.baranov.pft.tests.GroupData;
import com.baranov.pft.utils.SortedListOf;

public class ApplicationGroupModel {
    private SortedListOf<GroupData> groups;

    public SortedListOf<GroupData> getGroups() {
	return groups;
    }

    public void setGroups(List<GroupData> groups) {
	this.groups = new SortedListOf<GroupData>(groups);
    }

    public void addGroup(GroupData group) {
	groups.add(group);
    }

    public ApplicationGroupModel removeGroup(int index) {
	groups.remove(index);
	return this;
    }
    
    
}
