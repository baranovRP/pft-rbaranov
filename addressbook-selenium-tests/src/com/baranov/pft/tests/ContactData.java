package com.baranov.pft.tests;

public class ContactData implements Comparable<ContactData> {
    private String id;
    private String firstName;
    private String secondName;
    private String fullName;
    private ContactAddress contactAddress = new ContactAddress();
    private BirhtdayData bdata = new BirhtdayData();
    private GroupData group = new GroupData();

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

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
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
	setFullName(this.firstName, this.secondName);
    }

    public ContactAddress getContactAddress() {
	return contactAddress;
    }

    public void setContactAddress(ContactAddress contactAddress) {
	this.contactAddress = contactAddress;
    }

    public String getAddress() {
	return contactAddress.getAddress();
    }

    public void setAddress(String address) {
	contactAddress.setAddress(address);
    }

    public String getHomePhone() {
	return contactAddress.getHomePhone();
    }

    public void setHomePhone(String homePhone) {
	contactAddress.setHomePhone(homePhone);
    }

    public String getMobilePhone() {
	return contactAddress.getMobilePhone();
    }

    public void setMobilePhone(String mobilePhone) {
	contactAddress.setMobilePhone(mobilePhone);
    }

    public String getWorkPhone() {
	return contactAddress.getWorkPhone();
    }

    public void setWorkPhone(String workPhone) {
	contactAddress.setWorkPhone(workPhone);
    }

    public String getEmail() {
	return contactAddress.getEmail();
    }

    public void setEmail(String email) {
	contactAddress.setEmail(email);
    }

    public String getEmail2() {
	return contactAddress.getEmail2();
    }

    public void setEmail2(String email2) {
	contactAddress.setEmail2(email2);
    }

    public String getAddress2() {
	return contactAddress.getAddress2();
    }

    public void setAddress2(String address2) {
	contactAddress.setAddress2(address2);
    }

    public String getPhone2() {
	return contactAddress.getPhone2();
    }

    public void setPhone2(String phone2) {
	contactAddress.setPhone2(phone2);
    }

    public BirhtdayData getBdata() {
	return bdata;
    }

    public void setBdata(BirhtdayData bdata) {
	this.bdata = bdata;
    }

    public String getBday() {
	return bdata.getBday();
    }

    public void setBday(String bday) {
	bdata.setBday(bday);
    }

    public String getBmonth() {
	return bdata.getBmonth();
    }

    public void setBmonth(String bmonth) {
	bdata.setBmonth(bmonth);
    }

    public String getByear() {
	return bdata.getByear();
    }

    public void setByear(String byear) {
	bdata.setByear(byear);
    }

    public GroupData getGroup() {
	return group;
    }

    public void setGroup(GroupData group) {
	this.group = group;
    }

    public String getGroupName() {
	return group.getGroupName();
    }

    public void setGroupName(String groupName) {
	group.setGroupName(groupName);
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

    public ContactData withId(String id) {
	this.setId(id);
	return this;
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