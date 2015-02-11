package hware.weekmenu.BusinessLogic.Entities;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
	
	public Recipe()
	{
		this.Ingredients = new ArrayList<Ingredient>();
	}
	
	//ID
	private int ID;
	public int getID(){return this.ID;}
	public void setID(int value){this.ID = value;}
	
	//recipe name
	private String Name;
	public String getName(){return this.Name;}
	public void setName(String value){this.Name = value;}
	
	//recipe description
	private String Descr;
	public String getDescr(){return this.Descr;}
	public void setDescr(String value){this.Descr = value;}
	
	
	//reciper pleasure descr
	private float PleasureLevel;
	public float getPleasure(){return this.PleasureLevel;}
	public void setPleasure(float pleasureName){this.PleasureLevel = pleasureName;}
	
	//ingredients list
	private ArrayList<Ingredient> Ingredients;
	public ArrayList<Ingredient> getIngredients(){return this.Ingredients;}
	public void setIngredients(ArrayList<Ingredient> ingredients){this.Ingredients = ingredients;}
	
	//Downloaded from Wood Restaurant
	private boolean IsFromWoodRestaurant;
	public boolean getIsFromWoodRestaurant(){return this.IsFromWoodRestaurant;}
	public void setIsFromWoodRestaurant(boolean value){this.IsFromWoodRestaurant = value;}
	
	private RecipeType RecipeType;
	public RecipeType getRecipeType(){return this.RecipeType;}
	public void setRecipeType(RecipeType Value){this.RecipeType = Value;}
	
	//recipeType descr
	private String RecipeTypeName;
	public String getRecipeTypeName() {return this.RecipeTypeName;}
	public void setRecipeTypeName(String recipeTypeName){this.RecipeTypeName = recipeTypeName;}
	
	
	private int MakeTime;
	public int getMakeTime(){return this.MakeTime;}
	public void setMakeTime(int value){this.MakeTime = value;}
 	

}
