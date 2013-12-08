package myweb.csuchico.edu;

import java.util.ArrayList;

import net.kenyang.piechart.PieChart;
import net.kenyang.piechart.PieChart.OnSelectedLisenter;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class ChartView extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_layout);
        
       
         /////////////////Pie Chart Stuff Here//////////////////////////////
        
        //create shared preference object
        prefs = this.getSharedPreferences("myweb.csuchico.edu", Context.MODE_PRIVATE);
        
        //Initilaize Pie Chart
        final PieChart pie = (PieChart) findViewById(R.id.pieChart);
        
        //Fetch Values to be Displayed on Pie Chart
        agility=(double)prefs.getInt("myweb.csuchico.edu.agility_Level", 0);
      	strengthArms=(double)prefs.getInt("myweb.csuchico.edu.strengtha_Level", 0);
      	strengthLegs=(double)prefs.getInt("myweb.csuchico.edu.strengthl_Level", 0);
      	health=(double)prefs.getInt("myweb.csuchico.edu.health_Level", 0);
      	defense=(double)prefs.getInt("myweb.csuchico.edu.defense_Level", 0);
        
      	
      //Convert the values fethed such that their percentage sum is equal to 100
        double sum=agility+strengthArms+strengthLegs+health+defense;
        
        //Convert the values to appropriate percentages
        final float fagility=((float)(agility/sum))*100;
        final float fstrengthArms=((float)(strengthArms/sum))*100;
        final float fstrengthLegs=((float)(strengthLegs/sum))*100;
        final float fdefense=((float)(defense/sum))*100;
        float healths=((float)(health/sum))*100;
        
        //PreCautionery check as Pie chart will throw an error if sum is not equal to 100 
        final float fhealth = healths+(100-(fagility+fstrengthArms+fstrengthLegs+fdefense+healths));
        
        ArrayList<Float> alPercentage = new ArrayList<Float>();
        alPercentage.add(fagility);
        alPercentage.add(fstrengthArms);
        alPercentage.add(fstrengthLegs);
        alPercentage.add(fdefense);
        alPercentage.add(fhealth);
       
       

        try {
          // setting data
          pie.setAdapter(alPercentage);

          // setting a listener 
          pie.setOnSelectedListener(new OnSelectedLisenter() {
            @Override
            public void onSelected(int iSelectedIndex) {
            	
            	switch(iSelectedIndex)
            	{
            	case 0: 
            		Toast.makeText(ChartView.this,  "Agility:"+ (int)fagility, Toast.LENGTH_SHORT).show();
            		break;
            	case 1: 
            		Toast.makeText(ChartView.this,  "Arms Strength:"+ (int)fstrengthArms, Toast.LENGTH_SHORT).show();
            		break;
            	case 2: 
            		Toast.makeText(ChartView.this,  "Legs Strength:"+ (int)fstrengthLegs, Toast.LENGTH_SHORT).show();
            		break;
            	case 3: 
            		Toast.makeText(ChartView.this,  "Defense:"+ (int)fdefense, Toast.LENGTH_SHORT).show();
            		break;
            	case 4: 
            		Toast.makeText(ChartView.this,   "Health:"+ (int)fhealth, Toast.LENGTH_SHORT).show();
            		break;
            	default: 
            		break;
                 }
            }
          });  
        } catch (Exception e) {
          if (e.getMessage().equals(PieChart.ERROR_NOT_EQUAL_TO_100)){
            Log.e("kenyang","percentage is not equal to 100");
          }
        }
        
        
        ////////////The Line Graph Stuff Here/////////////////
           // init weight series data
           double wt1=(double)prefs.getInt("myweb.csuchico.edu.weight1", 0);
           double wt2=(double)prefs.getInt("myweb.csuchico.edu.weight2", 0);
           double wt3=(double)prefs.getInt("myweb.csuchico.edu.weight3", 0);
           double wt4=(double)prefs.getInt("myweb.csuchico.edu.weight4", 0);
           double wt5=(double)prefs.getInt("myweb.csuchico.edu.weight5", 0);
              
           GraphViewSeries weightSeries = new GraphViewSeries(new GraphViewData[] {
                     new GraphViewData(1,wt1)
                     , new GraphViewData(2,wt2 )
                     , new GraphViewData(3, wt3)
                     , new GraphViewData(4, wt4)
                     , new GraphViewData(5, wt5)
           });

        GraphView graphView = new LineGraphView(
              this // context
              , " " // heading
        );
        graphView.addSeries(weightSeries); // data
         
        LinearLayout layout = (LinearLayout) findViewById(R.id.relative);
        layout.addView(graphView);
    }
    
    private double agility,defense,strengthArms,strengthLegs,health;
    private SharedPreferences prefs;
}
