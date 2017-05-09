package andara.heatcliff.yuclean.Menu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import andara.heatcliff.yuclean.Adapter.AdapterHargaSampah;
import andara.heatcliff.yuclean.ItemObject.ItemObjectHargaSampah;
import andara.heatcliff.yuclean.R;

/**
 * Created by Heatcliff on 25/08/2016.
 */
public class HargaSampahFragment extends Fragment {

    private RecyclerView lstHargaSampah;
    private LinearLayoutManager layoutManager;
    private ItemObjectHargaSampah itemObjectHargaSampah;
    private AdapterHargaSampah adapterHargaSampah;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_harga_sampah, container, false);
        lstHargaSampah = (RecyclerView) rootView.findViewById(R.id.lstHargaSampah);
        layoutManager = new LinearLayoutManager(getActivity());
        lstHargaSampah.setLayoutManager(layoutManager);
        lstHargaSampah.setHasFixedSize(true);
        ListRefresh();
        return rootView;
    }

    private void ListRefresh() {
        LoadData("https://trashpedia.net/api/sampah");
    }

    private void LoadData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("hasilnya", response);
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();
                itemObjectHargaSampah = mGson.fromJson(response, ItemObjectHargaSampah.class);
                adapterHargaSampah = new AdapterHargaSampah(getContext(), itemObjectHargaSampah.data);
                lstHargaSampah.setAdapter(adapterHargaSampah);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}