package miw.dasm.inigo.genderguesser;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FirstNameStore extends SQLiteOpenHelper {

    private static final String DEFAULT_DATABASE_FILENAME = "FirstNames.db";

    private static final int DATABASE_VERSION = 1;

    public FirstNameStore(Context context) {
        super(context, DEFAULT_DATABASE_FILENAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

    }
}
