package activities;


import activities.GPS;
import model.ClueModel;
import model.VerifyModel;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.shotchart.R;

import fragments.AccessCodeFragment;
import fragments.CartFragment;
import fragments.CluesListFragment;
import fragments.GameFragment;
import fragments.HomeFragment;
import fragments.MainMenuFragment;
import fragments.MakeHuntFragment;
import fragments.StatisticsFragment;
import fragments.VerifyImage;
//import com.facebook.Request;
//import com.facebook.Response;
//import com.facebook.Session;
//import com.facebook.model.GraphUser;
//import com.parse.FindCallback;
//import com.parse.ParseException;
//import com.parse.ParseFacebookUtils;
//import com.parse.ParseObject;
//import com.parse.ParseQuery;
//import com.parse.ParseUser;
import fragments.VerifyText;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class HuntActivity extends ActionBarActivity {
	private final String TAG = HuntActivity.class.getSimpleName();
	private static final int HOME_FRAGMENT = 0;
	private static final int GAME_FRAGMENT = 1;
	private static final int MAKE_FRAGMENT = 2;
	private static final int JOIN_FRAGMENT = 3;

	
	private static final String PLACEHOLDER_STRING = "";
	
	public android.support.v7.app.ActionBar actionBar;
	public FrameLayout cartDrawerFrame = null;
	public DrawerLayout cartDrawerLayout = null;
	public ActionBarDrawerToggle cartDrawerToggle = null;
	public CharSequence activityTitle = null;
	public CharSequence cartDrawerTitle = null;

	private ClueModel clue;
	private ClueModel model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hunt);
		actionBar = getSupportActionBar();
		activityTitle = actionBar.getTitle();
		//clue = new ClueModel();

		// Check if there is a currently logged in user
		// and they are linked to a Facebook account.
//		ParseUser currentUser = ParseUser.getCurrentUser();
//		if ((currentUser != null) && ParseFacebookUtils.isLinked(currentUser)) {
//			// Fetch Facebook user info if the session is active
//			Session session = ParseFacebookUtils.getSession();
//			if (session != null && session.isOpened()) {
//				makeMeRequest();
//			} else {
//				// If the user is not logged in, go to the
//				// activity showing the login view.
//				Log.i(TAG, "User was not logged in.");
//				startLoginActivity();
//			}
//		}

		prepareCartDrawer();

		if (savedInstanceState == null) {
			updateMainContent(HOME_FRAGMENT, PLACEHOLDER_STRING);
		}


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.i(TAG, "onCreateOptionsMenu");
		// Inflate the menu; this adds items to the action bar if it is present.
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu_home, menu);
	    return super.onCreateOptionsMenu(menu);
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		Log.i(TAG, "onPrepareOptionsMenu");
		// If the nav drawer is open, hide action items related to the content
		// view
		boolean drawerOpen = cartDrawerLayout.isDrawerOpen(cartDrawerFrame);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.i(TAG, "onOptionsItemSelected");
		if (cartDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		switch (item.getItemId()) {
//		case R.id.action_logout:
//			Intent i = new Intent(HuntActivity.this, SignUpOrLogInActivity.class);
//			startActivity(i);
//			return true;
//		case R.id.action_settings:
//			updateMainContent(SETTINGS_FRAGMENT, null);
//			return true;
		case R.id.action_home:
			// updateMainContent(SNAG_FRAGMENT, null);
			updateMainContent(HOME_FRAGMENT, null);
			return true;
		case R.id.action_cart:
			Log.i(TAG, "Cart Item Clicked");
			if (cartDrawerLayout.isDrawerOpen(cartDrawerFrame)) {
				cartDrawerLayout.closeDrawer(cartDrawerFrame);
			} else {
				cartDrawerLayout.openDrawer(cartDrawerFrame);
			}
			return true;
		case R.id.action_profile:
			Log.i(TAG, "Profile Item Clicked");
			updateMainContent(MAKE_FRAGMENT, PLACEHOLDER_STRING);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/*
	 * Sets the activity_fragment to the fragment corresponding to the paramater
	 * fragmentID
	 */
	public void updateMainContent(int fragmentId, String clothingEntityId) {
		Log.i(TAG, "updateMainContent");
		Fragment fragment = null;
		//android.app.Fragment fragment = manager.findFragmentById(R.id.activity_frame);

		Bundle args = new Bundle();
		FragmentManager fragmentManager = getSupportFragmentManager();

		switch (fragmentId) {
		case MAKE_FRAGMENT:
			//fragment = new MakeHuntFragment();
			fragment = new MakeHuntFragment();
			break;
		case GAME_FRAGMENT:
			fragment = new GameFragment();
			break;
		default:
			// Default sets Home Fragment
			//fragment = new HomeFragment();
			fragment = new MainMenuFragment();
		}

		fragment.setArguments(args);
		// update the main content by replacing fragments
		System.out.println("Starting fragment");
		this.prepareCartDrawer();
		fragmentManager.beginTransaction()
				.replace(R.id.activity_frame, fragment).addToBackStack("tag")
				.commit();
		cartDrawerLayout.closeDrawer(cartDrawerFrame);

	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.i(TAG, "onKeyDown");
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (getFragmentManager().getBackStackEntryCount() == 0) {
				this.finish();
				return false;
			} else {
				getFragmentManager().popBackStack();
				removeCurrentFragment();
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	public void removeCurrentFragment() {
		Log.i(TAG, "removeFragment");
		android.app.FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		android.app.Fragment currentFrag = getFragmentManager().findFragmentById(
				R.id.activity_frame);
	}

	@Override
	public void setTitle(CharSequence title) {
		Log.i(TAG, "setTitle");
		actionBar.setTitle(title);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		//Log.i(TAG, "onPostCreate");
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		cartDrawerToggle.syncState();
	}

	public ClueModel getCurrentClue() {
		return clue;
	}

	@Override
	public void onResume() {
		//Log.i(TAG, "onResume");
		super.onResume();
//		ParseUser currentUser = ParseUser.getCurrentUser();
//		if (currentUser != null) {
//			// Check if the user is currently logged
//			// and show any cached content
//		} else {
//			// If the user is not logged in, go to the
//			// activity showing the login view.
//			startLoginActivity();
//		}
	}




	/**
	 * Adds the cart fragment to the right drawer
	 */
	private void prepareCartDrawer() {
		Log.i(TAG, "onPrepareCartDrawer");
		// Set up cart drawer, in progress
		
		cartDrawerTitle = "Cart";
		cartDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		cartDrawerFrame = (FrameLayout) findViewById(R.id.right_drawer);
System.out.println("IN HERE");
		// create the cart drawer fragment
		Fragment fragment = new CartFragment();//new CartFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		// Insert the fragment by replacing any existing fragment
		FragmentManager fragmentManager = this.getSupportFragmentManager();
		
		
		fragmentManager.beginTransaction().replace(R.id.right_drawer, fragment)
				.commit();

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		cartDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		cartDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				actionBar.setTitle(activityTitle);
			}

			public void onDrawerOpened(View view) {
				actionBar.setTitle(cartDrawerTitle);
			}
		};
	}
}