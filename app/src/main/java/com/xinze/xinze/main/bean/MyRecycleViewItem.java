package com.xinze.xinze.main.bean;

/**
 * @author lxf
 * 我的界面条目
 */
public class MyRecycleViewItem {
    private String title;

    private boolean isShowRightText;
    private String rightText;
    private int titleResources;
    private int icon;
    private boolean isShowRightArrow;
    private boolean isShowSpace;




    private boolean isShowBottomLine;

    public MyRecycleViewItem(String title, int icon, boolean isShowRightArrow, boolean isShowSpace, boolean isShowRightText,boolean isShowBottomLine,String rightText) {
        this.title = title;
        this.icon = icon;
        this.isShowRightArrow = isShowRightArrow;
        this.isShowSpace = isShowSpace;
        this.isShowRightText = isShowRightText;
        this.isShowBottomLine = isShowBottomLine;
        this.rightText = rightText;
    }

    public MyRecycleViewItem(int title, int icon, boolean isShowRightArrow, boolean isShowSpace, boolean isShowRightText,boolean isShowBottomLine,String rightText) {
        this.titleResources = title;
        this.icon = icon;
        this.isShowRightArrow = isShowRightArrow;
        this.isShowSpace = isShowSpace;
        this.isShowRightText = isShowRightText;
        this.isShowBottomLine = isShowBottomLine;
        this.rightText = rightText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
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

    public boolean isShowSpace() {
        return isShowSpace;
    }

    public void setShowSpace(boolean showSpace) {
        isShowSpace = showSpace;
    }

    public boolean isShowRightText() {
        return isShowRightText;
    }

    public void setShowRightText(boolean showRightText) {
        isShowRightText = showRightText;
    }
    public boolean isShowBottomLine() {
        return isShowBottomLine;
    }

    public void setShowBottomLine(boolean showBottomLine) {
        isShowBottomLine = showBottomLine;
    }
    @Override
    public String toString() {
        return "MyRecycleViewItem{" +
                "title='" + title + '\'' +
                ", icon=" + icon +
                ", isShowRightArrow=" + isShowRightArrow +
                ", isShowSpace=" + isShowSpace +
                ", isShowRightText=" + isShowRightText +
                '}';
    }
}
