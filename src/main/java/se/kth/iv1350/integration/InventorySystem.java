package se.kth.iv1350.integration;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.DTO.ItemDTO;
import se.kth.iv1350.model.StoreItem;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InventorySystem
{
    private Map<String, StoreItem> itemMap;

    /**
     * Creates the inventory
     */

    public InventorySystem(){
        itemMap = new HashMap<>();

    }
    /**
     * Creates all the items for the inventory.
     */
    public void createItem(){
        addItem(new StoreItem(new ItemDTO("apple", new Amount(10, "SEK"), "green apple 150g"), "apple", 8, 0.25));
        addItem(new StoreItem(new ItemDTO("orange", new Amount(10, "SEK"), " an orange that weights 100g"), "orange", 5, 0.25));
        addItem(new StoreItem(new ItemDTO("BigWheel Oatmeal", new Amount(50, "SEK"), "BigWheel Oatmeal 500 g , whole grain oats , high fiber , gluten free"), "BigWheel Oatmeal", 10, 0.06));
    }

    /**
     * addItem function adds items to the Hashmap
     *
     * @param item takes in the item to add
     */
    public void addItem( StoreItem item) {
        itemMap.put(item.getItemID(), item);
    }

    /**
     * is used to retrieve Item by id
     *
     * @param itemId is the itemID that is used as a key to retrieve the item
     */
    public StoreItem getItemById(String itemId) {
        return itemMap.get(itemId);
    }
    /**
     * CheckItem function is used to check if an item exist in the HashMap
     *
     * @param ID is the key that is used for search
     */
    public boolean checkItem(String ID){
        return itemMap.containsKey(ID);
    }
    /**
     * function removes item from HashMap
     *
     * @param itemId is the key that is used for search
     */
    public void removeItem(String itemId) {
        itemMap.remove(itemId);
    }
    /**
     * updateInventory removes and increases the quantity or Item in the inventory
     *
     * @param boughtItems represent ShoppingCart during the sale
     */
    public void updateInventory( Collection<StoreItem> boughtItems) {
        for (StoreItem item : boughtItems ) {

            StoreItem itemInStore = getItemById(item.getItemID());
            if (item.equals(itemInStore)){
                if (itemInStore.getQuantity() == item.getQuantity()) {
                    // If new quantity is less than or equal to 0, remove the item
                    removeItem(item.getItemID());
                } else {
                    // If new quantity is positive, update the item in the map
                    itemInStore.setQuantity(itemInStore.getQuantity() - item.getQuantity() );

                }
            }



        }

    }

}

