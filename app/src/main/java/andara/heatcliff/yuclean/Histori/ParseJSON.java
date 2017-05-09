package andara.heatcliff.yuclean.Histori;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import andara.heatcliff.yuclean.SQLiteHandler;

/**
 * Created by Asus on 27/04/2017.
 */

public class ParseJSON {
    //private String[] ids;
    public static String[] jenis_transaksis;
    public static String[] saldos;
    public static String[] tanggals;
    public static String[] operators;

    public static final String JSON_ARRAY = "data";
    //public static final String KEY_ID = "id";
    public static final String KEY_JENIS = "jenis_transaksi";
    public static final String KEY_SALDO = "saldo";
    public static final String KEY_TANGGAL = "tanggal";
    public static final String KEY_OPERATOR = "operator";

    private JSONArray users = null;

    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            //ids = new String[users.length()];
            jenis_transaksis = new String[users.length()];
            saldos = new String[users.length()];
            tanggals = new String[users.length()];
            operators = new String[users.length()];


            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                //ids[i] = jo.getString(KEY_ID);
                jenis_transaksis[i] = jo.getString(KEY_JENIS);
                saldos[i] = jo.getString(KEY_SALDO);
                tanggals[i] = jo.getString(KEY_TANGGAL);
                operators[i] = jo.getString(KEY_OPERATOR);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}