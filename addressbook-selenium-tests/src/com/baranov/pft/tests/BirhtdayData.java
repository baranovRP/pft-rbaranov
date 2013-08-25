package com.baranov.pft.tests;

public class BirhtdayData {
    private String bday;
    private String bmonth;
    private String byear;

    public BirhtdayData(String bday, String bmonth, String byear) {
	this.bday = bday;
	this.bmonth = bmonth;
	this.byear = byear;
    }

    public BirhtdayData() {
	this.bday = "-";
	this.bmonth = "-";
    }

    public String getBday() {
	return bday;
    }

    public void setBday(String bday) {
	this.bday = bday;
    }

    public String getBmonth() {
	return bmonth;
    }

    public void setBmonth(String bmonth) {
	this.bmonth = bmonth;
    }

    public String getByear() {
	return byear;
    }

    public void setByear(String byear) {
	this.byear = byear;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((bday == null) ? 0 : bday.hashCode());
	result = prime * result + ((bmonth == null) ? 0 : bmonth.hashCode());
	result = prime * result + ((byear == null) ? 0 : byear.hashCode());
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
	BirhtdayData other = (BirhtdayData) obj;
	if (bday == null) {
	    if (other.bday != null)
		return false;
	} else if (!bday.equals(other.bday))
	    return false;
	if (bmonth == null) {
	    if (other.bmonth != null)
		return false;
	} else if (!bmonth.equals(other.bmonth))
	    return false;
	if (byear == null) {
	    if (other.byear != null)
		return false;
	} else if (!byear.equals(other.byear))
	    return false;
	return true;
    }

    public BirhtdayData withBDay(String bday) {
	this.bday = bday;
	return this;
    }

    public BirhtdayData withBMonth(String bmonth) {
	this.bmonth = bmonth;
	return this;
    }

    public BirhtdayData withBYear(String byear) {
	this.byear = byear;
	return this;
    }

}