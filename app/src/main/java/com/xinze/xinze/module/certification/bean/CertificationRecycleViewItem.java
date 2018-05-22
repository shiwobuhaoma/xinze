package com.xinze.xinze.module.certification.bean;

import android.graphics.Bitmap;

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
    private String leftBitmap;
    private String rightBitmap;
    private String content;
    /**
     * ImageView控件id
     */
    private int resoucesId;

    public CertificationRecycleViewItem(String leftText, boolean isShowRightArrow, boolean isShowSpace, boolean isShowBottomLine, boolean isShowMiddleText, String middleHintText, String leftBitmap) {
        this.leftText = leftText;
        this.isShowRightArrow = isShowRightArrow;
        this.isShowMiddleText = isShowMiddleText;
        this.isShowSpace = isShowSpace;
        this.isShowBottomLine = isShowBottomLine;
        this.middleHintText = middleHintText;
        this.leftBitmap = leftBitmap;
    }

    public CertificationRecycleViewItem(int leftText, boolean isShowRightArrow, boolean isShowSpace, boolean isShowBottomLine, String middleHintText, boolean isShowMiddleText) {
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

    public String getLeftBitmap() {
        return leftBitmap;
    }

    public void setLeftBitmap(int resoucesId, String leftBitmap) {
        this.resoucesId = resoucesId;
        this.leftBitmap = leftBitmap;
    }

    public String getRightBitmap() {
        return rightBitmap;
    }

    public void setRightBitmap(int resoucesId, String rightBitmap) {
        this.resoucesId = resoucesId;
        this.rightBitmap = rightBitmap;
    }

    public int getResoucesId() {
        return resoucesId;
    }

    public void setResoucesId(int resoucesId) {
        this.resoucesId = resoucesId;
    }

    public void setLeftBitmap(String leftBitmap) {
        this.leftBitmap = leftBitmap;
    }

    public void setRightBitmap(String rightBitmap) {
        this.rightBitmap = rightBitmap;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
