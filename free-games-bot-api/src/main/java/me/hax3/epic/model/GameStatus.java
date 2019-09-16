package me.hax3.epic.model;

public class GameStatus {

    private String name;

    private String price;

    private boolean discount;

    private boolean owned;

    public GameStatus(String name, String price, boolean discount, boolean owned) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.owned = owned;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public boolean isDiscount() {
        return discount;
    }

    public boolean isOwned() {
        return owned;
    }

}
