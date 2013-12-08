package myweb.csuchico.edu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
	
public class FirstTimeView extends Activity implements OnClickListener{
	
	// Private variables
	private TextView textViewName,textViewWeight,textViewAge,textViewSex;
	private SharedPreferences prefs;
	private Button buttonSubmitFirstTime;
	private EditText editTextAge,editTextWeight,editTextName;
    
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_time);
		
		//Submit Button Object Setup
		buttonSubmitFirstTime=(Button)findViewById(R.id.buttonSubmitFirstTime);
		buttonSubmitFirstTime.setOnClickListener(this);
        
		//Display Name Parameter 
		textViewName=(TextView)findViewById(R.id.textViewName);
		textViewName.setText("Name:");
		
		//Display Weight Parameter
		textViewWeight=(TextView)findViewById(R.id.textViewWeight);
		textViewWeight.setText("Weight (lbs.):");
		
		//Display Age Parameter
		textViewAge=(TextView)findViewById(R.id.textViewAge);
		textViewAge.setText("Age:");
		
		//Display Sex Parameter
		textViewSex=(TextView)findViewById(R.id.textViewSex);
		textViewSex.setText("Sex:");  
		
		//set type fields
		editTextAge=(EditText)findViewById(R.id.editTextAge);
		editTextWeight=(EditText)findViewById(R.id.editTextWeight);
		editTextName=(EditText)findViewById(R.id.editTextName);
    }
	
	public void onClick(View v) {
		// Find out if "Start" or "About" button was pressed
		if (v.getId() == R.id.buttonSubmitFirstTime) {
			SharedPreferences prefs = this.getSharedPreferences("myweb.csuchico.edu", Context.MODE_PRIVATE);
			
			// get values the user entered
			String name=editTextName.getText().toString();
			int age=Integer.parseInt(editTextAge.getText().toString());
		    int weight=Integer.parseInt(editTextWeight.getText().toString());
		    String sex="Male"; 
		    RadioButton rb;
		    rb = (RadioButton) findViewById(R.id.radioButtonSexMale);
		    if(rb.isChecked()){
		    	sex="Male";
		    }else{
		    	sex="Female";
		    }
			
		    prefs.edit().putString("myweb.csuchico.edu.user_Name", name).commit(); 
		    prefs.edit().putInt("myweb.csuchico.edu.user_Age", age).commit(); 
		    prefs.edit().putInt("myweb.csuchico.edu.user_Weight", weight).commit(); 
		    prefs.edit().putInt("myweb.csuchico.edu.weight5", weight).commit();
		    prefs.edit().putString("myweb.csuchico.edu.user_Sex", sex).commit();
		    
		    Intent i = new Intent(this, AndroidTabLayoutActivity.class);
    		startActivity(i);
			finish();	
		}
	}
}