package andara.heatcliff.yuclean.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import andara.heatcliff.yuclean.Adapter.AdapterBankSampah;
import andara.heatcliff.yuclean.ItemObject.ItemObjectBank;
import andara.heatcliff.yuclean.R;

/**
 * Created by Isfahani on 10-Agu-16.
 */
public class BankSampahFragment extends Fragment {

    private RecyclerView lstbankSampah;
    private LinearLayoutManager layoutManager;
    private ItemObjectBank itemObjectBanks;
    private AdapterBankSampah adapterBankSampah;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_banksampah, container, false);
        lstbankSampah = (RecyclerView) rootView.findViewById(R.id.lstbankSampah);
        layoutManager = new LinearLayoutManager(getActivity());
        lstbankSampah.setLayoutManager(layoutManager);
        lstbankSampah.setHasFixedSize(true);
        setHasOptionsMenu(true);
        ListRefresh();
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuInflater menuInflater = inflater;
        menuInflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                ListRefresh();
                Toast.makeText(getContext(),"test", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ListRefresh() {
        LoadData("http://dev.daeng.id/yuclean/daftarbank.php");
    }

    private void LoadData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("hasilnya", response);
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();
                itemObjectBanks = mGson.fromJson(response, ItemObjectBank.class);
                adapterBankSampah = new AdapterBankSampah(getContext(), itemObjectBanks.yuclean);
                lstbankSampah.setAdapter(adapterBankSampah);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}
