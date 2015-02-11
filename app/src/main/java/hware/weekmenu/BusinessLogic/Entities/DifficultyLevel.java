package hware.weekmenu.BusinessLogic.Entities;

public enum DifficultyLevel {

	EASY(0),
	MEDIUM(1),
	HARD(2),
	VERYHARD(3);
	
private final int value;
	
	private DifficultyLevel(int Val)
	{
		this.value = Val;
	}
	
	public int getValue(){return value;}
}


