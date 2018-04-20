package com.xinze.xinze.main.bean;

/**
 * @author lxf
 * 我的界面条目
 */
public class HomeRecycleViewItem {
    private String title;
    private int titleResources;
    private int icon;
    private boolean isShowRightArrow;
    private int itemType;

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public boolean isShowRightText() {
        return isShowRightText;
    }

    public void setShowRightText(boolean showRightText) {
        isShowRightText = showRightText;
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }

    private boolean isShowRightText;
    private String rightText;
    public HomeRecycleViewItem(String title, String rightText, int icon, boolean isShowRightArrow, boolean isShowRightText,int itemType) {
        this.title = title;
        this.icon = icon;
        this.rightText = rightText;
        this.isShowRightText = isShowRightText;
        this.isShowRightArrow = isShowRightArrow;
        this.itemType = itemType;
    }

    public HomeRecycleViewItem(int title, String rightText,int icon, boolean isShowRightArrow, int itemType, boolean isShowRightText) {
        this.titleResources = title;
        this.icon = icon;
        this.rightText = rightText;
        this.isShowRightText = isShowRightText;
        this.isShowRightArrow = isShowRightArrow;
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getTitleResources() {
        return titleResources;
    }

    public void setTitleResources(int titleResources) {
        this.titleResources = titleResources;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isShowRightArrow() {
        return isShowRightArrow;
    }

    public void setShowRightArrow(boolean showRightArrow) {
        isShowRightArrow = showRightArrow;
    }




    @Override
    public String toString() {
        return "MyRecycleViewItem{" +
                "title='" + title + '\'' +
                ", icon=" + icon +
                ", itemType=" + itemType +
                ", isShowRightArrow=" + isShowRightArrow +
                '}';
    }
}
