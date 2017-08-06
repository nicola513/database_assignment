package com.example.winner.q4_2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Winner on 5/5/2016.
 */
public class ProductData extends Activity{
    DB db;

    TextView data_id;
    TextView data_name;
    TextView description;
    ImageView data_image;
    Button first_button;
    Button next_button;
    Button prev_button;

    List<Product> products = new ArrayList<Product>();
    List<Integer> imageSrc = new ArrayList<Integer>();
    int pageNo=0;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_data);

        data_id=(TextView)findViewById(R.id.data_id);
        data_name=(TextView)findViewById(R.id.data_name);
        description=(TextView)findViewById(R.id.description);
        data_image=(ImageView)findViewById(R.id.product_image);
        first_button=(Button)findViewById(R.id.first_button);
        next_button=(Button)findViewById(R.id.next_button);
        prev_button=(Button)findViewById(R.id.prev_button);

        first_button.setOnClickListener(listener);
        next_button.setOnClickListener(listener);
        prev_button.setOnClickListener(listener);

        String url = "http://10.0.1.100/database/selection.php";
        String sql = "select * from product";
       db = new DB();
        db.execute(url, sql);

        data_id.setText(products.get(0).getName());
      data_name.setText(products.get(0).getName());
      description.setText(products.get(0).getDescription());
       data_image.setImageResource(R.drawable.one);
    }

    public void dbResult(JSONObject result){
            String rc = (String) result.remove("$rc");
            Log.e("json,response", rc);

            if(result!=null) {
                products = db.getAllProduct();
            }
    }

    Button.OnClickListener listener = new Button.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.first_button:
                    pageNo=0;
                    break;
                case R.id.next_button:
                    if((pageNo+1)!=products.size())
                        pageNo++;
                    break;
                case R.id.prev_button:
                    if(pageNo!=0)
                        pageNo--;
                    break;
            }
            data_id.setText(products.get(pageNo).getId());
            data_name.setText(products.get(pageNo).getName());
            description.setText(products.get(pageNo).getDescription());
            data_image.setImageBitmap(getImage(products.get(pageNo).getImageFile()));
        }
    };

    public static Bitmap getImage(byte[] image){
        return BitmapFactory.decodeByteArray(image,0,image.length);
    }

}
