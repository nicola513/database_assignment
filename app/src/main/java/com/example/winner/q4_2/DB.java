package com.example.winner.q4_2;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Winner on 6/5/2016.
 */
public class DB extends AsyncTask<String,Void,JSONObject> {
    Product product;

    @Override
    protected JSONObject doInBackground(String... urls) {
        String url = urls[0];
        String sql = urls[1];

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("sql",sql));
        JSONObject json = httpPostRequest(url,params);
        return json;
    }
    private JSONObject httpPostRequest(String url,List<NameValuePair>params){
       InputStream in;
        StringBuilder sb = null;
        JSONObject error = new JSONObject();
        String errorString="0";

        try{
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            DefaultHttpClient httpClient = new DefaultHttpClient();
            in = httpClient.execute(httpPost).getEntity().getContent();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(in, "iso-8859-1"), 8);
            sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            in.close();
            // return JSONObject
            return new JSONObject(sb.toString());
        } catch (JSONException e) {
            errorString = e.toString();
            Log.e("post", "Invalid json data " + e.toString() + "\n" +
                    sb.toString());
        } catch (UnsupportedEncodingException e) {
            errorString = e.toString();
        } catch (ClientProtocolException e) {
            errorString = e.toString();
        } catch (IOException e) {
            errorString = e.toString();
        } catch (Exception e) {
            errorString = e.toString();
        } finally {
            try {
                error.put("$rc", errorString);
            } catch (JSONException e) {
            }
        }
        return error;


        }

    public List<Product> getAllProduct(){
        List<Product> productList = new ArrayList<Product>();
        String sql = "select id,name,description,lo.read() from product";
        return productList;
    }

    }

