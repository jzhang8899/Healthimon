
package myweb.csuchico.edu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
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


public class FightsView extends Activity implements OnClickListener {
	private Button boss1, boss2, boss3, boss4, boss5, fightfriends, fightRandom;
	private EditText fightName;
    private SharedPreferences prefs;


	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fights_layout);
        
        /*//Creates a thread to post data to the server everytime you enter the tab
        ServerImplementation postData=new ServerImplementation();
        Thread postData1=new Thread(postData);
        postData1.start();
        */
        fightName=(EditText)findViewById(R.id.fightName);
        
        try{
            
            // CALL GetText method to make post method call
           postData("HMON1234UPDTE5678");
    }
   catch(Exception ex)
    {
       
    }
        
			
		
        
        // Add button for Boss 1
        boss1=(Button)findViewById(R.id.boss1);
        boss1.setOnClickListener(this);
        
        // Add button for Boss 2
        boss2=(Button)findViewById(R.id.boss2);
        boss2.setOnClickListener(this);
        
        // Add button for Boss 3
        boss3=(Button)findViewById(R.id.boss3);
        boss3.setOnClickListener(this);
        
        // Add button for Boss 4
        boss4=(Button)findViewById(R.id.boss4);
        boss4.setOnClickListener(this);
        
        // Add button for Boss 5
        boss5=(Button)findViewById(R.id.boss5);
        boss5.setOnClickListener(this);
        
        fightfriends=(Button)findViewById(R.id.fightfriends);
        fightfriends.setOnClickListener(this);
        
        fightRandom=(Button)findViewById(R.id.fightRandom);
        fightRandom.setOnClickListener(this);
        
        setBossImages();
    }
	
	
	
	public void setBossImages(){
		 //set boss images on load
        prefs = this.getSharedPreferences("myweb.csuchico.edu", Context.MODE_PRIVATE);
        int onBossNum=prefs.getInt("myweb.csuchico.edu.onBossNum", 0);
        int userLevel=prefs.getInt("myweb.csuchico.edu.users_Level", 0);
        if(onBossNum == 1){
        	if(userLevel >= 4)
        		boss1.setBackgroundResource(R.drawable.boss1_ready);
        	else
        		boss1.setBackgroundResource(R.drawable.boss1_locked);
        	boss2.setBackgroundResource(R.drawable.boss2_locked);
        	boss3.setBackgroundResource(R.drawable.boss3_locked);
        	boss4.setBackgroundResource(R.drawable.boss4_locked);
        	boss5.setBackgroundResource(R.drawable.boss5_locked);
        }else if(onBossNum == 2){
        	boss1.setBackgroundResource(R.drawable.boss1_complete);
        	if(userLevel >= 8)
        		boss2.setBackgroundResource(R.drawable.boss2_ready);
        	else
        		boss2.setBackgroundResource(R.drawable.boss2_locked);
        	boss3.setBackgroundResource(R.drawable.boss3_locked);
        	boss4.setBackgroundResource(R.drawable.boss4_locked);
        	boss5.setBackgroundResource(R.drawable.boss5_locked);
        }else if(onBossNum == 3){
        	boss1.setBackgroundResource(R.drawable.boss1_complete);
        	boss2.setBackgroundResource(R.drawable.boss2_complete);
        	if(userLevel >= 12)
        		boss3.setBackgroundResource(R.drawable.boss3_ready);
        	else
        		boss3.setBackgroundResource(R.drawable.boss3_locked);
        	boss4.setBackgroundResource(R.drawable.boss4_locked);
        	boss5.setBackgroundResource(R.drawable.boss5_locked);
        }else if(onBossNum == 4){
        	boss1.setBackgroundResource(R.drawable.boss1_complete);
        	boss2.setBackgroundResource(R.drawable.boss2_complete);
        	boss3.setBackgroundResource(R.drawable.boss3_complete);
        	if(userLevel >= 16)
        		boss4.setBackgroundResource(R.drawable.boss4_ready);
        	else
        		boss4.setBackgroundResource(R.drawable.boss4_locked);
        	boss5.setBackgroundResource(R.drawable.boss5_locked);
        }else if(onBossNum == 5){
        	boss1.setBackgroundResource(R.drawable.boss1_complete);
        	boss2.setBackgroundResource(R.drawable.boss2_complete);
        	boss3.setBackgroundResource(R.drawable.boss3_complete);
        	boss4.setBackgroundResource(R.drawable.boss4_complete);
        	if(userLevel >= 20)
        		boss5.setBackgroundResource(R.drawable.boss5_ready);
        	else
        		boss5.setBackgroundResource(R.drawable.boss5_locked);
        }else{
        	boss1.setBackgroundResource(R.drawable.boss1_complete);
        	boss2.setBackgroundResource(R.drawable.boss2_complete);
        	boss3.setBackgroundResource(R.drawable.boss3_complete);
        	boss4.setBackgroundResource(R.drawable.boss4_complete);
        	boss5.setBackgroundResource(R.drawable.boss5_complete);
        }
	}
	
	public void onClick(View v) {
		// Tmp boss number
		int bossNum = 0; 
		int foughtboss=0;
		
		// Find out which button was pushed based on its ID
		// and set bossNum accordingly
		
		if(v.getId()==R.id.fightfriends)
		{
			try {
				String abc = fightName.getText().toString();
				postData(abc);
				foughtboss=1;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				foughtboss=1;
			}
			
		}else if (v.getId()== R.id.fightRandom){
			try {
				postData("RNDM23456");
				foughtboss=1;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				foughtboss=1;
			}
		}
		else if (v.getId()== R.id.boss1)
		{
			bossNum = 1;
		}
		else if (v.getId()== R.id.boss2)
		{
			bossNum = 2;
		}
		else if (v.getId()== R.id.boss3)
		{
			bossNum = 3;
		}
		else if (v.getId()== R.id.boss4)
		{
			bossNum = 4;
		}
		else if (v.getId()== R.id.boss5)
		{
			bossNum = 5;
		}
		
		//battleMode(bossNum);
		if (foughtboss==1)
		{
			
		}
		else
		{
		if(canFightBoss(bossNum))
		{
			// If user wins return true bool
			if (battleMode(bossNum))
			{	
				// update the boss number the user can fight once they win
				prefs = this.getSharedPreferences("myweb.csuchico.edu", Context.MODE_PRIVATE);
				int nextBossNum=(prefs.getInt("myweb.csuchico.edu.onBossNum", 0))+1;
				prefs.edit().putInt("myweb.csuchico.edu.onBossNum", nextBossNum).commit();
				
				// rewards user stats for beating a boss
				// returns string to add on to the pop up alerta
				String statAddOns = rewardUser(bossNum);
				
	    		// Create an alert telling user they won
	    		AlertDialog fightWin = new AlertDialog.Builder(this).create();
	    		
	    		// Determine if user beat final boss(5)
	    		if (bossNum == 5)
	    		{	
	    			//set boss to complete instantly
	    			boss5.setBackgroundResource(R.drawable.boss5_complete);
	    			
	    			fightWin.setTitle("         !!VICTORY!!          ");
	    			fightWin.setMessage("    Good Job! You have beat the final boss and have unlocked the abilty to challenge other users. TRAIN ON!!! "+statAddOns);
	    			fightWin.setButton("DONE", new DialogInterface.OnClickListener() {
	    				public void onClick(DialogInterface dialog, int which) {
	    					// here you can add functions
	    				}});
	    			fightWin.show();
	    			//champion = true;
	    		}
	    		else
	    		{	
	    			fightWin.setTitle("---------You Win!---------");
	    			fightWin.setMessage("Congrats! Keep on training the next boss won't be so easy! "+statAddOns);
	    			fightWin.setButton("DONE", new DialogInterface.OnClickListener() {
	    				public void onClick(DialogInterface dialog, int which) {
	    					// here you can add functions
	    				}});
	    			fightWin.show();
	    		}
	    	}
			// Else they LOSE
	    	else 
	    	{
	    		// Pop up an alert telling user they lost
	    		AlertDialog fightLose = new AlertDialog.Builder(this).create();
	    		fightLose.setTitle(" -------------LOSER!!!-------------");
	    		fightLose.setMessage("MUHAHAHAH! You have been defeated. Go exercise some more and challenge me again tommarrow!");
	    		fightLose.setButton("DONE", new DialogInterface.OnClickListener() {	
	    			public void onClick(DialogInterface dialog, int which) {
	    				// here you can add functions
	    			}});
	    		fightLose.show();    		
	    	}
	    }
		else
		{
			String displayVal = "";
			String displayTitle = "";
			int bossNumToFight=prefs.getInt("myweb.csuchico.edu.bossNumToFight", 0);
			//user already bear the boss
			if(bossNumToFight >= bossNum){
				displayTitle = "---------TOO STRONG---------";
				displayVal = "You beat this boss already. Go pick on someone your own size!";
			}else{
				//user is fighting a boss he/she is not allowed to
				String neededLevel = "";
				if(bossNum==1)
					neededLevel = "4";
				else if(bossNum==2)
					neededLevel = "8";
				else if(bossNum==3)
					neededLevel = "12";
				else if(bossNum==4)
					neededLevel = "16";
				else if(bossNum==5)
					neededLevel = "20";
				displayTitle = "---------TOO WEAK---------";
				displayVal = "You must be at least level "+neededLevel+" to fight this boss! Go train!";
			}
			
			AlertDialog notReady = new AlertDialog.Builder(this).create();
			notReady.setTitle(displayTitle);
			notReady.setMessage(displayVal);
			notReady.setButton("DONE", new DialogInterface.OnClickListener() {	
    			public void onClick(DialogInterface dialog, int which) {
    				// here you can add functions
    			}});
			notReady.show();
		}
		}
		//finish();	
		 	
		 
    }
	
	//determines whether or not the use can fight a specified boss
	public Boolean canFightBoss(int bossNumber)
	{
		prefs = this.getSharedPreferences("myweb.csuchico.edu", Context.MODE_PRIVATE);
		int bossNumToFight=prefs.getInt("myweb.csuchico.edu.bossNumToFight", 0);
		int onBossNum=prefs.getInt("myweb.csuchico.edu.onBossNum", 0);
		
		if((onBossNum == bossNumber) && (bossNumToFight >= bossNumber))
			return true;
		else
			return false;
	}
	
	//gives user Boss Number*5 EXP in each stat for beating the boss
	public String rewardUser(int bossNum)
	{	
		//create InputView object so we can call functions
		InputView stats = new InputView();
		
		prefs = this.getSharedPreferences("myweb.csuchico.edu", Context.MODE_PRIVATE);
		String statVal = "";
		int increaseBy = 0;
		int userExpBonus = 0;
		if(bossNum == 1){
			statVal = "You increased each stat by 1 level!";
			increaseBy = 1;
			userExpBonus = 5;
		}else if(bossNum == 2){
			statVal = "You increased each stat by 2 levels!";
			increaseBy = 2;
			userExpBonus = 10;
		}else if(bossNum == 3){
			statVal = "You increased each stat by 3 levels!";
			increaseBy = 3;
			userExpBonus = 15;
		}else if(bossNum == 4){
			statVal = "You increased each stat by 4 levels!";
			increaseBy = 4;
			userExpBonus = 20;
		}else{
			statVal = "You increased each stat by 5 levels!";
			increaseBy = 5;
			userExpBonus = 25;
		}
		
		int newUserExp = userExpBonus+prefs.getInt("myweb.csuchico.edu.users_Exp", 0);
		int oldLevel = prefs.getInt("myweb.csuchico.edu.users_Level", 0);
		int newLevel = stats.getNewLevel(newUserExp);
		
		//update users stats based on what boss they just beat
	  	prefs.edit().putInt("myweb.csuchico.edu.health_Level", (increaseBy+prefs.getInt("myweb.csuchico.edu.health_Level", 0))).commit();
	  	prefs.edit().putInt("myweb.csuchico.edu.defense_Level", (increaseBy+prefs.getInt("myweb.csuchico.edu.defense_Level", 0))).commit();
	  	prefs.edit().putInt("myweb.csuchico.edu.agility_Level", (increaseBy+prefs.getInt("myweb.csuchico.edu.agility_Level", 0))).commit();
	  	prefs.edit().putInt("myweb.csuchico.edu.strengtha_Level", (increaseBy+prefs.getInt("myweb.csuchico.edu.strengtha_Level", 0))).commit();
	  	prefs.edit().putInt("myweb.csuchico.edu.strengthl_Level", (increaseBy+prefs.getInt("myweb.csuchico.edu.strengthl_Level", 0))).commit();
	  	prefs.edit().putInt("myweb.csuchico.edu.users_Exp", newUserExp).commit();
	  	prefs.edit().putInt("myweb.csuchico.edu.users_Level", newLevel).commit();
	  	
	  	//edit what boss can be fought
  		if(newLevel >= 20)
  			prefs.edit().putInt("myweb.csuchico.edu.bossNumToFight", 5).commit();
  		else if(newLevel >= 16)
  			prefs.edit().putInt("myweb.csuchico.edu.bossNumToFight", 4).commit();
  		else if(newLevel >= 12)
  			prefs.edit().putInt("myweb.csuchico.edu.bossNumToFight", 3).commit();
  		else if(newLevel >= 8)
  			prefs.edit().putInt("myweb.csuchico.edu.bossNumToFight", 2).commit();
  		else if(newLevel >= 4)
  			prefs.edit().putInt("myweb.csuchico.edu.bossNumToFight", 1).commit();
  			
  		//reset the boss images
  		setBossImages();
  		
  		//if the user levels up we notify them
  		if(newLevel != oldLevel){
  			// Let user know what level reached
	  		Toast.makeText(FightsView.this,  "Congratulations! You've Reached Level "+newLevel, Toast.LENGTH_LONG).show();
	  		showDialog(0);
  		}
	  	
		return statVal;
	}
		
    public Boolean battleMode(int bossNumber)
    {	
    	prefs = this.getSharedPreferences("myweb.csuchico.edu", Context.MODE_PRIVATE);
        // Users default chance to win
    	double chanceToWin = 65.0;
    	
    	// Instantiate boss stats
    	int boss_AgilityTotal = 0;
		int boss_ArmStrengthTotal = 0;
		int boss_LegStrengthTotal = 0;
		int boss_DefenseTotal = 0;
		int boss_HealthTotal = 0;
    	
    	// Read in user levels
    	int users_Agility=prefs.getInt("myweb.csuchico.edu.agility_Level", 0);
      	int users_ArmStrength=prefs.getInt("myweb.csuchico.edu.strengtha_Level", 0);
      	int users_LegStrength=prefs.getInt("myweb.csuchico.edu.strengthl_Level", 0);
      	int users_Health=prefs.getInt("myweb.csuchico.edu.health_Level", 0);
      	int users_Defense=prefs.getInt("myweb.csuchico.edu.defense_Level", 0);
      	
      	// Read in user stat left over exp
      	int users_Agility_Exp=prefs.getInt("myweb.csuchico.edu.agility_Exp", 0);
      	int users_ArmStrength_Exp=prefs.getInt("myweb.csuchico.edu.strengtha_Exp", 0);
      	int users_LegStrength_Exp=prefs.getInt("myweb.csuchico.edu.strengthl_Exp", 0);
      	int users_Health_Exp=prefs.getInt("myweb.csuchico.edu.health_Exp", 0);
      	int users_Defense_Exp=prefs.getInt("myweb.csuchico.edu.defense_Exp", 0);
      	
      	// Create user total stat exp
      	int users_AgilityTotal = users_Agility * 100 + users_Agility_Exp;
      	int users_ArmStrengthTotal = users_ArmStrength * 100 + users_ArmStrength_Exp;
      	int users_LegStrengthTotal = users_LegStrength * 100 + users_LegStrength_Exp;
      	int users_HealthTotal = users_Health * 100 + users_Health_Exp;
      	int users_DefenseTotal = users_Defense * 100 + users_Defense_Exp;

      	// Switch statement to assign boss appropriate stats 
      	switch (bossNumber)
    	{
    	case 1:
    		boss_AgilityTotal = 400;
    		boss_ArmStrengthTotal = 400;
    		boss_LegStrengthTotal = 400;
    		boss_DefenseTotal = 400;
    		boss_HealthTotal = 500;
    		
    		break;
    	case 2:
    		boss_AgilityTotal = 1300;
    		boss_ArmStrengthTotal = 1300;
    		boss_LegStrengthTotal = 1300;
    		boss_DefenseTotal = 1300;
    		boss_HealthTotal = 1400;
    		
    		break;
    	case 3:
    		boss_AgilityTotal = 2900;
    		boss_ArmStrengthTotal = 2900;
    		boss_LegStrengthTotal = 2900;
    		boss_DefenseTotal = 2900;
    		boss_HealthTotal = 3000;
    		
    		break;
    	case 4:
    		boss_AgilityTotal = 5200;
    		boss_ArmStrengthTotal = 5200;
    		boss_LegStrengthTotal = 5200;
    		boss_DefenseTotal = 5200;
    		boss_HealthTotal = 5300;
    		
    		break;
    	case 5:
    		boss_AgilityTotal = 8100;
    		boss_ArmStrengthTotal = 8100;
    		boss_LegStrengthTotal = 8100;
    		boss_DefenseTotal = 8100;
    		boss_HealthTotal = 8100;
    		
    		break;
    	
    	}
    	
    	// Compare and add/subtract percent chance to win
      	// Make sure we only award +7 at max!
      	if(users_AgilityTotal >= (boss_AgilityTotal*2))
      		chanceToWin += 7;
      	else
      		chanceToWin += ( (users_AgilityTotal - boss_AgilityTotal) / (double)boss_AgilityTotal * 7);
      	
      	if(users_ArmStrengthTotal >= (boss_ArmStrengthTotal*2))
      		chanceToWin += 7;
      	else
      		chanceToWin += ( (users_ArmStrengthTotal - boss_ArmStrengthTotal) / (double)boss_ArmStrengthTotal * 7);
    	
      	if(users_LegStrengthTotal >= (boss_LegStrengthTotal*2))
      		chanceToWin += 7;
      	else
      		chanceToWin += ( (users_LegStrengthTotal - boss_LegStrengthTotal) / (double)boss_LegStrengthTotal * 7);
    	
      	if(users_DefenseTotal >= (boss_DefenseTotal*2))
      		chanceToWin += 7;
      	else
      		chanceToWin += ( (users_DefenseTotal - boss_DefenseTotal) / (double)boss_DefenseTotal * 7);
      	
      	if(users_HealthTotal >= (boss_HealthTotal*2))
      		chanceToWin += 7;
      	else
      		chanceToWin += ( (users_HealthTotal - boss_HealthTotal) / (double)boss_HealthTotal * 7);
    	
    	// Chance to win is now set to correct amount
    	// So take a random number out of 0-99 and if the random number is less than
    	// or equal to user chance to win then they win... if it is greater than they lose
    	
    	Random randomGenerator = new Random();
    	int randomNum = randomGenerator.nextInt(100); 	
    	
    	// Return bool for win or lose
    	if (randomNum < chanceToWin)
    		return true;
    	else
    		return false;	
    }	
    
    //Posting to server on opening the TAB
    //int type=1 just post
    //int type=2 postandfight
    private void postData(String nameToFight) throws  UnsupportedEncodingException
    {
    	 String dataToSend="";
 	   // String data="";
 		
 		//create shared preference object
        SharedPreferences  prefs = this.getSharedPreferences("myweb.csuchico.edu", Context.MODE_PRIVATE);
       
        //Getting the data stored by the shared prefs
       // Read in user levels
    	int users_Agility=prefs.getInt("myweb.csuchico.edu.agility_Level", 0);
      	int users_ArmStrength=prefs.getInt("myweb.csuchico.edu.strengtha_Level", 0);
      	int users_LegStrength=prefs.getInt("myweb.csuchico.edu.strengthl_Level", 0);
      	int users_Health=prefs.getInt("myweb.csuchico.edu.health_Level", 0);
      	int users_Defense=prefs.getInt("myweb.csuchico.edu.defense_Level", 0);
      	
      	// Read in user stat left over exp
      	int users_Agility_Exp=prefs.getInt("myweb.csuchico.edu.agility_Exp", 0);
      	int users_ArmStrength_Exp=prefs.getInt("myweb.csuchico.edu.strengtha_Exp", 0);
      	int users_LegStrength_Exp=prefs.getInt("myweb.csuchico.edu.strengthl_Exp", 0);
      	int users_Health_Exp=prefs.getInt("myweb.csuchico.edu.health_Exp", 0);
      	int users_Defense_Exp=prefs.getInt("myweb.csuchico.edu.defense_Exp", 0);
      	String username=prefs.getString("myweb.csuchico.edu.user_Name", "FAILED");
      	
      	
      	//Posting to your Server
      	
      	
      		dataToSend= username + ":"+ nameToFight +"|"+ users_Agility + " " + users_Agility_Exp +" "+ users_ArmStrength + " "+ users_ArmStrength_Exp + " " + users_LegStrength + " "+ users_LegStrength_Exp + " "+ users_Defense + " "+ users_Defense_Exp + " " + users_Health + " "+users_Health_Exp ;	
      	
          
         
 		
 			String data = URLEncoder.encode("fightData", "UTF-8") 
 			               + "=" + URLEncoder.encode(dataToSend, "UTF-8");
 	

           // Send data 
 			
        String text = "";
        BufferedReader reader=null;
        

        // Send data 
      try
      { 
        
          // Defined URL  where to send data
          URL url = new URL("http://myweb.csuchico.edu/~dshaikh/determineWinner.php");
           
       // Send POST data request

        URLConnection conn = url.openConnection(); 
        conn.setDoOutput(true); 
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream()); 
        wr.write( data ); 
        wr.flush(); 
    
        // Get the server response 
         
      reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      StringBuilder sb = new StringBuilder();
      String line = null;
      
      // Read Server Response
      while((line = reader.readLine()) != null)
          {
                 // Append server response in string
                 sb.append(line+ "\n");
          }
          
          
          text = sb.toString();
      }
      catch(Exception ex)
      {
           
      }
      finally
      {
          try
          {

              reader.close();
          }

          catch(Exception ex) {}
      }
      
      //increase stats if user wins fight
      Boolean hasWon = text.matches(".*\\won\\b.*");
      if(hasWon){
    	int increaseBy = 2;
        InputView stats = new InputView();
    	int newUserExp = 10+prefs.getInt("myweb.csuchico.edu.users_Exp", 0);
  		int oldLevel = prefs.getInt("myweb.csuchico.edu.users_Level", 0);
  		int newLevel = stats.getNewLevel(newUserExp);
  		
  		//update users stats based on what boss they just beat
  	  	prefs.edit().putInt("myweb.csuchico.edu.health_Level", (increaseBy+prefs.getInt("myweb.csuchico.edu.health_Level", 0))).commit();
  	  	prefs.edit().putInt("myweb.csuchico.edu.defense_Level", (increaseBy+prefs.getInt("myweb.csuchico.edu.defense_Level", 0))).commit();
  	  	prefs.edit().putInt("myweb.csuchico.edu.agility_Level", (increaseBy+prefs.getInt("myweb.csuchico.edu.agility_Level", 0))).commit();
  	  	prefs.edit().putInt("myweb.csuchico.edu.strengtha_Level", (increaseBy+prefs.getInt("myweb.csuchico.edu.strengtha_Level", 0))).commit();
  	  	prefs.edit().putInt("myweb.csuchico.edu.strengthl_Level", (increaseBy+prefs.getInt("myweb.csuchico.edu.strengthl_Level", 0))).commit();
  	  	prefs.edit().putInt("myweb.csuchico.edu.users_Exp", newUserExp).commit();
  	  	prefs.edit().putInt("myweb.csuchico.edu.users_Level", newLevel).commit();
    	text += " +10xp";
      }
      
      //Toast to tell user if he won or lost the fight
      if (!nameToFight.equals("HMON1234UPDTE5678"))
      {
    	  Toast.makeText(FightsView.this,  text, Toast.LENGTH_LONG).show();
      }  
   }
}
