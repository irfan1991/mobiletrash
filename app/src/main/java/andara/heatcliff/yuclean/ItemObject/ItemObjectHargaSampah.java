package andara.heatcliff.yuclean.ItemObject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by irfan on 10/29/2016.
 */
public class ItemObjectHargaSampah {
    @SerializedName("data")
    public List<Children> data;

    public class Children{
        @SerializedName("id")
        public String id;

        @SerializedName("satuan")
        public String satuan;

        @SerializedName("potongan")
        public String potongan;

        @SerializedName("harga")
        public String harga;

        @SerializedName("nama")
        public String nama;

    }
}
