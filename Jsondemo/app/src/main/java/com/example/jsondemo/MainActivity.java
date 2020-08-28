package com.example.jsondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView resulttextView;

    public class downloadtask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try
            {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection)url.openConnection();
                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data!=-1)
                {
                    char currentchar = (char)data;
                    result+=currentchar;
                    data = reader.read();
                }

                return result;

            }
            catch (Exception e)
            {
                e.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run()
                    {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(),"Could not find weather :(",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                return null;
            }
        }

        @Override
        protected void onPostExecute(String resultstring) {
            super.onPostExecute(resultstring);

            try
            {
                JSONObject jsonObject = new JSONObject(resultstring);

                String weatherinfo = jsonObject.getString("weather");

                //Log.i("weather content", "onPostExecute: "+weatherinfo);

                String message = "";

                JSONArray arr = new JSONArray(weatherinfo);

                for(int i=0;i<arr.length();i++)
                {
                    JSONObject jsonPart = arr.getJSONObject(i);

                    String main = jsonPart.getString("main");
                    String desc = jsonPart.getString("description");

                    if(!main.equals("") && !desc.equals(""))
                    {
                        message = main+" : "+desc;
                    }



//                    Log.i("main", jsonPart.getString("main"));
//                    Log.i("description", jsonPart.getString("description"));
                }

                if(!message.equals(""))
                    resulttextView.setText(message);
               
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    public void run() {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(),"Could not find weather :(",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }

        }
    }

    public void getWeather(View view) {

        downloadtask task = new downloadtask();
        task.execute("https://openweathermap.org/data/2.5/weather?q="+ editText.getText().toString() +"&appid=439d4b804bc8187953eb36d2a8c26a02");

        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(editText.getWindowToken(),0);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextCityName);

        resulttextView = findViewById(R.id.resulttext);
    }
}