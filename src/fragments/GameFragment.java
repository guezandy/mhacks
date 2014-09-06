package fragments;

import java.util.ArrayList;

import model.ClueModel;

import com.example.shotchart.R;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.content.DialogInterface;

import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;



public class GameFragment extends Fragment {
    private final String TAG = HomeFragment.class.getSimpleName();

    ActionBar actionBar;
    
    public static GameFragment newInstance(String param) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putString("param", param);
        fragment.setArguments(args);
        return fragment;
    }
    
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
    	
    	//LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_home,
        //        container, false);
    	final RelativeLayout mLinearLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_game,
                container, false);

    	String game = this.getArguments().getString("param");
    	System.out.println("Game info");
    	Spinner spinner = (Spinner) mLinearLayout.findViewById(R.id.clues_spinner);
    	// Create an ArrayAdapter using the string array and a default spinner layout
    	ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity().getBaseContext(),
    	        R.array.clue_array, android.R.layout.simple_spinner_item);
    	// Specify the layout to use when the list of choices appears
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	// Apply the adapter to the spinner
    	spinner.setAdapter(adapter);
    	
    	actionBar = getActivity().getActionBar();
 //   	actionBar.removeAllTabs();
 //   	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    	
        // Inflate the layout for this fragment
        return mLinearLayout;
    }
    
    
    
    public GameFragment() {
        // TODO Auto-generated constructor stub
    	//System.out.println("Do we go in here?");
    	    	

    }
    
    public void recordShot(int x, int y) {
    	
    }
    
}
