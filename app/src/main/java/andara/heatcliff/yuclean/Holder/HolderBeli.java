package andara.heatcliff.yuclean.Holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import andara.heatcliff.yuclean.R;

/**
 * Created by irfan on 10/17/2016.
 */
public class HolderBeli extends RecyclerView.ViewHolder {

    public TextView txtNamaBarang, txtHargaBeliBarang;
    public ImageView img_barang;
    public CardView cardview_beli;

    public HolderBeli(View itemView) {
        super(itemView);
        txtNamaBarang = (TextView) itemView.findViewById(R.id.txtNamaBarang);
        txtHargaBeliBarang = (TextView) itemView.findViewById(R.id.txtHargaBeliBarang);
        img_barang = (ImageView) itemView.findViewById(R.id.img_barang);
        cardview_beli = (CardView) itemView.findViewById(R.id.cardview_beli);
    }
}
