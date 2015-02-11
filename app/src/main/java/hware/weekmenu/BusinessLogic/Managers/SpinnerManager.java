package hware.weekmenu.BusinessLogic.Managers;

import hware.weekmenu.BusinessLogic.Entities.SpinnerItem;
import hware.weekmenu.db.DataProvider;
import hware.weekmenu.db.DataProviderContract;

import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SpinnerManager {
	
	Context CurrentContext = null;
	public SpinnerManager(Context context)
	{
		CurrentContext = context;
	}
	
	public ArrayList<SpinnerItem> GetRecipeTypes()
	{
		DataProvider WmDataProvider = new DataProvider(CurrentContext);
		SQLiteDatabase WmDb = WmDataProvider.getReadableDatabase();
		
		String[] ItemCols = new String[]{DataProviderContract.RecipeTypes._ID,
										 DataProviderContract.RecipeTypes.COLUMN_TYPE_NAME};
		
		Cursor TypesCursor = WmDb.query(DataProviderContract.RecipeTypes.TABLE_NAME, ItemCols, null, null, null, null, null);
			
		int ID_INDEX = TypesCursor.getColumnIndex(DataProviderContract.RecipeTypes._ID);
		int NAME_INDEX = TypesCursor.getColumnIndex(DataProviderContract.RecipeTypes.COLUMN_TYPE_NAME);
		
		ArrayList<SpinnerItem> Items = new ArrayList<SpinnerItem>();
		while(TypesCursor.moveToNext())
		{
			int resid = CurrentContext.getResources().getIdentifier(TypesCursor.getString(NAME_INDEX),"string", CurrentContext.getPackageName());
			SpinnerItem itm = new SpinnerItem(TypesCursor.getInt(ID_INDEX),CurrentContext.getResources().getString(resid));
			//add recipe type  to list
            Items.add(itm);
		}
		TypesCursor.close();
		return Items;
	}
	
	public ArrayList<SpinnerItem> GetMeasures()
	{
		DataProvider WmDataProvider = new DataProvider(CurrentContext);
		SQLiteDatabase WmDb = WmDataProvider.getReadableDatabase();
		
		String[] ItemCols = new String[]{DataProviderContract.MeasuresTable._ID,
										 DataProviderContract.MeasuresTable.COLUMN_MEASURE_NAME};
		
		Cursor MeasureCursor = WmDb.query(DataProviderContract.MeasuresTable.TABLE_NAME, ItemCols, null, null, null, null, null);
			
		int ID_INDEX = MeasureCursor.getColumnIndex(DataProviderContract.MeasuresTable._ID);
		int NAME_INDEX = MeasureCursor.getColumnIndex(DataProviderContract.MeasuresTable.COLUMN_MEASURE_NAME);
		
		ArrayList<SpinnerItem> Measures = new ArrayList<SpinnerItem>();
		while(MeasureCursor.moveToNext())
		{
			int resid = CurrentContext.getResources().getIdentifier(MeasureCursor.getString(NAME_INDEX),"string", CurrentContext.getPackageName());
			SpinnerItem itm = new SpinnerItem(MeasureCursor.getInt(ID_INDEX),CurrentContext.getResources().getString(resid));
			//add Measure  to list
			Measures.add(itm);
		}
		MeasureCursor.close();
	
		return Measures;
	}

}
