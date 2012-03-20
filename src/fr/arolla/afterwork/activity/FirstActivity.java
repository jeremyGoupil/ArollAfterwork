package fr.arolla.afterwork.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ImageView;

public class FirstActivity extends Activity {
	protected Bitmap colorMap;
	
	protected float colorMapFactor;
	
	protected Map<Integer, String> colorMapMap;

	private ImageView arollaMapView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		initFields();

		arollaMapView = (ImageView) findViewById(R.id.arollaMap);
		arollaMapView.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				float x = event.getX();
				float y = event.getY();
				String selectedExpertise = getSelectedExpertiseFromCoordinates(x, y);
				if (selectedExpertise == null){
					return false;
				}
				

				Intent intent = new Intent("ShowResult");
				intent.putExtra("selected", selectedExpertise);
				startActivity(intent);
				return true;
			}
		});

	}

	private void initFields() {
		colorMap = BitmapFactory.decodeResource(getResources(),
				R.drawable.arolla_expertise_map_color);
		
		float colorMapWidth = colorMap.getWidth();
		float deviceWidth = getDeviceWidth();
		colorMapFactor = colorMapWidth/deviceWidth;
		
		colorMapMap = new HashMap<Integer, String>();
		colorMapMap.put(Color.GREEN, "java");
		colorMapMap.put(Color.RED, ".net");
		colorMapMap.put(Color.BLUE, "functionnal");
		colorMapMap.put(Color.BLACK, "legacy");
		colorMapMap.put(Color.CYAN, "tools");
		colorMapMap.put(-8126332, "enterprise");
		colorMapMap.put(Color.YELLOW, "askell");
		colorMapMap.put(-8716288, "mobile");
		colorMapMap.put(Color.MAGENTA, "academic");
	}

	protected int getDeviceWidth() {
		WindowManager w = getWindowManager(); 
        Display d = w.getDefaultDisplay(); 
        int width = d.getWidth();
        return width;
	}

	private String getSelectedExpertiseFromCoordinates(float x, float y) {
		int colorMapX = Math.round(x*colorMapFactor);
		int colorMapY = Math.round(y*colorMapFactor);
		int pixel = -1;
		try{
			pixel = colorMap.getPixel(colorMapX, colorMapY);
		}catch(IllegalArgumentException ex){
			Log.d("FirstActivity", "Selection outside the map");
		}
		String selectedExpertise = colorMapMap.get(pixel);
		if(selectedExpertise==null && pixel != -1){
			Log.e("FirstActivity", "Le code couleur '"+pixel+"' est définit sur la carte mais pas dans la map");
		}
		
		return selectedExpertise;
	}
}
