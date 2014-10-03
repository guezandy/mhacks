package activities;

import java.util.ArrayList;

import model.ClueModel;
import model.VerifyModel;

import com.example.shotchart.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import activities.HuntActivity;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
//import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.content.DialogInterface;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import android.widget.Toast;

public class Ziggeo extends Activity {
    private final String TAG = Ziggeo.class.getSimpleName(); 

    ActionBar actionBar;
    WebView webView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.ziggeo);

    	actionBar = getActionBar();
    	
    	webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("https://dl.dropboxusercontent.com/u/62622354/ziggeo/index.html");
    	
    }
    
    public Ziggeo() {
        // TODO Auto-generated constructor stub
    	//System.out.println("Do we go in here?");
    	    	

    }    
}
