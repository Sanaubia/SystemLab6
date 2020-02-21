package com.example.systemprogramminglab6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RequestQueue pictureRequest;
    private List<Data> dataList = new ArrayList<>();
    Adapter adapter;
    RecyclerView recycler_view;
    Button load;
    EditText searchText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent introIntent = new Intent(this, Introactivity.class);
        startActivity(introIntent);
        load = (Button) findViewById(R.id.button);

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        pictureRequest = new RequestQueue(cache, network);
        pictureRequest.start();
        searchText = (EditText)findViewById(R.id.editText);

      //  pictureRequest = Volley.newRequestQueue(this);



        recycler_view = findViewById(R.id.recycler_view);
        adapter = new Adapter(dataList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
       // recycler_view.addItemDecoration(new RecyclerView.ItemDecoration(this, LinearLayoutManager.HORIZONTAL, 16));
        recycler_view.setAdapter(adapter);


        load.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String text = searchText.getText().toString();
                jsonParse(text);

            }
        });




    }


    private void jsonParse(String url){
        String search = url;
        String searchUrl = "https://api.flickr.com/services/feeds/photos_public.gne?nojsoncallback=?&format=json&tags=" + search;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, searchUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("items");

                            for (int i = 0; i < jsonArray.length(); i++){
                                Data data = new Data();
                                /* JSONObject urlAdress = jsonArray.getJSONObject(i); */
                                String url = jsonArray.getJSONObject(i).getJSONObject("media").getString("m");
                                //glidePicture(url);
                                data.setImage(url);
                                dataList.add(data);
                              // Log.d("URLIA", url);


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        request.setShouldCache(false);
        pictureRequest.add(request);

    }






}
