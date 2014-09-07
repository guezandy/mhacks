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
public class MakeFragment extends Fragment {
    private final String TAG = MakeFragment.class.getSimpleName();    
    ActionBar actionBar;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
    	
    	//LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_home,
        //        container, false);
    	final RelativeLayout mLinearLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_home,
                container, false);

    	actionBar = getActivity().getActionBar();

 //   	actionBar.removeAllTabs();
 //   	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    	
        // Inflate the layout for this fragment
        return mLinearLayout;
    }
    
    @SuppressWarnings("null")
	public void replaceFrag() {
    	FragmentManager fragmentManager2 = getFragmentManager();
    	//FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
    	Fragment fragment2 = new MakeHuntFragment();
		fragmentManager2.beginTransaction().replace(R.id.activity_frame, fragment2).addToBackStack("tag")
		.commit();
    }
    
    public MakeFragment() {
        // TODO Auto-generated constructor stub
    	//System.out.println("Do we go in here?");
    	    	

    }    
}
