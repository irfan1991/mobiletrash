package andara.heatcliff.yuclean;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.HashMap;

import andara.heatcliff.yuclean.Cari.CariActivity;
import andara.heatcliff.yuclean.Fragment.JualBeliFragment;
import andara.heatcliff.yuclean.Histori.HistoriActivity;
import andara.heatcliff.yuclean.Menu.AboutFragment;
import andara.heatcliff.yuclean.Menu.UserProfilFragment;
import andara.heatcliff.yuclean.Menu.HargaSampahFragment;
import andara.heatcliff.yuclean.Login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    TextView txtFullname, txtSaldo;
    SQLiteHandler sqLiteHandler;
    Context context;
    ImageView imgProfileUser;

    String imgUrl = "https://trashpedia.net/images/user/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);

        View view = mNavigationView.getHeaderView(0);
        txtFullname = (TextView) view.findViewById(R.id.txtFullname);
        txtSaldo = (TextView) view.findViewById(R.id.txtSaldo);
        imgProfileUser = (ImageView) view.findViewById(R.id.imgProfileUser);

        sqLiteHandler = new SQLiteHandler(MainActivity.this);

        final HashMap<String, String> user = sqLiteHandler.getUserDetails();

        txtFullname.setText(user.get("nama"));


        if (!user.get("saldo").equalsIgnoreCase("null") ) {
            txtSaldo.setText("Rp. " +user.get("saldo") );
        } else {
            txtSaldo.setText("Rp. 0 " );
           
        }

        Glide.with(this).load(imgUrl + user.get("foto"))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_profile_bgputih)
                .into(imgProfileUser);


        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                if (menuItem.getItemId() == R.id.header_menu) {
                    Intent profile = new Intent (MainActivity.this, UserProfilFragment.class);
                    startActivity(profile);
                }

                if (menuItem.getItemId() == R.id.nav_beranda) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
                }

                if (menuItem.getItemId() == R.id.nav_form) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new HargaSampahFragment()).commit();
                }

                if (menuItem.getItemId() == R.id.nav_cari) {
                    startActivity(new Intent(MainActivity.this,CariActivity.class));
                }

                if (menuItem.getItemId() == R.id.nav_histori) {
                    startActivity(new Intent(MainActivity.this, HistoriActivity.class));
                }

                if (menuItem.getItemId() == R.id.nav_tarik) {
                    startActivity(new Intent(MainActivity.this,TarikDanaActivity.class));
                }

                if (menuItem.getItemId() == R.id.nav_bagikan) {
                    Toast.makeText(getApplicationContext(),"Bagikan", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plan");
//                    i.setType("image/png");
                    i.putExtra(Intent.EXTRA_SUBJECT, "Trashpedia");
                    i.putExtra(Intent.EXTRA_TEXT, "Kunjungi Halaman Kami di \n https://trashpedia.net/");
//                    i.putExtra(Intent.EXTRA_STREAM, Uri.parse("android.resource://entertaiment.emaka.planetku/drawable/ic_icon_planetku"));
//                    i.putExtra(Intent.EXTRA_STREAM, Uri.parse(getIntent().getStringExtra("ikon planet")));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    Intent share = Intent.createChooser(i, "Bagikan Apliksai Trashpedia");
                    share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(share);
                }

                if (menuItem.getItemId() == R.id.nav_about) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new AboutFragment()).commit();
                }

                if (menuItem.getItemId() == R.id.nav_call) {

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                    // Setting Dialog Title
                    alertDialog.setTitle("Apakah ingin menghubungi kami?");
                    // Setting Dialog Message
                    alertDialog.setMessage("Trashpedia Staff akan menjemput sampah anda");
                    // Setting Icon to Dialog

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


                if (menuItem.getItemId() == R.id.nav_logout) {
                    sqLiteHandler.deteleTable();

                    Log.v("Profil : ", user.toString());

                    Intent logout = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(logout);
                    finish();
                }

                return false;
            }

        });

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

    }

    @Override
    public void onBackPressed() {
        JualBeliFragment fragment = (JualBeliFragment)
                getSupportFragmentManager().findFragmentById(R.id.webViewJualBeli);
        if (fragment.canGoBack()) {
            fragment.goBack();
        } else {
            super.onBackPressed();
        }
        }



}
