package hware.weekmenu;

import hware.weekmenu.BusinessLogic.Entities.Recipe;
import hware.weekmenu.BusinessLogic.Entities.RecipeType;
import hware.weekmenu.BusinessLogic.Managers.RecipeManager;
import hware.weekmenu.db.*;
import hware.weekmenu.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.lang.Thread;
/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class LoaderActivity extends Activity {
	/**
	 * Whether or not the system UI should be auto-hidden after
	 * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
	 */
	private static final boolean AUTO_HIDE = true;

	/**
	 * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
	 * user interaction before hiding the system UI.
	 */
	private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

	/**
	 * If set, will toggle the system UI visibility upon interaction. Otherwise,
	 * will show the system UI visibility upon interaction.
	 */
	private static final boolean TOGGLE_ON_CLICK = true;

	/**
	 * The flags to pass to {@link SystemUiHider#getInstance}.
	 */
	private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

	/**
	 * The instance of the {@link SystemUiHider} for this activity.
	 */
	private SystemUiHider mSystemUiHider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_loader);
		FakeLoading();
		    	  
					
	}
	
	
	
	private void FakeLoading()
	{
		Thread trd = new Thread(null, backGroundProcess,"Background");
		trd.start();
	}
	
	private Runnable backGroundProcess = new Runnable(){public void run(){backGroundOperation();}};
	private Runnable updateGUI = new Runnable(){public void run(){doUpdateGUI();}};
    private Handler handler = new Handler();
	
	private void backGroundOperation()
	{
		try
		{
		/*	//creating DB
			DataProvider WMProvider = new DataProvider(getBaseContext());
			
			SQLiteDatabase WmDb = WMProvider.getWritableDatabase();
			//insert a test recipe
			Recipe rec = new Recipe();
			rec.setDescr("questa è una ricetta di test");
			rec.setName("Spaghetti al pomodoro");
			rec.setIsFromWoodRestaurant(false);
			rec.setMakeTime(20);
			rec.setRecipeType(RecipeType.MainCourse);
			rec.setPleasure(4);
			
			RecipeManager Rmng = new RecipeManager(getBaseContext());
		    //Rmng.InsertRecipe(rec);
*/		
		   
			
			Thread.sleep(2000);
			handler.post(updateGUI);
		}	
		catch(InterruptedException ex){}
	}
	 
	private void doUpdateGUI()
	{
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
	}
	
}
