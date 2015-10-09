package com.jsupport.listviewimages;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListViewImagesActivity extends Activity {
	/** Called when the activity is first created. */
	public ArrayList<ItemDetails> image_details;
	public ListView lv1;
	ItemListBaseAdapter adapter;
	final long totalScrollTime = 10000; //total scroll time. I think that 300 000 000 years is close enouth to infinity. if not enought you can restart timer in onFinish()
    final int scrollPeriod = 500; // every 100 ms scoll will happened. smaller values for smoother
    final int heightToScroll = 5; // will be scrolled to 20 px every time. smaller values for smoother scrolling

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        image_details = GetSearchResults();
        
        lv1 = (ListView) findViewById(R.id.listV_main);
        
        adapter = new ItemListBaseAdapter(this, image_details);
        
        lv1.setAdapter(adapter);
        
        lv1.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) { 
        		Object o = lv1.getItemAtPosition(position);
            	ItemDetails obj_itemDetails = (ItemDetails)o;
        		Toast.makeText(ListViewImagesActivity.this, "You have chosen : " + " " + obj_itemDetails.getName(), Toast.LENGTH_LONG).show();
        	}  
        });
        
       
        lv1.post(new Runnable() {
        	@Override
        	public void run() {
        		new CountDownTimer(totalScrollTime, scrollPeriod ) {
        			public void onTick(long millisUntilFinished) {
        				//lv1.scrollBy(0, heightToScroll);
        				
        				lv1.setSelectionFromTop(lv1.getFirstVisiblePosition() + 1, lv1.getChildAt(0).getTop() + heightToScroll);
        				if (lv1.getLastVisiblePosition() == lv1.getAdapter().getCount() -1 &&
        						lv1.getChildAt(lv1.getChildCount() - 1).getBottom() <= lv1.getHeight()){
        					    //It is scrolled all the way down here
        					    addMoreResults(image_details);
        					    adapter.notifyDataSetChanged();

        					}
        			}

					@Override
					public void onFinish() {
						// TODO Auto-generated method stub
						
					}
        		}.start();
        	}
        });

    }

    public ArrayList<ItemDetails> GetSearchResults(){
    	ArrayList<ItemDetails> results = new ArrayList<ItemDetails>();
    	
    	ItemDetails item_details = new ItemDetails();
    	item_details.setName("Pizza");
    	item_details.setItemDescription("Spicy Chiken Pizza");
    	item_details.setImageNumber(1);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("Burger");
    	item_details.setItemDescription("Beef Burger");
    	item_details.setImageNumber(2);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("Pizza");
    	item_details.setItemDescription("Chiken Pizza");
    	item_details.setImageNumber(3);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("Burger");
    	item_details.setItemDescription("Chicken Burger");
    	item_details.setImageNumber(4);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("Burger");
    	item_details.setItemDescription("Fish Burger");
    	item_details.setImageNumber(5);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("Mango");
    	item_details.setItemDescription("Mango Juice");
    	item_details.setImageNumber(6);
    	results.add(item_details);
    	
    	return results;
    }
    
    public void addMoreResults(ArrayList<ItemDetails> results){
    	ItemDetails item_details = new ItemDetails();
    	item_details.setName("Pizza");
    	item_details.setItemDescription("Spicy Chiken Pizza");
    	item_details.setImageNumber(1);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("Burger");
    	item_details.setItemDescription("Beef Burger");
    	item_details.setImageNumber(2);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("Pizza");
    	item_details.setItemDescription("Chiken Pizza");
    	item_details.setImageNumber(3);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("Burger");
    	item_details.setItemDescription("Chicken Burger");
    	item_details.setImageNumber(4);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("Burger");
    	item_details.setItemDescription("Fish Burger");
    	item_details.setImageNumber(5);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("Mango");
    	item_details.setItemDescription("Mango Juice");
    	item_details.setImageNumber(6);
    	results.add(item_details);
    }
    
}