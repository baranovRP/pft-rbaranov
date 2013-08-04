package com.baranov.pft01;

public class GroupData {
    private String groupName;
    private String header;
    private String footer;

    public GroupData() {
    }

    public GroupData(String groupName, String header, String footer) {
        this.groupName = groupName;
        this.header = header;
        this.footer = footer;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupname(String groupName) {
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
}
