package andara.heatcliff.yuclean.ItemObject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Heatcliff on 11/08/2016.
 */
public class ItemObjectKegiatan {
    @SerializedName("data")
    public List<Children>data;

    public class Children{
        @SerializedName("id")
        public String id;

        @SerializedName("judul")
        public String judul;

        @SerializedName("konten")
        public String konten;

        @SerializedName("tanggal")
        public String tanggal;

        @SerializedName("image")
        public String image;

        @SerializedName("registered")
        public String registered;
    }
}
