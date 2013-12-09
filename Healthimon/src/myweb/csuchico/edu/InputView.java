package myweb.csuchico.edu;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class InputView extends Activity implements OnClickListener {
	private SharedPreferences prefs;
	private Button submit,cancel;
	private TextView exerciseName,exerciseAttribute1,exerciseAttribute2,exTip,randomQuote;
	private EditText parameter1,parameter2;
	private int lastChance = 5;
	
	String names,attr1,attr2; ///Initializing variables for the textview
	int exerciseNo=0;
	
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.input_view);
	        
	        //create shared preference object
	        prefs = this.getSharedPreferences("myweb.csuchico.edu", Context.MODE_PRIVATE);
	        
	        ///Getting Extras from Intent passed in the previous activity
	        Bundle extras = getIntent().getExtras();
	        if (extras != null) {
	            names = extras.getString("exercisename");
	            attr1=extras.getString("exerciseAttr1");
	            attr2=extras.getString("exerciseAttr2");
	            exerciseNo=extras.getInt("exerciseNo");
	        }
	       
	        //Submit Button Object Setup
	        submit=(Button)findViewById(R.id.submit);
	        submit.setOnClickListener(this);
	        
	        //Cancle Button Object Setup
	        cancel=(Button)findViewById(R.id.cancel);
	        cancel.setOnClickListener(this);
	        
	        //Display Parameter 1
	        exerciseName=(TextView)findViewById(R.id.exerciseName);
	        exerciseName.setText(names);
	        
	        //Display Parameter 2
	        exerciseAttribute1=(TextView)findViewById(R.id.exerciseAttribute1);
	        exerciseAttribute1.setText(attr1);
	        
	        //Display exercise tip
	        String tip=getExerciseTip(exerciseNo);
	        exTip=(TextView)findViewById(R.id.exTip);
	        exTip.setText(tip);
	        
	        //Display randome quote
	        String quote=getRandomQuote();
	        randomQuote=(TextView)findViewById(R.id.randomQuote);
	        randomQuote.setText(quote);
	        
	        //Dispaly Parameter 3
	        exerciseAttribute2=(TextView)findViewById(R.id.exerciseAttribute2);
	        exerciseAttribute2.setText(attr2);
	        
	        parameter1=(EditText)findViewById(R.id.parameter1);
	        parameter2=(EditText)findViewById(R.id.parameter2);
	}

	public void onClick(View v) {
	    	
			// find out which button was pushed based on its ID
			if (v.getId()== R.id.submit) 
			{
				// Tmp bool variable to check if user is honest
				Boolean isValid = true;
				//Decrement last chance
				lastChance--;
				String input1= parameter1.getText().toString();
				String input2=parameter2.getText().toString();
				
				///Cheacking for empty or string overflow errors 
				///if found throw dialouge error else proceede as usual
				if(input1.length()>=5 || input1.length()==0 || input2.length()>=4 || input2.length()==0)
				{
					AlertDialog about = new AlertDialog.Builder(this).create();
					about.setTitle("---------ERROR!!!---------");
					about.setMessage("Either you've entered a invalid input or you're lying about your inputs!");
					about.setButton("DONE", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
						// here you can add functions
						}
						});
					about.show();
					
				}
				else
				{
				
				// get the two values input by the user
				int x=Integer.parseInt(input1.toString());
				// exerciseName.setText(parameter1.getText());
			    int y =Integer.parseInt(input2.toString());

			    // Calculate energy and see if valid/invalid inputs
			    isValid = calculateEnergy(x,y);
			    // Check if valid or not
			    if(isValid)
			    {
			    	finish();
			    }
			    else
			    {
			    	AlertDialog about = new AlertDialog.Builder(this).create();
					about.setTitle("---------CHEATER!!!---------");
					about.setMessage("You are either way too PHAT or your lying on your inputs. I will give you " + lastChance + " more chances...");
					about.setButton("DONE", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
						// here you can add functions
						}
						});
					about.show();
			    }
			    // Sick of user false inputs
			    if (lastChance ==  0)
			    	finish();
			}
			}
			if (v.getId()== R.id.cancel) {
				finish();
			}
			
	}

	private Boolean calculateEnergy(int param1,int param2){
	      //Initializing variables to zero
	      int strengtha=0;
	      int strengthl =0;
	      int agility=0;
	      int defense=0;
	      int health=0;
	      
	      switch(exerciseNo)
	      {
	      case 1:
	    	if(param1 > 2500)
	  			return false;
	  		health = param1/10;
	  		prefs.edit().putInt("myweb.csuchico.edu.user_Weight", param2).commit();	
	  		
	  		//Put weights in the array to dispaly on a chart
	 		prefs.edit().putInt("myweb.csuchico.edu.weight1", prefs.getInt("myweb.csuchico.edu.weight2", 0)).commit();
	  		prefs.edit().putInt("myweb.csuchico.edu.weight2", prefs.getInt("myweb.csuchico.edu.weight3", 0)).commit();
	  		prefs.edit().putInt("myweb.csuchico.edu.weight3", prefs.getInt("myweb.csuchico.edu.weight4", 0)).commit();
	 		prefs.edit().putInt("myweb.csuchico.edu.weight4", prefs.getInt("myweb.csuchico.edu.weight5", 0)).commit();
	  		prefs.edit().putInt("myweb.csuchico.edu.weight5",param2).commit();
	  		break;

  		case 2:
  			// Check if input for exercise is unbelievable. If it is return false.
  			// Each exercise is a different estimate
  			//if (param1 > 200)
  				//return false;
  			//if (param2 > 100)
  				//return false;
  			// If neither check flagged false it must be valid
  			
  			// So proceed...
  			int bicepCurl_Level=prefs.getInt("myweb.csuchico.edu.bicepCurl_Level", 0);
  			int bicepCurl_Exp=prefs.getInt("myweb.csuchico.edu.bicepCurl_Exp", 0);
  			strengtha = (param1 * param2) / (bicepCurl_Level * 3 + 20);
  			defense = strengtha/2;

  			bicepCurl_Exp = bicepCurl_Exp + strengtha + defense;

  			while(bicepCurl_Exp >= 100){
	  	    		bicepCurl_Exp = bicepCurl_Exp - 100;
	  	    		bicepCurl_Level++;
	  	    	}
  			
  			//save exercise values to preferences
  			prefs.edit().putInt("myweb.csuchico.edu.bicepCurl_Level", bicepCurl_Level).commit();
  			prefs.edit().putInt("myweb.csuchico.edu.bicepCurl_Exp", bicepCurl_Exp).commit();
  		    break;   

  		case 3:
  			// Check if input for exercise is unbelievable. If it is return false.
  			// Each exercise is a different estimate
  			if (param1 > 200)
  				return false;
  			if (param2 > 100)
  				return false;
  			// If neither check flagged false it must be valid
  			
  			// So proceed...
  			int tricepExtension_Level=prefs.getInt("myweb.csuchico.edu.tricepExtension_Level", 0);
  			int tricepExtension_Exp=prefs.getInt("myweb.csuchico.edu.tricepExtension_Exp", 0);
  			strengtha = (param1 * param2) / (tricepExtension_Level * 2 + 15);
  			defense = strengtha/3;

  			tricepExtension_Exp = tricepExtension_Exp + strengtha + defense;

  			while(tricepExtension_Exp >= 100){
	    		tricepExtension_Exp = tricepExtension_Exp - 100;
	    		tricepExtension_Level++;
	    	}
  			prefs.edit().putInt("myweb.csuchico.edu.tricepExtension_Level", tricepExtension_Level).commit();
  			prefs.edit().putInt("myweb.csuchico.edu.tricepExtension_Exp", tricepExtension_Exp).commit();
  			break;   

  		case 4:
  			// Check if input for exercise is unbelievable. If it is return false.
  			// Each exercise is a different estimate
  			if (param1 > 300)
  				return false;
  			if (param2 > 100)
  				return false;
  			// If neither check flagged false it must be valid
  			
  			// So proceed...
  			int shoulderPress_Level=prefs.getInt("myweb.csuchico.edu.shoulderPress_Level", 0);
  			int shoulderPress_Exp=prefs.getInt("myweb.csuchico.edu.shoulderPress_Exp", 0);
  			strengtha = (param1 * param2) / (shoulderPress_Level * 4 + 30);
  			defense = strengtha/3;
  			agility = strengtha/6;

  			shoulderPress_Exp = shoulderPress_Exp + strengtha + defense + agility;

  			while(shoulderPress_Exp >= 100){
	    		shoulderPress_Exp = shoulderPress_Exp - 100;
	    		shoulderPress_Level++;
	    	}
  			prefs.edit().putInt("myweb.csuchico.edu.shoulderPress_Level", shoulderPress_Level).commit();
  			prefs.edit().putInt("myweb.csuchico.edu.shoulderPress_Exp", shoulderPress_Exp).commit();
  			break;

  		case 5:
  			// Check if input for exercise is unbelievable. If it is return false.
  			// Each exercise is a different estimate
  			if (param1 > 500)
  				return false;
  			if (param2 > 200)
  				return false;
  			// If neither check flagged false it must be valid
  			
  			// So proceed...
  			int tricepDips_Level=prefs.getInt("myweb.csuchico.edu.tricepDips_Level", 0);
  			int tricepDips_Exp=prefs.getInt("myweb.csuchico.edu.tricepDips_Exp", 0);
  			strengtha = (sqrt(param1) * param2) / (tricepDips_Level + 6);
  			defense = strengtha/3;
  			agility = strengtha/6;

  			tricepDips_Exp = tricepDips_Exp + strengtha + defense + agility;

  			while(tricepDips_Exp >= 100){
	    		tricepDips_Exp = tricepDips_Exp - 100;
	    		tricepDips_Level++;
	    	}
  			
  			//save exercise values to preferences
  			prefs.edit().putInt("myweb.csuchico.edu.tricepDips_Level", tricepDips_Level).commit();
  			prefs.edit().putInt("myweb.csuchico.edu.tricepDips_Exp", tricepDips_Exp).commit();
  			break;

  		case 6:
  			// Check if input for exercise is unbelievable. If it is return false.
  			// Each exercise is a different estimate
  			if (param1 > 200)
  				return false;
  			if (param2 > 100)
  				return false;
  			// If neither check flagged false it must be valid
  			
  			// So proceed...
  			int shoulderExtend_Level=prefs.getInt("myweb.csuchico.edu.shoulderExtend_Level", 0);
  			int shoulderExtend_Exp=prefs.getInt("myweb.csuchico.edu.shoulderExtend_Exp", 0);
  			strengtha = (param1 * param2) / (shoulderExtend_Level * 2 + 20);
  			defense = strengtha/3;
  			agility = strengtha/6;

  			shoulderExtend_Exp = shoulderExtend_Exp + strengtha + defense + agility;

  			while(shoulderExtend_Exp >= 100){
	    		shoulderExtend_Exp = shoulderExtend_Exp - 100;
	    		shoulderExtend_Level++;
	    	}
  			//save exercise values to preferences
  			prefs.edit().putInt("myweb.csuchico.edu.shoulderExtend_Level", shoulderExtend_Level).commit();
  			prefs.edit().putInt("myweb.csuchico.edu.shoulderExtend_Exp", shoulderExtend_Exp).commit();
  			break;

  		case 7:
  			// Check if input for exercise is unbelievable. If it is return false.
  			// Each exercise is a different estimate
  			if (param1 > 500)
  				return false;
  			if (param2 > 300)
  				return false;
  			// If neither check flagged false it must be valid
  			
  			// So proceed...
  			int pushUp_Level=prefs.getInt("myweb.csuchico.edu.pushUp_Level", 0);
  			int pushUp_Exp=prefs.getInt("myweb.csuchico.edu.pushUp_Exp", 0);
  			strengtha = (sqrt(param1) * param2) / (pushUp_Level * 2 + 15);
  			defense = strengtha/3;
  			agility = strengtha/6;

  			pushUp_Exp = pushUp_Exp + strengtha + defense + agility;

  			while(pushUp_Exp >= 100){
	    		pushUp_Exp = pushUp_Exp - 100;
	    		pushUp_Level++;
	    	}
  			//save exercise values to preferences
  			prefs.edit().putInt("myweb.csuchico.edu.pushUp_Level", pushUp_Level).commit();
  			prefs.edit().putInt("myweb.csuchico.edu.pushUp_Exp",pushUp_Exp).commit();
  			break;

  		case 8:
  			// Check if input for exercise is unbelievable. If it is return false.
  			// Each exercise is a different estimate
  			//if (param1 > 350)
  				//return false;
  			//if (param2 > 100)
  				//return false;
  			// If neither check flagged false it must be valid
  			
  			// So proceed...
  			int bench_Level = prefs.getInt("myweb.csuchico.edu.bench_Level", 0);
  			int bench_Exp = prefs.getInt("myweb.csuchico.edu.bench_Exp", 0);
  			strengtha = (param1 * param2) / (bench_Level * 4 + 40);
  			defense = strengtha/3;
  			agility = strengtha/6;

  			bench_Exp = bench_Exp + strengtha + defense + agility;

  			while(bench_Exp >= 100){
	    		bench_Exp = bench_Exp - 100;
	    		bench_Level++;
	    	}
  			//save exercise values to preferences
  			prefs.edit().putInt("myweb.csuchico.edu.bench_Level", bench_Level).commit();
  			prefs.edit().putInt("myweb.csuchico.edu.bench_Exp", bench_Exp).commit();
  			break;

  		case 9:
  			// Check if input for exercise is unbelievable. If it is return false.
  			// Each exercise is a different estimate
  			if (param1 > 200)
  				return false;
  			if (param2 > 100)
  				return false;
  			// If neither check flagged false it must be valid
  			
  			// So proceed...
  			int dumbbellFlys_Level = prefs.getInt("myweb.csuchico.edu.dumbbellFlys_Level", 0);
  			int dumbbellFlys_Exp = prefs.getInt("myweb.csuchico.edu.dumbbellFlys_Exp", 0);
  			strengtha = (param1 * param2) / (dumbbellFlys_Level * 2 + 10);
  			defense = strengtha/3;
  			agility = strengtha/6;

  			dumbbellFlys_Exp = dumbbellFlys_Exp + strengtha + defense + agility;

  			while(dumbbellFlys_Exp >= 100){
	    		dumbbellFlys_Exp = dumbbellFlys_Exp - 100;
	    		dumbbellFlys_Level++;
	    	}
  			//save exercise values to preferences
  			prefs.edit().putInt("myweb.csuchico.edu.dumbbellFlys_Level", dumbbellFlys_Level).commit();
  			prefs.edit().putInt("myweb.csuchico.edu.dumbbellFlys_Exp", dumbbellFlys_Exp).commit();
  			break;
 
  		case 10:
  			// Check if input for exercise is unbelievable. If it is return false.
  			// Each exercise is a different estimate
  			if (param1 > 500)
  				return false;
  			if (param2 > 100)
  				return false;
  			// If neither check flagged false it must be valid
  			
  			// So proceed...
  			int pullUp_Level = prefs.getInt("myweb.csuchico.edu.pullUp_Level", 0);
  			int pullUp_Exp = prefs.getInt("myweb.csuchico.edu.pullUp_Exp", 0);
  			defense = (sqrt(param1) * param2) / (pullUp_Level * 1 + 5);
  			agility = defense/5;

  			pullUp_Exp = pullUp_Exp + defense + agility;

  			while(pullUp_Exp >= 100){
	    		pullUp_Exp = pullUp_Exp - 100;
	    		pullUp_Level++;
	    	}
  			//save exercise values to preferences
  			prefs.edit().putInt("myweb.csuchico.edu.pullUp_Level", pullUp_Level).commit();
  			prefs.edit().putInt("myweb.csuchico.edu.pullUp_Exp", pullUp_Exp).commit();
  			break;

  		case 11:
  			// Check if input for exercise is unbelievable. If it is return false.
  			// Each exercise is a different estimate
  			if (param1 > 500)
  				return false;
  			if (param2 > 100)
  				return false;
  			// If neither check flagged false it must be valid
  			
  			// So proceed...
  			int chinUp_Level = prefs.getInt("myweb.csuchico.edu.chinUp_Level", 0);
  			int chinUp_Exp = prefs.getInt("myweb.csuchico.edu.chinUp_Exp", 0);
  			defense = (sqrt(param1) * param2) / (chinUp_Level * 1 + 4);
  			agility = defense/6;

  			chinUp_Exp = chinUp_Exp + defense + agility;

  			while(chinUp_Exp >= 100){
	    		chinUp_Exp = chinUp_Exp - 100;
	    		chinUp_Level++;
	    	}
  			//save exercise values to preferences
  			prefs.edit().putInt("myweb.csuchico.edu.chinUp_Level", chinUp_Level).commit();
  			prefs.edit().putInt("myweb.csuchico.edu.chinUp_Exp", chinUp_Exp).commit();
  			break;

  		case 12:
  			// Check if input for exercise is unbelievable. If it is return false.
  			// Each exercise is a different estimate
  			if (param1 > 300)
  				return false;
  			if (param2 > 100)
  				return false;
  			// If neither check flagged false it must be valid
  			
  			// So proceed...
  			int seatedRows_Level=prefs.getInt("myweb.csuchico.edu.seatedRows_Level", 0);
  			int seatedRows_Exp=prefs.getInt("myweb.csuchico.edu.seatedRows_Exp", 0);
  			defense = (param1 * param2) / (seatedRows_Level * 3 + 50);
  			agility = defense/6;

  			seatedRows_Exp = seatedRows_Exp + defense + agility;

  			while(seatedRows_Exp >= 100){
	    		seatedRows_Exp = seatedRows_Exp - 100;
	    		seatedRows_Level++;
	    	}
  			//save exercise values to preferences
  			prefs.edit().putInt("myweb.csuchico.edu.seatedRows_Level", seatedRows_Level).commit();
  			prefs.edit().putInt("myweb.csuchico.edu.seatedRows_Exp", seatedRows_Exp).commit();
  			break;

  		case 13:
  			// Check if input for exercise is unbelievable. If it is return false.
  			// Each exercise is a different estimate
  			if (param1 > 400)
  				return false;
  			if (param2 > 100)
  				return false;
  			// If neither check flagged false it must be valid
  			
  			// So proceed...
  			int shrugs_Level=prefs.getInt("myweb.csuchico.edu.shrugs_Level", 0);
  			int shrugs_Exp=prefs.getInt("myweb.csuchico.edu.shrugs_Exp", 0);
  			defense = (param1 * param2) / (shrugs_Level * 5 + 100);
  			agility = defense/6;

  			shrugs_Exp = shrugs_Exp + defense + agility;

  			while(shrugs_Exp >= 100){
	    		shrugs_Exp = shrugs_Exp - 100;
	    		shrugs_Level++;
	    	}
  			//save exercise values to preferences
  			prefs.edit().putInt("myweb.csuchico.edu.shrugs_Level", shrugs_Level).commit();
  			prefs.edit().putInt("myweb.csuchico.edu.shrugs_Exp", shrugs_Exp).commit();
  			break;

  		case 14:
  			// Check if input for exercise is unbelievable. If it is return false.
  			// Each exercise is a different estimate
  			//if (param1 > 500)
  				//return false;
  			//if (param2 > 200)
  				//return false;
  			// If neither check flagged false it must be valid
  			
  			// So proceed...
  			int sitUp_Level=prefs.getInt("myweb.csuchico.edu.sitUp_Level", 0);
  			int sitUp_Exp=prefs.getInt("myweb.csuchico.edu.sitUp_Level", 0);
  			defense = (sqrt(param1) * param2) / (sitUp_Level * 2 + 10);
  			agility = defense/6;

  			sitUp_Exp = sitUp_Exp + defense + agility;

  			while(sitUp_Exp >= 100){
	    		sitUp_Exp = sitUp_Exp - 100;
	    		sitUp_Level++;
	    	}
  			//save exercise values to preferences
  			prefs.edit().putInt("myweb.csuchico.edu.sitUp_Level", sitUp_Level).commit();
  			prefs.edit().putInt("myweb.csuchico.edu.sitUp_Exp", sitUp_Exp).commit();
  			break;

  		case 15:
  			// Check if input for exercise is unbelievable. If it is return false.
  			// Each exercise is a different estimate
  			if (param1 > 500)
  				return false;
  			if (param2 > 300)
  				return false;
  			// If neither check flagged false it must be valid
  			
  			// So proceed...
  			int crunches_Level=prefs.getInt("myweb.csuchico.edu.crunches_Level", 0);
  			int crunches_Exp=prefs.getInt("myweb.csuchico.edu.crunches_Exp", 0);
  			defense = (sqrt(param1) * param2) / (crunches_Level * 5 + 10);
  			agility = defense/6;

  			crunches_Exp = crunches_Exp + defense + agility;

  			while(crunches_Exp >= 100){
	    		crunches_Exp = crunches_Exp - 100;
	    		crunches_Level++;
	    	}
  			//save exercise values to preferences
  			prefs.edit().putInt("myweb.csuchico.edu.crunches_Level", crunches_Level).commit();
  			prefs.edit().putInt("myweb.csuchico.edu.crunches_Exp", crunches_Exp).commit();
  			break;

  		case 16:
  			// Check if input for exercise is unbelievable. If it is return false.
  			// Each exercise is a different estimate
  			//if (param1 > 500)
  				//return false;
  			//if (param2 > 100)
  				//return false;
  			// If neither check flagged false it must be valid
  			
  			// So proceed...
  			int squats_Level=prefs.getInt("myweb.csuchico.edu.squats_Level", 0);
  			int squats_Exp=prefs.getInt("myweb.csuchico.edu.squats_Exp", 0);
  			strengthl = (param1 * param2) / (squats_Level * 3 + 50);
  			defense = strengthl/3;

  			squats_Exp = squats_Exp + strengthl + defense;

  			while(squats_Exp >= 100){
	    		squats_Exp = squats_Exp - 100;
	    		squats_Level++;
	    	}
  			//save exercise values to preferences
  			prefs.edit().putInt("myweb.csuchico.edu.squats_Level", squats_Level).commit();
  			prefs.edit().putInt("myweb.csuchico.edu.squats_Exp", squats_Exp).commit();
  			break;

  		case 17:
  			// Check if input for exercise is unbelievable. If it is return false.
  			// Each exercise is a different estimate
  			if (param1 > 300)
  				return false;
  			if (param2 > 100)
  				return false;
  			// If neither check flagged false it must be valid
  			
  			// So proceed...
  			int legExtension_Level=prefs.getInt("myweb.csuchico.edu.legExtension_Level", 0);
  			int legExtension_Exp=prefs.getInt("myweb.csuchico.edu.legExtension_Level", 0);
  			strengthl = (param1 * param2) / (legExtension_Level * 5 + 30);
  			defense = strengthl/3;
  			agility = strengthl/3;

  			legExtension_Exp = legExtension_Exp + strengthl + defense + agility;

  			while(legExtension_Exp >= 100){
	    		legExtension_Exp = legExtension_Exp - 100;
	    		legExtension_Level++;
	    	}
  			//save exercise values to preferences
  			prefs.edit().putInt("myweb.csuchico.edu.legExtension_Level", legExtension_Level).commit();
  			prefs.edit().putInt("myweb.csuchico.edu.legExtension_Exp", legExtension_Exp).commit();
  			break;

  		case 18:
  			// Check if input for exercise is unbelievable. If it is return false.
  			// Each exercise is a different estimate
  			if (param1 > 300)
  				return false;
  			if (param2 > 100)
  				return false;
  			// If neither check flagged false it must be valid
  			
  			// So proceed...
  			int legCurls_Level=prefs.getInt("myweb.csuchico.edu.legCurls_Level", 0);
  			int legCurls_Exp=prefs.getInt("myweb.csuchico.edu.legCurls_Exp", 0);
  			strengthl = (param1 * param2) / (legCurls_Level * 5 + 30);
  			defense = strengthl/3;
  			agility = strengthl/3;

  			legCurls_Exp = legCurls_Exp + strengthl + defense + agility;

  			while(legCurls_Exp >= 100){
	    		legCurls_Exp = legCurls_Exp - 100;
	    		legCurls_Level++;
	    	}
  			//save exercise values to preferences
  			prefs.edit().putInt("myweb.csuchico.edu.legCurls_Level", legCurls_Level).commit();
  			prefs.edit().putInt("myweb.csuchico.edu.legCurls_Exp", legCurls_Exp).commit();
  			break;

  		case 19:
  			// Check if input for exercise is unbelievable. If it is return false.
  			// Each exercise is a different estimate
  			if (param1 > 15)
  				return false;
  			if (param2 > 180)
  				return false;
  			// If neither check flagged false it must be valid
  			
  			// So proceed...
  			int running_Level=prefs.getInt("myweb.csuchico.edu.running_Level", 0);
  			int running_Exp=prefs.getInt("myweb.csuchico.edu.running_Exp", 0);
  			agility = param1 * 1000 / param2 / running_Level;
  			health = agility/3;
  			strengthl = agility/6;

  			running_Exp = running_Exp + agility + health + strengthl;

  			while(running_Exp >= 100){
	    		running_Exp = running_Exp - 100;
	    		running_Level++;
	    	}
  			//save exercise values to preferences
  			prefs.edit().putInt("myweb.csuchico.edu.running_Level", running_Level).commit();
  			prefs.edit().putInt("myweb.csuchico.edu.runing_Exp", running_Exp).commit();
  			break;

  		case 20:
  			// Check if input for exercise is unbelievable. If it is return false.
  			// Each exercise is a different estimate
  			if (param1 > 10)
  				return false;
  			if (param2 > 200)
  				return false;
  			// If neither check flagged false it must be valid
  			
  			// So proceed...
  			int swimming_Level=prefs.getInt("myweb.csuchico.edu.swimming_Level", 0);
  			int swimming_Exp=prefs.getInt("myweb.csuchico.edu.swimming_Exp", 0);
  			agility = param1 * param2 / 3 / swimming_Level;
  			health = agility/3;
  			defense = agility/6;

  			swimming_Exp = swimming_Exp + agility + health + defense;

  			while(swimming_Exp >= 100){
	    		swimming_Exp = swimming_Exp - 100;
	    		swimming_Level++;
	    	}
  			//save exercise values to preferences
  			prefs.edit().putInt("myweb.csuchico.edu.swimming_Level", swimming_Level).commit();
  			prefs.edit().putInt("myweb.csuchico.edu.swimming_Exp", swimming_Exp).commit();
  			break;

  		case 21:
  			// Check if input for exercise is unbelievable. If it is return false.
  			// Each exercise is a different estimate
  			if (param1 > 50)
  				return false;
  			if (param2 > 400)
  				return false;
  			// If neither check flagged false it must be valid
  			
  			// So proceed...
  			int biking_Level=prefs.getInt("myweb.csuchico.edu.biking_Level", 0);
  			int biking_Exp=prefs.getInt("myweb.csuchico.edu.biking_Exp", 0);
  			agility = (param1 * 500 / param2) /biking_Level;
  			health = agility/3;
  			strengthl = agility/6;

  			biking_Exp = biking_Exp + agility + health + strengthl;

  			while(biking_Exp >= 100){
	    		biking_Exp = biking_Exp - 100;
	    		biking_Level++;
	    	}
  			//save exercise values to preferences
  			prefs.edit().putInt("myweb.csuchico.edu.biking_Level", biking_Level).commit();
  			prefs.edit().putInt("myweb.csuchico.edu.biking_Exp", biking_Exp).commit();
  			break;
  			
  		default:
  			break;
	 }
	// Inputs must have been valid so save stats
  saveStats(strengtha,strengthl,agility,defense,health);
  return true;
}


	private int sqrt(int param1) {
		// TODO Auto-generated method stub
		return 0;
	}

	///Saves STATS
	public void saveStats(int strengtha,int strengthl,int agility,int defense,int health){
		
	  	// Temp variable for user level to see if they leveled up
	  	int tmp_userLevel = 0;
	  	int users_Exp=prefs.getInt("myweb.csuchico.edu.users_Exp", 0);
	  	int users_Agility=prefs.getInt("myweb.csuchico.edu.agility_Level", 0);
	  	int users_ArmStrength=prefs.getInt("myweb.csuchico.edu.strengtha_Level", 0);
	  	int users_LegStrength=prefs.getInt("myweb.csuchico.edu.strengthl_Level", 0);
	  	int users_Health=prefs.getInt("myweb.csuchico.edu.health_Level", 0);
	  	int users_Defense=prefs.getInt("myweb.csuchico.edu.defense_Level", 0);
	    int userLevel=prefs.getInt("myweb.csuchico.edu.users_Level", 0);
	
	  	// Let user know what exp they received for exercise
	  	// Pop up alert that looks like so:
	
	  	//-------------------------------------
	  	// 				Exp Awarded
	  	//-------------------------------------
	  	// Arm Strength: strengtha
	  	// Leg Strength: strengthl
	  	// Agility:		 agility
	  	// Defense:		 defense
	  	// Health: 		 health
	  	//-------------------------------------
	  	//					OK
	  	//-------------------------------------
	   
	
	  	// Set new exp = left_over_exp + new exp so you can add correct exp
	  	strengtha += prefs.getInt("myweb.csuchico.edu.strengtha", 0);
	  	strengthl +=prefs.getInt("myweb.csuchico.edu.strengthl", 0);
	  	agility   +=prefs.getInt("myweb.csuchico.edu.agility", 0);
	  	defense +=prefs.getInt("myweb.csuchico.edu.defense", 0);
	  	health +=prefs.getInt("myweb.csuchico.edu.health", 0);
	
	  	// Increase Statistic Level for every 100 points
	  	while(strengtha >= 100){
	  		strengtha = strengtha - 100;
	  		users_ArmStrength++;
	  		users_Exp++;
	  	}
	  	while(strengthl >= 100){
	  		strengthl = strengthl - 100;
	  		users_LegStrength++;
	  		users_Exp++;
	  	}
	  	while(agility >= 100){
	  		agility = agility - 100;
	  		users_Agility++;
	  		users_Exp++;
	  	}
	  	while(defense >= 100){
	  		defense = defense - 100;
	  		users_Defense++;
	  		users_Exp++;
	  	}
	  	while(health >= 100){
	  		health = health - 100;
	  		users_Health ++;
	  		users_Exp++;
	  	}
	
	  
	  	// Save left over exercise exp in corresponding extra_variable
	  	prefs.edit().putInt("myweb.csuchico.edu.strengtha", strengtha).commit();
	  	prefs.edit().putInt("myweb.csuchico.edu.strengthl", strengthl).commit();
	  	prefs.edit().putInt("myweb.csuchico.edu.agility", agility).commit();
	  	prefs.edit().putInt("myweb.csuchico.edu.defense", defense).commit();
	  	prefs.edit().putInt("myweb.csuchico.edu.health", health).commit();
	  	prefs.edit().putInt("myweb.csuchico.edu.health_Level", users_Health).commit();
	  	prefs.edit().putInt("myweb.csuchico.edu.defense_Level", users_Defense).commit();
	  	prefs.edit().putInt("myweb.csuchico.edu.agility_Level", users_Agility).commit();
	  	prefs.edit().putInt("myweb.csuchico.edu.strengtha_Level", users_ArmStrength).commit();
	  	prefs.edit().putInt("myweb.csuchico.edu.strengthl_Level", users_LegStrength).commit();
	  	prefs.edit().putInt("myweb.csuchico.edu.users_Exp", users_Exp).commit();
	  	// Upgrade user level if need be
	  	
	  	tmp_userLevel = getNewLevel(users_Exp);
	 	  	
	  	// Congratulate user on leveling up if need be
	  	if (tmp_userLevel != userLevel)
	  	{
	  		//edit what boss can be fought
	  		if(tmp_userLevel >= 20)
	  			prefs.edit().putInt("myweb.csuchico.edu.bossNumToFight", 5).commit();
	  		else if(tmp_userLevel >= 16)
	  			prefs.edit().putInt("myweb.csuchico.edu.bossNumToFight", 4).commit();
	  		else if(tmp_userLevel >= 12)
	  			prefs.edit().putInt("myweb.csuchico.edu.bossNumToFight", 3).commit();
	  		else if(tmp_userLevel >= 8)
	  			prefs.edit().putInt("myweb.csuchico.edu.bossNumToFight", 2).commit();
	  		else if(tmp_userLevel >= 4)
	  			prefs.edit().putInt("myweb.csuchico.edu.bossNumToFight", 1).commit();
	  		
	  		// Then user must have leveled up
	  		// Set user level equal to new level
	  		userLevel = tmp_userLevel;
	  		prefs.edit().putInt("myweb.csuchico.edu.users_Level", userLevel).commit();
	  		
	  		// Let user know what level reached
	  		Toast.makeText(InputView.this,  "Congratulations! You've Reached Level "+userLevel, Toast.LENGTH_LONG).show();
	  		showDialog(0);
	  		
	  	}
	}
	
	public int getNewLevel(int users_Exp){
		int tmp_userLevel = 0;
		if (users_Exp<4) 
  			tmp_userLevel = 1;
  		else if(users_Exp>3 && users_Exp<9) 
  			tmp_userLevel = 2;
  		else if(users_Exp>8 && users_Exp<16) 
  			tmp_userLevel = 3;	 
  		else if(users_Exp>15 && users_Exp<25)
  			tmp_userLevel = 4;
  		else if (users_Exp>24 && users_Exp<36) 
  			tmp_userLevel = 5;	  			
  		else if (users_Exp>35 && users_Exp<49) 
  			tmp_userLevel = 6;	  			
  		else if (users_Exp>48 && users_Exp<64) 
  			tmp_userLevel = 7;	  		
  		else if  (users_Exp>63 && users_Exp<81) 
  			tmp_userLevel = 8;
  		else if  (users_Exp>80 && users_Exp<100) 
  			tmp_userLevel = 9;  			
  		else if (users_Exp>99 && users_Exp<121) 
  			tmp_userLevel = 10;			
  		else if (users_Exp>120 && users_Exp<144) 
  			tmp_userLevel = 11;  			
  		else if  (users_Exp>143 && users_Exp<168) 
  			tmp_userLevel = 12;	
  		else if  (users_Exp>167 && users_Exp<196) 
  			tmp_userLevel = 13;	  			
  		else if  (users_Exp>195 && users_Exp<225) 
  			tmp_userLevel = 14;	  			
  		else if  (users_Exp>224 && users_Exp<256) 
  			tmp_userLevel = 15;	  			
  		else if (users_Exp>255 && users_Exp<289) 
  			tmp_userLevel = 16;	  	
  		else if  (users_Exp>288 && users_Exp<324) 
  			tmp_userLevel = 17;  			
  		else if ( users_Exp>323 && users_Exp<361) 
  			tmp_userLevel = 18;	  		
  		else if  (users_Exp>360 && users_Exp<400) 
  			tmp_userLevel = 19;	  			
  		else
  			tmp_userLevel = 20;
		
		return tmp_userLevel;
	}
	
	private String getExerciseTip(int exerciseNo){
		// Set Exercise tip for certain exercise
		String exerciseTip = "";
	    switch (exerciseNo){
	    	case 1:
	    		exerciseTip = "*Suggested Daily Calroie Intake: Males: 2000-2500, Females: 1500-2000*";
	    		break;
		    case 2:
		    	exerciseTip = "*Use your arms only and fully extend*";
		    	break;
		    case 3:
		    	exerciseTip = "*Breathe during extensions for power*";
		    	break;
		    case 4:
		    	exerciseTip = "*Slow and controlled young grasshopper*";
		    	break;
		    case 5:
		    	exerciseTip = "*Keep your elbows in and stabilize core*";
		    	break;
		    case 6:
		    	exerciseTip = "*Combined weight of Dumbbells. All directions.*";
		    	break;
		    case 7:
		    	exerciseTip = "*How many 1UP's did you do in your whole workout?*";
		    	break;
		    case 8:
		    	exerciseTip = "*Go until your elbows are at 90 degrees*";
		    	break;
		    case 9:
		    	exerciseTip = "*Combined weight of Dumbbells. Try not to use your biceps*";
		    	break;
		    case 10:
		    	exerciseTip = "*Pull Ups are with your palms facing AWAY from you!*";
		    	break;
		    case 11:
		    	exerciseTip = "*Chin Ups are with your palms facing TOWARDS you!*";
		    	break;
		    case 12:
		    	exerciseTip = "*Row Row Row your boat gently down the stream!*";
		    	break;
		    case 13:
		    	exerciseTip = "*DO NOT rotate your shoulders! Up. Down. Up. Down...*";
		    	break;
		    case 14:
		    	exerciseTip = "*Try bringing your head all the way to your knees*";
		    	break;
		    case 15:
		    	exerciseTip = "*Concentrate on your abs and do all directions*";
		    	break;
		    case 16:
		    	exerciseTip = "*Your weight plus any weight your holding*";
		    	break;
		    case 17:
		    	exerciseTip = "*Control the weight upon relaxation*";
		    	break;
		    case 18:
		    	exerciseTip = "*Control the weight upon relaxation*";
		    	break;
		    case 19:
		    	exerciseTip = "*Go farther in a shorter amount of time*";
		    	break;
		    case 20:
		    	exerciseTip = "*Estimate the distance in miles you swam*";
		    	break;
		    case 21:
		    	exerciseTip = "*Pace yourself...*";
		    	break;
	    }
	    return exerciseTip;
	}
	
	private String getRandomQuote(){
		 // Generate random work-out quote
	    Random randomGenerator = new Random();
    	int randomNum = randomGenerator.nextInt(15);
    	
    	String randomTip = "";
    	// Select random quote
    	switch(randomNum){
			case 0:
				randomTip = "Motivation will almost always beat mere talent. -Norman R. Augustine";
				break;
			case 1:
				randomTip = "Whatever doesn't kill you makes you stronger! -Unknown";
				break;
			case 2:
				randomTip = "Pain is just WEAKNESS leaving the body! -Unknown";
				break;
			case 3:
				randomTip = "\"You miss 100% of the shots you don't take. -Wayne Gretzky\" -Michael Scott";
				break;
			case 4:
				randomTip = "Energy and persistence conquer all things. -Benjamin Franklin";
				break;
			case 5:
				randomTip = "Fear is what stops you... courage is what keeps you going. -Unknown";
				break;
			case 6:
				randomTip = "Just do it. -Nike";
				break;
			case 7:
				randomTip = "The secret of getting ahead is getting started. -Mark Twain";
				break;
			case 8:
				randomTip = "Luck is a matter of preparation meeting opportunity. -Oprah Winfrey";
				break;
			case 9:
				randomTip = "It's never too late to become what you might have been. -George Eliot";
				break;
			case 10:
				randomTip = "Clear your mind of can't. -Samuel Johnson";
				break;
			case 11:
				randomTip = "If you're going through hell, keep going. -Winston Churchill";
				break;
			case 12:
				randomTip = "Even if you fall on your face you're still moving forward. -Victor Kiam";
				break;
			case 13:
				randomTip = "Our greatest weakness lies in giving up. -Thomas A. Edison";
				break;
			case 14:
				randomTip = "Here's a tip... TRY HARDER!!! -Taylor Rand";
				break;
    	}
    	return randomTip;
	}
}
