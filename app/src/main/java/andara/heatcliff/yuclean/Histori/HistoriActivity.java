package andara.heatcliff.yuclean.Histori;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import andara.heatcliff.yuclean.R;
import andara.heatcliff.yuclean.SQLiteHandler;

public class HistoriActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String JSON_URL = "https://trashpedia.net/api/histori";
    public static final String KEY_USERID = "nasabah_id";
    private Button buttonGet;
    SQLiteHandler sqLiteHandler;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histori);

        buttonGet = (Button) findViewById(R.id.buttonGet);
        buttonGet.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Data Transaksi");
        sqLiteHandler = new SQLiteHandler(getApplicationContext());
    }

    private void sendRequest(){
        HashMap<String, String> user = sqLiteHandler.getUserDetails();

        final String userid = user.get("type").toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HistoriActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
        @Override
        protected Map<String,String> getParams(){
            Map<String,String> params = new HashMap<String, String>();
            params.put(KEY_USERID,userid);

            return params;
        }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        CustomList cl = new CustomList(this, ParseJSON.jenis_transaksis,ParseJSON.saldos,ParseJSON.tanggals,ParseJSON.operators);
        listView.setAdapter(cl);
    }

    @Override
    public void onClick(View v) {
        sendRequest();
    }
}