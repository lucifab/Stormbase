package stormbase;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class IngMap {
		LinkedHashMap<String, IngList> ingmap;
		int idGen=0;
		
		IngMap(){
			ingmap = new LinkedHashMap<>();
		}
		/**
		 * Checks measurement inputed. If measurement presented is the same as the one from
		 *  the existing list, returns original value, otherwise it will convert the number
		 *  to fit the measurements present in the ingredient list.
		 *
		 */
		public double checkMeasurement(double qte, String measure, String name) {
			if(this.ingmap.get(name).measure.equalsIgnoreCase(measure)) {
				return qte;
			}
			else {
				double aux=Converter.converter(qte, measure,this.ingmap.get(name).measure);
				return aux;
			}
		}		
		
		/**
		 * Adds a new ingredient to the map. If map already contains that ingredient, it
		 * will check for the measurement provided and call checkMeasurement. With adjusted
		 * values, it will then add the specified ingredient to its associated linked list.
		 * <br> Otherwise, it creates a new entry on HashMap to contain that new ingredient.
		 *
		 */
		public void addIng(String name, double qte, Date day, String measure){
			System.out.println("You added: "+name);
			if(ingmap.containsKey(name)) {
				double aux = this.checkMeasurement(qte, measure, name); //this is corrected quantity if conversion is necessary
				ingmap.get(name).addEntry(aux, day, this.idGen);
			}
			else {
				IngList aux = new IngList(measure); //creates new ingredient list for that specific ingredient
				aux.addEntry(qte, day, this.idGen); //since the list is new there's no need for object conversion
				ingmap.put(name, aux);
			}
			this.idGen+=1;
		}
		/**
		 * <b>OVERLOAD METHOD FOR RECIPES</b><br>
		 * Adds a new ingredient to the map. If map already contains that ingredient, it
		 * will check for the measurement provided and call checkMeasurement. With adjusted
		 * values, it will then add the specified ingredient to its associated linked list.
		 * <br> Otherwise, it creates a new entry on HashMap to contain that new ingredient.
		 * 
		 * <br>Does not require idGen since recipes have no expiration date
		 *
		 */
		public void addIng(String name, double qte,String measure){
			System.out.println("You added: "+name);
			if(ingmap.containsKey(name)) {
				double aux = this.checkMeasurement(qte, measure, name);
				ingmap.get(name).addEntry(aux);
			}
			else {
				IngList aux = new IngList(measure);
				aux.addEntry(qte);
				ingmap.put(name, aux);
			}
		}
		public void removeIng(String name, int id) {
			if(ingmap.containsKey(name)) {
				System.out.println("You've removed: "+name);
				ingmap.get(name).removeEntry(id);
			}
			else {
				System.out.println("Entry does not exist.");
			}
		}
		//OVERLOAD for recipe
		public void removeIng(String name) {
			if(ingmap.containsKey(name)) {
				System.out.println("You've removed: "+name);
				ingmap.remove(name);
			}
			else {
				System.out.println("Entry does not exist.");
			}
		}
	
		public void useIng(String name,double qte, String measure) {
			if(ingmap.containsKey(name)) {
				double aux = this.checkMeasurement(qte, measure, name); //this is corrected quantity if conversion is necessary
				System.out.println("Using "+aux+" "+measure+" of "+name);
				ingmap.get(name).use(aux);
			}
			else {
				System.out.println("Entry does not exist.");
			}
		}
		
		public void changeIngMeasure(String measure, String name) {
			ingmap.get(name).setMeasure(measure);
		}
		
		public String getIng(String name) {
			String str="Ingredient:"+name+"\n";
			str+= ingmap.get(name).toString();
			return str;
		}
		
		@Override
		public String toString() {
			String str="Your list is:\n-----------\n";
			
			for(Entry<String, IngList> entry : ingmap.entrySet()) {
				str+= getIng(entry.getKey());
			}
			return str;
		}

}
