package se.kth.iv1350.integration;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.DTO.ItemDTO;
import se.kth.iv1350.model.DTO.SaleDTO;
import se.kth.iv1350.model.StoreItem;

import java.util.HashMap;
import java.util.Map;

public class InventorySystem
{
    private static final InventorySystem INSTANCE = new InventorySystem();
    private Map<String, StoreItem> itemMap;
    private int inventoryStatus;

    /**
     * Creates the inventory
     */

    private InventorySystem(){
        itemMap = new HashMap<>();
        setInventoryStatus("Online");

    }
    /**
     * Returns the object instance
     */
    public static InventorySystem getInstance(){
        return INSTANCE;
    }
    /**
     * Creates all the items for the inventory.
     */
    public void createItem(){
        if (inventoryStatus == 1){
            addItem(new StoreItem(new ItemDTO("apple", new Amount(10, "SEK"), "green apple 150g"), "apple", 8, 0.25));
            addItem(new StoreItem(new ItemDTO("orange", new Amount(10, "SEK"), " an orange that weights 100g"), "orange", 5, 0.25));
            addItem(new StoreItem(new ItemDTO("BigWheel Oatmeal", new Amount(50, "SEK"), "BigWheel Oatmeal 500 g , whole grain oats , high fiber , gluten free"), "BigWheel Oatmeal", 10, 0.06));
        }
        else {
            throw new DatabaseUnavailableException("Inventory is Unavailable");
        }



    }

    /**
     * We set the inventory status
     */
    public void setInventoryStatus( String status ) {
        if (status.toLowerCase().equals("online"))
            this.inventoryStatus = 1;
        else if (status.toLowerCase().equals("offline"))
            this.inventoryStatus = 0;
    }

    /**
     * addItem function adds items to the Hashmap
     *
     * @param item takes in the item to add
     */
    public void addItem( StoreItem item) {

        if (inventoryStatus == 1){
            itemMap.put(item.getItemID(), item);
        }
        else {
            throw new DatabaseUnavailableException("Inventory is Unavailable");
        }



    }

    /**
     * is used to retrieve Item by id
     *
     * @param itemId is the itemID that is used as a key to retrieve the item
     */
    public StoreItem getItemById(String itemId) throws InvalidItemException {

        if (checkItem(itemId)){
            return itemMap.get(itemId);
        }
        if (inventoryStatus == 0){
            throw new DatabaseUnavailableException("ERROR: Inventory is Unavailable");
        }

        throw new InvalidItemException("ERROR: The given item ID is Invalid");


    }
    /**
     * CheckItem function is used to check if an item exist in the HashMap
     *
     * @param ID is the key that is used for search
     */
    public boolean checkItem(String ID){
        if (inventoryStatus == 1){
            return itemMap.containsKey(ID);
        }
        else {
            throw new DatabaseUnavailableException("ERROR: Inventory is Unavailable");
        }

    }
    /**
     * function removes item from HashMap
     *
     * @param itemId is the key that is used for search
     */
    public void removeItem(String itemId) {
        if (inventoryStatus == 1){
            itemMap.remove(itemId);
        }
        else {
            throw new DatabaseUnavailableException("ERROR : Inventory is Unavailable");
        }



    }
    /**
     * updateInventory removes and increases the quantity or Item in the inventory
     *
     * If new quantity is less than or equal to 0, remove the item
     * If new quantity is positive, update the item in the map
     *
     * @param saleDetails are the saleDetails used during sale
     */
    public void updateInventory( SaleDTO saleDetails ) throws InvalidItemException {
        if (inventoryStatus == 1){
            for (StoreItem item : saleDetails.getAllItems() ) {

                StoreItem itemInStore = getItemById(item.getItemID());
                if (item.equals(itemInStore)){
                    if (itemInStore.getQuantity() == item.getQuantity()) {
                        removeItem(item.getItemID());
                    } else {
                        itemInStore.setQuantity(itemInStore.getQuantity() - item.getQuantity() );

                    }
                }



            }
        }
        else {
            throw new DatabaseUnavailableException("ERROR: Cannot update. Inventory status is equal to 0");
        }


    }

}

