package pareto;

public class MyPoint{
	double[] value;
	int dimension;
	String name;
	public MyPoint(double[] newValue, String str){
		value = newValue;
		dimension = newValue.length;
		name = str;
	}
	
	public static int compare(MyPoint p1, MyPoint p2){
		int oldsign = getSign(p1.value[0], p2.value[0]);
		int newsign = 0;
		for (int i = 1; i < p1.dimension; i++){
			newsign = getSign(p1.value[i], p2.value[i]);
			if (newsign == 0){
				continue;
			} else if (newsign != oldsign){
				return 0;//these two points are peers
			} else {
				continue;
			}
		}
		return oldsign;
	}
	
	public static int getSign(double d1, double d2){
		if (d1 > d2){
				return 1;
		} else if (d1 < d2){
				return -1;
		} else
			return 0;
	}
}