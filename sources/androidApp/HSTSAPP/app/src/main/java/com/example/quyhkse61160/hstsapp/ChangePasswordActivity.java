package com.example.quyhkse61160.hstsapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quyhkse61160.hstsapp.Common.Constant;
import com.example.quyhkse61160.hstsapp.Common.HSTSUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class ChangePasswordActivity extends ActionBarActivity {
    EditText current, newPassword, confirm;
    Button cancel,update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_to_doctor);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3ea000")));

        current = (EditText) findViewById(R.id.txt_change_password_current);
        newPassword = (EditText) findViewById(R.id.txt_change_password_new);
        confirm = (EditText) findViewById(R.id.txt_change_password_new_confirm);
        cancel = (Button) findViewById(R.id.btn_change_password_cancel);
        update = (Button) findViewById(R.id.btn_change_password_update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_current = current.getText().toString();
                String txt_new = newPassword.getText().toString();
                String txt_confirm = confirm.getText().toString();
                if(txt_new.equals(txt_confirm)){
                    new SendPasswordAsyncTask().execute(txt_current,txt_new);
                } else {
                    Toast.makeText(getApplicationContext(),R.string.change_password_error_password,Toast.LENGTH_LONG).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notify_to_doctor, menu);
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

    private class SendPasswordAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            String stringURL = Constant.hostURL + Constant.changePassword;
            Log.d("QUYYYY1111", "Login url: " + stringURL);
            Log.d("QUYYYY1111", "Login param: " + Constant.username + " - " + strings[0] + " - " + strings[1]);

            try {
                URL url = new URL(stringURL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(100000);
                urlConnection.setConnectTimeout(30000);
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", Constant.username));
                params.add(new BasicNameValuePair("oldPassword", strings[0]));
                params.add(new BasicNameValuePair("newPassword", strings[1]));

                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(HSTSUtils.getQuery(params));
                writer.flush();
                writer.close();
                os.close();

                urlConnection.connect();

                InputStream inStream = urlConnection.getInputStream();
                BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));
                String temp, response = "";
                while ((temp = bReader.readLine()) != null) {
                    response += temp;
                }
                Log.d("Khuong Dap Trai", "Response: -- " + response);
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if(result.equals("200")){
                Toast.makeText(ChangePasswordActivity.this,R.string.change_password_success_password,Toast.LENGTH_LONG).show();
            }
        }
    }

}
