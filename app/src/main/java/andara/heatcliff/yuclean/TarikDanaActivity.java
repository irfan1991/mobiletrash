package andara.heatcliff.yuclean;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Heatcliff on 13/09/2016.
 */
public class TarikDanaActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String REGISTER_URL = "https://trashpedia.net/api/tarikdana";
    SQLiteHandler sqLiteHandler;

    public static final String KEY_USERID = "user_id";
    public static final String KEY_BANK = "bank";
    public static final String KEY_JUMLAH = "jumlah";

    private EditText Mjumlah;
    private Button btnTransfer;
    private Spinner Mbank;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarikdana);

        Mbank = (Spinner) findViewById(R.id.sp_name);
        btnTransfer = (Button) findViewById(R.id.btn_masuk);
        //nama = (EditText) findViewById(R.id.nama);
        Mjumlah = (EditText) findViewById(R.id.jumlah);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tarik Dana");
        sqLiteHandler = new SQLiteHandler(getApplicationContext());
        btnTransfer.setOnClickListener(this);
        }

    private void Reg() {
        if ( Mjumlah.getText().toString().equals("") || Mbank.getSelectedItem().toString().equals("")) {
            Toast.makeText(getApplication(), "Isi data dengan lengkap", Toast.LENGTH_LONG).show();

        } else {

          //  sqLiteHandler = new SQLiteHandler(getApplicationContext());

            HashMap<String, String> user = sqLiteHandler.getUserDetails();

            final String userid = user.get("type").toString().trim();
            final String bank = Mbank.getSelectedItem().toString().trim();
            final String jumlah = Mjumlah.getText().toString().trim();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                    new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(TarikDanaActivity.this,"SALDO BERHASIL DIAMBIL",Toast.LENGTH_LONG).show();
                                    //response.toString(),Toast.LENGTH_LONG).show();
                            Log.d("Eror",response.toString());
                        }
                    },
                    new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                           Toast.makeText(TarikDanaActivity.this,"MAAF, SALDO ANDA TIDAK CUKUP",Toast.LENGTH_LONG).show();
                                   //error.toString(),Toast.LENGTH_LONG).show();
                            Log.d("Eror",error.toString());
                        }
                    }){
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<String, String>();
                    params.put(KEY_USERID,userid);
                    params.put(KEY_BANK,bank);
                    params.put(KEY_JUMLAH, jumlah);
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        }

    }

    @Override
    public void onClick(View view) {
        if(view == btnTransfer){
            Reg();
        }
    }


    }