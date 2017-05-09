package andara.heatcliff.yuclean.Menu;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import andara.heatcliff.yuclean.R;

public class AboutFragment extends Fragment {

    TextView url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);

        url = (TextView) rootView.findViewById(R.id.url);

        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://trashpedia.net"));
                startActivity(myIntent);
            }
        });

        return rootView;
    }
}
