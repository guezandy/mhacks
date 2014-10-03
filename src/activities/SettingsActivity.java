package activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.parse.ParseUser;
import com.example.shotchart.R;
/**
 * Activity that displays the settings screen.
 */
public class SettingsActivity extends Activity {

  private RadioGroup searchDistanceGroup;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);

    // The search distance choices
    searchDistanceGroup = (RadioGroup) findViewById(R.id.searchDistanceGroup);
    float searchDistance = HuntApplication.getSearchDistance();
    if (searchDistance == 30) {
      searchDistanceGroup.check(R.id.feet4000Button);
    } else if (searchDistance == 7) {
      searchDistanceGroup.check(R.id.feet1000Button);
    } else {
      searchDistanceGroup.check(R.id.feet250Button);
    }
    // Set up the selection handler to save the selection to the application
    searchDistanceGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
        case R.id.feet4000Button:
          HuntApplication.setSearchDistance(30);
          break;
        case R.id.feet1000Button:
          HuntApplication.setSearchDistance(7);
          break;
        case R.id.feet250Button:
          HuntApplication.setSearchDistance(1);
          break;
        }
      }
    });

    // Set up the log out button click handler
    ((Button) findViewById(R.id.logOutButton)).setOnClickListener(new OnClickListener() {
      public void onClick(View v) {
        // Call the Parse log out method
        ParseUser.logOut();
        // Start and intent for the dispatch activity
        Intent intent = new Intent(SettingsActivity.this, SignUpOrLogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
      }
    });
  }
}
