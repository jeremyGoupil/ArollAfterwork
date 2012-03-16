package fr.arolla.afterwork.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class FirstActivity extends Activity {
	private Bitmap colorMap;

	private Map<Integer, String> colorMapMap;

	private ImageView arollaMapView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		initColorMapMap();

		arollaMapView = (ImageView) findViewById(R.id.arollaMap);
		arollaMapView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				float x = event.getX();
				float y = event.getY();
				int pixel = colorMap.getPixel(Math.round(x), Math.round(y));

				String selectedExpertise = colorMapMap.get(pixel);
				if (selectedExpertise == null)
					return false;

				Intent intent = new Intent("ShowResult");
				intent.putExtra("selected", selectedExpertise);
				startActivity(intent);
				return true;
			}
		});

	}

	private void initColorMapMap() {
		colorMap = BitmapFactory.decodeResource(getResources(),
				R.drawable.arolla_expertise_map_color);
		colorMapMap = new HashMap<Integer, String>();
		colorMapMap.put(Color.GREEN, "java");
		colorMapMap.put(Color.RED, ".net");
		colorMapMap.put(Color.BLUE, "functionnal");
		colorMapMap.put(Color.BLACK, "legacy");
		colorMapMap.put(Color.CYAN, "tools");
		colorMapMap.put(Color.MAGENTA, "enterprise");
		colorMapMap.put(Color.YELLOW, "askell");
		colorMapMap.put(Color.GRAY, "mobile");
		colorMapMap.put(Color.DKGRAY, "academic");
	}
}
