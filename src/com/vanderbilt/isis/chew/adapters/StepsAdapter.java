package com.vanderbilt.isis.chew.adapters;

import java.util.ArrayList;
import com.vanderbilt.isis.chew.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.vanderbilt.isis.chew.recipes.Step;

public class StepsAdapter extends ArrayAdapter<Step> {
	
	private final Context context;
	private final ArrayList<Step> values;
	
	public StepsAdapter(Context context, ArrayList<Step> values){
		
		super(context, R.layout.step_row, values);
		this.context = context;
		this.values = values;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.step_row, parent, false);
		
		TextView tv = (TextView) rowView.findViewById(R.id.step);
		tv.setText(values.get(position).getStep());
		
		return rowView;
	}
}
