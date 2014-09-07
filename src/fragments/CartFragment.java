package fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import adapters.ClueAdapter;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CartFragment extends ListFragment {
	private final String TAG = CluesListFragment.class.getSimpleName();
	private ClueAdapter clueAdapter;

	public CartFragment() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "Inside taghistoryfragment on create");
		
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.i(TAG, "onActivityCreated");
		super.onActivityCreated(savedInstanceState);
		clueAdapter = new ClueAdapter(this.getActivity().getApplicationContext());
		
		getListView().setClickable(false);
		setListAdapter(clueAdapter);
	}
	
    public void setAdapter(ClueAdapter adapter) {
    	Log.i(TAG, "Setting adapter");
  	
    	//nfcid = clothingEntityId;
    	clueAdapter = adapter;
        
    }

}
