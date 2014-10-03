package activities;

import model.ClueModel;
import model.VerifyModel;

import com.example.shotchart.R;

import fragments.CameraFragment;
import fragments.CartFragment;
import fragments.GameFragment;
import fragments.MainMenuFragment;
import fragments.MakeHuntFragment;
import fragments.VerifyImage;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

public class VerifyImageActivity extends RefMainMenu {
	
	public FrameLayout cartDrawerFrame = null;
	public DrawerLayout cartDrawerLayout = null;
	
	public VerifyModel model;
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_image);
		prepareCartDrawer();
		model = new VerifyModel();
		System.out.println("Starting to verify image");
System.out.println(HuntApplication.getCurrentClueString());
		if (savedInstanceState == null) {
			updateMainContent(0, null);
		}
	  }
	  
	  /*
		 * Sets the activity_fragment to the fragment corresponding to the paramater
		 * fragmentID
		 */
		public void updateMainContent(int fragmentId, String clothingEntityId) {
			//Log.i(TAG, "updateMainContent");
			Fragment fragment = null;
			//android.app.Fragment fragment = manager.findFragmentById(R.id.activity_frame);

			Bundle args = new Bundle();
			FragmentManager fragmentManager = getSupportFragmentManager();

			switch (fragmentId) {
			case 0:
				//fragment = new MakeHuntFragment();
				fragment = new VerifyImage();
				((VerifyImage) fragment).verify();
				break;
			case 1:
				//fragment = new GameFragment();
				break;
			default:
				// Default sets Home Fragment
				//fragment = new HomeFragment();
				//fragment = new MainMenuFragment();
				break;
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


		/**
		 * Adds the cart fragment to the right drawer
		 */
		private void prepareCartDrawer() {
			//Log.i(TAG, "onPrepareCartDrawer");
			// Set up cart drawer, in progress
			//cartDrawerTitle = "Cart";
			cartDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
			cartDrawerFrame = (FrameLayout) findViewById(R.id.right_drawer);

			// create the cart drawer fragment
			Fragment fragment = new CartFragment();//new CartFragment();
			Bundle args = new Bundle();
			fragment.setArguments(args);
			// Insert the fragment by replacing any existing fragment
			android.app.FragmentManager fragmentManager = getFragmentManager();
			//fragmentManager.beginTransaction().replace(R.id.right_drawer, fragment)
			//		.commit();

			// ActionBarDrawerToggle ties together the the proper interactions
			// between the sliding drawer and the action bar app icon
//			cartDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
//			cartDrawerLayout, /* DrawerLayout object */
//			R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
//			R.string.drawer_open, /* "open drawer" description for accessibility */
//			R.string.drawer_close /* "close drawer" description for accessibility */
//			) {
//				public void onDrawerClosed(View view) {
//					//actionBar.setTitle(activityTitle);
//				}
//
//				public void onDrawerOpened(View view) {
//					//actionBar.setTitle(cartDrawerTitle);
//				}
//			};
		}
		
}