package hware.weekmenu.BusinessLogic.Managers;

import java.util.ArrayList;
import hware.weekmenu.BusinessLogic.Entities.*;
import hware.weekmenu.db.*;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

public class RecipeManager {

	Context CurrentContext = null;
	public RecipeManager(Context context)
	{
		CurrentContext = context;
	}
	
	//Get all recipes from database
	public ArrayList<Recipe> GetRecipes()
	{
		final ArrayList<Recipe> Recipes = new ArrayList<Recipe>();
	
		//get All Recipes
		String[] RecColumns = new String[]{DataProviderContract.RecipeTable.COLUMN_NAME,
										   DataProviderContract.RecipeTable.COLUMN_DESCR,
										   DataProviderContract.RecipeTable.COLUMN_PLEASURE,
										   DataProviderContract.RecipeTable.COLUMN_TYPE,
										   DataProviderContract.RecipeTable.COLUMN_MAKE_TIME,
										   "R."+DataProviderContract.RecipeTable._ID,
										   DataProviderContract.RecipeTypes.COLUMN_TYPE_NAME,
										   DataProviderContract.RecipeTable.COLUMN_PLEASURE};	
				      
		SQLiteQueryBuilder qBuilder = new SQLiteQueryBuilder();
		qBuilder.setTables(DataProviderContract.RecipeTable.TABLE_NAME + " AS R LEFT OUTER JOIN " + DataProviderContract.RecipeTypes.TABLE_NAME + " as T  ON (T." + 
		DataProviderContract.RecipeTypes._ID + " = R." + DataProviderContract.RecipeTable.COLUMN_TYPE +")");
					      
		DataProvider WmDataProvider = new DataProvider(CurrentContext);
		SQLiteDatabase WmDb = WmDataProvider.getReadableDatabase();
		Cursor RecipesCursor = qBuilder.query(WmDb, RecColumns, null, null, null, null, null);
		
		int ID_INDEX = RecipesCursor.getColumnIndex(DataProviderContract.RecipeTable._ID);
		int NAME_INDEX = RecipesCursor.getColumnIndex(DataProviderContract.RecipeTable.COLUMN_NAME);
		int DESCR_INDEX = RecipesCursor.getColumnIndex(DataProviderContract.RecipeTable.COLUMN_DESCR);
		int TYPE_NAME_INDEX = RecipesCursor.getColumnIndex(DataProviderContract.RecipeTypes.COLUMN_TYPE_NAME);
		int TYPE_INDEX = RecipesCursor.getColumnIndex( DataProviderContract.RecipeTable.COLUMN_TYPE);
		int PLEAS_INDEX = RecipesCursor.getColumnIndex(DataProviderContract.RecipeTable.COLUMN_PLEASURE);
		int TIME_INDEX = RecipesCursor.getColumnIndex(DataProviderContract.RecipeTable.COLUMN_MAKE_TIME);
		
		while(RecipesCursor.moveToNext())
		{
			Recipe rec = new Recipe();
			rec.setID(RecipesCursor.getInt(ID_INDEX));
			rec.setName(RecipesCursor.getString(NAME_INDEX));
            rec.setRecipeTypeName(RecipesCursor.getString(TYPE_NAME_INDEX));
            rec.setRecipeType(RecipeType.valueOf(RecipesCursor.getInt(TYPE_INDEX)));
            rec.setPleasure(RecipesCursor.getFloat(PLEAS_INDEX));
            rec.setDescr(RecipesCursor.getString(DESCR_INDEX));
            rec.setMakeTime(RecipesCursor.getInt(TIME_INDEX));
			//add recipe to list
            Recipes.add(rec);
		}
		//closing cursor
		RecipesCursor.close();	
		return Recipes;
	}
	
	public ArrayList<Recipe> GetRecipesByCriteria(RecipeType rType, DifficultyLevel rLevel)
	{
		ArrayList<Recipe> Recipes = new ArrayList<Recipe>();
		
		return Recipes;
	}
	
	public ArrayList<Recipe> GetRecipesBySearch(String searchFilter)
	{
		//query on name and description
		Recipe rcp = new Recipe();
		rcp.setName("Test recipe");
		
		ArrayList<Recipe> Results = new ArrayList<Recipe>();
		Results.add(rcp);
		
		return Results;
		
	}

	public Recipe GetRecipeById(int RecipeId)
	{
		Recipe rcipe = new Recipe();
		return rcipe;
	}
	
	public long InsertRecipe(Recipe NewRecipe)
	{
		
		DataProvider WmDataProvider = new DataProvider(CurrentContext);
		SQLiteDatabase WmDb = WmDataProvider.getWritableDatabase();
		
		ContentValues RecipeValues = new ContentValues();
		RecipeValues.put(DataProviderContract.RecipeTable.COLUMN_NAME,NewRecipe.getName());
		RecipeValues.put(DataProviderContract.RecipeTable.COLUMN_DESCR, NewRecipe.getDescr());
		RecipeValues.put(DataProviderContract.RecipeTable.COLUMN_MAKE_TIME, NewRecipe.getMakeTime());
		RecipeValues.put(DataProviderContract.RecipeTable.COLUMN_ISFROMWR, NewRecipe.getIsFromWoodRestaurant());
		RecipeValues.put(DataProviderContract.RecipeTable.COLUMN_PLEASURE, NewRecipe.getPleasure());
		RecipeValues.put(DataProviderContract.RecipeTable.COLUMN_TYPE, NewRecipe.getRecipeType().getValue());
		
		long NewRecipeID = WmDb.insert(DataProviderContract.RecipeTable.TABLE_NAME, null, RecipeValues);
		
	    //inserting recipe Ingredients
		for(Ingredient ingr : NewRecipe.getIngredients())
		{
			ContentValues RecipeIngredient = new ContentValues();
			RecipeIngredient.put(DataProviderContract.IngredientTable.COLUMN_RECIPE_ID, NewRecipeID);
			RecipeIngredient.put(DataProviderContract.IngredientTable.COLUMN_NAME, ingr.getName());
			RecipeIngredient.put(DataProviderContract.IngredientTable.COLUMN_QUANTITY,ingr.getQuantity());
			RecipeIngredient.put(DataProviderContract.IngredientTable.COLUMN_MEASURE, ingr.getMeasure().getValue());
			//add ingredient
			WmDb.insert(DataProviderContract.IngredientTable.TABLE_NAME, null, RecipeIngredient);
		}			
		return NewRecipeID;
	}
	
	public void UpdateRecipe(Recipe RecipeToUpdate)
	{
		DataProvider WmDataProvider = new DataProvider(CurrentContext);
		SQLiteDatabase WmDb = WmDataProvider.getWritableDatabase();
		
		String UpdateClause = DataProviderContract.RecipeTable._ID + " = " + RecipeToUpdate.getID();
		
		ContentValues RecipeValues = new ContentValues();
		RecipeValues.put(DataProviderContract.RecipeTable.COLUMN_NAME,RecipeToUpdate.getName());
		RecipeValues.put(DataProviderContract.RecipeTable.COLUMN_DESCR, RecipeToUpdate.getDescr());
		RecipeValues.put(DataProviderContract.RecipeTable.COLUMN_MAKE_TIME, RecipeToUpdate.getMakeTime());
		RecipeValues.put(DataProviderContract.RecipeTable.COLUMN_ISFROMWR, RecipeToUpdate.getIsFromWoodRestaurant());
		RecipeValues.put(DataProviderContract.RecipeTable.COLUMN_PLEASURE, RecipeToUpdate.getPleasure());
		RecipeValues.put(DataProviderContract.RecipeTable.COLUMN_TYPE, RecipeToUpdate.getRecipeType().getValue());
		
		WmDb.update(DataProviderContract.RecipeTable.TABLE_NAME, RecipeValues, UpdateClause, null);
		
		//deleting and inserting ingredients
		String IngredientsWhereCond = DataProviderContract.IngredientTable.COLUMN_RECIPE_ID +" = " +RecipeToUpdate.getID();
		WmDb.delete(DataProviderContract.IngredientTable.TABLE_NAME, IngredientsWhereCond, null);
		
		 //inserting recipe Ingredients
		for(Ingredient ingr : RecipeToUpdate.getIngredients())
		{
			ContentValues RecipeIngredient = new ContentValues();
			RecipeIngredient.put(DataProviderContract.IngredientTable.COLUMN_RECIPE_ID, RecipeToUpdate.getID());
			RecipeIngredient.put(DataProviderContract.IngredientTable.COLUMN_NAME, ingr.getName());
			RecipeIngredient.put(DataProviderContract.IngredientTable.COLUMN_QUANTITY,ingr.getQuantity());
			RecipeIngredient.put(DataProviderContract.IngredientTable.COLUMN_MEASURE, ingr.getMeasure().getValue());
			//add ingredient
			WmDb.insert(DataProviderContract.IngredientTable.TABLE_NAME, null, RecipeIngredient);
		}		
	}
	
	public void DeleteReipe(int RecipeId)
	{
		DataProvider WmDataProvider = new DataProvider(CurrentContext);
		SQLiteDatabase WmDb = WmDataProvider.getWritableDatabase();
		
		String IngredientsWhereCond = DataProviderContract.IngredientTable.COLUMN_RECIPE_ID +" = " + RecipeId;
		String RecipeWhereCond = DataProviderContract.RecipeTable._ID + " = " + RecipeId;
		//deleting ingredients and recipe
		WmDb.delete(DataProviderContract.IngredientTable.TABLE_NAME, IngredientsWhereCond, null);
		WmDb.delete(DataProviderContract.RecipeTable.TABLE_NAME, RecipeWhereCond, null);
	}
	

}
