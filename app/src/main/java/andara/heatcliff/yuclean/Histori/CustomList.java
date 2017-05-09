package andara.heatcliff.yuclean.Histori;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import andara.heatcliff.yuclean.R;

/**
 * Created by Asus on 27/04/2017.
 */

public class CustomList extends ArrayAdapter<String> {

    //private String[] ids;
    private String[] jenis_transaksi;
    private String[] saldo;
    private String[] tanggal;
    private String[] operator;
    private Activity context;

    public CustomList(  Activity context,String[] jenis_transaksi, String[] saldo, String[] tanggal, String[] operator) {
        super(context,  R.layout.list_view_layout, jenis_transaksi);
        this.context = context;
        this.jenis_transaksi = jenis_transaksi;
        this.saldo = saldo;
        this.tanggal = tanggal;
        this.operator = operator;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_view_layout, null, true);
        TextView textViewJenisTransaksi = (TextView) listViewItem.findViewById(R.id.textViewJenisTransaksi);
        TextView textViewSaldo = (TextView) listViewItem.findViewById(R.id.textViewSaldo);
        TextView textViewTanggal = (TextView) listViewItem.findViewById(R.id.textViewWaktu);
        TextView textViewOperator = (TextView) listViewItem.findViewById(R.id.textViewOperator);

        textViewJenisTransaksi.setText(jenis_transaksi[position]);
        textViewSaldo.setText(saldo[position]);
        textViewTanggal.setText(tanggal[position]);
        textViewOperator.setText(operator[position]);

        return listViewItem;
    }
}
