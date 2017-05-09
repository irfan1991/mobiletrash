package andara.heatcliff.yuclean;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "dbnasabah";
    private static final String TABLE_USER = "tbl_account";

    public static final String KEY_ID = "_id";
    public static final String KEY_TYPE = "tipe";
    public static final String KEY_NAME = "nama";
    public static final String KEY_ALAMAT = "alamat";
    public static final String KEY_PASS = "pass";
    public static final String KEY_NO_HP = "no_hp";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_SALDO = "saldo";
    public static final String KEY_BANK = "bank";
    public static final String KEY_KELURAHAN = "kelurahan";
    public static final String KEY_RW = "rw";
    public static final String KEY_RT = "rt";
    public static final String KEY_IMAGE = "foto";
    public static final String KEY_TOKEN = "token";

    public SQLiteHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + KEY_TYPE + " TEXT,"
                + KEY_NAME + " TEXT,"
                + KEY_ALAMAT + " TEXT,"
                + KEY_PASS + " TEXT,"
                + KEY_NO_HP + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_SALDO + " TEXT,"
                + KEY_BANK + " TEXT,"
                + KEY_KELURAHAN + " TEXT,"
                + KEY_RW + " TEXT,"
                + KEY_RT + " TEXT,"
                + KEY_IMAGE + " TEXT,"
                + KEY_TOKEN + " TEXT " + ")";
        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG, "Database tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        // Create tables again
        onCreate(db);
    }

    public void addDatabase(String type, String name, String alamat, String pass, String no_hp, String email, String saldo,
                            String bank, String kelurahan, String rw, String rt, String foto, String token) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TYPE, type);
        values.put(KEY_NAME, name);
        values.put(KEY_ALAMAT, alamat);
        values.put(KEY_PASS, pass);
        values.put(KEY_NO_HP, no_hp);
        values.put(KEY_EMAIL, email);
        values.put(KEY_SALDO, saldo);
        values.put(KEY_BANK, bank);
        values.put(KEY_KELURAHAN, kelurahan);
        values.put(KEY_RW, rw);
        values.put(KEY_RT, rt);
        values.put(KEY_IMAGE, foto);
        values.put(KEY_TOKEN, token);

        long id = db.insert(TABLE_USER, null, values);
        db.close();

        Log.d(TAG, "Data ditambah: " + id);
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("type", cursor.getString(1));
            user.put("nama", cursor.getString(2));
            user.put("alamat", cursor.getString(3));
            user.put("pass", cursor.getString(4));
            user.put("no_hp", cursor.getString(5));
            user.put("email", cursor.getString(6));
            user.put("saldo", cursor.getString(7));
            user.put("bank", cursor.getString(8));
            user.put("kelurahan", cursor.getString(9));
            user.put("rw", cursor.getString(10));
            user.put("rt", cursor.getString(11));
            user.put("foto", cursor.getString(12));
            user.put("token", cursor.getString(13));
        }
        cursor.close();
        db.close();

        Log.d(TAG, "Fetching user from Sqlite:" + user.toString());

        return user;
    }


    public void deteleTable() {

        SQLiteDatabase db = this.getWritableDatabase(); // helper is object extends SQLiteOpenHelper
        db.delete(SQLiteHandler.TABLE_USER, null, null);
    }
}