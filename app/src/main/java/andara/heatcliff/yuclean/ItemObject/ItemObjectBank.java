package andara.heatcliff.yuclean.ItemObject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Isfahani on 10-Agu-16.
 */
public class ItemObjectBank {
    @SerializedName("yuclean")
    public List<Children> yuclean;

    public class Children {
        @SerializedName("nama_bank")
        public String nama_bank;

        @SerializedName("lokasi")
        public String lokasi;

        @SerializedName("lokasi_gps")
        public String lokasi_gps;

        @SerializedName("deskripsi")
        public String deskripsi;

        @SerializedName("foto")
        public String foto;
    }
}
