package calorimeter.kraydn.com.calorimeter.Order;

import android.os.Bundle;
import android.app.Activity;
import android.util.SparseBooleanArray;
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
    List<OrderItem> listProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //addListenerOnButtonClick();
        initAll();
        initListener();
        loadOrder();
    }

    private void initAll(){
        //Getting instance of CheckBoxes and Button from the activty_main.xml file
        buttonOrder=(Button)findViewById(R.id.button1);
        lvProduct = (ListView) findViewById(R.id.lvProduct);
        listProduct = new ArrayList<>();
    }

    private void initListener(){

//        lvProduct.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(OrderActivity.this,"TIKLANDI",Toast.LENGTH_SHORT).show();
//            }
//        });

        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(OrderActivity.this,listProduct.get(i).getName(),Toast.LENGTH_SHORT).show();

            }
        });


        lvProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(OrderActivity.this,listProduct.get(i).getName(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        buttonOrder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray arr = lvProduct.getCheckedItemPositions();

                List<String> list = new ArrayList<>();
                for (int i = 0; i < listProduct.size(); i++) {

                    if(listProduct.get(i).isSelected()){
                        list.add(listProduct.get(i).getName());
                    }

                }

                Toast.makeText(OrderActivity.this, list.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadOrder(){
        listProduct.add(new OrderItem(false,"Pizza"));
        listProduct.add(new OrderItem(false,"Water"));
        listProduct.add(new OrderItem(false,"Çay"));
        lvProduct.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvProduct.setAdapter(new OrderAdapter(OrderActivity.this,listProduct));
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_start, menu);

        return true;
    }

}