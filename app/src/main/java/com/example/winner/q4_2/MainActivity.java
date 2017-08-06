package com.example.winner.q4_2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
    Button newButton;
    Button dataButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        newButton = (Button)findViewById(R.id.new_button);
        dataButton = (Button)findViewById(R.id.data_button);

        newButton.setOnClickListener(listener);
        dataButton.setOnClickListener(listener);
    }

    Button.OnClickListener listener = new Button.OnClickListener(){
        public void onClick(View v){
            switch (v.getId()){
                case R.id.new_button:
                    Intent new_product_page = new Intent();
                    new_product_page.setClass(MainActivity.this, NewProduct.class);
                    startActivity(new_product_page);
                    break;
                case R.id.data_button:
                    Intent product_data = new Intent();
                    product_data.setClass(MainActivity.this,ProductData.class);
                    startActivity(product_data);
                    break;
            }
        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
