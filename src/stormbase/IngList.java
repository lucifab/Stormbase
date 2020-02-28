package stormbase;

import java.util.LinkedList;
import java.util.ListIterator;

public class IngList {
	LinkedList<Ingredient> list;
	String measure;
	private double total=0;

    IngList(){
        this.list = new LinkedList<>();
    }
    IngList(String measure){
        this.list = new LinkedList<>();
        this.measure =  measure;
    }
    
    //copy method
    IngList(IngList myList){
    	this.list=myList.list;
    	this.measure=myList.measure;
    }

    
    //This method returns index if it's given the item ID; useful only for RECIPE
    public int indexOfID(int itemid){
        ListIterator<Ingredient> iterate = list.listIterator();
        while(iterate.hasNext()){
        	StockIng aux = (StockIng)iterate.next();
            if(aux.id==itemid){
                return iterate.previousIndex();
            }
        }
        return -2;
    }

    //This method adds the STOCK ingredient to the list
    public void addEntry(double qte, Date day,int idGen){
        StockIng aux = new StockIng(day,qte,idGen);
        this.list.add(aux);
        System.out.println(aux.toString(this.measure));
        this.total+=qte;
    }
    
    //OVERLOAD method for adding RECIPE ingredients to the list
    public void addEntry(double qte){
    	if(list.isEmpty()) {
    		Ingredient Ing = new Ingredient(qte);
            this.list.add(Ing);
            System.out.println(Ing.toString(this.measure));
        }
    	else {
    		Ingredient Ing = new Ingredient(qte+list.get(0).qte);
            list.set(0, Ing);
            System.out.println(Ing.toString(this.measure));
    	}
    	this.total+=qte;
    }
    
    //This method removes an ingredient of STOCK if given its item number
    public void removeEntry(int itemid) {
    	int index = indexOfID(itemid);
    	
    	if(index==-2) {
    		System.out.println("This item does not exist on list.");
    	}
    	else {
    		System.out.println("Item removal successful.");
    		list.remove(index);
    		this.total-=this.list.get(index).qte;
    	}	
    }
    //Reduces quantity of item if given ID. STOCK only
    public void reduceEntry(int itemid, double qte) {
    	int index = indexOfID(itemid);
    	if(index==-2) {
    		System.out.println("This item does not exist on list.");
    	}
    	else {
    		Ingredient aux = list.get(index);
    		if(aux.qte>qte) {
    			aux.qte-=qte;list.set(index, aux);
    			System.out.println("You've reduced the item.");
    			this.total-=qte;
    		}
    		else {
    			System.out.println("Item cannot be reduced. Qte. being reduced higher than actual qte.");
    		}
    	}
    	
    }
    public void reduceEntryINDEX(int index, double qte) {
    	Ingredient aux = list.get(index);
    	if(aux.qte>qte) {
    		aux.qte-=qte;
    		//list.set(index, aux);
    		System.out.println("You've reduced the item.");
    		this.total-=qte;
    	}
    	else if(aux.qte==qte) {
    		this.list.remove(index);
    		this.total-=qte;
    	}
    	else {
    		System.out.println("Item cannot be reduced. Qte. being reduced higher than actual qte.");
    	}


    }
    public void use(double qte) {
    	if(this.total>qte) {
    		double aux=qte;
    		ListIterator<Ingredient> iterate = list.listIterator();
    		int i=0;
            while(aux!=0){
            	double dummy=iterate.next().qte;
            	System.out.println("Value we will detract from:"+dummy);
            	
            	if(dummy<=aux) {
            		System.out.println("CASE "+dummy+" < "+aux);
            		System.out.println("Reduce now by:"+dummy);
            		aux-=iterate.previous().qte;
            		this.total-=dummy;
            		System.out.println("Remaining to be reduced:"+aux);
            		
            		iterate.next();
            		i++;
            	}
            	else {
            		
            		this.reduceEntryINDEX(i, aux);
            		break;
            	}
            	
            }
            for(;i>0;i--) {
            	System.out.println("Reduce now by:"+aux);
            	this.list.remove();
            }
    	}
    	else {
    		System.out.println("Amount to be used greater than stock.");
    	}
    }
    
    
    public void setMeasure(String m) {
    	if(!this.list.isEmpty()) {
    		ListIterator<Ingredient> iterate = list.listIterator();
            while(iterate.hasNext()){
            	iterate.next().qte= Converter.converter(iterate.previous().qte, this.measure, m);
            	iterate.next();
            }
    	}
    	this.total=Converter.converter(this.total, this.measure, m);
    	this.measure=m;
    }
    public String getMeasure() {
    	return this.measure;
    }

	@Override
	//Method toString to print the entire list
	public String toString() {
		ListIterator<Ingredient> iterate = list.listIterator();
		String res="Total: "+this.total+" "+this.measure+"\n-----\nEntries:\n";
        while(iterate.hasNext()){
        	res+=iterate.next().toString(this.measure);
        }
		return res;
	}

}
