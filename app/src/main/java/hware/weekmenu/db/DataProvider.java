package hware.weekmenu.db;

import hware.weekmenu.BusinessLogic.Entities.Measures;
import hware.weekmenu.BusinessLogic.Entities.RecipeType;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataProvider extends SQLiteOpenHelper {

	 public static final int DATABASE_VERSION = 2;
	 public static final String DATABASE_NAME = "WeekMenu.db";
	 
	 //public static final String DB_TABLES_DIFFICULTY = "CREATE TABLE " + DataProviderContract.DifficultyLevelTable.TABLE_NAME +" ("+ 
	 //DataProviderContract.DifficultyLevelTable._ID + " INTEGER PRIMARY KEY," + 
	 //DataProviderContract.DifficultyLevelTable.COLUMN_DIFFICULTY_NAME + " TEXT not null)";

	 public static final String DB_TABLES_MEASURES = "CREATE TABLE " + DataProviderContract.MeasuresTable.TABLE_NAME +" ("+ 
			 DataProviderContract.MeasuresTable._ID + " INTEGER PRIMARY KEY," + 
			 DataProviderContract.MeasuresTable.COLUMN_MEASURE_NAME + " TEXT not null)";
	 
	 //public static final String DB_TABLES_PLEASURES = "CREATE TABLE " + DataProviderContract.PleasureTable.TABLE_NAME +" ("+ 
		//	 DataProviderContract.PleasureTable._ID + " INTEGER PRIMARY KEY," + 
		//	 DataProviderContract.PleasureTable.COLUMN_PLEASURE_NAME + " TEXT not null)";
	  
	 public static final String DB_TABLES_RECIPESTYPE = "CREATE TABLE " + DataProviderContract.RecipeTypes.TABLE_NAME +" ("+
			 DataProviderContract.RecipeTypes._ID + " INTEGER PRIMARY KEY, " + 
			 DataProviderContract.RecipeTypes.COLUMN_TYPE_NAME + " TEXT not null)";
	 
	 public static final String DB_TABLES_RECIPES = "CREATE TABLE " + DataProviderContract.RecipeTable.TABLE_NAME +" ("+ 
			 DataProviderContract.RecipeTable._ID + " INTEGER PRIMARY KEY autoincrement," + 
			 DataProviderContract.RecipeTable.COLUMN_NAME + " TEXT not null,  " +
			 DataProviderContract.RecipeTable.COLUMN_DESCR + " TEXT, " +
		//	 DataProviderContract.RecipeTable.COLUMN_DIFFICULTY + " INTEGER, " +
			 DataProviderContract.RecipeTable.COLUMN_PLEASURE + " REAL, " +
			 DataProviderContract.RecipeTable.COLUMN_MAKE_TIME + " INTEGER, " +
			 DataProviderContract.RecipeTable.COLUMN_ISFROMWR + " INTEGER, " +
			 DataProviderContract.RecipeTable.COLUMN_TYPE + " INTEGER, " +
			// "FOREIGN KEY ("+ DataProviderContract.RecipeTable.COLUMN_DIFFICULTY +") REFERENCES "+ 
			 //DataProviderContract.DifficultyLevelTable.TABLE_NAME +" ("+ DataProviderContract.DifficultyLevelTable._ID +")," +
			// "FOREIGN KEY ("+ DataProviderContract.RecipeTable.COLUMN_PLEASURE +") REFERENCES "+ 
			// DataProviderContract.PleasureTable.TABLE_NAME +" ("+ DataProviderContract.PleasureTable._ID +")," +
			 "FOREIGN KEY ("+ DataProviderContract.RecipeTable.COLUMN_TYPE +") REFERENCES "+
			 DataProviderContract.RecipeTypes.TABLE_NAME +" ("+DataProviderContract.RecipeTypes._ID+"))";
	 
	 public static final String DB_TABLES_INGREDIENTS = "CREATE TABLE " + DataProviderContract.IngredientTable.TABLE_NAME +" ("+ 
			 DataProviderContract.IngredientTable._ID + " INTEGER PRIMARY KEY autoincrement," + 
			 DataProviderContract.IngredientTable.COLUMN_NAME + " TEXT not null,  " +
			 DataProviderContract.IngredientTable.COLUMN_QUANTITY + " INTEGER, " +
			 DataProviderContract.IngredientTable.COLUMN_MEASURE + " INTEGER, " +
			 DataProviderContract.IngredientTable.COLUMN_RECIPE_ID + " INTEGER, " +
			 "FOREIGN KEY ("+ DataProviderContract.IngredientTable.COLUMN_MEASURE +") REFERENCES "+ 
			 DataProviderContract.MeasuresTable.TABLE_NAME +" ("+ DataProviderContract.MeasuresTable._ID +")," +
			 "FOREIGN KEY ("+ DataProviderContract.IngredientTable.COLUMN_RECIPE_ID +") REFERENCES "+ 
			 DataProviderContract.RecipeTable.TABLE_NAME +" ("+ DataProviderContract.RecipeTable._ID +"))";
	 
	public static final String DB_TABLES_SCHEDULES = "";
	 
	 
	//class ctor
	public DataProvider(Context context)
	{
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL("DROP TABLE IF EXISTS "+ DataProviderContract.IngredientTable.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS "+ DataProviderContract.RecipeTable.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS "+ DataProviderContract.RecipeTypes.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS "+ DataProviderContract.MeasuresTable.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS "+ DataProviderContract.PleasureTable.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS "+ DataProviderContract.DifficultyLevelTable.TABLE_NAME);
		
		//db.execSQL(DB_TABLES_DIFFICULTY);
		db.execSQL(DB_TABLES_MEASURES);
		//db.execSQL(DB_TABLES_PLEASURES);
		db.execSQL(DB_TABLES_RECIPESTYPE);
		db.execSQL(DB_TABLES_RECIPES);
		db.execSQL(DB_TABLES_INGREDIENTS);
		
		//inserting domain data if not yet present
		ContentValues DomainData = new ContentValues();
		
		//difficulties
		//DomainData.put(DataProviderContract.DifficultyLevelTable._ID, DifficultyLevel.EASY.getValue());
		//DomainData.put(DataProviderContract.DifficultyLevelTable.COLUMN_DIFFICULTY_NAME,"EasyKey");
		//db.insert(DataProviderContract.DifficultyLevelTable.TABLE_NAME, null, DomainData);
		
		//DomainData.clear();
		//DomainData.put(DataProviderContract.DifficultyLevelTable._ID, DifficultyLevel.MEDIUM.getValue());
		//DomainData.put(DataProviderContract.DifficultyLevelTable.COLUMN_DIFFICULTY_NAME,"MediumKey");
		//db.insert(DataProviderContract.DifficultyLevelTable.TABLE_NAME, null, DomainData);
		
		//DomainData.clear();
		//DomainData.put(DataProviderContract.DifficultyLevelTable._ID, DifficultyLevel.HARD.getValue());
		//DomainData.put(DataProviderContract.DifficultyLevelTable.COLUMN_DIFFICULTY_NAME,"HardKey");
		//db.insert(DataProviderContract.DifficultyLevelTable.TABLE_NAME, null, DomainData);
		
		//DomainData.clear();
		//DomainData.put(DataProviderContract.DifficultyLevelTable._ID, DifficultyLevel.VERYHARD.getValue());
		//DomainData.put(DataProviderContract.DifficultyLevelTable.COLUMN_DIFFICULTY_NAME,"VeryHardKey");
		//db.insert(DataProviderContract.DifficultyLevelTable.TABLE_NAME, null, DomainData);
		
		//Recipe Type
		DomainData.clear();
		DomainData.put(DataProviderContract.RecipeTypes._ID,RecipeType.Appetizer.getValue());
		DomainData.put(DataProviderContract.RecipeTypes.COLUMN_TYPE_NAME,"AppetizerKey");
		db.insert(DataProviderContract.RecipeTypes.TABLE_NAME, null, DomainData);
		
		DomainData.clear();
		DomainData.put(DataProviderContract.RecipeTypes._ID, RecipeType.MainCourse.getValue());
		DomainData.put(DataProviderContract.RecipeTypes.COLUMN_TYPE_NAME,"MainCourseKey");
		db.insert(DataProviderContract.RecipeTypes.TABLE_NAME, null, DomainData);
		
		DomainData.clear();
		DomainData.put(DataProviderContract.RecipeTypes._ID, RecipeType.MainCourse2.getValue());
		DomainData.put(DataProviderContract.RecipeTypes.COLUMN_TYPE_NAME,"MainCourse2Key");
		db.insert(DataProviderContract.RecipeTypes.TABLE_NAME, null, DomainData);
		
		DomainData.clear();
		DomainData.put(DataProviderContract.RecipeTypes._ID, RecipeType.Starter.getValue());
		DomainData.put(DataProviderContract.RecipeTypes.COLUMN_TYPE_NAME,"StarterKey");
		db.insert(DataProviderContract.RecipeTypes.TABLE_NAME, null, DomainData);
		
		DomainData.clear();
		DomainData.put(DataProviderContract.RecipeTypes._ID, RecipeType.Dessert.getValue());
		DomainData.put(DataProviderContract.RecipeTypes.COLUMN_TYPE_NAME,"DessertKey");
		db.insert(DataProviderContract.RecipeTypes.TABLE_NAME, null, DomainData);
		
		//Pleasures
		//DomainData.clear();
		//DomainData.put(DataProviderContract.PleasureTable._ID, PleasureLevel.NOTHINGSPECIAL.getValue());
		//DomainData.put(DataProviderContract.PleasureTable.COLUMN_PLEASURE_NAME,"NothingSpecialKey");
		//db.insert(DataProviderContract.PleasureTable.TABLE_NAME, null, DomainData);
		
		//DomainData.clear();
		//omainData.put(DataProviderContract.PleasureTable._ID, PleasureLevel.NORMAL.getValue());
		//DomainData.put(DataProviderContract.PleasureTable.COLUMN_PLEASURE_NAME,"NormalKey");
		//db.insert(DataProviderContract.PleasureTable.TABLE_NAME, null, DomainData);
		
		//DomainData.clear();
		//DomainData.put(DataProviderContract.PleasureTable._ID, PleasureLevel.GOOD.getValue());
		//DomainData.put(DataProviderContract.PleasureTable.COLUMN_PLEASURE_NAME,"GoodKey");
		//db.insert(DataProviderContract.PleasureTable.TABLE_NAME, null, DomainData);
		
		//DomainData.clear();
		//DomainData.put(DataProviderContract.PleasureTable._ID, PleasureLevel.VERYGOOD.getValue());
		//DomainData.put(DataProviderContract.PleasureTable.COLUMN_PLEASURE_NAME,"VeryGoodKey");
		//db.insert(DataProviderContract.PleasureTable.TABLE_NAME, null, DomainData);
		
		//DomainData.clear();
		//DomainData.put(DataProviderContract.PleasureTable._ID, PleasureLevel.CHEFDISH.getValue());
		//DomainData.put(DataProviderContract.PleasureTable.COLUMN_PLEASURE_NAME,"ChefDishKey");
		//db.insert(DataProviderContract.PleasureTable.TABLE_NAME, null, DomainData);
		
		//measures
		DomainData.clear();
		DomainData.put(DataProviderContract.MeasuresTable._ID, Measures.G.getValue());
		DomainData.put(DataProviderContract.MeasuresTable.COLUMN_MEASURE_NAME,"GramKey");
		db.insert(DataProviderContract.MeasuresTable.TABLE_NAME, null, DomainData);
		
		DomainData.clear();
		DomainData.put(DataProviderContract.MeasuresTable._ID, Measures.ML.getValue());
		DomainData.put(DataProviderContract.MeasuresTable.COLUMN_MEASURE_NAME,"MilliLiterKey");
		db.insert(DataProviderContract.MeasuresTable.TABLE_NAME, null, DomainData);
		
		DomainData.clear();
		DomainData.put(DataProviderContract.MeasuresTable._ID, Measures.KG.getValue());
		DomainData.put(DataProviderContract.MeasuresTable.COLUMN_MEASURE_NAME,"KilogramKey");
		db.insert(DataProviderContract.MeasuresTable.TABLE_NAME, null, DomainData);
		
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		//re-create database
		onCreate(db);
	}
}
