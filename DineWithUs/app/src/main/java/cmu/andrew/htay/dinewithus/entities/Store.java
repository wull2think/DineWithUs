package cmu.andrew.htay.dinewithus.entities;

/**
 * Created by HuiJun on 4/12/16.
 */
public class Store {

    private String name;
    private String address;
    private long latitude;
    private long longitude;
    private int rating;
    private int openingTime;
    private int closingTime;
    private int priceRange;
    private String cuisine;
    private String description;
    private String menuURL;
    private String shopPictureURL;
    private String websiteURL;

    public Store() {

    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public long getLatitude() {
        return latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public int getRating() {
        return rating;
    }

    public int getOpeningTime() {
        return openingTime;
    }

    public int getClosingTime() {
        return closingTime;
    }

    public int getPriceRange() {
        return priceRange;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getDescription() {
        return description;
    }

    public String getMenuURL() {
        return menuURL;
    }

    public String getShopPictureURL() {
        return shopPictureURL;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setOpeningTime(int startTime) {
        this.openingTime = startTime;
    }

    public void setClosingTime(int endTime) {
        this.closingTime = endTime;
    }

    public void setPriceRange(int priceRange) {
        this.priceRange = priceRange;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMenuURL(String menuURL) {
        this.menuURL = menuURL;
    }

    public void setShopPictureURL(String pictureURL){
        this.shopPictureURL = pictureURL;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    public boolean hasCoordinates(long latitude, long longitude) {
        return this.latitude == latitude && this.longitude == longitude;
    }

    public boolean hasHours(int startTime, int closingTime) {
        return this.openingTime >= startTime && this.closingTime <= closingTime;
    }

    public boolean hasCuisine(String cuisine) {
        return this.cuisine == cuisine;
    }

}
