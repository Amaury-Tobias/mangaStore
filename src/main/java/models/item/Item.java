package models.item;

public class Item {

    private int id;
    private String name;
    private String description;
    private Float price;
    private int valoration;
    private int searchedTimes;
    private int viewedTimes;

    private byte[] picture1;
    private byte[] picture2;
    private byte[] picture3;
    private String videoPath;

    public int getId() {
        return id;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public byte[] getPicture3() {
        return picture3;
    }

    public void setPicture3(byte[] picture3) {
        this.picture3 = picture3;
    }

    public byte[] getPicture2() {
        return picture2;
    }

    public void setPicture2(byte[] picture2) {
        this.picture2 = picture2;
    }

    public byte[] getPicture1() {
        return picture1;
    }

    public void setPicture1(byte[] picture1) {
        this.picture1 = picture1;
    }

    public int getViewedTimes() {
        return viewedTimes;
    }

    public void setViewedTimes(int viewedTimes) {
        this.viewedTimes = viewedTimes;
    }

    public int getSearchedTimes() {
        return searchedTimes;
    }

    public void setSearchedTimes(int searchedTimes) {
        this.searchedTimes = searchedTimes;
    }

    public int getValoration() {
        return valoration;
    }

    public void setValoration(int valoration) {
        this.valoration = valoration;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

}
