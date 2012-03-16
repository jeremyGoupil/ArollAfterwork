package fr.arolla.afterwork.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {

	private TextView selectedTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		selectedTextView = (TextView) findViewById(R.id.selected);

		Intent startIntent = getIntent();
		if (startIntent != null) {
			String selected = startIntent.getStringExtra("selected");
			selectedTextView.setText(selected);
		}
	}

}
