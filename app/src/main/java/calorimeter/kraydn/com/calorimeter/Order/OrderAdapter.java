package calorimeter.kraydn.com.calorimeter.Order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import calorimeter.kraydn.com.calorimeter.R;

/**
 * Created by Koray on 6.10.2014.
 */
public class OrderAdapter extends BaseAdapter {
    private Context context;
    private List<OrderItem> listProduct;

    public OrderAdapter(Context context, List<OrderItem> listProduct){
        this.context = context;
        this.listProduct = listProduct;
    }

    @Override
    public int getCount() {
        return listProduct.size();
    }

    @Override
    public Object getItem(int position) {
        return listProduct.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //View view = ((LayoutInflater) (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))).inflate(R.layout.order_item_layout, null);;
        View view = LayoutInflater.from(context).inflate(R.layout.order_item_layout, null);

        CheckedTextView cbOrder = (CheckedTextView)view.findViewById(R.id.cbOrder);
        cbOrder.setSelected(listProduct.get(position).isSelected());

        cbOrder.setText(listProduct.get(position).getName());

        return view;
    }
}
