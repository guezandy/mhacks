package adapters;


import model.ClueModel;
import activities.GPS;
import activities.HuntActivity;
import activities.HuntApplication;
import activities.VerifyImageActivity;
import activities.Ziggeo;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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

import fragments.CluesListFragment;
import fragments.StatisticsFragment;
import fragments.VerifyImage;
import fragments.VerifyText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

/*
 * The FavoriteTagHistoryItemAdapter is an extension of ParseQueryAdapter
 * that has a custom layout for favorite TagHistoryItems, including a 
 * bigger preview image, the TagHistoryItem's rating, and a "favorite"
 * star. 
 */

public class EasterEggAdapter extends ParseQueryAdapter<ClueModel> {
	private Context context;
	public EasterEggAdapter(Context context) {
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
			if(Clue.getType().equals("GPS")) {
				//insert gps image
			} else if(Clue.getType().equals("Text")) {
				//
			} else if(Clue.getType().equals(" Image")) {
				
			} else if(Clue.getType().equals("Video")) {
				ClueImage.setImageResource(R.drawable.video_camera);
			}
		}
		
		
		
		Button verify = (Button) v.findViewById(R.id.verify);
		verify.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Activity act = new HuntActivity();
				System.out.println("Inside on click clue: "+Clue.getType());
				if(Clue.getType().equals("GPS")) {
					System.out.println("Equal gps");
					Intent i = new Intent(v.getContext(), GPS.class);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					HuntApplication.setCurrentClueString(Clue.getClue());
					v.getContext().startActivity(i);
					System.out.println("GPS");
				} else if(Clue.getType().equals("Text")) {
					Intent i = new Intent(v.getContext(), VerifyText.class);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					i.putExtra("clueTitle", Clue.getClue());
					v.getContext().startActivity(i);
				} else if(Clue.getType().equals("Photo")) {
					Intent i = new Intent(v.getContext(), VerifyImageActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					i.putExtra("clueTitle", Clue.getClue());
					HuntApplication.setCurrentClueString(Clue.getClue());
					System.out.println("starting photo activity");
					v.getContext().startActivity(i);
				} else if(Clue.getType().equals("Video")) {
					Intent i = new Intent(v.getContext(), Ziggeo.class);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					i.putExtra("clueTitle", Clue.getTitle());
					System.out.println("starting video activity");
					v.getContext().startActivity(i);
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
