package hware.weekmenu;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import hware.weekmenu.BusinessLogic.Entities.*;
import hware.weekmenu.BusinessLogic.Managers.RecipeManager;
import hware.weekmenu.BusinessLogic.Managers.SpinnerManager;
import hware.weekmenu.db.DataProvider;

public class NewRecipeActivity extends Activity {

	MenuItem SaveRecipeItem = null;
	ArrayAdapter<SpinnerItem> MeasuresSpnAdapter;
	int IngNameBaseId = 100;
	int IngQuantityBaseId = 200;
	int IngQuantityTypeBaseId = 300;
	int IngDelBtnBaseId = 400;
	int IngredientsAdded =0;
	int LastBaseId = 100;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_recipe);	
		//navigate back to recipes list
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		//populate activity spinners
		Spinner TypeSpin = (Spinner) findViewById(R.id.rec_spinnerType);
		SpinnerManager SpinnerMng = new SpinnerManager(getBaseContext());
		ArrayAdapter<SpinnerItem> SpinnerAdapter = new ArrayAdapter<SpinnerItem>(this,android.R.layout.simple_spinner_item,SpinnerMng.GetRecipeTypes());
		SpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		TypeSpin.setAdapter(SpinnerAdapter);
		
		//populate ingredients Type
		MeasuresSpnAdapter = new ArrayAdapter<SpinnerItem>(this,android.R.layout.simple_spinner_dropdown_item,SpinnerMng.GetMeasures());
		Spinner MeasSpin = (Spinner)findViewById(R.id.ing_quantityType);
		MeasSpin.setAdapter(MeasuresSpnAdapter);
		
		//adding listener to add ingredient button
		Button AddIngBtn = (Button) findViewById(R.id.ing_btnadd);
		AddIngBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				GenerateIngredientControls();
			}
		});
		
		//add text inserted listener to title textview
		TextView TbxName = (TextView)findViewById(R.id.rec_tbxname);
		TbxName.addTextChangedListener(new TextWatcher()
		{
			public void afterTextChanged(Editable s) {
				if(SaveRecipeItem != null)
				{
					if(s.length() > 3)
						SaveRecipeItem.setVisible(true);
					else
						SaveRecipeItem.setVisible(false);
				}
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
		});
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_recipe, menu);
		this.SaveRecipeItem =  menu.findItem(R.id.Save_Recipe);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.Save_Recipe:
			//saving Recipe
			SaveRecipe();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
	//save Recipe
	private void SaveRecipe()
	{

		//insert  recipe into Db
		Recipe rec = new Recipe();
		rec.setIsFromWoodRestaurant(false);
		rec.setDescr(((TextView)findViewById(R.id.rec_tbxdescr)).getText().toString());
		rec.setName(((TextView)findViewById(R.id.rec_tbxname)).getText().toString());
		rec.setMakeTime(Integer.parseInt(((TextView)findViewById(R.id.rec_tbxmaketime)).getText().toString()));
		
		SpinnerItem recType  =  (SpinnerItem)((Spinner)findViewById(R.id.rec_spinnerType)).getSelectedItem();
		rec.setRecipeType(RecipeType.valueOf(recType.getId()));
		rec.setPleasure(((RatingBar)findViewById(R.id.rec_rating)).getRating());
		
		//insert ingredients
		ArrayList<Ingredient> RecIngredients = new ArrayList<Ingredient>();
		
		//getting default ingredient
		RecIngredients.add(GetIngredient(R.id.ing_name,R.id.ing_quantity,R.id.ing_quantityType));
		//reading other ingredient
		for(int i=LastBaseId; i>=100;i--)
		{
			Ingredient newIng = GetIngredient(i,i+100,i+200);
			if(newIng != null)
				RecIngredients.add(newIng);
		}
		
		rec.setIngredients(RecIngredients);
		
		RecipeManager Rmng = new RecipeManager(getBaseContext());
	    long RecId = Rmng.InsertRecipe(rec);
		
		Toast.makeText(getBaseContext(), R.string.RecipeSaved, Toast.LENGTH_SHORT).show();
		//go to list or recipe detail
		Intent scheduleintent = new Intent(NewRecipeActivity.this,RecipesActivity.class);
		startActivity(scheduleintent);
		
	}
	
	private Ingredient GetIngredient(int recNameId, int QuantityId, int QuantityTypeId)
	{
		
		TextView ingName = (TextView)findViewById(recNameId);
		TextView ingQuantity = (TextView)findViewById(QuantityId);
		Spinner ingQuantityType = (Spinner)findViewById(QuantityTypeId);
		if(ingName != null)
		{
			Ingredient ingr = new Ingredient();
			ingr.setName(ingName.getText().toString());
			ingr.setQuantity(Integer.parseInt(ingQuantity.getText().toString()));
			ingr.setMeasure(Measures.valueOf(((SpinnerItem)ingQuantityType.getSelectedItem()).getId()));
			return ingr;
		}
		else
			return null;
		
	}
	
	//generate ingredients controls for name, quantity and type
	private void GenerateIngredientControls()
	{
		int CurrNameId = IngNameBaseId;
		int CurrQuantityId = IngQuantityBaseId;
		int CurrQuantityTypeId = IngQuantityTypeBaseId;
		int CurrDelBtnId = IngDelBtnBaseId;
		
		EditText IngNameDefault = (EditText) findViewById(R.id.ing_name);
		EditText IngQuantityDefault = (EditText) findViewById(R.id.ing_quantity);
		Button AddBtn = (Button)findViewById(R.id.ing_btnadd);
		
		
		int BelowTo = IngNameBaseId-1;
		if(IngredientsAdded <=0 || BelowTo < 100)
			BelowTo = IngNameDefault.getId();
		
		
		RelativeLayout NewRecipeLayout = (RelativeLayout)findViewById(R.id.rec_relcontainer);
		
		EditText IngNameTbx = new EditText(this);
		EditText IngQuantityTbx = new EditText(this);
		Spinner IngQuantityTypeSpn = new Spinner(this);
		Button IngDelBtn = new Button(this);
		
		IngNameTbx.setId(CurrNameId);
		IngNameTbx.setWidth(IngNameDefault.getWidth());
		
		IngQuantityTbx.setId(CurrQuantityId);
		IngQuantityTbx.setWidth(IngQuantityDefault.getWidth());
		
		IngQuantityTypeSpn.setId(CurrQuantityTypeId);
		IngQuantityTypeSpn.setAdapter(MeasuresSpnAdapter);
		
		IngDelBtn.setId(CurrDelBtnId);
		IngDelBtn.setText(R.string.IngDelBtn);
		
		IngDelBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DeleteIngredientControls(arg0);
			}
		});
		
		RelativeLayout.LayoutParams AddBtnParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		AddBtnParams.addRule(RelativeLayout.BELOW, CurrNameId);
		
		RelativeLayout.LayoutParams NameParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		NameParams.addRule(RelativeLayout.BELOW, BelowTo);
	
		RelativeLayout.LayoutParams IngQuantityParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		IngQuantityParams.addRule(RelativeLayout.BELOW, BelowTo);
		IngQuantityParams.addRule(RelativeLayout.RIGHT_OF, CurrNameId);
		
		RelativeLayout.LayoutParams IngQuantityTypeParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		IngQuantityTypeParams.addRule(RelativeLayout.BELOW, BelowTo);
		IngQuantityTypeParams.addRule(RelativeLayout.RIGHT_OF, CurrQuantityId);
		
		RelativeLayout.LayoutParams IngDelBtnParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		IngDelBtnParams.addRule(RelativeLayout.BELOW, BelowTo);
		IngDelBtnParams.addRule(RelativeLayout.RIGHT_OF, CurrQuantityTypeId);
		
	    NewRecipeLayout.addView(IngNameTbx,NameParams);
	    NewRecipeLayout.addView(IngQuantityTbx,IngQuantityParams);
	    NewRecipeLayout.addView(IngQuantityTypeSpn,IngQuantityTypeParams);
	    NewRecipeLayout.addView(IngDelBtn,IngDelBtnParams);
		
	    AddBtn.setLayoutParams(AddBtnParams);
	   
		IngNameBaseId++;
		IngQuantityBaseId++;
		IngQuantityTypeBaseId++;
		IngDelBtnBaseId++;
		IngredientsAdded++;
		LastBaseId++;
	}
 
	//delete an ingredient
	private void DeleteIngredientControls(View btn)
	{
		 int BelowTo=0;
		 RelativeLayout NewRecipeLayout = (RelativeLayout)findViewById(R.id.rec_relcontainer);
		
		 //getting ingredient row and deleting controls
		 Button AddBtn = (Button)findViewById(R.id.ing_btnadd);
		 EditText IngNameDefault = (EditText) findViewById(R.id.ing_name);
		 
		 EditText IngNameTbx = (EditText)findViewById(btn.getId()-300);
		 EditText IngQuantityTbx = (EditText)findViewById(btn.getId()-200);
		 Spinner IngQuantitySpn = (Spinner)findViewById(btn.getId()-100);
		 EditText PrevIngNameTbx =null;
		 
		 //adjust view layout to fit the deleted row hole
		 EditText NextIngNameTbx = null;
		 EditText NextIngQuantityTbx = null;
		 Spinner NextIngQuantitySpn = null;
		 Button NextIngDelBtn = null;
		 boolean found = false;
		 int controlIdDelta = 1; 
		 while(!found)
		 {
			 int controlid = IngNameTbx.getId()+controlIdDelta;
			 NextIngNameTbx = (EditText)findViewById(controlid);
			 if(NextIngNameTbx == null && controlid > IngNameBaseId )
			 {
				 found = true;
			 }
			 else if(NextIngNameTbx == null)
				 controlIdDelta++;
			 else
			 {
				 NextIngQuantityTbx = (EditText)findViewById(IngQuantityTbx.getId()+controlIdDelta);
				 NextIngQuantitySpn = (Spinner)findViewById(IngQuantitySpn.getId()+controlIdDelta);
				 NextIngDelBtn  = (Button) findViewById(btn.getId()+controlIdDelta);
				 found =true;
			 }
		 }
		 
		 BelowTo = IngNameTbx.getId()-1;
		 if(IngredientsAdded <=0 || BelowTo  < 100)
			 BelowTo = IngNameDefault.getId();
		 else
		 {
			 PrevIngNameTbx = (EditText)findViewById(BelowTo);
			 while(PrevIngNameTbx == null && BelowTo >= 100)
			 {
				 PrevIngNameTbx = (EditText)findViewById(--BelowTo);
			 }
			 if(BelowTo < 100)
				 BelowTo = IngNameDefault.getId();	 
		 }
		 
		 RelativeLayout.LayoutParams AddBtnParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		 if(NextIngNameTbx != null)
		 {
			 RelativeLayout.LayoutParams NameParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			 NameParams.addRule(RelativeLayout.BELOW, BelowTo);
			 NextIngNameTbx.setLayoutParams(NameParams);
			 
			 RelativeLayout.LayoutParams IngQuantityParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			 IngQuantityParams.addRule(RelativeLayout.BELOW, BelowTo);
			 IngQuantityParams.addRule(RelativeLayout.RIGHT_OF, NextIngNameTbx.getId());
			 NextIngQuantityTbx.setLayoutParams(IngQuantityParams);
			 
			 RelativeLayout.LayoutParams IngQuantityTypeParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			 IngQuantityTypeParams.addRule(RelativeLayout.BELOW, BelowTo);
			 IngQuantityTypeParams.addRule(RelativeLayout.RIGHT_OF, NextIngQuantityTbx.getId());
			 NextIngQuantitySpn.setLayoutParams(IngQuantityTypeParams);
			 
			 RelativeLayout.LayoutParams IngDelBtnParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			 IngDelBtnParams.addRule(RelativeLayout.BELOW, BelowTo);
			 IngDelBtnParams.addRule(RelativeLayout.RIGHT_OF, NextIngQuantitySpn.getId());
			 NextIngDelBtn.setLayoutParams(IngDelBtnParams);
			 
			 AddBtnParams.addRule(RelativeLayout.BELOW, NextIngNameTbx.getId());
			 
		 }
		 else
		 {
			 AddBtnParams.addRule(RelativeLayout.BELOW, BelowTo); 
			 
			 if(BelowTo ==IngNameDefault.getId())
				 BelowTo = 100;
			 IngNameBaseId = BelowTo;
			 IngQuantityBaseId = BelowTo + 100;
			 IngQuantityTypeBaseId = BelowTo + 200;
			 IngDelBtnBaseId = BelowTo + 300;
			
		 }
		  AddBtn.setLayoutParams(AddBtnParams);
		  NewRecipeLayout.removeView(IngNameTbx);
		  NewRecipeLayout.removeView(IngQuantityTbx);
		  NewRecipeLayout.removeView(IngQuantitySpn);
		  NewRecipeLayout.removeView(btn);
		  IngredientsAdded --;
		  Toast.makeText(getBaseContext(), R.string.IngRemoved, Toast.LENGTH_SHORT).show();
	}

}
