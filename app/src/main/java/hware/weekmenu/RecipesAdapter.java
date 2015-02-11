package hware.weekmenu;

import java.util.List;

import hware.weekmenu.BusinessLogic.Entities.Recipe;
import hware.weekmenu.BusinessLogic.Entities.RecipeType;
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
		float pleasure = recipe.getPleasure();
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
		recipePleasuer.setImageResource(pleasureImg);
        //setting recipe type image  --> implement type switch	
		RecipeType rt =  recipe.getRecipeType();
		int recipeImg=-1;
		if(rt ==  RecipeType.Appetizer){recipeImg = R.drawable.antipasti;}
		else if (rt == RecipeType.Dessert ){ recipeImg = R.drawable.dolci;}
		else if(rt ==  RecipeType.MainCourse){recipeImg = R.drawable.secondi;}
		else if (rt == RecipeType.MainCourse2){recipeImg = R.drawable.piattounico;}
		else if (rt == RecipeType.Starter){recipeImg = R.drawable.primi;}
		
		recipeIcon.setImageResource(recipeImg);	
		return RecipesList;
	}
	
	

}
