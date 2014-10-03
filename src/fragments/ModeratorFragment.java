package fragments;

import com.example.shotchart.R;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import adapters.ClueAdapter;
import adapters.EasterEggAdapter;
import adapters.ModAdapter;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ModeratorFragment extends ListFragment {
	private final String TAG = ModeratorFragment.class.getSimpleName();
	private ModAdapter clueAdapter;

	public ModeratorFragment() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "Inside taghistoryfragment on create");
		
    	FragmentManager fragmentManager2 = getFragmentManager();
		fragmentManager2.beginTransaction().replace(R.id.right_drawer, new ModDrawerFragment()).addToBackStack("tag")
		.commit();
		
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.i(TAG, "onActivityCreated");
		super.onActivityCreated(savedInstanceState);
		clueAdapter = new ModAdapter(this.getActivity().getApplicationContext());
		//getListView().addFooterView(R.layout.header);
		getListView().setClickable(false);
		setListAdapter(clueAdapter);
	}
	
    public void setAdapter(ModAdapter adapter) {
    	Log.i(TAG, "Setting adapter");
  	
    	//nfcid = clothingEntityId;
    	clueAdapter = adapter;
        
    }

}
