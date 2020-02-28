package stormbase;



public class Execution {

	public static void main(String[] args) {
		IngMap StormStock = new IngMap();
		//IngMap recipe1 = new IngMap();
		Date myDay = new Date();
		
		

		StormStock.addIng("Cayenne", 500, myDay, "g");
		StormStock.addIng("Cayenne", 1, myDay, "kg");
		StormStock.addIng("Cayenne", 2, myDay, "kg");
		System.out.println(StormStock.toString());
		StormStock.useIng("Cayenne", 1700, "g");
		System.out.println(StormStock.toString());
		
		/*
		recipe1.addIng("banana", 5,"units");
		recipe1.addIng("mustard", 7,"L");
		recipe1.addIng("mayo", 3,"L");
		recipe1.addIng("Mayo", 3,"L");
		System.out.println(recipe1.toString());
		recipe1.removeIng("mayo");
		System.out.println(recipe1.toString());
		*/
		
		
		
		
		
	}

}
