package com.xinze.xinze.module.certification.bean;

/**
 * @author lxf
 * 认证界面条目
 */
public class CertificationRecycleViewItem {
    private String leftText;
    private String middleHintText;
    private boolean isShowRightArrow;
    private boolean isShowMiddleText;
    private boolean isShowSpace;
    private boolean isShowBottomLine;

    public CertificationRecycleViewItem(String leftText,  boolean isShowRightArrow, boolean isShowSpace,  boolean isShowBottomLine,   boolean isShowMiddleText, String middleHintText) {
        this.leftText = leftText;
        this.isShowRightArrow = isShowRightArrow;
        this.isShowMiddleText = isShowMiddleText;
        this.isShowSpace = isShowSpace;
        this.isShowBottomLine = isShowBottomLine;
        this.middleHintText = middleHintText;
    }

    public CertificationRecycleViewItem(int leftText, boolean isShowRightArrow, boolean isShowSpace,  boolean isShowBottomLine, String middleHintText,   boolean isShowMiddleText) {
        this.isShowRightArrow = isShowRightArrow;
        this.isShowMiddleText = isShowMiddleText;
        this.isShowSpace = isShowSpace;
        this.isShowBottomLine = isShowBottomLine;
        this.middleHintText = middleHintText;
    }

    public String getLeftText() {
        return leftText;
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
    }

    public String getMiddleHintText() {
        return middleHintText;
    }

    public void setMiddleHintText(String middleHintText) {
        this.middleHintText = middleHintText;
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


    public boolean isShowMiddleText() {
        return isShowMiddleText;
    }

    public void setShowMiddleText(boolean showMiddleText) {
        isShowMiddleText = showMiddleText;
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
                "leftText='" + leftText + '\'' +
                ", isShowRightArrow=" + isShowRightArrow +
                ", isShowSpace=" + isShowSpace +
                '}';
    }
}
