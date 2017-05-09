package andara.heatcliff.yuclean.Cari;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import andara.heatcliff.yuclean.R;

public class AdapterCari extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataSet> dataSets;
    ImageLoader imageLoader = Controller.getInstance().getImageLoader();

    public AdapterCari(Activity activity, List<DataSet> dataSets) {
        this.activity = activity;
        this.dataSets = dataSets;
    }

    @Override
    public int getCount() {
        return dataSets.size();
    }

    @Override
    public Object getItem(int location) {
        return dataSets.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = Controller.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView namabanksampah = (TextView) convertView.findViewById(R.id.namabanksampah);
        TextView alamat = (TextView) convertView.findViewById(R.id.alamat);
        TextView city = (TextView) convertView.findViewById(R.id.city);
        TextView district = (TextView) convertView.findViewById(R.id.district);

        // getting movie data for the row
        DataSet m = dataSets.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getImage(), imageLoader);

        // title
        namabanksampah.setText(m.getNama());

        // rating
        alamat.setText(m.getAlamat());

        // genre
        city.setText(m.getCity());

        // release year
        district.setText(m.getDistrict());

        return convertView;
    }

}