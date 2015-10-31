package miw.dasm.inigo.genderguesser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class FirstNameStore extends SQLiteOpenHelper {

    private static final String DEFAULT_DATABASE_FILENAME = "FirstNames.db";
    private static final String MALE_CONSTANT = "male";
    private static final String FEMALE_CONSTANT = "female";
    private static final int DATABASE_VERSION = 1;
    private static final int RECENT_QUERY_LIMIT = 3;

    public FirstNameStore(Context context) {
        super(context, DEFAULT_DATABASE_FILENAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String consultaSQL = "CREATE TABLE " + FirstNameContract.FirstNameTable.TABLE_NAME + " ("
                + FirstNameContract.FirstNameTable.COL_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FirstNameContract.FirstNameTable.COL_NAME_NAME + " TEXT, "
                + FirstNameContract.FirstNameTable.COL_NAME_GENDER + " TEXT, "
                + FirstNameContract.FirstNameTable.COL_NAME_DESCRIPTION + " TEXT)";
        database.execSQL(consultaSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        String consulta = "DROP TABLE " + FirstNameContract.FirstNameTable.TABLE_NAME;
        database.execSQL(consulta);
        this.onCreate(database);
    }

    public int addFirstName(FirstName firstName) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FirstNameContract.FirstNameTable.COL_NAME_ID, firstName.getId());
        contentValues.put(FirstNameContract.FirstNameTable.COL_NAME_NAME, firstName.getName());
        contentValues.put(FirstNameContract.FirstNameTable.COL_NAME_GENDER, firstName.getGender());
        contentValues.put(FirstNameContract.FirstNameTable.COL_NAME_DESCRIPTION, firstName.getDescription());
        return (int) database.insert(FirstNameContract.FirstNameTable.TABLE_NAME, null, contentValues);
    }

    public void emptyAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + FirstNameContract.FirstNameTable.TABLE_NAME;
        db.execSQL(query);
    }

    public ArrayList<FirstName> getRecentFirstNames() {
        ArrayList<FirstName> recentFirstNames = new ArrayList<FirstName>();
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM " + FirstNameContract.FirstNameTable.TABLE_NAME
                + " ORDER BY " + FirstNameContract.FirstNameTable.COL_NAME_ID
                + " DESC LIMIT " + RECENT_QUERY_LIMIT;
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            recentFirstNames.add(new FirstName(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        return recentFirstNames;
    }

    public ArrayList<FirstName> getMaleFirstNames() {
        ArrayList<FirstName> maleFirstNames = new ArrayList<FirstName>();
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM " + FirstNameContract.FirstNameTable.TABLE_NAME
                + " WHERE " + FirstNameContract.FirstNameTable.COL_NAME_GENDER
                + " = '" + MALE_CONSTANT + "'";
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            maleFirstNames.add(new FirstName(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        return maleFirstNames;
    }

    public ArrayList<FirstName> getFemaleFirstNames() {
        ArrayList<FirstName> femaleFirstNames = new ArrayList<FirstName>();
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM " + FirstNameContract.FirstNameTable.TABLE_NAME
                + " WHERE " + FirstNameContract.FirstNameTable.COL_NAME_GENDER
                + " = '" + FEMALE_CONSTANT + "'";
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            femaleFirstNames.add(new FirstName(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        return femaleFirstNames;
    }

    public int getNextId() {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM " + FirstNameContract.FirstNameTable.TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);
        int count = 0;
        while (cursor.moveToNext()) {
            count++;
        }
        return count;
    }
}
