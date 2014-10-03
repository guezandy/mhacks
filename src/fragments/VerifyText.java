package fragments;

import java.util.ArrayList;

import model.ClueModel;
import model.VerifyModel;

import com.example.shotchart.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import activities.HuntActivity;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
//import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.content.DialogInterface;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class VerifyText extends Activity {
    private final String TAG = VerifyText.class.getSimpleName(); 
	ParseUser user;
	String solution;
	String clueTitle;
    ActionBar actionBar;
    
  @Override
  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.response_text);
		final ParseUser user = new ParseUser();
		final String solution;
		final String clueTitle= "";
	    final ActionBar actionBar;
	    final Bundle extras = getIntent().getExtras(); 
	    
	    

    	actionBar = getActionBar();
    	final EditText textVerify = (EditText) findViewById(R.id.response_text);
    	
    	Button submit = (Button) findViewById(R.id.textVerify);
    	submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user.getCurrentUser();

				VerifyModel ver = new VerifyModel();
				ver.setString(textVerify.getText().toString());
				ver.setAuthor(user.getCurrentUser());
				ver.setClueID(extras.getString("clueTitle"));
				// Save the meal and return
				ver.saveInBackground(new SaveCallback() {

					@Override
					public void done(ParseException e) {
						if (e == null) {
							setResult(Activity.RESULT_OK);
							//Intent i = new Intent(getActivity(), )
							finish();
						} else {
							Toast.makeText(
									getApplicationContext(),
									"Error saving: " + e.getMessage(),
									Toast.LENGTH_SHORT).show();
						}
					}

				});
				
			}
    	});
 //   	actionBar.removeAllTabs();
 //   	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    	
        // Inflate the layout for this fragment
        //return mLinearLayout;
    }
    
    public VerifyText() {
        // TODO Auto-generated constructor stub
    	//System.out.println("Do we go in here?");
    	    	

    }    
}
