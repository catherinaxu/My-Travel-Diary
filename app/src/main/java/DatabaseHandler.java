import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Address;

import com.example.catherinaxu.mycityfinder.Loc;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "locationManager";
    private static final String TABLE_LOCATIONS = "locations";
    private static final String KEY_ID = "id";
    private static final String KEY_FEATURE_NAME = "feature_name";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_DESCRIPTION = "description";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //creates a table for the first time
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_LOCATIONS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FEATURE_NAME + " TEXT,"
                + KEY_LATITUDE + " TEXT," + KEY_LONGITUDE + " TEXT," + KEY_DESCRIPTION + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //upgrades the table with a newer version
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);

        onCreate(db);
    }

    public void addLocation(Address address, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        //creating values
        ContentValues values = new ContentValues();
        values.put(KEY_FEATURE_NAME, address.getFeatureName());
        values.put(KEY_LATITUDE, address.getLatitude());
        values.put(KEY_LONGITUDE, address.getLongitude());
        values.put(KEY_DESCRIPTION, description);

        db.insert(TABLE_LOCATIONS, null, values);
        db.close();
    }

    //public Loc getLocation(int id) {

    //}
}
