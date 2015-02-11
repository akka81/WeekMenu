package hware.weekmenu.BusinessLogic.Managers;

import java.util.ArrayList;
import hware.weekmenu.BusinessLogic.Entities.*;
import hware.weekmenu.R;
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


    public ArrayList<Ingredient> GetRecipeIngredients(int RecipeId)
    {
        //get All Recipes
        String[] IngColumns = new String[]{DataProviderContract.IngredientTable.COLUMN_NAME,
                DataProviderContract.IngredientTable.COLUMN_QUANTITY,
                DataProviderContract.IngredientTable.COLUMN_MEASURE,
                "M."+DataProviderContract.MeasuresTable.COLUMN_MEASURE_NAME};

        SQLiteQueryBuilder qBuilder = new SQLiteQueryBuilder();
        qBuilder.setTables(DataProviderContract.IngredientTable.TABLE_NAME + " AS ING LEFT OUTER JOIN " + DataProviderContract.MeasuresTable.TABLE_NAME + " as M  ON (M." +
                DataProviderContract.MeasuresTable._ID + " = ING." + DataProviderContract.IngredientTable.COLUMN_MEASURE +")");

        DataProvider WmDataProvider = new DataProvider(CurrentContext);
        SQLiteDatabase WmDb = WmDataProvider.getReadableDatabase();
        Cursor IngredientsCursor = qBuilder.query(WmDb, IngColumns, null, null, null, null, null);


        int NAME_INDEX = IngredientsCursor.getColumnIndex(DataProviderContract.IngredientTable.COLUMN_NAME);
        int QUANTITY_INDEX = IngredientsCursor.getColumnIndex(DataProviderContract.IngredientTable.COLUMN_QUANTITY);
        int MEASURE_INDEX = IngredientsCursor.getColumnIndex( DataProviderContract.MeasuresTable.COLUMN_MEASURE_NAME);

        ArrayList<Ingredient> recIngredients = new ArrayList<Ingredient>();
        while(IngredientsCursor.moveToNext())
        {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(IngredientsCursor.getString(NAME_INDEX));
            ingredient.setQuantity(IngredientsCursor.getInt(QUANTITY_INDEX));
            ingredient.setMeasure(Measures.valueOf(IngredientsCursor.getInt(MEASURE_INDEX)));

            recIngredients.add(ingredient);
        }
        return recIngredients;
    }

	public Recipe GetRecipeById(int RecipeId)
	{
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
                DataProviderContract.RecipeTypes._ID + " = R." + DataProviderContract.RecipeTable.COLUMN_TYPE +") " +
                "where R." + DataProviderContract.RecipeTable._ID +" = "+ RecipeId);

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

        Recipe recipe = new Recipe();
        while(RecipesCursor.moveToNext())
        {
            recipe.setID(RecipesCursor.getInt(ID_INDEX));
            recipe.setName(RecipesCursor.getString(NAME_INDEX));
            recipe.setRecipeTypeName(RecipesCursor.getString(TYPE_NAME_INDEX));
            recipe.setRecipeType(RecipeType.valueOf(RecipesCursor.getInt(TYPE_INDEX)));
            recipe.setPleasure(RecipesCursor.getFloat(PLEAS_INDEX));
            recipe.setDescr(RecipesCursor.getString(DESCR_INDEX));
            recipe.setMakeTime(RecipesCursor.getInt(TIME_INDEX));
        }
        recipe.setIngredients(GetRecipeIngredients(RecipeId));
        //closing cursor
        RecipesCursor.close();
		return recipe;
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
	
	public void DeleteRecipe(int RecipeId)
	{
		DataProvider WmDataProvider = new DataProvider(CurrentContext);
		SQLiteDatabase WmDb = WmDataProvider.getWritableDatabase();
		
		String IngredientsWhereCond = DataProviderContract.IngredientTable.COLUMN_RECIPE_ID +" = " + RecipeId;
		String RecipeWhereCond = DataProviderContract.RecipeTable._ID + " = " + RecipeId;
		//deleting ingredients and recipe
		WmDb.delete(DataProviderContract.IngredientTable.TABLE_NAME, IngredientsWhereCond, null);
		WmDb.delete(DataProviderContract.RecipeTable.TABLE_NAME, RecipeWhereCond, null);
	}

    public static int GetRecipeImage(RecipeType rt)
    {
        int recipeImg=-1;
        if(rt ==  RecipeType.Appetizer){recipeImg = R.drawable.antipasti;}
        else if (rt == RecipeType.Dessert ){ recipeImg = R.drawable.dolci;}
        else if(rt ==  RecipeType.MainCourse){recipeImg = R.drawable.secondi;}
        else if (rt == RecipeType.MainCourse2){recipeImg = R.drawable.piattounico;}
        else if (rt == RecipeType.Starter){recipeImg = R.drawable.primi;}
        return recipeImg;
    }

    public static  int GetRecipePleasureIcon(float pleasure)
    {

        int pleasureImg = -1;
        if(pleasure>0  && pleasure <1){pleasureImg = R.drawable.stelle_05;}
        else if(pleasure == 1){pleasureImg = R.drawable.stelle_1;}
        else if(pleasure>1 && pleasure <2){pleasureImg = R.drawable.stelle_15;}
        else if (pleasure ==2){pleasureImg = R.drawable.stelle_2;}
        else if (pleasure>2 && pleasure <3){pleasureImg = R.drawable.stelle_25;}
        else if(pleasure == 3){pleasureImg = R.drawable.stelle_3;}
        else if (pleasure>3 && pleasure <4){pleasureImg = R.drawable.stelle_35;}
        else if(pleasure == 4){pleasureImg = R.drawable.stelle_4;}
        else if (pleasure>4 && pleasure <5){pleasureImg = R.drawable.stelle_45;}
        else
            pleasureImg = R.drawable.stelle_5;

        return pleasureImg;
    }

}
