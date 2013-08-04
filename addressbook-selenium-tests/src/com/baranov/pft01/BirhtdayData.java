package com.baranov.pft01;

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
}