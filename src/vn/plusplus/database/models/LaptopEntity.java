package vn.plusplus.database.models;

import java.sql.Timestamp;

public class LaptopEntity {
    private int id;
    private String name;
    private String url;
    private String maker;
    private String type;
    private String ram;
    private String cpu;
    private String ssd;
    private String hdd;
    private Float price;
    private String card;
    private String screenResolution;
    private Float screenSize;
    private int sold;
    private Timestamp createdTimeStamp;
    private Timestamp lastedUpdateTimeStamp;
    public LaptopEntity(){ }

    public LaptopEntity(String name, String url, String maker, String type, String ram, String cpu, String ssd, String hdd, Float price, String card, String screenResolution, Float screenSize, int sold, Timestamp createdTimeStamp, Timestamp lastedUpdateTimeStamp) {
        this.name = name;
        this.url = url;
        this.maker = maker;
        this.type = type;
        this.ram = ram;
        this.cpu = cpu;
        this.ssd = ssd;
        this.hdd = hdd;
        this.price = price;
        this.card = card;
        this.screenResolution = screenResolution;
        this.screenSize = screenSize;
        this.sold = sold;
        this.createdTimeStamp = createdTimeStamp;
        this.lastedUpdateTimeStamp = lastedUpdateTimeStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getSsd() {
        return ssd;
    }

    public void setSsd(String ssd) {
        this.ssd = ssd;
    }

    public String getHdd() {
        return hdd;
    }

    public void setHdd(String hdd) {
        this.hdd = hdd;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    public Float getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(Float screenSize) {
        this.screenSize = screenSize;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public Timestamp getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Timestamp createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public Timestamp getLastedUpdateTimeStamp() {
        return lastedUpdateTimeStamp;
    }

    public void setLastedUpdateTimeStamp(Timestamp lastedUpdateTimeStamp) {
        this.lastedUpdateTimeStamp = lastedUpdateTimeStamp;
    }
}

