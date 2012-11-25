package com.example;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    ArrayList<String> items = new ArrayList<String>();
    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data


        setContentView(R.layout.main);
        NetworkActivity na = new NetworkActivity();
        try {
        String h = na.downloadUrl("http://0.0.0.0:8000/log.xml");} catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        String[] s = {"A", "B", "C", "D", "E"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, s);
        ListView lv = (ListView) findViewById(R.id.listview);
        final Button btn = (Button) findViewById(R.id.btn);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(mMessageClickedHandler);
        }

;

    }

    private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("Position:").append(position);
            buffer.append("\nView:").append(v);
            buffer.append("\nParent:").append(parent);
            buffer.append("\nId:").append(id);
            CheckedTextView view = (CheckedTextView) v;
            buffer.append(view.isChecked());
            view.toggle();
            buffer.append(view.isChecked());
            buffer.append(view.getText());

            if(view.isChecked()) {
                items.add((String) view.getText());
                Log.d(TAG, (String) view.getText());
            }
            else {
                items.remove((String) view.getText());
            }


            Toast.makeText(MainActivity.this, items.toString() , Toast.LENGTH_SHORT).show();



        }
    };


}
