package ru.efremov.booklist.entity;

import java.io.Serializable;

/**
 * 图片实体类
 *
 * @author Administrator
 */
public class ImageItem implements Serializable {
    /**
     * 本地图片
     */
    public final static int IMAGE_LOCAL = 0;
    /**
     * 网络图片
     */
    public final static int IMAGE_NETWORK = 1;
    /**
     * bitmap
     */
    public final static int IAMGE_BITMAP = 2;

    private String imageId;
    private String thumbnailPath;
    private String imagePath;
    private String imageUrl;
    private boolean isSelected = false;
    private int pointln;

    //本地/网络图片
    private int model = IMAGE_LOCAL;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public int getPointln() {
        return pointln;
    }

    public void setPointln(int pointln) {
        this.pointln = pointln;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public int getModel() {
        return model;
    }

}
