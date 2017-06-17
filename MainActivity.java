package com.kingdom.raj.json;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ProgressDialog dialog;
    ArrayList<String> id1;
    ArrayList<String> name1;
    ArrayList<String> email1;
    ArrayList<String> address1;
    ArrayList<String> gender1;
    ListView mlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   mlist= (ListView) findViewById(R.id.mylist);
        new GetResponse().execute();
    }

    class GetResponse extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            dialog=new ProgressDialog(MainActivity.this);
            dialog.setTitle("Loding....");
            dialog.setMessage("Please Wait");
            dialog.setCancelable(false);
            dialog.show();
        }

        protected Void doInBackground(Void... params) {
String url="http://api.androidhive.info/contacts/";
            String response=HitURL.urlReader(url);

            try {
                JSONObject outer=new JSONObject();
                JSONArray array=outer.getJSONArray("contacts");
   for (int i=0;i<array.length();i++){
       JSONObject inner=array.getJSONObject(i);
       String id=inner.getString("id");
       id1.add(id);
       String name=inner.getString("name");
name1.add(name);
       String email=inner.getString("email");
       email1.add(email);
       String address=inner.getString("address");
       address1.add(address);
       String gender=inner.getString("gender");
gender1.add(gender);
       Log.e(">>>",id);
       Log.e(">>>",name);
       Log.e(">>>",email);
       Log.e(">>>",address);
       Log.e(">>>",gender);
   }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e(">>>",response);
            return null;
        }
    protected void onPostExecute(Void aVoid){
if (dialog.isShowing()){
    dialog.dismiss();
}
        ArrayAdapter adapter=new ArrayAdapter(MainActivity.this,R.layout.activity_main,name1);
        mlist.setAdapter(adapter);
    super.onPostExecute(aVoid);
    }
    }
}
