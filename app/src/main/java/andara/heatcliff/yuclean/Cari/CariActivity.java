package andara.heatcliff.yuclean.Cari;



import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import andara.heatcliff.yuclean.R;

public class CariActivity extends AppCompatActivity {
    // Log tag
    private static final String TAG = CariActivity.class.getSimpleName();

    // Movies json url
    private static final String url = "https://trashpedia.net/api/getlokasi";
    private ProgressDialog pDialog;
    private List<DataSet> dataSets = new ArrayList<DataSet>();
    private ListView listView;
    private AdapterCari adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari);

        listView = (ListView) findViewById(R.id.list);
        adapter = new AdapterCari(this, dataSets);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Cari Lokasi Bank Sampah");
        String result = "";
        JsonObjectRequest movieReq = new  JsonObjectRequest(url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                      Log.d("Log", response.toString());
                        hidePDialog();
                        try {
                            JSONArray taxiJsonArray = response.getJSONArray("data");

                        for (int i = 0; i < taxiJsonArray.length(); i++) {
                            try {

                                JSONObject obj = taxiJsonArray.getJSONObject(i);
                                Log.d("Log", response.toString());
                                DataSet m = new DataSet();
                                m.setId(obj.getString("id"));
                                m.setNama(obj.getString("nama"));
                                m.setDeskripsi(obj.getString("deskripsi"));
                                m.setAlamat(obj.getString("alamat"));
                                m.setDistrict(obj.getString("district"));
                                m.setCity(obj.getString("city"));
                                m.setImage(obj.getString("image"));
                                m.setLat(obj.getString("lat"));
                                m.setLong(obj.getString("lng"));
                                m.setRegistered(obj.getString("registered"));

                                // adding movie to movies array
                                dataSets.add(m);
                                Log.d("Log", response.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplication(),  error.getMessage(), Toast.LENGTH_LONG).show();
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        Controller.getInstance().addToRequestQueue(movieReq);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

}