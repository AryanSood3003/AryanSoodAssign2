//Aryan Sood
//N01393003
//CENG 258 RNA


package aryan.sood.n01393003;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DecimalFormat;

public class PaymentActivity extends AppCompatActivity {
    public static final String TOTAL = "TOTAL";
    public static final String SUMMARY = "SUMMARY";
    public static final String PAYMENT = "PAYMENT";
    public static final String DELIVERY = "DELIVERY";
    TextView totalLabel;
    EditText name,card,address;

    String[] orderDetails = new String[25];
    String[] payInfo = new String[20];
    String CCname,CCnum,CCaddress,Province;
    boolean delivery = false;
    double grossTotal =0;
    double delCharge = 0;
String storeName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        name= findViewById(R.id.AryanCCName);
        card = findViewById(R.id.AryanCCNumber);
        address = findViewById(R.id.AryanAddress);
        String [] arr= getResources().getStringArray(R.array.stores);
        Intent intent = getIntent();
        total(intent);
        storeName = getIntent().getStringExtra(OrderActivity.LOCATION);
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
        else if(storeName.equalsIgnoreCase(arr[3]))
        {
            img.setImageResource(R.drawable.papa);
        }
        Spinner spinner = (Spinner)findViewById(R.id.Provinces);
        String text = spinner.getSelectedItem().toString();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public void total(Intent intent) {
        double total = intent.getDoubleExtra(OrderActivity.TOTAL,0);
        double tax = Math.round((total*.13));
        tax=Math.round(tax*100.00)/100.00;
        orderDetails = intent.getStringArrayExtra(OrderActivity.SUMMARY);
        TextView orderSummary = findViewById(R.id.AryanOrderSummary);
        orderSummary.setText(getString(R.string.style)+ orderDetails[0] +getString(R.string.size)+ orderDetails[1]+ getString(R.string.toppings)+ orderDetails[2] +getString(R.string.tax)+ tax);
        totalLabel = findViewById(R.id.AryanTotalAmount);
        total += tax;
        grossTotal = total;
        DecimalFormat df = new DecimalFormat("####0.00");
        totalLabel.setText("$"+df.format(total));
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, AryanActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void addDelivery(boolean delivery){
        TextView deliveryCharge =  findViewById(R.id.DeliveryCharge);
        if(delivery){
            delCharge = 3;
            deliveryCharge.setText(getString(R.string.charge)+String.valueOf(delCharge) + getString(R.string.Free));
        }else{
            deliveryCharge.setText(getString(R.string.charge)+getString(R.string.zero));

        }
    }
    public void getPayInfo(){
        payInfo[0] = CCname;
        payInfo[1] =CCnum;
        payInfo[2] = CCaddress;
        payInfo[3]=Province;
    }
    public boolean validateName(){
        CCname = name.getText().toString().trim();
        CCnum = card.getText().toString();
        CCaddress = address.getText().toString();
        Spinner spinner = (Spinner)findViewById(R.id.Provinces);
        Province = spinner.getSelectedItem().toString();
        boolean validate = true;
        if(CCname.length()<3){
            name.setError(getString(R.string.CCname_3_error));
            validate = false;
        }else if (CCnum.length()<5){
            validate = false;
            card.setError(getString(R.string.CCnum_error));
        }else if (CCnum.isEmpty()){
            validate = false;
            card.setError(getString(R.string.empty_field_error));
        }else if(CCaddress.isEmpty()){
            validate = false;
            address.setError(getString(R.string.address_empty_error));
        }
        return validate;
    }
    public void callSwitch(View view){
        Switch switchLabel = findViewById(R.id.AryanDeliveryOption);

        if(switchLabel.isChecked()){
            delivery =true;
        }else{
            delivery =false;
        }
        addDelivery(delivery);
    }
    public void onPlaceOrder(View view){
        if(validateName()) {
            getPayInfo();
            Intent intent = new Intent(this,CheckoutActivity.class);
            intent.putExtra(TOTAL, grossTotal);
            intent.putExtra(SUMMARY, orderDetails);
            intent.putExtra(DELIVERY,delivery);
            intent.putExtra(PAYMENT,payInfo);
            startActivity(intent);
        }
    }


}