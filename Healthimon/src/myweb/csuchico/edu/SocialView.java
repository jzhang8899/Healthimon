package myweb.csuchico.edu;

//import java.io.File;
// Resource Library from 3 pillar global
import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;

import android.app.Activity;
//import android.content.Intent;
//import android.net.Uri;
import android.os.Bundle;
//import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SocialView extends Activity {

	// SocialAuth Component
	SocialAuthAdapter adapter;
	boolean status;

	// Android Components
	Button update;
	EditText edit;
	 public void cancelText (View view)
	    {
	    	edit = (EditText) findViewById(R.id.editTxt);
	        edit.setText("");
	    }
	 public void requestFriend (View view)
	    {
	    	edit = (EditText) findViewById(R.id.editTxt);
	        edit.setText("Come join me at https://play.google.com/store/");
	    }
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.social_layout);

		// Introduction Message
		TextView textview = (TextView) findViewById(R.id.text);
		textview.setText("Connect any provider and tell your friends your workout achievements!!!.");
        // create share bar
		LinearLayout bar = (LinearLayout) findViewById(R.id.sharebutton);
		// Add Bar to library
		adapter = new SocialAuthAdapter(new ResponseListener());

		// Add providers
		adapter.addProvider(Provider.FACEBOOK, R.drawable.facebook);
		adapter.addProvider(Provider.TWITTER, R.drawable.twitter);
		adapter.addProvider(Provider.LINKEDIN, R.drawable.linkedin);
		
	    // Provider requires setting user call back url
		adapter.addCallBack(Provider.TWITTER, "http://dummysite.com");
        // enable provider
		adapter.enable(bar);

	}

	private final class ResponseListener implements DialogListener {
		@Override
		public void onComplete(Bundle values) {

			// Variable to receive message status
			Log.d("Share-Bar", "Authentication Successful");

			// Get name of provider after authentication
			final String providerName = values.getString(SocialAuthAdapter.PROVIDER);
			Log.d("Share-Bar", "Provider Name = " + providerName);
			// Toast.Length_Long = Ox00000001 second by default
			Toast.makeText(SocialView.this, providerName + " connected", Toast.LENGTH_SHORT).show();

			update = (Button) findViewById(R.id.update);
			edit = (EditText) findViewById(R.id.editTxt);

			update.setOnClickListener(new OnClickListener() 
			{
				public void onClick(View v) {
					adapter.updateStatus(edit.getText().toString(), new MessageListener(), true);
				}
			});

			
		}

		@Override
		public void onError(SocialAuthError error) {
			error.printStackTrace();
			Log.d("Share-Bar", error.getMessage());
		}

		@Override
		public void onCancel() {
			Log.d("Share-Bar", "Authentication Cancelled");
		}

		@Override
		public void onBack() {
			Log.d("Share-Bar", "Dialog Closed by pressing Back Key");

		}
	}

	// To get status of message after authentication
	private final class MessageListener implements SocialAuthListener<Integer> {
		@Override
		public void onExecute(String provider, Integer t) {
			Integer status = t;
			// server/port socket return 200,201,204
			if (status.intValue() == 200 || status.intValue() == 201 || status.intValue() == 204)
			{
				Toast.makeText(SocialView.this, "Message posted on " + provider, Toast.LENGTH_LONG).show();
			    edit = (EditText) findViewById(R.id.editTxt);
                edit.setText("");
			}
            else
				Toast.makeText(SocialView.this, "Message not posted on" + provider, Toast.LENGTH_LONG).show();
		}

		@Override
		public void onError(SocialAuthError e) {

		}
	}
}
