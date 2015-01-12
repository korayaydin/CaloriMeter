package calorimeter.kraydn.com.calorimeter.Order;

/**
 * Created by Koray on 6.1.2015.
 */
public class OrderItem {
    private boolean isSelected;
    private String name;

    public OrderItem(boolean isSelected, String name){
        this.isSelected = isSelected;
        this.name = name;

    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
