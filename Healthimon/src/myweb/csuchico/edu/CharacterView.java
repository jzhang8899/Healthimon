package myweb.csuchico.edu;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CharacterView extends Activity implements OnClickListener{
	
	// Private variables
	private TextView strengthaView,strengthlView,agilityView,defenseView,healthView,userLevel,userInfo;
	private SharedPreferences prefs;
	private ProgressBar progressBarArmStr,progressBarLegStr,progressBarAgility,progressBarDefense,progressBarLvl,progressBarHlth;
    private ImageView armImg, legImg, chestImg, absImg;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.character_layout);
        
		// Create shared preference object
		prefs = this.getSharedPreferences("myweb.csuchico.edu", Context.MODE_PRIVATE);
		String userNameVal=prefs.getString("myweb.csuchico.edu.user_Name", "FAILED"); 
	    int userAgeVal=prefs.getInt("myweb.csuchico.edu.user_Age", 69); 
	    int userWeightVal=prefs.getInt("myweb.csuchico.edu.user_Weight", 69);
	    
	    //Get imageStat average to display image
	    int armAvg = 
	    		(
	    		prefs.getInt("myweb.csuchico.edu.bicepCurl_Level",0) + 
	    		prefs.getInt("myweb.csuchico.edu.tricepExtension_Level",0) + 
	    		prefs.getInt("myweb.csuchico.edu.shoulderPress_Level",0) + 
	    		prefs.getInt("myweb.csuchico.edu.tricepDips_Level",0) +
	    		prefs.getInt("myweb.csuchico.edu.shoulderExtend_Level",0)
	    		) / 5;
	    
	    int legAvg = 
	    		(
	    		prefs.getInt("myweb.csuchico.edu.squats_Level",0) + 
	    		prefs.getInt("myweb.csuchico.edu.legExtension_Level",0) + 
	    		prefs.getInt("myweb.csuchico.edu.legCurls_Level",0)
	    		) / 3;
	    int chestAvg = 
	    		(
	    		prefs.getInt("myweb.csuchico.edu.pushUp_Level",0) + 
	    		prefs.getInt("myweb.csuchico.edu.bench_Level",0) + 
	    		prefs.getInt("myweb.csuchico.edu.dumbbellFlys_Level",0)
	    		) / 3;
	    
	    int absAvg =
	    		(
	    		prefs.getInt("myweb.csuchico.edu.sitUp_Level",0) + 
	    		prefs.getInt("myweb.csuchico.edu.crunches_Level",0)
	    		) / 2;
	    
	    //calculate percentages for stat bars
	    int agilityPercent=prefs.getInt("myweb.csuchico.edu.agility", 80);
	    int armStrPercent=prefs.getInt("myweb.csuchico.edu.strengtha", 80);
	  	int legStrPercent=prefs.getInt("myweb.csuchico.edu.strengthl", 0);
	  	int healthPercent=prefs.getInt("myweb.csuchico.edu.health", 0);
	  	int defensePercent=prefs.getInt("myweb.csuchico.edu.defense", 0);
	  	
	  	int curLvl=prefs.getInt("myweb.csuchico.edu.users_Level", 0);
	  	int curExp=prefs.getInt("myweb.csuchico.edu.users_Exp", 0);
	  	int curLvlExp=curLvl*curLvl;
	  	int nextLvlExp=(curLvl+1)*(curLvl+1);
	  	int expNeededToLvlUp=nextLvlExp-curLvlExp;
	  	int usrExpProgress=curExp-curLvlExp;
	  	double lvlDec=(((double)usrExpProgress)/expNeededToLvlUp)*100;
	  	int lvlPercent=(int)lvlDec;
	  	
	  	
	  	
		// Create Text view's for stats and user level/exp
		userInfo=(TextView)findViewById (R.id.userInfo);
		userInfo.setText("Name: " + userNameVal + "   Weight: " + userWeightVal);
		
		userLevel=(TextView)findViewById (R.id.userLevel);
		userLevel.setText("Level: " + prefs.getInt("myweb.csuchico.edu.users_Level", 0));
		progressBarLvl = (ProgressBar) findViewById(R.id.progressBarLvl);
		progressBarLvl.setProgress(lvlPercent);
		
		strengthaView=(TextView)findViewById (R.id.strengthaView);
		strengthaView.setText("Arm Strength: " + prefs.getInt("myweb.csuchico.edu.strengtha_Level", 0));
		progressBarArmStr = (ProgressBar) findViewById(R.id.progressBarArmStr);
		progressBarArmStr.setProgress(armStrPercent);
		
		strengthlView=(TextView)findViewById (R.id.strengthlView);
		strengthlView.setText("Leg Strength: " + prefs.getInt("myweb.csuchico.edu.strengthl_Level", 0));
		progressBarLegStr = (ProgressBar) findViewById(R.id.progressBarLegStr);
		progressBarLegStr.setProgress(legStrPercent);
        
		agilityView=(TextView)findViewById (R.id.agilityView);
		agilityView.setText("Agility: " + prefs.getInt("myweb.csuchico.edu.agility_Level", 0));
		progressBarAgility = (ProgressBar) findViewById(R.id.progressBarAgility);
		progressBarAgility.setProgress(agilityPercent);
        
		defenseView=(TextView)findViewById (R.id.defenseView);
		defenseView.setText("Defense: " + prefs.getInt("myweb.csuchico.edu.defense_Level", 0));
		progressBarDefense = (ProgressBar) findViewById(R.id.progressBarDefense);
		progressBarDefense.setProgress(defensePercent);
		
		healthView=(TextView)findViewById (R.id.healthView);
		healthView.setText("Health: " + prefs.getInt("myweb.csuchico.edu.health_Level", 0));
		progressBarHlth = (ProgressBar) findViewById(R.id.progressBarHealth);
		progressBarHlth.setProgress(healthPercent);
		
	  	// Set image views equal to XML equivalent
	  	armImg = (ImageView) findViewById(R.id.armImgView);
	  	legImg = (ImageView) findViewById(R.id.legImgView);
	  	chestImg = (ImageView) findViewById(R.id.chestImgView);
	  	absImg = (ImageView) findViewById(R.id.absImgView);
	  	
		// Figure out what arm/shoulder img to display
		if (armAvg < 5)
		{
			// Leave image blank to show base body part (stage 1)
			armImg.setImageDrawable(null);
		}
		else if (armAvg >= 5 && armAvg <= 9)
		{
			// Stage 2
			armImg.setImageDrawable(getResources().getDrawable(R.drawable.arms_level2));
		}
		else if (armAvg >=10 && armAvg <= 14)
		{
			// Stage 3
			armImg.setImageDrawable(getResources().getDrawable(R.drawable.arms_level3));
		}
		else if (armAvg >= 15 && armAvg <= 19)
		{
			// Stage 4
			armImg.setImageDrawable(getResources().getDrawable(R.drawable.arms_level4));
		}
		else if (armAvg >= 20)
		{
			// Stage 5
			armImg.setImageDrawable(getResources().getDrawable(R.drawable.arms_level5));
		}
	
		// Figure out what leg img to display
		if (legAvg < 5)
		{
			// Leave image blank to show base body part (stage 1)
			legImg.setImageDrawable(null);
		}
		else if (legAvg >= 5 && legAvg <= 9)
		{
			// Stage 2
			legImg.setImageDrawable(getResources().getDrawable(R.drawable.legs_level2));
		}
		else if (legAvg >=10 && legAvg <= 14)
		{
			// Stage 3
			legImg.setImageDrawable(getResources().getDrawable(R.drawable.legs_level3));
		}
		else if (legAvg >= 15 && legAvg <= 19)
		{
			// Stage 4
			legImg.setImageDrawable(getResources().getDrawable(R.drawable.legs_level4));
		}
		else if (legAvg >= 20)
		{
			// Stage 5
			legImg.setImageDrawable(getResources().getDrawable(R.drawable.legs_level5));
		}

		// Figure out what chest img to display
		if (chestAvg < 5)
		{
			// Leave image blank to show base body part (stage 1)
			chestImg.setImageDrawable(null);
		}
		else if (chestAvg >= 5 && chestAvg <= 9)
		{
			// Stage 2
			chestImg.setImageDrawable(getResources().getDrawable(R.drawable.chest_level2));
		}
		else if (chestAvg >=10 && chestAvg <= 14)
		{
			// Stage 3
			chestImg.setImageDrawable(getResources().getDrawable(R.drawable.chest_level3));
		}
		else if (chestAvg >= 15 && chestAvg <= 19)
		{
			// Stage 4
			chestImg.setImageDrawable(getResources().getDrawable(R.drawable.chest_level4));
		}
		else if (chestAvg >= 20)
		{
			// Stage 5
			chestImg.setImageDrawable(getResources().getDrawable(R.drawable.chest_level5));
		}
		
		// Figure out what abs img to display
		if (absAvg < 5)
		{
			// Leave image blank to show base body part (stage 1)
			absImg.setImageDrawable(null);
		}
		else if (absAvg >= 5 && absAvg <= 9)
		{
			// Stage 2
			absImg.setImageDrawable(getResources().getDrawable(R.drawable.abs_level1));
		}
		else if (absAvg >=10 && absAvg <= 14)
		{
			// Stage 3
			absImg.setImageDrawable(getResources().getDrawable(R.drawable.abs_level3));
		}
		else if (absAvg >= 15 && absAvg <= 19)
		{
			// Stage 4
			absImg.setImageDrawable(getResources().getDrawable(R.drawable.abs_level4));
		}
		else if (absAvg >= 20)
		{
			// Stage 5
			absImg.setImageDrawable(getResources().getDrawable(R.drawable.abs_level5));
		}
    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}
