package se.kth.iv1350.model.DTO;

import se.kth.iv1350.model.Amount;

public class ItemDTO
{

    private Amount price;
    private String name;
    private String description;

    public ItemDTO(String name , Amount price, String desc){
        this.name = name;
        this.price = price;
        this.description = desc;


    }

    public Amount getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
