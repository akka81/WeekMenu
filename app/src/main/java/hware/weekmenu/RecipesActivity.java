package hware.weekmenu;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import hware.weekmenu.BusinessLogic.Entities.*;
import hware.weekmenu.BusinessLogic.Managers.*;

public class RecipesActivity extends Activity implements
		ActionBar.OnNavigationListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipes);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		// Set up the action bar to show a dropdown list. --> to change with supported libraries

		//getting recipes list from DataBase
		ListView RecipesList = (ListView)findViewById(R.id.RecipesList);
		RecipesList.setOnItemClickListener(RecipesItem_Click);
		
		//get recipes Manager 
		RecipeManager recipeMng = new RecipeManager(getBaseContext());
		ArrayList<Recipe> AllRecipes = recipeMng.GetRecipes();
		
		if(!AllRecipes.isEmpty())
		{
			//hide no recipes text view and show recipes
			TextView NoResultMessage = (TextView)findViewById(R.id.RecipesEmpty);
			NoResultMessage.setVisibility(View.GONE);
			RecipesAdapter recipesAdapter = new RecipesAdapter(this,R.layout.item_recipe,AllRecipes);
			RecipesList.setAdapter(recipesAdapter);
		}
	}

	/**
	 * Backward-compatible version of {@link ActionBar#getThemedContext()} that
	 * simply returns the {@link android.app.Activity} if
	 * <code>getThemedContext</code> is unavailable.
	 */
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	private Context getActionBarThemedContextCompat() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			return getActionBar().getThemedContext();
		} else {
			return this;
		}
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recipes, menu);
		
		//Action Bar Search Function
	    SearchManager SearchMng = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
		SearchableInfo SInfo = SearchMng.getSearchableInfo(getComponentName());			
		SearchView SView = (SearchView)findViewById(R.id.SearchView);
		SView.setSearchableInfo(SInfo);
	
		MenuItem mItem = menu.add(0,Menu.FIRST , Menu.NONE, R.string.menu_seach);
		mItem.setActionView(SView);
		mItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		//mItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);		
		return true;
	}

	@Override
	public boolean onNavigationItemSelected(int position, long id) {
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.menu_newrecipe:
			Intent newRec = new Intent(RecipesActivity.this,NewRecipeActivity.class);
			startActivity(newRec);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	//go to Recipe Detail activity
	private OnItemClickListener RecipesItem_Click = new OnItemClickListener()
	{
		public void onItemClick(AdapterView<?> parent,View view, int pos, long id)
		{
			 Recipe selectedrecipe = (Recipe)parent.getAdapter().getItem(pos); 
			 Intent DetailIntent = new Intent(RecipesActivity.this,RecipeDetailActivity.class);
			 Bundle recid = new Bundle();
			 recid.putInt("ID", selectedrecipe.getID());
			 DetailIntent.putExtras(recid);
			 startActivity(DetailIntent);
				
		}
	};
	
	
	

}
