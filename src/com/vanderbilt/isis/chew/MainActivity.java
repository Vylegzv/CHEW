package com.vanderbilt.isis.chew;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {

	public static final String[] titles = new String[] { "Scan the Barcode",
		"Choose a Family Member", "Buy Produce", "Healthy Snack Recipes",
		"Watch Snack Recipe Videos" };

public static final String[] descriptions = new String[] {
		"Shop for all family", "Shop for a particular member",
		"Calculate the prices", "Very easy and quick!",
		"Watch how to fix snacks!" };

	public static final Integer[] images = { R.drawable.barcode,
			R.drawable.family, R.drawable.produce, R.drawable.recipes,
			R.drawable.videos };

	ListView listView;
	List<MainListRowItem> rowItems;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		rowItems = new ArrayList<MainListRowItem>();
		for (int i = 0; i < titles.length; i++) {
			MainListRowItem item = new MainListRowItem(images[i], titles[i],
					descriptions[i]);
			rowItems.add(item);
		}

		listView = (ListView) findViewById(R.id.list);
		MainListViewAdapter adapter = new MainListViewAdapter(this,
				R.layout.main_list_row, rowItems);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		switch (position) {

		case 0:

		case 1:

		case 2:

		case 3:
			
			Intent intent = new Intent(MainActivity.this, RecipesActivity.class);
			startActivity(intent);

		case 4:

		default:

		}

		/*Toast toast = Toast.makeText(getApplicationContext(), "Item "
				+ (position + 1) + ": " + rowItems.get(position),
				Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
		toast.show();*/
	}
}
