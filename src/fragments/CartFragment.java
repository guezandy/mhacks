package fragments;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TextView;


public class CartFragment extends Fragment {
	private final String TAG = CartFragment.class.getSimpleName();
	//private CartAdapter cartAdapter;


	public CartFragment() {
		// Empty constructor required for fragment subclasses

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.i(TAG, "onActivityCreated");
		super.onActivityCreated(savedInstanceState);
//		cartAdapter = new CartAdapter(this.getActivity()
//				.getApplicationContext());
//		getListView().setClickable(false);
//		setListAdapter(cartAdapter);
//		LayoutInflater inflater = this.getLayoutInflater(savedInstanceState);

	}
}
