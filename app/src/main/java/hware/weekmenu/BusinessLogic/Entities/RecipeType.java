package hware.weekmenu.BusinessLogic.Entities;

import java.util.HashMap;
import java.util.Map;

public enum RecipeType {

	Starter(0),
	Appetizer(1),
	MainCourse(2),
	MainCourse2(3),
	Dessert(4);
	
	
	private final int value;
	private static Map<Integer, RecipeType> map = new HashMap<Integer, RecipeType>();

    static {
        for (RecipeType RecEnum : RecipeType.values()) {
            map.put(RecEnum.value, RecEnum);
        }
    }
	
	
	private RecipeType(int Val)
	{
		this.value = Val;
	}
	
	public int getValue(){return value;}
	
	  public static RecipeType valueOf(int value) {
	        return map.get(value);
	    }
}
