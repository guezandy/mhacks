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
import android.widget.RelativeLayout;
import android.widget.Toast;



@SuppressLint("CommitTransaction")
public class VerifyText extends Fragment {
    private final String TAG = VerifyText.class.getSimpleName(); 
	ParseUser user = new ParseUser();
	String solution;
	String clueTitle;
    ActionBar actionBar;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
    	
    	//LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_home,
        //        container, false);
    	final RelativeLayout mLinearLayout = (RelativeLayout) inflater.inflate(R.layout.response_text,
                container, false);

    	actionBar = getActivity().getActionBar();
    	final EditText textVerify = (EditText) mLinearLayout.findViewById(R.id.response_text);
    	
    	Button submit = (Button) mLinearLayout.findViewById(R.id.textVerify);
    	submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user.getCurrentUser();
				solution = textVerify.getText().toString();
clueTitle = "";

				VerifyModel ver = new VerifyModel();
				ver.setTitle(solution);
				ver.setAuthor(user.getCurrentUser());
				ver.setClueID(clueTitle);
				// Save the meal and return
				ver.saveInBackground(new SaveCallback() {

					@Override
					public void done(ParseException e) {
						if (e == null) {
							getActivity().setResult(Activity.RESULT_OK);
							//Intent i = new Intent(getActivity(), )
							getActivity().finish();
						} else {
							Toast.makeText(
									getActivity().getApplicationContext(),
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
        return mLinearLayout;
    }
    
    @SuppressWarnings("null")
	public void replaceFrag() {
    	FragmentManager fragmentManager2 = getFragmentManager();
    	FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
    	Fragment fragment2 = new GameFragment();
    	fragmentTransaction2.addToBackStack("xyz");
    	fragmentTransaction2.hide(VerifyText.this);
    	
    	////////////////////
    	Activity act = new Activity();
    	((HuntActivity) act).updateMainContent(2, null);
    	fragmentTransaction2.add(fragment2, "start");
		//fragmentManager2.beginTransaction().replace(R.id.activity_frame, fragment2).addToBackStack("tag")
		//.commit();
    	//fragmentTransaction2.add(R.id.activity_frame, fragment2);
    	fragmentTransaction2.commit();
    }
    
    public VerifyText() {
        // TODO Auto-generated constructor stub
    	//System.out.println("Do we go in here?");
    	    	

    }    
}
