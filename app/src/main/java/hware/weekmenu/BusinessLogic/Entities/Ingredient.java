package hware.weekmenu.BusinessLogic.Entities;

public class Ingredient {
	
	//ingredient Name
	private String Name;
	public String getName(){return this.Name;}
	public void setName(String value){this.Name = value;}
	
	//quantiy
	private int Quantity;
	public int getQuantity(){return this.Quantity;}
	public void setQuantity(int value){this.Quantity = value;}
	
	//measure unity
	private Measures Measure;
	public Measures getMeasure(){return this.Measure;}
	public void setMeasure(Measures value){this.Measure = value;}
	
	
	

}
