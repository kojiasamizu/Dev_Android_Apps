# データベースの扱いについて

## Contractを作成する
	* インスタンス化されないよう、修飾子にはfinalを付け、念のため空のコンストラクタを作っておく。

	* BaseColumns(識別子に自動的に、"\_id"が付与される)
	public static abstract class User implements BaseColumns {
		//定数を記述していく...
	}  

## OpenHelperを作成する  
	* SQLiteOpenHelperを継承し、必要なメソッドを追加、コンストラクタを定義
		public class UserOpenHelper extends SQLiteOpenHelper {
			//コンストラクタ
			public void UserOpenHelper(Context c) {
				super(c , DB_NAME , null , DB_VERSION);
			}
			@Override
			public void onCreate(SQLiteDatabase db) {

			}
			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			}
		}

	* SQL文の実行
		db.execSQL(...);

## レコードの抽出
	1. インスタンス化
		//OpenHelperのインスタンス化
		UserOpenHelper userOpenHelper = new UserOpenHelper(this);
		//DB
		SQLiteDatabase db = userOpenHelper.getWritableDatabase();	//書き込み可

	2. Cursorの定義
	//レコード抽出
	Cursor c = null;
	c = db.query(
		UserContract.User.TABLE_NAME ,
		null ,
		null ,
		null ,
		null ,
		null ,
		null ,
		);

	3. 全件抽出
	while (c.moveToNext()) {
		int id = c.getInt(c.getColumnIndex(UserContract.User.\_ID));
		String name = c.getString(c.getColumnIndex(UserContract.User.COL_NAME));
		int score = c.getInt(c.getColumnIndex(UserContract.User.COL_SCORE));
		Log.v("DB_TEST" , "id : " + id + "name : " + name + "score : " + score);
	}

	4. カーソルとDBを閉じる
	c.close();
	db.close();
