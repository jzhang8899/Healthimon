package myweb.csuchico.edu;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;




public class AddView extends Activity implements OnChildClickListener {
	
	// Private variables
	private int donotselectview=1;
	//Private Instantiations
    private ExpandableListViewAdapter expandableListViewAdapter=null;
	private ExpandableListView expandableListView=null;
    
	//Enter Number of Group Rows
	private String[]groupStrings=new String[]{
			"Arms/Shoulders",
			"Chest",
			"Back",
			"Abdominal",
			"Legs",
			"Cardio",
			"Nutrition"
	};
	
	//Enter Your Sub Rows
	private String[][]childStrings=new String[][]{
			{
				"Bicep Curls",
				"Tricep Extensions",
				"Tricep Dips",
				"Shoulder Press",
				"Shoulder Abduction/Flexion/Extension"
				
			},
			{
				"Push Ups",
				"Bench Press",
				"Dumb-Bell FLys"
				
			},
			{
				"Pull Ups",
				"Chin Ups",
				"Seated Rows",
				"Shrugs"
			},
			{
				"SitUps",
				"Crunches"
				
			},
			{  
				"Squats",
				"Leg Extensions",
				"Leg Curls"
				
			},
			{
				"Running",
				"Swimming",
				"Biking",
			},
			{
				"Manage Your Diet"
			}
	};
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_view1);
		
		expandableListViewAdapter=new ExpandableListViewAdapter(groupStrings, childStrings, this);
		
		expandableListView=(ExpandableListView) findViewById(R.id.expandableListView);
		expandableListView.setAdapter(expandableListViewAdapter);
		expandableListView.setOnChildClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.introduction, menu);
		return true;
	}

	
	//The On Click Listener Method
	 public boolean onChildClick(ExpandableListView parent, View v,
			   int groupPosition, int childPosition, long id) {
		    
		   int childId=(int)id;    ///Typecasting from long to int
		 	String exercisename="";
			String exerciseAttr1="";
			String exerciseAttr2="";
			int exerciseNo=0;
			
			// find out which button  or textview was pushed based on its ID
			switch (childId)
			{
				case 600:                                           
					exercisename="Manage Diet/Weight";
					exerciseAttr1="Estimated Daily Calorie Intake";
					exerciseAttr2="Update Weight";
	    			exerciseNo=1;
	    			break;
	           
	    			
	    		// For touchable ARMS AND SHOULDERS exercise views
	    		case 0:
	    			exercisename="Bicep Curls";
	    			exerciseAttr1="Weight";
	    			exerciseAttr2="Reps";
	    			exerciseNo=2;
	    			break;   
	    		case 1:
	    			exercisename="Tricep Extensions";
	    			exerciseAttr1="Weight";
	    			exerciseAttr2="Reps";
	    			exerciseNo=3;
	    			break;   
	    		case 3:
	    			exercisename="Shoulder Press";
	    			exerciseAttr1="Weight";
	    			exerciseAttr2="Reps";
	    			exerciseNo=4;
	    			break;   
	    		case 2:
	    			exercisename="Tricep Dips";
	    			exerciseAttr1="User Weight";
	    			exerciseAttr2="Reps";
	    			exerciseNo=5;
	    			break;   
	    		case 4:
	    			exercisename="Shoulder Extensions";
	    			exerciseAttr1="Weight";
	    			exerciseAttr2="Reps";
	    			exerciseNo=6;
	    			break;   
	    			
	    		// For touchable CHEST exercise views
	    		case 100:
	    			exercisename="Push Ups";
	    			exerciseAttr1="User Weight";
	    			exerciseAttr2="Reps";
	    			exerciseNo=7;
	    			break;   
	    		case 101:
	    			exercisename="Bench Press";
	    			exerciseAttr1="Weight";
	    			exerciseAttr2="Reps";
	    			exerciseNo=8;
	    			break;   
	    		case 102:
	    			exercisename="Dumb-Bell Flys";
	    			exerciseAttr1="Weight";
	    			exerciseAttr2="Reps";
	    			exerciseNo=9;
	    			break;
	    			
	    		// For touchable BACK exercise views 	
	    		case 200:
	    			exercisename="Pull Ups";
	    			exerciseAttr1="Weight";
	    			exerciseAttr2="Reps";
	    			exerciseNo=10;
	    			break;   
	    		case 201:
	    			exercisename="Chin Ups";
	    			exerciseAttr1="Weight";
	    			exerciseAttr2="Reps";
	    			exerciseNo=11;
	    			break;   
	    		case 202:
	    			exercisename="Seated Rows";
	    			exerciseAttr1="User Weight";
	    			exerciseAttr2="Reps";
	    			exerciseNo=12;
	    			break;   
	    		case 203:
	    			exercisename="Shrugs";
	    			exerciseAttr1="Weight";
	    			exerciseAttr2="Reps";
	    			exerciseNo=13;
	    			break;
	    			
	    		// For touchable ABS exercise views	
	    		case 300:
	    			exercisename="Sit Ups";
	    			exerciseAttr1="User Weight";
	    			exerciseAttr2="Reps";
	    			exerciseNo=14;
	    			break;   
	    		case 301:
	    			exercisename="Crunches";
	    			exerciseAttr1="User Weight";
	    			exerciseAttr2="Reps";
	    			exerciseNo=15;
	    			break;
	    			
	    		// For touchable LEGS exercise views
	    		case 400:
	    			exercisename="Squats";
	    			exerciseAttr1="Weight";
	    			exerciseAttr2="Reps";
	    			exerciseNo=16;
	    			break;   
	    		case 401:
	    			exercisename="Leg Extensions";
	    			exerciseAttr1="Weight";
	    			exerciseAttr2="Reps";
	    			exerciseNo=17;
	    			break;   
	    		case 402:
	    			exercisename="Leg Curls";
	    			exerciseAttr1="User Weight";
	    			exerciseAttr2="Reps";
	    			exerciseNo=18;
	    			break;   
	    			
	    		// For touchable Cardio exercise views
	    		case 500:
	    			exercisename="Running";
	    			exerciseAttr1="Distance";
	    			exerciseAttr2="Time";
	    			exerciseNo=19;
	    			break;   
	    		case 501:
	    			exercisename="Swimming";
	    			exerciseAttr1="Distance";
	    			exerciseAttr2="Time";
	    			exerciseNo=20;
	    			break;   
	    		case 502:
	    			exercisename="Biking";
	    			exerciseAttr1="Distance";
	    			exerciseAttr2="Time";
	    			exerciseNo=21;
	    			break;     
	    		
	    		// Default value but shouldn't happen
	    		default:
	    			donotselectview=0;
	    			exercisename="";
	    			exerciseAttr1="";
	    			exerciseAttr2="";
	    			break;
			}
			// Pass needed values based on exercise selected to function below
			goToInputView(exercisename,exerciseAttr1,exerciseAttr2,exerciseNo);
			  return true;
			 }
	 
	 private void goToInputView(String exercisename,String exerciseAttr1,String exerciseAttr2,int exerciseNo)
		{
			//Start new activity only if views other than donotselcted views are clicked
			if(donotselectview !=0)
			{
				//Start Activity and pass in the parameters accordingly(note Inputview.putextra is used to pass data between activities using key value pairs)
				Intent inputview = new Intent(this, InputView.class);
				inputview.putExtra("exercisename",exercisename);
				inputview.putExtra("exerciseAttr1", exerciseAttr1);
				inputview.putExtra("exerciseAttr2", exerciseAttr2);
				inputview.putExtra("exerciseNo",exerciseNo);
				// start the activity based on the Intent
				startActivity(inputview);
			}
	    	donotselectview=1;
		}

}
