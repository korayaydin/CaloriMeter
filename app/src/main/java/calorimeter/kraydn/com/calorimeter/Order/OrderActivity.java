package calorimeter.kraydn.com.calorimeter.Order;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
                String result = "";

                int total = 0;
                for (int i = 0; i < listProduct.size(); i++) {
                    if(listProduct.get(i).isSelected()){
                        result = result.concat(listProduct.get(i).getName() + " " +listProduct.get(i).getValue() +  " CAL\n");
                        total += listProduct.get(i).getValue();
                    }
                }
                result = result.concat("\nTotal: " + total + " CAL");

                AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
                builder.setMessage("Your result is: \n\n"+result);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dlg = builder.create();
                dlg.show();

                //Toast.makeText(OrderActivity.this, list.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadOrder(){
        listProduct.add(new OrderItem(false,"Pizza", 120));
        listProduct.add(new OrderItem(false,"Burger", 50));
        listProduct.add(new OrderItem(false,"Coffee", 20));
        listProduct.add(new OrderItem(false,"Gencer", 0));

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