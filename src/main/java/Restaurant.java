import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        String hour = String.format("%02d", getCurrentTime().getHour());
        String minute = String.format("%02d", getCurrentTime().getMinute());
        String second = String.format("%02d", getCurrentTime().getSecond());

        LocalTime currentTime = LocalTime.parse(hour+":"+minute+":"+second);

        return !currentTime.isBefore(openingTime) && !currentTime.isAfter(closingTime);
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    /**
     * Add default menu items in the menu item list.
     */
    public void addDefaultMenuItems() {
        addToMenu("Sweet Corn Soup",119);
        addToMenu("Vegetable lasagne",269);
        addToMenu("Chole Bhature",150);
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    /**
     * Added new feature to get the order cost of selected items.
     * @param itemNames - name of selected menu item.
     * @return - total order cost of select menu item.
     */
    public int getOrderCost(List<String> itemNames) {

        int totalOrderCost = 0;

        for(String itemName : itemNames) {
            Item item = findItemByName(itemName);
            if(item != null)
                totalOrderCost += item.getPrice();
        }

        return totalOrderCost;
    }

}
