package vn.plusplus.database.models;

public class CounterEntity {
    private String maker;
    private Integer quantity;

    public CounterEntity() {
    }

    public CounterEntity(String maker, Integer quantity) {
        this.maker = maker;
        this.quantity = quantity;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
