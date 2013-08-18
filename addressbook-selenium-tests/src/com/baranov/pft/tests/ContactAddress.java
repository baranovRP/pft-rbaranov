package com.baranov.pft.tests;

public class ContactAddress {
    private String address;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String email;
    private String email2;
    private String address2;
    private String phone2;

    public ContactAddress(String address, String homePhone, String mobilePhone,
	    String workPhone, String email, String email2, String address2,
	    String phone2) {
	this.address = address;
	this.homePhone = homePhone;
	this.mobilePhone = mobilePhone;
	this.workPhone = workPhone;
	this.email = email;
	this.email2 = email2;
	this.address2 = address2;
	this.phone2 = phone2;
    }

    public ContactAddress() {

    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getHomePhone() {
	return homePhone;
    }

    public void setHomePhone(String homePhone) {
	this.homePhone = homePhone;
    }

    public String getMobilePhone() {
	return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
	this.mobilePhone = mobilePhone;
    }

    public String getWorkPhone() {
	return workPhone;
    }

    public void setWorkPhone(String workPhone) {
	this.workPhone = workPhone;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getEmail2() {
	return email2;
    }

    public void setEmail2(String email2) {
	this.email2 = email2;
    }

    public String getAddress2() {
	return address2;
    }

    public void setAddress2(String address2) {
	this.address2 = address2;
    }

    public String getPhone2() {
	return phone2;
    }

    public void setPhone2(String phone2) {
	this.phone2 = phone2;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((address == null) ? 0 : address.hashCode());
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result
		+ ((homePhone == null) ? 0 : homePhone.hashCode());
	result = prime * result
		+ ((mobilePhone == null) ? 0 : mobilePhone.hashCode());
	result = prime * result
		+ ((workPhone == null) ? 0 : workPhone.hashCode());
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
	ContactAddress other = (ContactAddress) obj;
	if (address == null) {
	    if (other.address != null)
		return false;
	} else if (!address.equals(other.address))
	    return false;
	if (email == null) {
	    if (other.email != null)
		return false;
	} else if (!email.equals(other.email))
	    return false;
	if (homePhone == null) {
	    if (other.homePhone != null)
		return false;
	} else if (!homePhone.equals(other.homePhone))
	    return false;
	if (mobilePhone == null) {
	    if (other.mobilePhone != null)
		return false;
	} else if (!mobilePhone.equals(other.mobilePhone))
	    return false;
	if (workPhone == null) {
	    if (other.workPhone != null)
		return false;
	} else if (!workPhone.equals(other.workPhone))
	    return false;
	return true;
    }

}