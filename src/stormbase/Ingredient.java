package stormbase;

public class Ingredient {
	double qte;
	 //-1 indicates recipe ing, otherwise its stocking

	Ingredient(){
		this.qte= 0;
	}

	//method for recipes
	Ingredient(double qte){
		this.qte= qte;
	}
	//method for stocks

	public String toString(String measure) {
		return "Qte: "+qte+ measure+"\n\n";
	}
	
}