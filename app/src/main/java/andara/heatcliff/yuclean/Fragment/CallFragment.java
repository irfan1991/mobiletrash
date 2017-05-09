package andara.heatcliff.yuclean.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import andara.heatcliff.yuclean.R;

public class CallFragment extends Fragment {
    private View parentView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.contact, container, false);
        parentView = inflater.inflate(R.layout.fragment_call, container, false);
        setUpViews();
        return parentView;
    }

    private void setUpViews() {
        parentView.findViewById(R.id.contact_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                // Creating alert Dialog with two Buttons
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                // Setting Dialog Title
                alertDialog.setTitle("Apakah ingin menghubungi kami?");
                // Setting Dialog Message
                alertDialog.setMessage("Trashpedia Staff akan menjemput sampah anda");
                // Setting Icon to Dialog
                //alertDialog.setIcon(R.drawable.warning);
                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // Write your code here to execute after dialog
                                dialog.cancel();
                            }
                        });
                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // Write your code here to execute after dialog
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:" +"081806954774"));
                                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(callIntent);

                            }
                        });

                // Showing Alert Message
                alertDialog.show();
            }
        });
    }
}
