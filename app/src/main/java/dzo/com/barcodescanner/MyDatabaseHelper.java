package dzo.com.barcodescanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Vivek vsking on 1/19/2019.
 * vivekpcst.kumar@gmail.com
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    String id[],name[],age[],occup[];

Context context;
    public MyDatabaseHelper(Context context){
        super(context, GlobalContact.DbContact.DB_NAME,null, GlobalContact.DbContact.DB_VERSION);
        this.context=context;
        Log.d("vskingdb<>","Database initialised !");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + GlobalContact.DbContact.TABLE_NAME + "(" + GlobalContact.DbContact.GIRL_ID + " INTEGER PRIMARY KEY, "
                + GlobalContact.DbContact.GIRL_NAME + " TEXT NOT NULL, " + GlobalContact.DbContact.GIRL_AGE + " TEXT NOT NULL, " + GlobalContact.DbContact.GIRL_OCCUPATION + " TEXT NOT NULL )";
        db.execSQL(CREATE_TABLE);
        Log.d("vskingdb<>","Database Created !");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + GlobalContact.DbContact.TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
        Log.d("vskingdb<>","Database Upgraded !");

    }

    public void insertNewRow(String id, String name, String age, String occup){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(GlobalContact.DbContact.GIRL_ID,id);
        contentValues.put(GlobalContact.DbContact.GIRL_NAME,name);
        contentValues.put(GlobalContact.DbContact.GIRL_AGE,age);
        contentValues.put(GlobalContact.DbContact.GIRL_OCCUPATION,occup);

        try {
            database.insertOrThrow(GlobalContact.DbContact.TABLE_NAME, null, contentValues);
            Log.d("vskingdb<>","Database inserted !");

        } catch (SQLiteException e){
            Toast.makeText(context, "Operation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            database.close();
        }
    }
    public void deleteRow(String id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        try {
            sqLiteDatabase.delete(GlobalContact.DbContact.TABLE_NAME,
                    GlobalContact.DbContact.GIRL_ID + " = ? ",
                    new String[]{ String.valueOf(id)});
            Log.d("vskingDb<>: ","Row deleted !");
        } catch (SQLiteException e){
            Log.d("vskingDb<>: ",""+ e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

    }

}
