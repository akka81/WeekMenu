package hware.weekmenu.db;

import android.provider.BaseColumns;

public class DataProviderContract {
	


	private DataProviderContract(){}
	
	public static abstract class RecipeTable implements BaseColumns {
	    public static final String TABLE_NAME = "TB_RECIPES";
	    public static final String COLUMN_RECIPE_ID = "RECIPE_ID";
	    public static final String COLUMN_NAME = "NAME";
	    public static final String COLUMN_DESCR = "DESCR";
	    public static final String COLUMN_DIFFICULTY = "DIFFICULTY";
	    public static final String COLUMN_PLEASURE = "PLEASURE";
	    public static final String COLUMN_ISFROMWR = "IS_FROM_WOOD_RESTAURANT";
	    public static final String COLUMN_TYPE = "RECIPE_TYPE";
	    public static final String COLUMN_MAKE_TIME = "MAKE_TIME";
	   
	}
	
	public static abstract class IngredientTable implements BaseColumns
	{
		public static final String TABLE_NAME = "TB_INGREDIENTS";
		public static final String COLUMN_INGREDIENT_ID = "INGREDIENT_ID";
		public static final String COLUMN_NAME = "NAME";
		public static final String COLUMN_QUANTITY = "QUANTITY";
		public static final String COLUMN_MEASURE = "MEASURE";
		public static final String COLUMN_RECIPE_ID = "RECIPE_ID";
	}
	
	public static abstract class MeasuresTable implements BaseColumns
	{
		public static final String TABLE_NAME = "TB_MEASURES";
		public static final String COLUMN_MEASURE_ID = "MEASURE_ID";
		public static final String  COLUMN_MEASURE_NAME = "MEASURE_NAME";
	}
	
	public static abstract class DifficultyLevelTable implements BaseColumns
	{
		public static final String TABLE_NAME = "TB_DIFFICULTYLEVELS";
		//public static final String COLUMN_DIFFICULTY_ID = "DIFFICULTY_ID";
		//public static final String  COLUMN_DIFFICULTY_NAME = "DIFFICULTY_NAME";
	}
	
	public static abstract class PleasureTable implements BaseColumns
	{
		public static final String TABLE_NAME = "TB_PLEASURES";
		//public static final String COLUMN_PLEASURE_ID = "PLEASURE_ID";
		//public static final String  COLUMN_PLEASURE_NAME = "PLEASURE_NAME";
	}
	
	public static abstract class ScheduledMenu implements BaseColumns
	{
		public static final String TABLE_NAME = "TB_SCHEDULED_MENU";
		public static final String COLUMN_RECIPE_ID = "RECIPE_ID";
		public static final String COLUMN_DAY_TIME = "DAY_TIME";
		public static final String COLUMN_DATE ="DATE";
		
	}
	public static abstract class RecipeTypes implements BaseColumns
	{
		public static final String TABLE_NAME = "TB_RECIPES_TYPES";
		public static final String COLUMN_TYPE_ID = "TYPE_ID";
		public static final String COLUMN_TYPE_NAME = "TYPE_NAME";
		
	}

}
