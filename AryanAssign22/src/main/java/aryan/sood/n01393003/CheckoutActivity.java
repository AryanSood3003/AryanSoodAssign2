//Aryan Sood
//N01393003
//CENG 258 RNA

package aryan.sood.n01393003;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class CheckoutActivity extends AppCompatActivity {
    double grossTotal =0;
    String[] orderDetails = new String[25];
    String[] payDetails = new String[25];
    boolean delivery =false;
    MenuItem icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        icon = findViewById(R.id.AryanStoreLogo);
            Intent intent = getIntent();
            payDetails = intent.getStringArrayExtra(PaymentActivity.PAYMENT);
            delivery = intent.getBooleanExtra(PaymentActivity.DELIVERY,false);

            grossTotal = intent.getDoubleExtra(PaymentActivity.TOTAL,0);
            grossTotal=Math.round(grossTotal*100.00)/100.00;
            orderDetails = intent.getStringArrayExtra(PaymentActivity.SUMMARY);
            TextView finalcheck = findViewById(R.id.finalOrderSummary);
            TextView finalpay = findViewById(R.id.FinalPaymentInfo);
            finalcheck.setText(getString(R.string.Style)+ orderDetails[0] +getString(R.string.Size)+ orderDetails[1]+ getString(R.string.Toppings)+ orderDetails[2] +getString(R.string.ttl)+ grossTotal);
            finalpay.setText(getString(R.string.name) + payDetails[0] + getString(R.string.Card_num) + payDetails[1]+ getString(R.string.addr) + payDetails[2]+getString(R.string.province)+payDetails[3]);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        icon = menu.getItem(1);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        String helpSite = getString(R.string.help_redirect);
        switch (item.getItemId())
        {
            case R.id.AryanStoreLogo:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(getString(R.string.p_no)));
                startActivity(intent);
                break;
            case R.id.AryanHelp:
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(helpSite));
                startActivity(intent);
                break;
            case R.id.Aryanlogo2:
                View parentLayout = findViewById(android.R.id.content);
                Snackbar snackbar=Snackbar.make(parentLayout,
                        R.string.help_pizza,
                        Snackbar.LENGTH_LONG);
                snackbar.show();
                break;
            case android.R.id.home:
                Intent intent2 = new Intent(this, AryanActivity.class);
                startActivity(intent2);
               break;

        }   return super.onOptionsItemSelected(item);
    }

        public String alertMsg(){
            int rand = (int) Math.round((Math.random()*100));
            String msg= null;
            if(delivery) {
                msg = getString(R.string.order_number) + String.valueOf(rand) + getString(R.string.del_msg);
            }else {
                msg = getString(R.string.order_number) + String.valueOf(rand) + getString(R.string.pick_msg);
            }
            return msg;
        }
        public void callMainActivity(){
            Intent intent = new Intent(this,AryanActivity.class);
            startActivity(intent);
        }
        public void onCheckout(View view){
            AlertDialog.Builder builder = new AlertDialog.Builder(CheckoutActivity.this);
            builder.setTitle(R.string.confirmed_order_msg);
            builder.setMessage(alertMsg())
                    .setCancelable(false)
                    .setPositiveButton(R.string.final_dialog_dismiss,new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            callMainActivity();
                        }})
                    .setIcon(R.drawable.applogo);

            builder.show();
        }
    }