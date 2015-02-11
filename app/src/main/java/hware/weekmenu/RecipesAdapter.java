package hware.weekmenu;

import java.util.List;

import hware.weekmenu.BusinessLogic.Entities.Recipe;
import hware.weekmenu.BusinessLogic.Entities.RecipeType;
import hware.weekmenu.BusinessLogic.Managers.RecipeManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.RelativeLayout;

public class RecipesAdapter extends ArrayAdapter<Recipe> {
	
	int resource;
	public RecipesAdapter(Context context,int resource, List<Recipe> recipes)
	{
		super(context,resource,recipes);
		this.resource = resource;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		RelativeLayout RecipesList;
		Recipe recipe = getItem(position);
		
		if(convertView == null)
		{
			RecipesList = new RelativeLayout(getContext());
			LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);	
			inflater.inflate(this.resource, RecipesList, true);
		}
		else
		{
			RecipesList = (RelativeLayout)convertView;
		}
		//get view controls
		ImageView recipeIcon = (ImageView)RecipesList.findViewById(R.id.recipe_icon);
		ImageView recipePleasuer = (ImageView)RecipesList.findViewById(R.id.pleasureIcon);
		TextView recipeName = (TextView)RecipesList.findViewById(R.id.recipe_name);
		
		Context ctx = getContext();
		int RecTypeId = ctx.getResources().getIdentifier(recipe.getRecipeTypeName(),"string",ctx.getPackageName());
		
		TextView recipeType = (TextView)RecipesList.findViewById(R.id.recipe_type);
		//set item data
		recipeName.setText(recipe.getName());
		recipeType.setText(ctx.getResources().getString(RecTypeId));
		
		//setting image by rating
		recipePleasuer.setImageResource(RecipeManager.GetRecipePleasureIcon(recipe.getPleasure()));
        //setting recipe type image  --> implement type switch	
		RecipeType rt =  recipe.getRecipeType();
		recipeIcon.setImageResource(RecipeManager.GetRecipeImage(rt));
		return RecipesList;
	}
	
	

}
