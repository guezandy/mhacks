package fragments;

import java.util.ArrayList;

import model.ClueModel;

import com.example.shotchart.R;

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
public class MainMenuFragment extends Fragment {
    private final String TAG = MainMenuFragment.class.getSimpleName();    
    ActionBar actionBar;
	private static final int CREATE_FRAGMENT = 1;
	private static final int JOIN_FRAGMENT = 2;
	private static final int ADMIN_FRAGMENT = 3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
    	
    	//LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_home,
        //        container, false);
    	final RelativeLayout mLinearLayout = (RelativeLayout) inflater.inflate(R.layout.f_main_menu,
                container, false);

    	actionBar = getActivity().getActionBar();
    	ImageView join = (ImageView)mLinearLayout.findViewById(R.id.b3);
    	join.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				replaceFrag(JOIN_FRAGMENT);
			}	
    	});
    	
    	ImageView make = (ImageView)mLinearLayout.findViewById(R.id.logo);
    	make.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Replace the frag with game frag
				replaceFrag(CREATE_FRAGMENT);
			}
    	});
    	
    	Button mod = (Button) mLinearLayout.findViewById(R.id.mod);
    	mod.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			replaceFrag(ADMIN_FRAGMENT);
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
    	case CREATE_FRAGMENT:
    		fragment2 =  new VerifyImage();//MakeHuntFragment();
    		break;
    	case JOIN_FRAGMENT: 
    		fragment2 = new AccessCodeFragment();
    		break;
    	case ADMIN_FRAGMENT: 
    		fragment2= new ModeratorFragment();
    		break;
    	default:
    		Log.e(TAG, "Inside MainMenuFragment default switch should not be here");
    		fragment2= new HomeFragment();
    		
    	}
//    	if(id == 1) {
//    		fragment2 = new AccessCodeFragment();
//    	} else if(id == 2) {
//    		fragment2 = new MakeFragment();
//    		//fragment2 = new CluesListFragment();
//    	} else if(id == 3) {
//    		System.out.println("Init mod");
//    		fragment2 = new ModeratorFragment();
//    	} else {
//    		fragment2 = new HomeFragment();
//    	}
		fragmentManager2.beginTransaction().replace(R.id.activity_frame, fragment2).addToBackStack("tag")
		.commit();
    }
    
    public MainMenuFragment() {
        // TODO Auto-generated constructor stub
    	//System.out.println("Do we go in here?");
    	    	

    }    
}
