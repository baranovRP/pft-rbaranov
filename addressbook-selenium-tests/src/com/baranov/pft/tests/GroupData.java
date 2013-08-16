package com.baranov.pft.tests;

public class GroupData implements Comparable<GroupData> {
    private String groupName;
    private String header;
    private String footer;

    public GroupData() {
    }

    public GroupData(String groupName) {
	this.groupName = groupName;
    }

    public GroupData(String groupName, String header, String footer) {
	this.groupName = groupName;
	this.header = header;
	this.footer = footer;
    }

    public String getGroupName() {
	return this.groupName;
    }

    public void setGroupName(String groupName) {
	this.groupName = groupName;
    }

    public String getHeader() {
	return this.header;
    }

    public void setHeader(String header) {
	this.header = header;
    }

    public String getFooter() {
	return this.footer;
    }

    public void setFooter(String footer) {
	this.footer = footer;
    }

    @Override
    public String toString() {
	return "GroupData [groupName=" + groupName + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	// result = prime * result
	// + ((groupName == null) ? 0 : groupName.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	GroupData other = (GroupData) obj;
	if (groupName == null) {
	    if (other.groupName != null)
		return false;
	} else if (!groupName.equals(other.groupName))
	    return false;
	return true;
    }

    @Override
    public int compareTo(GroupData other) {

	return this.getGroupName().toLowerCase()
		.compareTo(other.getGroupName().toLowerCase());
    }

}
