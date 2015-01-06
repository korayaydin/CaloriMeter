package calorimeter.kraydn.com.calorimeter.Order;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

import calorimeter.kraydn.com.calorimeter.R;

public class OrderActivity extends Activity {
    CheckBox pizza,coffee,burger;
    Button buttonOrder;
    ListView lvProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //addListenerOnButtonClick();
        initAll();
        loadOrder();
    }

    private void initAll(){
        //Getting instance of CheckBoxes and Button from the activty_main.xml file
        buttonOrder=(Button)findViewById(R.id.button1);
        lvProduct = (ListView) findViewById(R.id.lvProduct);
    }

    private void loadOrder(){
        List<OrderItem> list = new ArrayList<>();

        list.add(new OrderItem(false,"Pizza"));
        list.add(new OrderItem(false,"Water"));
        list.add(new OrderItem(false,"Çay"));

        lvProduct.setAdapter(new OrderAdapter(OrderActivity.this,list));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_start, menu);

        return true;
    }

}