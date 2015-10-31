package miw.dasm.inigo.genderguesser;

import android.provider.BaseColumns;

public abstract class FirstNameContract {
    public class FirstNameTable implements BaseColumns {
        public static final String TABLE_NAME = "FirstNames";
        public static final String COL_NAME_ID = "id";
        public static final String COL_NAME_NAME = "name";
        public static final String COL_NAME_GENDER = "gender";
        public static final String COL_NAME_DESCRIPTION = "description";
    }
}
