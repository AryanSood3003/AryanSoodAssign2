package aryan.sood.n01393003;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class AryanActivity extends AppCompatActivity {
    MenuItem icon;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        String helpSite = getString(R.string.help_redirect);
        switch (item.getItemId())
        {
            case R.id.AryanStoreLogo:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0123456789"));
                startActivity(intent);
                break;
            case R.id.AryanHelp:
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(helpSite));
                Context context = getApplicationContext();
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
}