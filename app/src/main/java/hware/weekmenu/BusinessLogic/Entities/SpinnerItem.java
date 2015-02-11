package hware.weekmenu.BusinessLogic.Entities;

public class SpinnerItem {

	
	   public SpinnerItem(int ID, String Value)
	   {
		   this.ItemId = ID;
		   this.ItemLabel = Value;
	   }
	
		private int ItemId;
		public int getId(){return this.ItemId;}
		public void setId(int value){this.ItemId = value;}
		
		//recipe name
		private String ItemLabel;
		public String getLabel(){return this.ItemLabel;}
		public void setLabel(String value){this.ItemLabel = value;}
		
		@Override
		public String toString()
		{
			return this.getLabel();
		}

	
	
}
