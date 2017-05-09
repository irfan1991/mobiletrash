package andara.heatcliff.yuclean;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Heatcliff on 11/08/2016.
 */
public class RegistrasiActivity extends AppCompatActivity {

    Button btnNext;
    EditText txtFullname, txtPassword, txtEmail, txtNoHp;
//    SQLiteHandler sqLiteHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnNext = (Button) findViewById(R.id.btnNext);

        txtFullname = (EditText) findViewById(R.id.txtFullname);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtNoHp = (EditText) findViewById(R.id.txtNoHp);

//        sqLiteHandler = new SQLiteHandler(this);

//        btnRegistrasi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sqLiteHandler.addDatabase("nasabah",
//                        txtFullname.getText().toString(),txtPassword.getText().toString(),
//                        txtNoHp.getText().toString(),
//                        txtEmail.getText().toString(),
//                        "500");
//                Toast.makeText(getApplicationContext(), "Registrasi Berhasil, Silahkan login.", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(RegistrasiActivity.this, LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

     /*   btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrasiActivity.this, RegistrasiActivityCount.class);
                intent.putExtra("nama", txtFullname.getText().toString());
                intent.putExtra("password", txtPassword.getText().toString());
                intent.putExtra("nohp", txtNoHp.getText().toString());
                startActivity(intent);
                finish();
            }
        });*/

    }
}
