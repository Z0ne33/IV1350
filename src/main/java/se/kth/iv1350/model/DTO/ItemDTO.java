package se.kth.iv1350.model.DTO;

import se.kth.iv1350.model.Amount;

public class ItemDTO
{

    private Amount price;
    private String name;
    private String description;

    /**
     * is used for construction new ItemDTO
     */

    public ItemDTO(String name , Amount price, String desc){
        this.name = name;
        this.price = price;
        this.description = desc;
    }

    /**
     * return price
     */

    public Amount getPrice() {
        return price;
    }


    /**
     * return name
     */
    public String getName() {
        return name;
    }

    /**
     * return desc
     */
    public String getDescription() {
        return description;
    }
}
