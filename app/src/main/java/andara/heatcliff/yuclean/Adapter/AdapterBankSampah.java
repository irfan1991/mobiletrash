package andara.heatcliff.yuclean.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import andara.heatcliff.yuclean.DetailBankActivity;
import andara.heatcliff.yuclean.Holder.HolderBankSampah;
import andara.heatcliff.yuclean.ItemObject.ItemObjectBank;
import andara.heatcliff.yuclean.R;

/**
 * Created by Irfan P
 */
public class AdapterBankSampah extends RecyclerView.Adapter<HolderBankSampah> {

    public Context context;
    public List<ItemObjectBank.Children> objectBankList;

    public AdapterBankSampah(Context context, List<ItemObjectBank.Children> objectBankList) {
        this.context = context;
        this.objectBankList = objectBankList;
    }

    @Override
    public HolderBankSampah onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_banksampah, null);
        HolderBankSampah holderBankSampah = new HolderBankSampah(view);
        return holderBankSampah;
    }

    @Override
    public void onBindViewHolder(HolderBankSampah holder, final int position) {
        holder.txtNamaBank.setText(objectBankList.get(position).nama_bank);
        holder.txtLokasi.setText(objectBankList.get(position).lokasi);

        Glide.with(context).load(objectBankList.get(position).foto)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.logo_yuclean_bgwhite)
                .into(holder.img_result);

        holder.cardview_banksampah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailBankActivity.class);
                intent.putExtra("nama bank", objectBankList.get(position).nama_bank);
                intent.putExtra("lokasi", objectBankList.get(position).lokasi);
                intent.putExtra("deskripsi", objectBankList.get(position).deskripsi);
                intent.putExtra("gps", objectBankList.get(position).lokasi_gps);
                intent.putExtra("foto", objectBankList.get(position).foto);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return objectBankList.size();
    }
}
