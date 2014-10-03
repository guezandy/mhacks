package fragments;

import com.example.shotchart.R;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.RelativeLayout;
import adapters.ClueAdapter;
import adapters.EasterEggAdapter;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CluesListFragment extends ListFragment {
	private final String TAG = CluesListFragment.class.getSimpleName();
	private EasterEggAdapter clueAdapter;

	public CluesListFragment() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "Inside taghistoryfragment on create");

    	FragmentManager fragmentManager2 = getFragmentManager();
		fragmentManager2.beginTransaction().replace(R.id.right_drawer, new JoinStatsFragment()).addToBackStack("tag")
		.commit();
		
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.i(TAG, "onActivityCreated");
		super.onActivityCreated(savedInstanceState);
		clueAdapter = new EasterEggAdapter(this.getActivity().getApplicationContext());
		//getListView().addFooterView(R.layout.header);
		getListView().setClickable(false);
		setListAdapter(clueAdapter);
	}
	
    public void setAdapter(EasterEggAdapter adapter) {
    	Log.i(TAG, "Setting adapter");
  	
    	//nfcid = clothingEntityId;
    	clueAdapter = adapter;
        
    }

}
