package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private final LinkedList<String> mWordList = new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream is=getApplicationContext().getResources().openRawResource(R.raw.note);
        BufferedReader br=new BufferedReader(new InputStreamReader(is));
        StringBuilder sb=new StringBuilder();
        String s=null;
        while (true){
            try {
                if(!((s=br.readLine())!=null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb.append(s);
            sb.append("\n");
        }
        final String sJson = sb.toString();

        try {
            JSONObject jsonRoot = new JSONObject(sJson);
            JSONArray jsonArray=jsonRoot.getJSONArray("ds");
            int len=jsonArray.length();
            for(int i=0;i<len;i++){
                mWordList.addLast(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Button bt=findViewById(R.id.thanh_toan);
        FloatingActionButton fab = findViewById(R.id.fab);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //.setAction("Action", null).show();

                Toast.makeText(getApplicationContext(),mWordList.getFirst(),Toast.LENGTH_LONG).show();;
            }
        });
    }
}