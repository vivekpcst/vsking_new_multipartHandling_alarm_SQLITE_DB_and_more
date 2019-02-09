package dzo.com.barcodescanner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class Fest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fest);
//        showFest();
//        shofestList();
        addUser();
    }



    public void addUser(){
        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, UrlConstant.POST_AddNewUser, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                String resultResponse = new String(response.data);
                Log.e("resultResponse",resultResponse);
//[{"Status":true,"Message":"UserDetails Data!","SellerId":36,"Name":"gbvhd","Gender":"male","MobileNumber":"457857464","Occuption":"","Image":"NA","MaterialStatus":"dhu"},{"Status":true,"Message":"UserDetails Data!","SellerId":37,"Name":"gbvhd","Gender":"male","MobileNumber":"457857464","Occuption":"","Image":"NA","MaterialStatus":"dhu"}]
                try {
                    final JSONArray jsonArray = new JSONArray(resultResponse);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String Name=jsonObject.getString("Name");
                        String Status=jsonObject.getString("Status");
                        String MaterialStatus=jsonObject.getString("MaterialStatus");
                        String Message=jsonObject.getString("Message");
                        String Image=jsonObject.getString("Image");
                        String Gender=jsonObject.getString("Gender");
                        String SellerId=jsonObject.getString("SellerId");
                        String Occuption=jsonObject.getString("Occuption");
                        String MobileNumber=jsonObject.getString("MobileNumber");

//                        usrData.add(new User1(Name,MobileNumber,SellerId,Image));
                    }
//                    selleradapter = new NewUserAdapter(AddUserActivity.this, item_user, usrData);
//                    sellerlist.setAdapter(selleradapter);
//                    selleradapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("SellerId","94");
                params.put("MobileNumber","457857464");
                params.put("Name","gbvhd");
                params.put("Gender","male");
                params.put("Occupation","hgruye");
                params.put("MaritalStatus","dhu");

                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                Bitmap icon = BitmapFactory.decodeResource(getResources(),
                        R.drawable.image);
                byte [] bytes=getFileDataFromDrawable(icon);
                params.put("Image", new DataPart(System.currentTimeMillis() + ".jpg",bytes, "image/jpeg"));

                return params;
            }

            private byte[] getFileDataFromDrawable(Bitmap icon) {
                ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                icon.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                return byteArrayOutputStream.toByteArray();
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(multipartRequest);
    }





/*

    private void shofestList() {
        String calApi="https://www.googleapis.com/calendar/v3/calendars/en.indian%23holiday%40group.v.calendar.google.com/events?";//key=AIzaSyCEghyZQYGgYn5OEx-iCYV41goS80dY8hw";
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("key","AIzaSyCEghyZQYGgYn5OEx-iCYV41goS80dY8hw");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                calApi, jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(final JSONObject response) {
                        Log.e("vsking:>", response.toString());
                        try {
                            String Status = response.getString("Status");

                            if (Status.equals("false")) {

                                Toast.makeText(Fest.this, response.getString("Message"), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Fest.this, response.getString("Message"), Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {

                        }

                    }


                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("vsking:>", "Error: " + error.getMessage());

            }

        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
RequestQueue requestQueue=Volley.newRequestQueue(this);
requestQueue.add(jsonObjReq);

    }




    private void showFest() {
        String calApi="https://www.googleapis.com/calendar/v3/calendars/en.indian%23holiday%40group.v.calendar.google.com/events?";//key=AIzaSyCEghyZQYGgYn5OEx-iCYV41goS80dY8hw";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, calApi, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("vskingCalRes: ",""+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Fest.this, "Oops ! Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<String,String>();
                map.put("key","AIzaSyCEghyZQYGgYn5OEx-iCYV41goS80dY8hw");

                return map;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(Fest.this);
        requestQueue.add(stringRequest);
    }
*/
}
