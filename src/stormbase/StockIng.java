package stormbase;

public class StockIng extends Ingredient {
	Date day;
	int id=-1;
	StockIng(){
		this.day= new Date();
	}
	StockIng(Date day, double qte,int idGen){
		super(qte);
		this.day=day;
		this.id=idGen;
	}
	@Override
	public String toString(String measure) {
		return "ID:" + id +"\nDate:" + day + "\nQte.:" + qte + measure +"\n\n";
	}
}
