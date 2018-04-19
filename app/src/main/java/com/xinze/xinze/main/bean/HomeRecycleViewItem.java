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

    public HomeRecycleViewItem(String title, int icon, boolean isShowRightArrow, int itemType) {
        this.title = title;
        this.icon = icon;
        this.isShowRightArrow = isShowRightArrow;
        this.itemType = itemType;
    }

    public HomeRecycleViewItem(int title, int icon, boolean isShowRightArrow, int itemType) {
        this.titleResources = title;
        this.icon = icon;
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
