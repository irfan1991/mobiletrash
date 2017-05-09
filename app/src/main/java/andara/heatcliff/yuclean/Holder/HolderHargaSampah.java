package andara.heatcliff.yuclean.Holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import andara.heatcliff.yuclean.R;

/**
 * Created by Heatcliff on 25/08/2016.
 */
public class HolderHargaSampah extends RecyclerView.ViewHolder {

    public TextView txtNamaSampah, txtHargaSampah;
    public CardView cardview_hargaSampah;

    public HolderHargaSampah(View itemView) {
        super(itemView);
        txtNamaSampah = (TextView) itemView.findViewById(R.id.txtNamaSampah);
        txtHargaSampah = (TextView) itemView.findViewById(R.id.txtHargaSampah);
        cardview_hargaSampah = (CardView) itemView.findViewById(R.id.cardview_hargaSampah);
    }
}
