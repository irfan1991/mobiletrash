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

import andara.heatcliff.yuclean.Holder.HolderKegiatan;
import andara.heatcliff.yuclean.ItemObject.ItemObjectKegiatan;
import andara.heatcliff.yuclean.R;

/**
 * Created by Heatcliff on 11/08/2016.
 */
public class AdapterKegiatan extends RecyclerView.Adapter<HolderKegiatan> {

    public Context context;
    public List<ItemObjectKegiatan.Children> objectKegiatanList;

    public AdapterKegiatan(Context context, List<ItemObjectKegiatan.Children> objectKegiatanList){
        this.context = context;
        this.objectKegiatanList = objectKegiatanList;
    }

    @Override
    public HolderKegiatan onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_kegiatan, null);
        HolderKegiatan holderKegiatan = new HolderKegiatan(view);
        return holderKegiatan;
    }

    @Override
    public void onBindViewHolder(HolderKegiatan holder, final int position) {
        holder.txtJudul.setText(objectKegiatanList.get(position).judul);
        holder.txtTanggal.setText(objectKegiatanList.get(position).tanggal);
        holder.txtDeskripsi.setText(objectKegiatanList.get(position).konten);

        Glide.with(context).load("https://trashpedia.net/public/images/event/" + objectKegiatanList.get(position).image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.logo_yuclean_bgwhite)
                .into(holder.imgKegiatan);

        holder.cardview_kegiatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserInternet = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(Uri.parse("https://trashpedia.net/event/" + objectKegiatanList.get(position).id) + "/show"));
                view.getContext().startActivity(browserInternet);
            }
        });
    }

    @Override
    public int getItemCount() {
        return objectKegiatanList.size();
    }
}
