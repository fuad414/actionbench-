package pareto;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import pareto.MyPoint;

public class MyTree {
	public ArrayList<MyPoint> peer;
	public MyTree leftChild;
	public MyTree rightChild;
	
	//initialize
	public MyTree(ArrayList<MyPoint> data){
		this.peer = new ArrayList<MyPoint>(data);
		reform(this);
	}
	
	
	public void reform(MyTree tr){	
		ArrayList<MyPoint> leftPoints = new ArrayList<MyPoint>();
		ArrayList<MyPoint> rightPoints = new ArrayList<MyPoint>();
		if(tr == null || tr.peer.size() ==0){
			return;
		}

		/*for (int j = 0; j < tr.peer.size(); j++){
			System.out.print(tr.peer.get(j).name);
		}*/
		if(tr.peer.size() != 0){
			int diff = 0;
			MyPoint beacon = tr.peer.get(0);
			for (int i = 1; i < tr.peer.size(); i++){
				diff = MyPoint.compare(tr.peer.get(i), beacon);
				if (diff == 1){
					leftPoints.add(tr.peer.get(i));
					tr.peer.remove(i);
					i = i - 1;
				} else if (diff == -1){
					rightPoints.add(tr.peer.get(i));
					tr.peer.remove(i);
					i = i - 1;
				}
			}
			leftChild = new MyTree(leftPoints);
			rightChild = new MyTree(rightPoints);
		}
	}
	
	public static void printTreeInOrder(MyTree tr){
		if (tr == null || tr.peer.size()==0){
			return;
		}		
		
		printTreeInOrder(tr.leftChild);
		printPoints(tr.peer);
		printTreeInOrder(tr.rightChild);
		
	}
	
	public static void printPoints(ArrayList<MyPoint> points){
		if (points.size()==0){
			return;
		}	
		for (int i = 0; i < points.size(); i++){
			System.out.print(points.get(i).name + " ");
		}
		System.out.println();
	}
	
	public static ArrayList<MyPoint> readFile(String fileName) throws IOException{
		FileInputStream inputStream = new FileInputStream(fileName);
        Scanner scanner = new Scanner(inputStream);
        DataInputStream in = new DataInputStream(inputStream);
        BufferedReader bf = new BufferedReader(new InputStreamReader(in));
        String line = "";
		int lineCount = 0;
		ArrayList<MyPoint> sample = new ArrayList<MyPoint>();
		String name = "";
		double [] numbers = null;
		
		while ((line = bf.readLine()) != null)
		{
		    String[] str = line.split(" ");
		    name = str[0];
		    numbers = new double[str.length-1];
		    for ( int i = 1 ; i < str.length ; i++) 
		          numbers[i-1] = Double.parseDouble(str[i]);

		    lineCount++;
		    MyPoint newPoint = new MyPoint(numbers, name);
		    sample.add(newPoint);
		}
		
		return sample;
	}
	
	public static void main(String args[]){
	/*	
		ArrayList<MyPoint> sample = new ArrayList<MyPoint>();

		MyPoint A = new MyPoint(new double[]{1.5, 2.5}, "A");
		MyPoint B = new MyPoint(new double[]{4.1, 3.2}, "B");
		MyPoint C = new MyPoint(new double[]{2.1, 1.2}, "C");
		MyPoint D = new MyPoint(new double[]{1.2, 1.2}, "D");
		MyPoint E = new MyPoint(new double[]{1.1, 2.2}, "E");
		sample.add(A);
		sample.add(B);
		sample.add(C);
		sample.add(D);
		sample.add(E);
*/
		ArrayList<MyPoint> sample = null;
		try {
			sample = readFile(args[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MyTree myTree = new MyTree(sample);
		
		printTreeInOrder(myTree);
	}
}