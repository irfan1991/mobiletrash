package andara.heatcliff.yuclean.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import andara.heatcliff.yuclean.MainActivity;
import andara.heatcliff.yuclean.R;
import andara.heatcliff.yuclean.RegistrasiWebViewActivity;
import andara.heatcliff.yuclean.SQLiteHandler;


public class LoginActivity extends AppCompatActivity{

    private Button btnLogin;
    private EditText txtNoHp, txtPassword;
    private TextView txtRegistrasi;
    SQLiteHandler sqLiteHandler;

    public static final String url = "https://trashpedia.net/api/auth/login";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtNoHp = (EditText) findViewById(R.id.txtNoHp);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtRegistrasi = (TextView) findViewById(R.id.txtRegistrasi);

        txtRegistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegistrasi = new Intent(LoginActivity.this, RegistrasiWebViewActivity.class);
                startActivity(intentRegistrasi);
            }
        });
        sqLiteHandler = new SQLiteHandler(this);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadData();
            }
        });
    }

    private void LoadData() {

        final String username = txtNoHp.getText().toString().trim();
        final String password = txtPassword.getText().toString().trim();
        //static String json = "";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Responnya : ", response);

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONObject data = jsonResponse.getJSONObject("data");
                            final String id = data.getString("id");
                            final String name = data.getString("name");
                            final String username = data.getString("username");
                            final String email = data.getString("email");
                            final String alamat = data.getString("alamat");
                            final String saldo = data.getString("saldo");
                            final String banksampah = data.getString("banksampah");
                            final String rt = data.getString("rt");
                            final String rw = data.getString("rw");
                            final String kelurahan = data.getString("kelurahan");
                            final String image = data.getString("image");
                            final String api_token = data.getString("api_token");
                            final String registered = data.getString("registered");

                            JSONObject meta = jsonResponse.getJSONObject("meta");
                            final String token = meta.getString("token");

                            Log.d("sukses", response);

                            sqLiteHandler.addDatabase(id, name, alamat, password, username, email,
                                    saldo, banksampah, kelurahan, rt, rw, image, api_token);

                            HashMap<String, String> user = sqLiteHandler.getUserDetails();
                            Toast.makeText(LoginActivity.this, "LOGIN SUKSES", Toast.LENGTH_LONG).show();
                            Log.d("profilnya database", user.toString());
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "NO.TEL/PASSWORD SALAH", Toast.LENGTH_LONG).show();
                            Log.d("gagal", response);
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Errornya : ", error.toString());
                        Toast.makeText(LoginActivity.this, "PERMASALAHAN KONEKSI, SILAHKAN COBA LAGI", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME, username);
            params.put(KEY_PASSWORD, password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}