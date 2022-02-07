//Aryan Sood
//N01393003
//CENG 258 RNA


package aryan.sood.n01393003;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class OrderActivity extends AppCompatActivity {
    boolean selectType = false;
    boolean selectSize = false;
    boolean selectTop = false;
    public static final String TOTAL = "TOTAL";
    public static final String SUMMARY = "SUMMARY";
    public static final  String LOCATION ="Store" ;
    String[] orderDetail = new String[20];
    String storeName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String [] arr= getResources().getStringArray(R.array.stores);
        setContentView(R.layout.activity_order);
        snackbar_show();
        storeName = getIntent().getStringExtra(AryanActivity.STORE);
        TextView storeTitle = findViewById(R.id.AryanStoreName);
        storeTitle.setText(storeName);
        ImageView img=(ImageView) findViewById(R.id.store_image);
        if(storeName.equalsIgnoreCase(arr[0]))
        {
           img.setImageResource(R.drawable.pizpiz);}
        else if(storeName.equalsIgnoreCase(arr[2]))
        {
            img.setImageResource(R.drawable.dominos);}
        else if(storeName.equalsIgnoreCase(arr[1]))
        {
            img.setImageResource((R.drawable.pizzanova));}
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    void snackbar_show() {
        View parentLayout = findViewById(android.R.id.content);
        Snackbar snackbar=Snackbar.make(parentLayout, R.string.Review, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.next , new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        onCheckout(view);
                    }
                });
        snackbar.show();
    }


    public double onSelectType() {
        RadioButton Boston = (RadioButton) findViewById(R.id.AryanBoston);
        RadioButton Cheese = (RadioButton) findViewById(R.id.AryanCheese);
        RadioButton Chicago = (RadioButton) findViewById(R.id.AryanChicago);
        RadioButton Italian = (RadioButton) findViewById(R.id.AryanItalian);
        if (Boston.isChecked()) {
            selectType = true;
            orderDetail[0] = getString(R.string.boston_lael);
            return 9.99;
        } else if (Cheese.isChecked()) {
            orderDetail[0] = getString(R.string.chesse_label);
            selectType = true;
            return 5.99;
        } else if (Chicago.isChecked()) {
            orderDetail[0] = getString(R.string.chicago_label);
            selectType = true;
            return 12.99;
        } else if (Italian.isChecked()) {
            orderDetail[0] = getString(R.string.italian_label);
            selectType = true;
            return 15.99;}
            return 0;
        }

    public double onSelectSize(){
        RadioButton Small = (RadioButton)findViewById(R.id.AryanSmall);
        RadioButton Medium = (RadioButton)findViewById(R.id.AryanMedium);
        RadioButton Large = (RadioButton)findViewById(R.id.AryanhLarge);

        if(Small.isChecked()){
            selectSize = true;
            orderDetail[1]=getString(R.string.pizza_size_small);
            return 4.5;
        }else if (Medium.isChecked()){
            selectSize = true;
            orderDetail[1]=getString(R.string.pizza_size_med);
            return 6.5;
        }else if(Large.isChecked()){
            selectSize = true;
            orderDetail[1]=getString(R.string.pizza_size_lg);
            return 8.5;
        }
        return 0;
    }

    public double onExtraTop(){
        CheckBox chick= findViewById(R.id.AryanChick);
        CheckBox rpep = findViewById(R.id.Aryanpepper);
        CheckBox pine = findViewById(R.id.AryanPineapple);
        CheckBox extcheese = findViewById(R.id.AryanExtraCheese);
        CheckBox oni = findViewById(R.id.AryanOnions);
        int count =0;
        String toppings = new String();
        if(chick.isChecked()){
            toppings = getString(R.string.top_chick);
            count ++;
        }
        if(rpep.isChecked()) {
            toppings += getString(R.string.top_R_pep);
            count++;
        }
        if(pine.isChecked()) {
            toppings += getString(R.string.top_pine);
            count++;
        }
        if(extcheese.isChecked()) {
            toppings += getString(R.string.top_ext_cheese);
            count++;
        }
        if(oni.isChecked()) {
            toppings += getString(R.string.top_oni);
            count++;
        }
        if(count>0){
            selectTop = true;
        }
        orderDetail[2] = toppings;
        return (count*.5);
    }
    public void onCheckout(View view){
        double total= 0;
        total = onSelectSize();
        total+= onSelectType();
        total+= onExtraTop();
        if(selectType&&selectSize&&selectTop) {
            Intent intent = null;
            intent = new Intent(this, PaymentActivity.class);
            intent.putExtra(TOTAL,total);
            intent.putExtra(SUMMARY,orderDetail);
            intent.putExtra(LOCATION,storeName);
            startActivity(intent);

        }else{
            Toast.makeText(getBaseContext(), R.string.selsction,Toast.LENGTH_SHORT).show();
            snackbar_show();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        snackbar_show();
    }
}