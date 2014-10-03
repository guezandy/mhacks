package fragments;

import java.util.ArrayList;

import model.ClueModel;
import model.RModel;
import model.VerifyModel;

import com.example.shotchart.R;
import com.google.android.gms.internal.v;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;



import activities.HuntApplication;
import activities.RefMainMenu;
import activities.VerifyImageActivity;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public class VerifyImage extends Fragment{
    private final String TAG = HomeFragment.class.getSimpleName();

	private ImageButton photoButton;
	private ParseImageView cluePreview;
private EditText clueString;
	private Spinner spinner;
	private Spinner difSpinner;
private EditText clueTitle;
	private int clueType;
	public boolean verify;
    ActionBar actionBar;
    private TextView tx;
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {

    	//LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_home,
        //        container, false);
    	final RelativeLayout mLinearLayout = (RelativeLayout) inflater.inflate(R.layout.verify_image,
                container, false);
    	//FragmentManager fragmentManager2 = getFragmentManager();
		//fragmentManager2.beginTransaction().replace(R.id.right_drawer, new MakeFragment()).addToBackStack("tag")
		//.commit();
		final EditText clueTitle = ((EditText) mLinearLayout.findViewById(R.id.clueTitle));
		final EditText clueString = ((EditText) mLinearLayout.findViewById(R.id.clueString));
		
		final TextView prev = (TextView) mLinearLayout.findViewById(R.id.prev);
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("RModel");
		query.whereEqualTo("title", HuntApplication.getCurrentClueString());
		query.whereEqualTo("user", ParseUser.getCurrentUser());
		query.getFirstInBackground(new GetCallback<ParseObject>() {
		  public void done(ParseObject object, ParseException e) {
		    if (object == null) {
		      prev.setText("");
		    } else {
		      //Log.d("score", "Retrieved the object.");
		    	if(object.getString("res") != null) {
		    		prev.setText("Prev response: "+object.getString("res"));
		    	} else {
		    		prev.setText("");
		    	}
		    }
		  }
		});
    	//String game = this.getArguments().getString("param");

    	spinner = (Spinner) mLinearLayout.findViewById(R.id.clues_spinner);
    	// Create an ArrayAdapter using the string array and a default spinner layout
    	ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity().getBaseContext(),
    	        R.array.clue_array, android.R.layout.simple_spinner_item);
    	// Specify the layout to use when the list of choices appears
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	// Apply the adapter to the spinner
    	spinner.setAdapter(adapter);
    	
    	spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				// TODO Auto-generated method stub
				System.out.println(parent.getItemAtPosition(pos));
				clueType = pos;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
    		
    	});
    	
    	Button cancel = (Button) mLinearLayout.findViewById(R.id.cancel_button);
    	cancel.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			replaceFrag();
    		}
    	});
    	difSpinner = (Spinner) mLinearLayout.findViewById(R.id.dif_spinner);
    	// Create an ArrayAdapter using the string array and a default spinner layout
    	ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this.getActivity().getBaseContext(),
    	        R.array.dif_array, android.R.layout.simple_spinner_item);
    	// Specify the layout to use when the list of choices appears
    	adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	// Apply the adapter to the spinner
    	difSpinner.setAdapter(adapter2);

    	tx = (TextView) mLinearLayout.findViewById(R.id.textView1);
		if(verify) {
			//clueString.setVisibility(View.GONE);
			difSpinner.setVisibility(View.GONE);
			spinner.setVisibility(View.GONE);
			//clueTitle.setText(HuntApplication.getCurrentClueString());
			clueString.setHint("Put your thoughts here");
			if(HuntApplication.getCurrentClueString() != "") {
				//clueTitle.setText(HuntApplication.getCurrentClueString());
				tx.setText(HuntApplication.getCurrentClueString());
				clueTitle.setVisibility(View.GONE);
			} else {
				clueTitle.setHint("Entry title here");
				tx.setText("Add a personal reflection");
			}
		}
    	Button submit = (Button) mLinearLayout.findViewById(R.id.save_button);
    	
    	submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ClueModel clue = ((RefMainMenu) getActivity()).getCurrentClue();
				
				if(verify) {
					RModel ver = new RModel();
					//ver.setClueID(clueFromAdapter);
					if(clue.getImage() != null) {
						ver.setImage(clue.getImage());
					}
					ver.setAuthor(ParseUser.getCurrentUser());
					
					if(HuntApplication.getCurrentClueString() == "") {
						ver.setTitle(clueTitle.getText().toString());
					} else {
						ver.setTitle(HuntApplication.getCurrentClueString());
					}
					ver.setResponse(clueString.getText().toString());
					System.out.println("Inside Verify Fragment's verify is true");
					// Save the meal and return
					ver.saveInBackground(new SaveCallback() {
	
						@Override
						public void done(ParseException e) {
							if (e == null) {
								getActivity().setResult(Activity.RESULT_OK);
								//Intent i = new Intent(getActivity(), )
								replaceFrag();
							} else {
								Toast.makeText(
										getActivity().getApplicationContext(),
										"Error saving: " + e.getMessage(),
										Toast.LENGTH_SHORT).show();
							}
						}
	
					});
				
				} else {
					// When the user clicks "Save," upload the meal to Parse
					// Add data to the meal object:
					clue.setClue(clueString.getText().toString());
					
					clue.setTitle(clueTitle.getText().toString());
	
					// Associate the meal with the current user
					clue.setAuthor(ParseUser.getCurrentUser());
	
					// Add the type
					clue.setType(spinner.getSelectedItem().toString());
					
					//Add the dif
					clue.setDif(difSpinner.getSelectedItem().toString());
	
	
					// Save the meal and return
					clue.saveInBackground(new SaveCallback() {
	
						@Override
						public void done(ParseException e) {
							if (e == null) {
								getActivity().setResult(Activity.RESULT_OK);
								//Intent i = new Intent(getActivity(), )
								replaceFrag();
							} else {
								Toast.makeText(
										getActivity().getApplicationContext(),
										"Error saving: " + e.getMessage(),
										Toast.LENGTH_SHORT).show();
							}
						}
	
					});
				}

			}
    	});
		photoButton = ((ImageButton) mLinearLayout.findViewById(R.id.photo_button));
		photoButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				InputMethodManager imm = (InputMethodManager) getActivity()
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(clueTitle.getWindowToken(), 0);
				startCamera();
			}
		});
    	actionBar = getActivity().getActionBar();
 //   	actionBar.removeAllTabs();
 //   	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		cluePreview = (ParseImageView) mLinearLayout.findViewById(R.id.clue_preview_image);
		cluePreview.setVisibility(View.INVISIBLE);
        // Inflate the layout for this fragment
        return mLinearLayout;
    }
    
	/*
	 * All data entry about a Meal object is managed from the NewMealActivity.
	 * When the user wants to add a photo, we'll start up a custom
	 * CameraFragment that will let them take the photo and save it to the Meal
	 * object owned by the NewMealActivity. Create a new CameraFragment, swap
	 * the contents of the fragmentContainer (see activity_new_meal.xml), then
	 * add the NewMealFragment to the back stack so we can return to it when the
	 * camera is finished.
	 */
	public void startCamera() {
		System.out.println("Starting camera");
		fragments.CameraFragment cameraFragment = new CameraFragment();
		FragmentTransaction transaction = getActivity().getFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.activity_frame, cameraFragment);
		transaction.addToBackStack("VerifyImage");
		System.out.println("Commiting");
		transaction.commit();
	}

	/*
	 * On resume, check and see if a meal photo has been set from the
	 * CameraFragment. If it has, load the image in this fragment and make the
	 * preview image visible.
	 */
	@Override
	public void onResume() {
		super.onResume();
		ParseFile photoFile;
		System.out.println("Starting file");
		if(verify) {
			System.out.println("Verify is true");
			photoFile = ((VerifyImageActivity) getActivity())
					.getCurrentClue().getImage();
		} else {
			System.out.println("Verify is false");
			photoFile = ((RefMainMenu) getActivity())
					.getCurrentClue().getImage();
		}
		System.out.println("Setting photo file");
		if (photoFile != null) {
			cluePreview.setParseFile(photoFile);
			cluePreview.loadInBackground(new GetDataCallback() {
				@Override
				public void done(byte[] data, ParseException e) {
					cluePreview.setVisibility(View.VISIBLE);
				}
			});
		}
	}

    @SuppressWarnings("null")
	public void replaceFrag() {
    	FragmentManager fragmentManager2 = getFragmentManager();
    	//FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
    	Fragment fragment2;
    	if(verify) {
    		fragment2 = new SubmitFragment();//RefMainMenuFragment();
    		verify = true;
    	} else {
    		fragment2 = new VerifyImage();
    	}
    	
		fragmentManager2.beginTransaction().replace(R.id.activity_frame, fragment2).addToBackStack("tag")
		.commit();
    }
    
    public VerifyImage() {
        // TODO Auto-generated constructor stub
    	verify = false;
    	System.out.println("Do we go in here?");
    }
    
    public void verify() {
    	System.out.println("Setting verify to true");
    	verify = true;
    }
    
    
    
}
