package com.example.hp.mommy;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

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
import java.net.URLEncoder;


public class UserLoginTask extends AsyncTask<String, Void, String> {

    private final String mmobile="";
    private final String mPassword="";
    AlertDialog alertDialog;
    Context ctx;
    private String method;
    private ProgressDialog progressDialog;

    UserLoginTask(Context ctx) {
                this.ctx=ctx;

    }

    @Override
    protected String doInBackground(String... params) {
        // TODO: attempt authentication against a network service.
        String x = "";

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            return x;
        }
if(isConnectingToInternet())
{
            String msg = "";
            String reg_url = "http://10.0.2.2:82/mommy/user_register.php";
            String login_url = "http://10.0.2.2:82/mommy/login.php";
             method = params[0];

            if (method.equals("Register")) {
                String mobile = params[5];
                String password = params[2];
                String name = params[1];
                String age = params[3];
                String address = params[4];
                try {
                    URL url = new URL(reg_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(name, "UTF-8")+"&"+
                            URLEncoder.encode("mobile", "UTF-8")+ "=" + URLEncoder.encode(mobile, "UTF-8") + "&" +
                            URLEncoder.encode("address", "UTF-8")+"="+URLEncoder.encode(address, "UTF-8")+"&"+
                            URLEncoder.encode("age", "UTF-8") + "=" + URLEncoder.encode(age, "UTF-8") + "&" +
                            URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS,"iso-8859-1"));
                    String response = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                    }
                    bufferedReader.close();
                    IS.close();
                    if(response.equals("success  "))
                        x="true";
                    httpURLConnection.disconnect();
                   // x = "Registration Success...";

                    return x;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (method.equals("Login")) {
                String mobile = params[1];
                String password = params[2];
                try {
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("mobile", "UTF-8") + "=" + URLEncoder.encode(mobile, "UTF-8") + "&" +
                            URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String response = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                        if(response.equals("Login Successful"))
                        { x = "true";}
                        else if (response.equals("Please check details and try again"))
                        {x="false";}
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    //x = "true";
                    return x;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }} else {
                x = "Check your internet connection";
               // Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
            }

            return x;
        }


    @Override
    protected void onPostExecute(final String success) {

        if (success.equals("true")&& method.equals("Register") ) {
            progressDialog.dismiss();
                 Toast.makeText(ctx,"Registration Successful",Toast.LENGTH_LONG).show();
            Intent i1=new Intent(ctx,Home.class);
            ctx.startActivity(i1);

                /* return "true";*/

        }
        else if (success.equals("true")&& method.equals("Login") ) {
            progressDialog.dismiss();
            Toast.makeText(ctx,"Login Successful",Toast.LENGTH_LONG).show();
            Intent i1=new Intent(ctx,Home.class);
            ctx.startActivity(i1);
                /* return "true";*/
                    }
       else if(success.equals("Check your internet connection")){
            progressDialog.dismiss();
            Toast.makeText(ctx,"Check your internet connection",Toast.LENGTH_LONG).show();
            /*return "true";*/
        }
        else {
            progressDialog.dismiss();
            Toast.makeText(ctx, "Registration Not Successful", Toast.LENGTH_LONG).show();
           /* return "true";*/
        }
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(ctx, "",
                "Please Wait...");
        super.onPreExecute();
    }
        public boolean isConnectingToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {

                        return true;
                    }

        }
        return false;
    }
}

