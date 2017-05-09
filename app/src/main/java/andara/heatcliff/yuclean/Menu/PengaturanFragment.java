package andara.heatcliff.yuclean.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import andara.heatcliff.yuclean.R;

/**
 * Created by Heatcliff on 12/08/2016.
 */
public class PengaturanFragment extends Fragment {

    CardView ubahProfil, aboutMe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pengaturan, container, false);

        ubahProfil = (CardView) rootView.findViewById(R.id.ubahProfil);
        aboutMe = (CardView) rootView.findViewById(R.id.aboutMe);

        ubahProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UserProfilFragment.class);
                startActivity(intent);
            }
        });

        aboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AboutFragment.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
