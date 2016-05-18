package ar.com.cusoft.pruebasqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper sInstance;

    private static final String DATABASE_NAME = "dbandroid";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CLIENTES = "clients";
    private static final String KEY_CLIENTE_ID = "id";
    private static final String KEY_CLIENTE_NAME = "name";

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CLIENTES_TABLE = "CREATE TABLE " + TABLE_CLIENTES +
                "(" +
                KEY_CLIENTE_ID + " INTEGER PRIMARY KEY," + // Define a primary key
                KEY_CLIENTE_NAME + " TEXT" +
                ")";
        db.execSQL(CREATE_CLIENTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTES);
            onCreate(db);
        }
    }

    public boolean insertClient(Client cliente) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_CLIENTE_NAME, cliente.getName());
            db.insertOrThrow(TABLE_CLIENTES, null, values);
            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            Log.d("facturar_data", "Error while trying to add un cliente to database");
            return false;
        } finally {
            db.endTransaction();
        }
    }

    public List<Client> getAllClientes() {
        ArrayList<Client> clientes = new ArrayList<>();

        String CLIENTES_SELECT_QUERY = String.format("SELECT * FROM %s", TABLE_CLIENTES);
        SQLiteDatabase db = this.getReadableDatabase(); //Se le agrego el this
        Cursor cursor = db.rawQuery(CLIENTES_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Client newCliente = new Client();
                    newCliente.setName(cursor.getString(cursor.getColumnIndex(KEY_CLIENTE_NAME)));
                    clientes.add(newCliente);
                } while(cursor.moveToNext());
            }
            return clientes;
        } catch (Exception e) {
            Log.d("facturar_data", "Error while trying to get clientes from database");
            return null;
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }
}
