package andara.heatcliff.yuclean;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Locale;

import andara.heatcliff.yuclean.R;

/**
 * Created by Isfahani on 10-Agu-16.
 */
public class DetailBankActivity extends AppCompatActivity {

    TextView txtNamaBank, txtLokasi, txtDeskripsi;
    Button btnPetunjukArah, btnJoin;
    ImageView imgFotoBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_banksampah_detail);
        txtNamaBank = (TextView) findViewById(R.id.txtNamaBank);
        txtLokasi = (TextView) findViewById(R.id.txtLokasi);
        txtDeskripsi = (TextView) findViewById(R.id.txtDeskripsi);
        btnPetunjukArah = (Button) findViewById(R.id.btnPetunjukArah);
        btnJoin = (Button) findViewById(R.id.btnJoin);
        imgFotoBank = (ImageView) findViewById(R.id.imgFotoBank);

        txtNamaBank.setText(getIntent().getStringExtra("nama bank"));
        txtLokasi.setText(getIntent().getStringExtra("lokasi"));
        txtDeskripsi.setText(getIntent().getStringExtra("deskripsi"));

        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("foto"))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.logo_yuclean_bgwhite)
                .into(imgFotoBank);

        btnPetunjukArah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] parts = getIntent().getStringExtra("gps").split(",");
//                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Float.valueOf(parts[0]), Float.valueOf(parts[1]));
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", Float.valueOf(parts[0]), Float.valueOf(parts[1]), "Where the party is at");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                view.getContext().startActivity(intent);
            }
        });

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Anda menjadi nasabah dari " + getIntent().getStringExtra("nama bank"), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
