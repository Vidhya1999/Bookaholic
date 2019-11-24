package com.example.bookaholic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.VoiceInteractor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public class search extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final EditText text = findViewById(R.id.search);
        //after clicking search
        final TextView textView=(TextView)findViewById(R.id.textView2);
        ImageButton searchButton = findViewById(R.id.search_button2);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build();

        BookService books = retrofit.create(BookService.class);
        String URL ="https://openlibrary.org/api/books?bibkeys=ISBN:0451526538&format=JSON";

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest= new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("REST RESPONSE",response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("REST RESPONSE",error.toString());

                    }
                }
        );
        requestQueue.add(objectRequest);

       /* String url ="http://www.google.com";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
*/
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = String.valueOf(text.getText());
                textView.setVisibility(View.VISIBLE);

                textView.setText("Recommended books");
                Toast toast = Toast.makeText(,textView,Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }


    public interface BookService {
        @GET("/")
       Call<String> resultString();
    }
}
