package hware.weekmenu;

import hware.weekmenu.BusinessLogic.Entities.Recipe;
import hware.weekmenu.BusinessLogic.Entities.Ingredient;
import hware.weekmenu.BusinessLogic.Managers.RecipeManager;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class RecipeDetailActivity extends FragmentActivity {

	int recipeId;

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
				NavUtils.navigateUpFromSameTask(this);
				return true;

			case R.id.recipe_edit:
				//start edit action


				return true;

			case R.id.recipe_delete:
				//display confirm dialog

				return true;

		}
		
		return super.onOptionsItemSelected(item);
	}

	/* end menu region*/
	
	
	private void FillRecipeDetail()
	{
		//get item passed to action
		Bundle RecId = getIntent().getExtras();
		this.recipeId = RecId.getInt("ID");

        //get selected recipe from DB
        RecipeManager rMng = new RecipeManager(getBaseContext());
        Recipe recipe = rMng.GetRecipeById(this.recipeId);


		//filling form
		TextView rname =  (TextView)this.findViewById(R.id.RecipeDetail_Name);
		rname.setText(recipe.getName());

		TextView rType = (TextView)this.findViewById(R.id.RecipeDetail_Type);
        Context ctx = getBaseContext();
        int RecTypeId = ctx.getResources().getIdentifier(recipe.getRecipeTypeName(),"string",ctx.getPackageName());
		rType.setText(ctx.getResources().getString(RecTypeId));

		TextView rDescr = (TextView)this.findViewById(R.id.RecipeDetail_Descr);
		rDescr.setText(recipe.getDescr());

        TextView rTime = (TextView)this.findViewById(R.id.RecipeDetail_Time);
        rTime.setText(String.valueOf(recipe.getMakeTime()));

        TextView rIngredients = (TextView) this.findViewById(R.id.RecipeDetail_Ingredients);
        rIngredients.setText(recipe.StringifyIngredients());

		//set image to display
        ImageView rIcon = (ImageView)this.findViewById(R.id.RecipeDetailImage);
        rIcon.setImageResource(RecipeManager.GetRecipeImage(recipe.getRecipeType()));
        //set pleasure level
        ImageView rPleasure = (ImageView)this.findViewById((R.id.RecipeDetail_PleasImg));
        rPleasure.setImageResource(RecipeManager.GetRecipePleasureIcon(recipe.getPleasure()));

	}
	

    private void DeleteRecipe()
    {
        //ask confirm

        //delete

        //toast deletion message
    }

    private void ToUpdateView()
    {
        //go to edit view, with recipe id
		Intent EditRecipe = new Intent(RecipeDetailActivity.this,NewRecipeActivity.class);
		Bundle recid = new Bundle();
		recid.putInt("ID", this.recipeId);
		recid.putBoolean("IsEdit",true);
		EditRecipe.putExtras(recid);
		startActivity(EditRecipe);
    }
	
}
