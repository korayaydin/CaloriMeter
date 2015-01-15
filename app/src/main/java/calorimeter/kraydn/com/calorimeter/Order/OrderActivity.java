package calorimeter.kraydn.com.calorimeter.Order;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.ArrayList;
import java.util.List;

import calorimeter.kraydn.com.calorimeter.R;

public class OrderActivity extends Activity {
    CheckBox pizza,coffee,burger;
    Button buttonOrder;
    ListView lvProduct;
    List<OrderItem> listProduct;

    private class UpdateSettings extends AsyncTask<String, Void, HttpResponse>{
        @Override
        protected HttpResponse doInBackground(String... params) {
            HttpResponse response = null;
            HttpGet httpget = new HttpGet(params[0]);

            try {
                HttpClient client = new DefaultHttpClient();
                response = client.execute(httpget);

                ;

            }
            catch (Exception ex){
            }

            return response;
        }
    }

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
                String pref = "";
                for (int i = 0; i < listProduct.size(); i++) {
                    if(listProduct.get(i).isSelected()){
                        result = result.concat(listProduct.get(i).getName() + " " +listProduct.get(i).getValue() +  " CAL\n");
                        total += listProduct.get(i).getValue();
                        pref += i;
                    }
                }

                final String finalPref = pref;
                result = result.concat("\nTotal: " + total + " CAL");

                AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
                builder.setMessage("Your result is: \n\n"+result);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        UpdateSettings settings = new UpdateSettings();
                        settings.execute("http://calorimeter.host22.com/?secret=dambil&msg="+finalPref);
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
        listProduct.add(new OrderItem(false,"A slice of BREAD", 90));
        listProduct.add(new OrderItem(false,"Croissant", 200));
        listProduct.add(new OrderItem(false,"Biscuit", 470));
        listProduct.add(new OrderItem(false,"Pasta", 85));
        listProduct.add(new OrderItem(false,"Steak(100g)", 278));
        listProduct.add(new OrderItem(false,"Chicken(100g)", 132));
        listProduct.add(new OrderItem(false,"Egg", 158));
        listProduct.add(new OrderItem(false,"Bean", 340));
        listProduct.add(new OrderItem(false,"Milk", 61));
        listProduct.add(new OrderItem(false,"Cacao", 91));
        listProduct.add(new OrderItem(false,"Cola", 149));
        listProduct.add(new OrderItem(false,"Orange Juice", 45));
        listProduct.add(new OrderItem(false,"Pasta", 85));
        listProduct.add(new OrderItem(false,"Pasta", 85));
        listProduct.add(new OrderItem(false,"Pasta", 85));
        listProduct.add(new OrderItem(false,"Pasta", 85));
        listProduct.add(new OrderItem(false,"Pasta", 85));
        listProduct.add(new OrderItem(false,"Pasta", 85));
        listProduct.add(new OrderItem(false,"Pasta", 85));
        listProduct.add(new OrderItem(false,"Pasta", 85));
        listProduct.add(new OrderItem(false,"Pasta", 85));
        listProduct.add(new OrderItem(false,"Pasta", 85));
        listProduct.add(new OrderItem(false,"Pasta", 85));
        listProduct.add(new OrderItem(false,"Pasta", 85));
        listProduct.add(new OrderItem(false,"Pasta", 85));
        listProduct.add(new OrderItem(false,"Pasta", 85));
        listProduct.add(new OrderItem(false,"Pasta", 85));
        listProduct.add(new OrderItem(false,"Pasta", 85));
        listProduct.add(new OrderItem(false,"Pasta", 85));
        listProduct.add(new OrderItem(false,"Pasta", 85));

        lvProduct.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvProduct.setAdapter(new OrderAdapter(OrderActivity.this,listProduct));

        UpdateSettings settings = new UpdateSettings();
        settings.execute("http://calorimeter.host22.com/?secret=dambil");
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_start, menu);

        return true;
    }

}