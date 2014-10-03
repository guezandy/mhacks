package adapters;


import model.ClueModel;
import model.VerifyModel;
import activities.GPS;
import activities.HuntActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.shotchart.R;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.SaveCallback;

import fragments.StatisticsFragment;
import android.support.v7.app.ActionBarActivity;

/*
 * The FavoriteTagHistoryItemAdapter is an extension of ParseQueryAdapter
 * that has a custom layout for favorite TagHistoryItems, including a 
 * bigger preview image, the TagHistoryItem's rating, and a "favorite"
 * star. 
 */

public class ModAdapter extends ParseQueryAdapter<VerifyModel> {
	private Context context;
	public ModAdapter(Context context) {
		super(context, new ParseQueryAdapter.QueryFactory<VerifyModel>() {
			
			public ParseQuery<VerifyModel> create() {
				// Here we can configure a ParseQuery to display
				// only top-rated TagHistoryItems.
				ParseQuery query = new ParseQuery("VerifyModel");
				
				// query.whereContainedIn("rating", Arrays.asList("5", "4"));
				// query.orderByDescending("rating");
				return query;
			}
		});
	}

	@Override
	public View getItemView(final VerifyModel Clue, View v,
			ViewGroup parent) {

		if (v == null) {
			v = View.inflate(getContext(), R.layout.row_mod,
					null);
		}

		super.getItemView(Clue, v, parent);

		System.out.println("Clue: "+Clue.getObjectId());

		ui.TextViewRobotoRegular title = (ui.TextViewRobotoRegular) v
				.findViewById(R.id.o_title);
		
		ui.TextViewRobotoThin clue = (ui.TextViewRobotoThin) v
				.findViewById(R.id.o_clue);

		title.setText(Clue.getClueId());
		
		if(Clue.getLocation() == null) {
			clue.setText(Clue.getSString());
		} else {
			clue.setText(Clue.getSString() + " (" + Clue.getLocation().getLatitude()+" , "+Clue.getLocation().getLongitude()+" )");
		}		
		
		ui.TextViewRobotoRegular dif = (ui.TextViewRobotoRegular) v
				.findViewById(R.id.o_dif);
		
		//dif.setText(Clue.getAuthor().toString());
		
		ParseImageView ClueImage = (ParseImageView) v
				.findViewById(R.id.item_image);
		ParseFile photoFile = Clue.getImage();
		
		if (photoFile != null) {
			ClueImage.setParseFile(photoFile);
			ClueImage.loadInBackground(new GetDataCallback() {
				@Override
				public void done(byte[] data, ParseException e) {
					// nothing to do
				}
			});
		} else {
			if(Clue.getType() != null){
				if(Clue.getType().equals("GPS")) {
					//insert gps image
				} else if(Clue.getType().equals("Text")) {
					//
				} else if(Clue.getType().equals(" Image")) {
					
				} else if(Clue.getType().equals("Video")) {
					ClueImage.setImageResource(R.drawable.video_camera);
				}
			} else {
				System.out.println("Clue is empty");
			}
		}
		

		
		ImageButton delete = (ImageButton) v.findViewById(R.id.good);
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(final View v) {
				Clue.setVerified(true);
				Clue.saveInBackground(new SaveCallback() {
					@Override
					public void done(ParseException e) {
						if (e == null) {
							Toast.makeText(
									v.getContext(),
									"Approved",
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(
									v.getContext(),
									"Error saving: " + e.getMessage(),
									Toast.LENGTH_SHORT).show();
						}
					}

				});
			}
		});
		
		ImageButton bad = (ImageButton) v.findViewById(R.id.bad);
		bad.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				Clue.setVerified(false);
				Clue.saveInBackground(new SaveCallback() {
					@Override
					public void done(ParseException e) {
						if (e == null) {
							Toast.makeText(
									v.getContext(),
									"Denied",
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(
									v.getContext(),
									"Error saving: " + e.getMessage(),
									Toast.LENGTH_SHORT).show();
						}
					}

				});
			}
		});
		
		
		return v;
	}
	
	public void getEasterEgg(int obj) {
		switch(obj) {
		case 1:
			break;
		case 2: 
			break;
		case 3:
			break;
		case 4: 
			break;
		}
	}
}
