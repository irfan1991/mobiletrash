package andara.heatcliff.yuclean.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import andara.heatcliff.yuclean.Holder.HolderBeli;
import andara.heatcliff.yuclean.ItemObject.ItemObjectBeli;
import andara.heatcliff.yuclean.R;

/**
 * Created by irfan on 10/17/2016.
 */
public class AdapterBeli extends RecyclerView.Adapter<HolderBeli> {

    List<ItemObjectBeli> itemObjectBeliList;
    Context context;

    public AdapterBeli (Context context, List<ItemObjectBeli> itemObjectBeliList) {
        this.context = context;
        this.itemObjectBeliList = itemObjectBeliList;
    }

    @Override
    public HolderBeli onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardiview_item_beli, null);
        return new HolderBeli(view);
    }

    @Override
    public void onBindViewHolder(HolderBeli holder, final int position) {
        holder.txtNamaBarang.setText(itemObjectBeliList.get(position).nama);
        holder.txtHargaBeliBarang.setText(itemObjectBeliList.get(position).harga);

        Glide.with(context).load(itemObjectBeliList.get(position).gambar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.icon_app)
                .into(holder.img_barang);

        holder.cardview_beli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserInternet = new Intent(Intent.ACTION_VIEW, Uri.parse(itemObjectBeliList.get(position).web));
                view.getContext().startActivity(browserInternet);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemObjectBeliList.size();
    }
}