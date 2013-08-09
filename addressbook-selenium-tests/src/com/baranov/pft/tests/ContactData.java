package com.baranov.pft.tests;

public class ContactData {
    private String firstName;
    private String secondName;
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

}