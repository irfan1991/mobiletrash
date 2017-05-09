package andara.heatcliff.yuclean.Fragment;

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

import andara.heatcliff.yuclean.Adapter.AdapterKegiatan;
import andara.heatcliff.yuclean.ItemObject.ItemObjectKegiatan;
import andara.heatcliff.yuclean.R;


/**
 * Created by Isfahani on 10-Agu-16.
 */
public class KegiatanFragment extends Fragment {

    private RecyclerView lstKegiatan;
    private LinearLayoutManager layoutManager;
    private ItemObjectKegiatan itemObjectKegiatan;
    private AdapterKegiatan adapterKegiatan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_kegiatan, container, false);
        lstKegiatan = (RecyclerView) rootView.findViewById(R.id.lstKegiatan);
        layoutManager = new LinearLayoutManager(getActivity());
        lstKegiatan.setLayoutManager(layoutManager);
        lstKegiatan.setHasFixedSize(true);
        ListRefresh();
        return rootView;
    }

    private void ListRefresh() {
        LoadData("https://trashpedia.net/api/event");
    }

    private void LoadData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("hasilnya", response);
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();
                itemObjectKegiatan = mGson.fromJson(response, ItemObjectKegiatan.class);
                adapterKegiatan = new AdapterKegiatan(getContext(), itemObjectKegiatan.data);
                lstKegiatan.setAdapter(adapterKegiatan);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}
