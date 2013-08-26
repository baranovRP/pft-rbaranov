package com.baranov.pft.tests;

public class ContactData implements Comparable<ContactData> {
    private String firstName;
    private String secondName;
    private String fullName;
    private ContactAddress contactAddress;
    private BirhtdayData bdata;
    private GroupData group;

    public ContactData(String firstName, String secondName,
	    ContactAddress contactAddress, BirhtdayData bdata, GroupData group) {
	this.firstName = firstName;
	this.secondName = secondName;
	this.contactAddress = contactAddress;
	this.bdata = bdata;
	this.group = group;
	this.fullName = secondName + " " + firstName;
    }

    public ContactData() {
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getSecondName() {
	return secondName;
    }

    public void setSecondName(String secondName) {
	this.secondName = secondName;
    }

    public ContactAddress getContactAddress() {
	return contactAddress;
    }

    public void setContactAddress(ContactAddress contactAddress) {
	this.contactAddress = contactAddress;
    }

    public BirhtdayData getBdata() {
	return bdata;
    }

    public void setBdata(BirhtdayData bdata) {
	this.bdata = bdata;
    }

    public GroupData getGroup() {
	return group;
    }

    public void setGroup(GroupData group) {
	this.group = group;
    }

    public String getFullName() {
	return fullName;
    }

    public void setFullName(String fullName) {
	this.fullName = fullName;
    }

    public void setFullName(String firstName, String secondName) {
	this.fullName = secondName + " " + firstName;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((fullName == null) ? 0 : fullName.hashCode());
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
	ContactData other = (ContactData) obj;
	if (fullName == null) {
	    if (other.fullName != null)
		return false;
	} else if (!fullName.equals(other.fullName))
	    return false;
	return true;
    }

    @Override
    public int compareTo(ContactData other) {

	return this.getFullName().toLowerCase()
		.compareTo(other.getFullName().toLowerCase());
    }

    @Override
    public String toString() {
	return "ContactData [fullName=" + fullName + "]";
    }

    public ContactData withFirstName(String firstName) {
	this.firstName = firstName;
	return this;
    }

    public ContactData withSecondName(String secondName) {
	this.secondName = secondName;
	return this;
    }

    public ContactData withFullName() {
	this.fullName = secondName + " " + firstName;
	return this;
    }

    public ContactData withContactAddress(ContactAddress contactAddress) {
	this.contactAddress = contactAddress;
	return this;
    }

    public ContactData withBData(BirhtdayData bdata) {
	this.bdata = bdata;
	return this;
    }

    public ContactData withGroup(GroupData group) {
	this.group = group;
	return this;
    }

}