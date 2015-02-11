package hware.weekmenu;

import java.util.ArrayList;

import hware.weekmenu.BusinessLogic.Entities.Recipe;
import hware.weekmenu.BusinessLogic.Managers.RecipeManager;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RecipesSearch extends ListActivity implements ActionBar.OnNavigationListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		//getting Intent
		parseIntent(getIntent());
	}

	@Override
	protected void onNewIntent(Intent intent)
	{
		super.onNewIntent(intent);
		parseIntent(intent);
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
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	private void parseIntent(Intent launchIntent)
	{
		
		if(Intent.ACTION_SEARCH.equals(launchIntent.getAction()))
		{
			String SearchQuery = launchIntent.getStringExtra(SearchManager.QUERY);
			
			//search recipes into Database
			RecipeManager recipeMng = new RecipeManager(getBaseContext());
			ArrayList<Recipe> res = recipeMng.GetRecipesBySearch(SearchQuery);
		   
			//displaying Results
			ArrayList<String> resArray = new ArrayList<String>();
			resArray.add(res.get(0).getName());
			
			ArrayAdapter<String> resultAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,resArray);
		
			setListAdapter(resultAdapter);
			
		}
		
	}

}
