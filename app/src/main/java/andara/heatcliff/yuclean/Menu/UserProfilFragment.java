package andara.heatcliff.yuclean.Menu;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.HashMap;

import andara.heatcliff.yuclean.R;
import andara.heatcliff.yuclean.SQLiteHandler;

public class UserProfilFragment extends Fragment {

    TextView txtName, txtAlamat, txtNoHp, txtSaldo, txtNamaBank, txtKelurahan, txtRt, txtRw, txtEmail;
    ImageView imgProfileUser;
    SQLiteHandler sqLiteHandler;
    String imgUrl = "https://trashpedia.net/images/user/";
    Context context;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_profil, container, false);

        txtName = (TextView) rootView.findViewById(R.id.txtName);
        txtAlamat = (TextView) rootView.findViewById(R.id.txtAlamat);
        txtNoHp = (TextView) rootView.findViewById(R.id.txtNoHp);
        txtSaldo = (TextView) rootView.findViewById(R.id.txtSaldo);
        txtNamaBank = (TextView) rootView.findViewById(R.id.txtNamaBank);
        txtKelurahan = (TextView) rootView.findViewById(R.id.txtKelurahan);
        txtRt = (TextView) rootView.findViewById(R.id.txtRt);
        txtRw = (TextView) rootView.findViewById(R.id.txtRw);
        txtEmail = (TextView) rootView.findViewById(R.id.txtEmail);
        imgProfileUser = (ImageView) rootView.findViewById(R.id.imgProfileUser);


        sqLiteHandler = new SQLiteHandler(getContext());

        HashMap<String, String> user = sqLiteHandler.getUserDetails();

        txtName.setText(user.get("nama"));
        txtAlamat.setText(user.get("alamat"));
        txtNoHp.setText(user.get("no_hp"));
        txtNamaBank.setText(user.get("bank"));
        txtKelurahan.setText(user.get("kelurahan"));
        txtRt.setText(user.get("rt"));
        txtRw.setText(user.get("rw"));
        txtEmail.setText(user.get("email"));

        if (!user.get("saldo").equalsIgnoreCase("null") ) {
            txtSaldo.setText("Rp. " +user.get("saldo") );
        } else {
            txtSaldo.setText("Rp. 0 " );
           // txtSaldo.setText("Rp. "  );
        }

        Glide.with(this).load(imgUrl + user.get("foto"))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_profile)
                .into(imgProfileUser);

        return rootView;
    }
}
