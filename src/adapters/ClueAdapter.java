package adapters;


import model.ClueModel;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
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

/*
 * The FavoriteTagHistoryItemAdapter is an extension of ParseQueryAdapter
 * that has a custom layout for favorite TagHistoryItems, including a 
 * bigger preview image, the TagHistoryItem's rating, and a "favorite"
 * star. 
 */

public class ClueAdapter extends ParseQueryAdapter<ClueModel> {

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
			v = View.inflate(getContext(), R.layout.row_shot,
					null);
		}

		super.getItemView(Clue, v, parent);

		System.out.println("Clue: "+Clue.getObjectId());

		ui.TextViewRobotoRegular makeMiss = (ui.TextViewRobotoRegular) v
				.findViewById(R.id.makeMiss);
		switch(Clue.getType()) {
		case 0: //zero for text
			System.out.println("TEXT");
			makeMiss.setText("Text");
			break;
		case 1: //for image
			System.out.println("IMAGE");
			makeMiss.setText("IMAGE");
			break;
		case 2: //for gps
			System.out.println("GPS");
			makeMiss.setText("GPS");
			break;
		case 3: //for video
			System.out.println("VIDEO");
			makeMiss.setText("Video");
			break;
		}
		
		ui.TextViewRobotoRegular ttp = (ui.TextViewRobotoRegular) v
				.findViewById(R.id.ttp);

		
		ImageButton delete = (ImageButton) v.findViewById(R.id.lvDelete);
		delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ParseQuery<ParseObject> query = ParseQuery
						.getQuery("ClueModel");

				// Retrieve the object by id
				query.getInBackground(Clue.getObjectId(),
					new GetCallback<ParseObject>() {
						public void done(ParseObject item, ParseException e) {
							if (e == null) {
								// Now let's update it with some new data.
								// In this case, only cheatMode and score
								item.deleteInBackground();
								Toast.makeText(getContext(), "Item deleted from Clue History", Toast.LENGTH_SHORT).show();
							}
						}
				});
			}
		});
		return v;
	}
}
