package calorimeter.kraydn.com.calorimeter.Order;

/**
 * Created by Koray on 6.1.2015.
 */
public class OrderItem {
    private boolean isSelected;
    private String name;
    private int value;

    public OrderItem(boolean isSelected, String name, int value){

        this.isSelected = isSelected;
        this.name = name;
        this.value = value;

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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
