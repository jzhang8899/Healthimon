package myweb.csuchico.edu;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class AndroidTabLayoutActivity extends TabActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.android_tab_layout_activity);
        
		TabHost tabHost = getTabHost();

		TabSpec addspec = tabHost.newTabSpec("Add");
		addspec.setIndicator("", getResources().getDrawable(R.drawable.icon_add_tab));
		Intent addIntent = new Intent(this, AddView.class);
		addspec.setContent(addIntent);

		TabSpec socialspec = tabHost.newTabSpec("Social");
		socialspec.setIndicator("", getResources().getDrawable(R.drawable.icon_social_tab));
		Intent socialIntent = new Intent(this, SocialView.class);
		socialspec.setContent(socialIntent);
        
		// Adding all TabSpec to TabHost
		tabHost.addTab(tabHost.newTabSpec("Character") //add character tab
				.setIndicator("", getResources().getDrawable(R.drawable.icon_character_tab))
				.setContent(new Intent(this, CharacterView.class)
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
		tabHost.addTab(tabHost.newTabSpec("Fight") //add fights tab
				.setIndicator("", getResources().getDrawable(R.drawable.icon_fights_tab))
				.setContent(new Intent(this, FightsView.class)
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
		tabHost.addTab(addspec); //add ADD tab
		tabHost.addTab(tabHost.newTabSpec("Charts") //add charts tab
				.setIndicator("", getResources().getDrawable(R.drawable.icon_chart_tab))
				.setContent(new Intent(this, ChartView.class)
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
		tabHost.addTab(socialspec); //add social tab
	}
}