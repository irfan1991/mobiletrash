package andara.heatcliff.yuclean.Holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import andara.heatcliff.yuclean.R;

/**
 * Created by Heatcliff on 11/08/2016.
 */
public class HolderKegiatan extends RecyclerView.ViewHolder {

    public CardView cardview_kegiatan;
    public TextView txtJudul, txtDeskripsi, txtTanggal;
    public ImageView imgKegiatan;

    public HolderKegiatan(View itemView) {
        super(itemView);
        cardview_kegiatan = (CardView) itemView.findViewById(R.id.cardview_kegiatan);
        txtJudul = (TextView) itemView.findViewById(R.id.txtJudul);
        txtDeskripsi = (TextView) itemView.findViewById(R.id.txtDeskripsi);
        txtTanggal = (TextView) itemView.findViewById(R.id.txtTanggal);
        imgKegiatan = (ImageView) itemView.findViewById(R.id.imgKegiatan);
    }
}
