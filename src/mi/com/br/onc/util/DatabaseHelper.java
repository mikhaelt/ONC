package mi.com.br.onc.util;

import java.io.IOException;
import java.util.List;

import mi.com.br.onc.R;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

		private static final String DATABASE_NAME = "onc.sqlite7";
		private static final int DATABASE_VERSION = 1;
	
	private final Context context;
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		try {
			runScript(db, R.raw.create_db_v1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void runScript(SQLiteDatabase db, int script) throws IOException {
		// TODO Auto-generated method stub
		List<String> lines = FileUtils.readLines(this.context, script);
		
		for (String sql : lines){
			if (sql != null && sql.trim().length() > 0){
				db.execSQL(sql);
			}
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		if(newVersion == 2) {
		}
	}

}
