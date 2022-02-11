//Aryan Sood
//N01393003
//CENG 258 RNA


package aryan.sood.n01393003;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class AryanActivity extends AppCompatActivity {
  public static final  String STORE ="store";
    boolean storeSelect = false;
    MenuItem icon;
    String store = null;
    int StoreId = 0;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aryan);
        icon = findViewById(R.id.AryanStoreLogo);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        icon = menu.getItem(1);
        return true;
    }

    public void storeClick(View view) {
        storeselect();
    }

    public void storeselect() {
        String[] arr = getResources().getStringArray(R.array.stores);
        RadioButton pizpiz = (RadioButton) findViewById(R.id.AryanPizPiz);
        RadioButton nova = (RadioButton) findViewById(R.id.AryanNova);
        RadioButton domi = (RadioButton) findViewById(R.id.AryanDomin);
        RadioButton papa=(RadioButton) findViewById(R.id.Aryanpapa);
        if (pizpiz.isChecked()) {
            storeSelect = true;
            StoreId = 1;
            store = arr[0];
        } else if (nova.isChecked()) {
            storeSelect = true;
            StoreId = 2;
            store = arr[1];
        } else if (domi.isChecked()) {
            storeSelect = true;
            StoreId = 3;
            store = arr[2];
        } else if(papa.isChecked()) {
            storeSelect = true;
            StoreId = 3;
            store = arr[3];
        }
    }


    public void displayToast(String msg){
        Toast.makeText(getBaseContext(),msg,Toast.LENGTH_SHORT).show();
    }

    public void onNext(View view){
        Intent intent = null;
        intent = new Intent(this, OrderActivity.class);
        if(storeSelect) {
            displayToast(store);
            intent.putExtra(STORE,store);
            startActivity(intent);
        }else{
            displayToast(getString(R.string.select_store_msg));
        }
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

        }   return super.onOptionsItemSelected(item);
    }
    @Override
    public void onResume(){
        super.onResume();
      storeselect();

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AryanActivity.this);
        builder.setTitle(R.string.exit);
        builder.setMessage(R.string.exitmsg)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                        System.exit(0);
                    }
                })
                .setIcon(R.drawable.alert)
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.show();
    }



}