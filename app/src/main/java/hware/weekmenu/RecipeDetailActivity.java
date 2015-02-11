package hware.weekmenu;

import hware.weekmenu.BusinessLogic.Entities.Recipe;
import hware.weekmenu.BusinessLogic.Entities.Ingredient;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class RecipeDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_detail);
		// Show the Up button in the action bar.
		setupActionBar();
		
		//display data
		FillRecipeDetail();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	/* menu region */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recipe_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.recipe_edit:
			//start edit action
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}

	/* end menu region*/
	
	
	private void FillRecipeDetail()
	{
		Recipe SelectedRecipe = new Recipe();
		//get item passed to action
		Bundle RecId = getIntent().getExtras();
		int value = RecId.getInt("ID");
		
		
		
		
		//filling form
		TextView rname =  (TextView)this.findViewById(R.id.RecipeDetail_Name);
		//rname.setText(SelectedRecipe.getName());
		rname.setText(String.valueOf(value));
		//TextView rType = (TextView)this.findViewById(R.id.RecipeDetail_Type);
		//rType.setText(SelectedRecipe.getRecipeType().toString());
		
		 //use rating bar in readonly mode
		//TextView rPleas = (TextView)this.findViewById(R.id.RecipeDetail_Pleas);
		//rPleas.setText(SelectedRecipe.getPleasure().toString());
		
		//TextView rDescr = (TextView)this.findViewById(R.id.RecipeDetail_Descr);
		//rDescr.setText(SelectedRecipe.getDescr());
		
		//set image to display
		
		
		
		
		
		
	}
	
	
	
}
