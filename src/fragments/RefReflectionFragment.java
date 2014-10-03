package fragments;

import java.util.ArrayList;

import model.ClueModel;

import com.example.shotchart.R;

import activities.HuntActivity;
import activities.HuntApplication;
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
import android.widget.TextView;
import android.widget.Toast;



@SuppressLint("CommitTransaction")
public class RefReflectionFragment extends Fragment {
    private final String TAG = HomeFragment.class.getSimpleName();    
    ActionBar actionBar;
    
	private static final int HOME_FRAGMENT = 0;
	
	public String reflections[] = new String[] {
			"What are the achievements you are most proud of?", 
			"What are you most grateful for in life",
			"What are the most important things to you in life?",
			"What is your ideal self",
			"What does it mean to be your highest self",
			"If you have one week left to live, what would you do?",
			"If you have one month left to live, what would you do?",
			"If you have an hour left to live, what would you do",
			"What opportunities are you looking for?",
			"If you are to do something for free for the rest of your life, what would you want to do?",
			"What’s the top priority in your life right now?",
			"Are you putting any parts of your life on hold?",
			"If you had 1 million dollars, what would you do with it?",
			"What good habits do you want to cultivate?",
			"What bad habits do you want to break?",
			"What drives you?",
			"How can you make your life more meaningful, starting today?",
			"What is your ideal life partner like?",
			"Who are your mentors in life?",
			"How important is social approval for you?",
			"What was the last life lesson you learnt?",
			"When was the last time you helped someone?",
			"What is your greatest fear?",
			"Who are the 3 people who had most powerful influence on you?",
			"What is happiness for you?",
			"What do I want most in this life?", 
			"What is one thing I want others to recognize or remember about me?"
							};
	
	public String s = ""; //should be random
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
    	
    	//LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_home,
        //        container, false);
    	final RelativeLayout v = (RelativeLayout) inflater.inflate(R.layout.ref_reflection,
                container, false);

    	//actionBar = getActivity().getActionBar();

    	//actionBar.removeAllTabs();
    	////actionBar.setNavigationMode(ActionBar.);
    	int randomNum = (int)(Math.random()*reflections.length);
    	s = reflections[randomNum];
    	TextView tx = (TextView) v.findViewById(R.id.textView1);
    	tx.setText(s);
    	
    	
    	ImageButton back = (ImageButton)v.findViewById(R.id.back1);
    	back.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			replaceFrag(HOME_FRAGMENT);
    		}
    	});
    	
    	ImageButton edit = (ImageButton) v.findViewById(R.id.edit1);
    	edit.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
	    		Intent i = new Intent(v.getContext(), VerifyImageActivity.class);
	    		HuntApplication.setCurrentClueString(s);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				v.getContext().startActivity(i);
    		}
    	});
    	
    	ImageButton add = (ImageButton) v.findViewById(R.id.add1);
    	add.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
	    		Intent i = new Intent(v.getContext(), VerifyImageActivity.class);
	    		HuntApplication.setCurrentClueString("");
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				v.getContext().startActivity(i);
    		}
    	});
    	
        // Inflate the layout for this fragment
        return v;
    }
    
    @SuppressWarnings("null")
	public void replaceFrag(int id) {
    	FragmentManager fragmentManager2 = getFragmentManager();
    	//FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
    	Fragment fragment2;
    	
    	switch(id) {
    	case HOME_FRAGMENT: 
    		fragment2 = new RefMainMenuFragment();
    		break;
    	default:
			fragment2 = new RefMainMenuFragment();
			break;
    	}
		fragmentManager2.beginTransaction().replace(R.id.activity_frame, fragment2).addToBackStack("tag")
		.commit();
    }
    
    public RefReflectionFragment() {
        // TODO Auto-generated constructor stub
    	//System.out.println("Do we go in here?");
    	    	

    }    
}
