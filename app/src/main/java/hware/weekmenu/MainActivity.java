package hware.weekmenu;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends Activity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.application_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		super.onOptionsItemSelected(item);
		
		//get the item selected and load activity
		switch(item.getItemId())
		{
		case(R.id.menu_recipes):
			//load recipes list activity
			Intent recipesintent = new Intent(MainActivity.this,RecipesActivity.class);
			startActivity(recipesintent);
			return true;
		case(R.id.menu_schedule):
			//load Schedule Activity	
			Intent scheduleintent = new Intent(MainActivity.this,RecipesActivity.class);
			startActivity(scheduleintent);
			return true;
		default: return false;
		}	
	}

	//avoid falling into loader activity
	@Override
	public void onBackPressed(){/*do nothing*/}

}
