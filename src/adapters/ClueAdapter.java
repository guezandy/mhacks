package adapters;


import model.ClueModel;
import activities.GPS;
import activities.HuntActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
			v = View.inflate(getContext(), R.layout.row_make_objective,
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
				
			} else if(ClueImage != null && Clue.getType() == "Video") {
				ClueImage.setImageResource(R.drawable.video_camera);
			}
		}
		
		final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        switch (which){
		        case DialogInterface.BUTTON_POSITIVE:
		            //Yes button clicked
		            break;

		        case DialogInterface.BUTTON_NEGATIVE:
		            //No button clicked
		            break;
		        }
		    }
		};

		final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
		
		ImageButton delete = (ImageButton) v.findViewById(R.id.lvDelete);
		delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
			    .setNegativeButton("No", dialogClickListener).show();
				
//				ParseQuery<ParseObject> query = ParseQuery
//						.getQuery("ClueModel");
//
//				// Retrieve the object by id
//				query.getInBackground(Clue.getObjectId(),
//						new GetCallback<ParseObject>() {
//							public void done(ParseObject item, ParseException e) {
//								if (e == null) {
//									// Now let's update it with some new data.
//									// In this case, only cheatMode and score
//									item.deleteInBackground();
//									Toast.makeText(getContext(), "Item deleted from Hunt", Toast.LENGTH_SHORT).show();
//								}
//							}
//						});
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
