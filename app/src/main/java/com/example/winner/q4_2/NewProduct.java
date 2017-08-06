package com.example.winner.q4_2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

/**
 * Created by Winner on 5/5/2016.
 */
public class NewProduct extends Activity{
    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;
    private DB db;

    Button image_button;
    Button send;
    ImageView product_image;
    EditText id;
    EditText name;
    EditText description;
    Product product = new Product();

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
        image_button=(Button)findViewById(R.id.image_button);
        product_image =(ImageView) findViewById(R.id.product_image);
        id=(EditText)findViewById(R.id.id);
        name=(EditText)findViewById(R.id.name);
        description=(EditText)findViewById(R.id.description);
        send=(Button)findViewById(R.id.send_button);

        image_button.setOnClickListener(getImage);
        send.setOnClickListener(sendData);
    }

    Button.OnClickListener getImage = new Button.OnClickListener(){
        public void onClick(View v){
            Intent image = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
          // image.setType("image/*");
            startActivityForResult(image, RESULT_LOAD_IMG);
        }
    };

    Button.OnClickListener sendData = new Button.OnClickListener(){
        public void onClick(View v){
            product.setId(id.getText().toString());
            product.setName(name.getText().toString());
            product.setDescription(description.getText().toString());

            String url = "http://10.0.1.100/database/selection.php";
            String sql ="insert into product values ( "+product.getId()+" , "+product.getName()+" , "+product.getDescription()+" , "+product.getImageFile()+");";
            db = new DB();
            db.execute(url,sql);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        try{
        if(requestCode==RESULT_LOAD_IMG && resultCode==RESULT_OK && data != null)
        {
            Uri selectedImage = data.getData();
             String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            imgDecodableString = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));

            product_image.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
            product.setImageFile(getBytes(BitmapFactory.decodeFile(imgDecodableString)));

            cursor.close();
        }else{
            Toast.makeText(this,"You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }catch (Exception e){
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    public static byte[] getBytes(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,0,stream);
        return stream.toByteArray();
    }
}
