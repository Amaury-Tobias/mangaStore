package models.item;

public class Item {

    private int id;
    private String name;
    private String description;
    private Float price;
    private int valoration;
    private int searchedTimes;
    private int viewedTimes;
    private String category;
    private boolean active;

    private String owner;
    private boolean ownerBool;

    private byte[] picture1;
    private byte[] picture2;
    private byte[] picture3;
    private String videoPath;

    public int getId() {
        return id;
    }

    /**
     * @return the ownerBool
     */
    public boolean isOwnerBool() {
        return ownerBool;
    }

    /**
     * @param ownerBool the ownerBool to set
     */
    public void setOwnerBool(boolean ownerBool) {
        this.ownerBool = ownerBool;
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
