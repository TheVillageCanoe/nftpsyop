public class Branch {
	String folderName;
	double percentChance;

	public Branch()
	{
		this.folderName = "NULL";
		this.percentChance = 1.0;
	}

	public Branch(String folderName, double percentChance)
	{
		this.folderName = folderName;
		this.percentChance = percentChance;
	}

	public String toString(){
		return this.folderName + " - " + this.percentChance*100 + "%";
	}
}
