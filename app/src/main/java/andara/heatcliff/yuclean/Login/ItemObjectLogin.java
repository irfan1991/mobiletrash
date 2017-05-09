package andara.heatcliff.yuclean.Login;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Heatcliff on 11/08/2016.
 */
public class ItemObjectLogin {
    @SerializedName("data")
    public List<Children> data;

    public class Children{
        @SerializedName("username")
        public String username;

        @SerializedName("password")
        public String password;

        @SerializedName("saldo")
        public String saldo;

        @SerializedName("fullname")
        public String fullname;

        @SerializedName("phonenumber")
        public String phonenumber;

        @SerializedName("email")
        public String email;
    }
}
