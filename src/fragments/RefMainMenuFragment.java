package fragments;

import java.util.ArrayList;

import model.ClueModel;

import com.example.shotchart.R;

import activities.HuntActivity;
import activities.HuntApplication;
import activities.SettingsActivity;
import activities.VerifyImageActivity;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
//import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.content.DialogInterface;
import android.content.Intent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;



@SuppressLint("CommitTransaction")
public class RefMainMenuFragment extends Fragment {
    private final String TAG = MainMenuFragment.class.getSimpleName();    
    ActionBar actionBar;
	private static final int ADD_FRAGMENT = 1;
	private static final int SETTINGS_FRAGMENT = 2;
	private static final int REFLECTION_FRAGMENT = 3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
    	
    	//LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_home,
        //        container, false);
    	final LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.ref_frag_main_menu,
                container, false);

    	actionBar = getActivity().getActionBar();

    	
    	ImageView make = (ImageView)mLinearLayout.findViewById(R.id.b2);
    	make.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Replace the frag with game frag
	    		Intent i = new Intent(v.getContext(), SettingsActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				System.out.println("starting photo activity");
				v.getContext().startActivity(i);
			}
    	});
    	
    	ImageView logo = (ImageView)mLinearLayout.findViewById(R.id.logo);
    	logo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Replace the frag with game frag
				replaceFrag(REFLECTION_FRAGMENT);
			}
    	});
    	
 //   	actionBar.removeAllTabs();
 //   	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    	
        // Inflate the layout for this fragment
        return mLinearLayout;
    }
    
    @SuppressWarnings("null")
	public void replaceFrag(int id) {
    	FragmentManager fragmentManager2 = getFragmentManager();
    	//FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
    	Fragment fragment2;
    	switch(id) {
    	case ADD_FRAGMENT:
    		fragment2 = new VerifyImage();
    		((VerifyImage) fragment2).verify();
    		break;
    	case REFLECTION_FRAGMENT:
    		fragment2 = new RefReflectionFragment();
    		break;
    	default:
    		Log.e(TAG, "Inside MainMenuFragment default switch should not be here");
    		fragment2= new HomeFragment();
    		break;
    	}

		fragmentManager2.beginTransaction().replace(R.id.activity_frame, fragment2).addToBackStack("tag")
		.commit();
    }
    
    public RefMainMenuFragment() {
        // TODO Auto-generated constructor stub
    	//System.out.println("Do we go in here?");
    	    	

    }    
}
