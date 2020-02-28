package stormbase;

public class Converter {

	public static int stringTransform(String name) {
		int i;
		switch(name) {
		//liquids
		case "mL":
			i=0;
			break;
		case "L":
			i=1;
			break;
		case "floz":
			i=2;
			break;
			//solids
		case "tbsp":
			i=3;
			break;
		case "tsp":
			i=4;
			break;
		case "oz":
			i=5;
			break;
		case "g":
			i=6;
			break;
		case "kg":
			i=7;
			break;
		case "lb":
			i=8;
			break;
		case "mg":
			i=9;
			break;
		default:
			i=-1;
		}
		return i;
	}
	
	public static boolean alertMsg(int i,int j) {
		boolean ans=false;
		if((i<3)&&(j<3)) {
			ans=true;
		}
		else if((i>=3)&&(j>=3)) {
			ans=true;
		}
		else {
			System.out.println("Oopsie, no converting liquid/solid.");
		}
		return ans;
	}
	
	public static double converter(double qte, String measure, String convertTo) {
		int i,j;
		double res=1;
		double[] converterLiq = {1,1000,33};
		double[] converterSolid = {15,5,28,1,1000,453,0.001};	
		
		i=stringTransform(measure);
		j=stringTransform(convertTo);
		
		if(alertMsg(i,j)) {		
			if(i<3) {
				res=qte*converterLiq[i]/converterLiq[j];
			}
			else {
				i-=3;
				j-=3;
				res=qte*converterSolid[i]/converterSolid[j];
			}
		}
		return res;
	}
}
