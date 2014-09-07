package adapters;


import model.ClueModel;
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

import fragments.StatisticsFragment;
import android.support.v7.app.ActionBarActivity;

/*
 * The FavoriteTagHistoryItemAdapter is an extension of ParseQueryAdapter
 * that has a custom layout for favorite TagHistoryItems, including a 
 * bigger preview image, the TagHistoryItem's rating, and a "favorite"
 * star. 
 */

public class ClueAdapter extends ParseQueryAdapter<ClueModel> {
	private Context context;
	public ClueAdapter(Context context) {
		super(context, new ParseQueryAdapter.QueryFactory<ClueModel>() {
			
			public ParseQuery<ClueModel> create() {
				// Here we can configure a ParseQuery to display
				// only top-rated TagHistoryItems.
				ParseQuery query = new ParseQuery("ClueModel");
				
				// query.whereContainedIn("rating", Arrays.asList("5", "4"));
				// query.orderByDescending("rating");
				return query;
			}
		});
	}

	@Override
	public View getItemView(final ClueModel Clue, View v,
			ViewGroup parent) {

		if (v == null) {
			v = View.inflate(getContext(), R.layout.row_objective,
					null);
		}

		super.getItemView(Clue, v, parent);

		System.out.println("Clue: "+Clue.getObjectId());

		ui.TextViewRobotoRegular title = (ui.TextViewRobotoRegular) v
				.findViewById(R.id.o_title);
		
		title.setText(Clue.getTitle());
		

		ui.TextViewRobotoThin clue = (ui.TextViewRobotoThin) v
				.findViewById(R.id.o_clue);
		
		clue.setText(Clue.getClue());
		
		ui.TextViewRobotoRegular dif = (ui.TextViewRobotoRegular) v
				.findViewById(R.id.o_dif);
		
		dif.setText(Clue.getDif());
		
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
			if(Clue.getType() == "GPS") {
				//insert gps image
			} else if(Clue.getType() == "Text") {
				
			} else if(Clue.getType() == " Image") {
				
			} else if(Clue.getType() == "Video") {
				
			}
		}
		
		ImageButton completed = (ImageButton) v.findViewById(R.id.lvDelete);
		Button verify = (Button) v.findViewById(R.id.verify);
		verify.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Activity act = new HuntActivity();
				System.out.println("Inside on click clue: "+Clue.getType());
				if(Clue.getType().equals("GPS")) {
					System.out.println("Equal gps");
					//Intent i = new Intent(v.getContext(), GPS.class);
					//v.getContext().startActivity(i);
					((HuntActivity) act).updateMainActivity(3);
					//getEasterEgg(3);
					System.out.println("GPS");
				} else if(Clue.getType().equals("Text")) {
					((HuntActivity) act).updateMainContent(5, null);
					getEasterEgg(1);
				} else if(Clue.getType().equals("Photo")) {
					((HuntActivity) act).updateMainContent(6, null);
					getEasterEgg(2);
					System.out.println("Photo");
				} else if(Clue.getType().equals("Video")) {
					getEasterEgg(4);
				}
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
