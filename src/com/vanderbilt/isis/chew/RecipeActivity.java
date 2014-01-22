package com.vanderbilt.isis.chew;

import java.util.ArrayList;

import com.vanderbilt.isis.chew.adapters.IngredientsAdapter;
import com.vanderbilt.isis.chew.adapters.IngredientsStepsAdapter;
import com.vanderbilt.isis.chew.adapters.StepsAdapter;
import com.vanderbilt.isis.chew.db.ChewContract;
import com.vanderbilt.isis.chew.recipes.Ingredient;
import com.vanderbilt.isis.chew.recipes.Recipe;
import com.vanderbilt.isis.chew.recipes.Step;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class RecipeActivity extends Activity {

	String TAG = getClass().getSimpleName();
	ImageView imageView;
	TextView titleView;
	ListView stepsLV;
	Bitmap mainImage;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe_view);
		
		View header = getLayoutInflater().inflate(R.layout.recipe_header, null);
		
		imageView = (ImageView) header.findViewById(R.id.recipeImage);
		titleView = (TextView) header.findViewById(R.id.recipeTitle);
		LinearLayout ll = (LinearLayout) header.findViewById(R.id.l2);
		stepsLV = (ListView) findViewById(R.id.stepsListView);
		stepsLV.addHeaderView(header);
		
		Bundle data = getIntent().getExtras();
		//int recipe_id = data.getInt("recipe_id");
		Recipe recipe = (Recipe) data.getParcelable("recipe");
		
		//Log.d(TAG, recipe_id+"");
		Log.d(TAG, recipe.getTitle());
		
		//ArrayList<Ingredient> ingredients = getIngredients(recipe.getId());
		recipe.setIngredients(getIngredients(recipe.getId()));
		
		/*for(Ingredient i : ingredients){
			Log.d(TAG, i.getLongerDescription());
		}
		
		ArrayList<Step> steps = getSteps(recipe.getId());*/
		recipe.setSteps(getSteps(recipe.getId()));
		
		/*for(Step s : steps){
			Log.d(TAG, s.getStep());
			if(s.getImage() == null){
				Log.d(TAG, "Image null");
			}
		}*/
		
		titleView.setText(recipe.getTitle());
		
    	int path = getResources().getIdentifier(recipe.getImage(), "drawable", "com.vanderbilt.isis.chew");
    	mainImage = BitmapFactory.decodeResource(getResources(), path);
		imageView.setImageBitmap(mainImage);
		
		TextView []txt = new TextView[recipe.getIngredients().size()];
		for(int i = 0; i < txt.length; i++){
			
			txt[i] = new TextView(RecipeActivity.this);
			txt[i].setText(recipe.getIngredients().get(i).getLongerDescription());
			txt[i].setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			txt[i].setTextSize(13);
			txt[i].setTextColor(getResources().getColor(R.color.textcolor));
			txt[i].setTypeface(null, Typeface.BOLD);
			ll.addView(txt[i]);
			
		}
		
	/*IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(RecipeActivity.this, recipe.getIngredients());
	ingredientsLV.setAdapter(ingredientsAdapter);*/
	
	StepsAdapter stepsAdapter = new StepsAdapter(RecipeActivity.this, recipe.getSteps());
	stepsLV.setAdapter(stepsAdapter);
	
	/*IngredientsStepsAdapter adapter = new IngredientsStepsAdapter(RecipeActivity.this, recipe.getIngredients(), recipe.getSteps());
	ingredientsLV.setAdapter(adapter);*/
		
	}
	
    private ArrayList<Ingredient> getIngredients(int id) {
    	
    	String selection = ChewContract.Ingredients.RECIPE_ID + "=?";
    	String selectionArgs[] = { id+"" };
        Cursor curIngrs = getContentResolver().query(ChewContract.Ingredients.CONTENT_URI, null, selection, selectionArgs, null);

        ArrayList<Ingredient> ings = new ArrayList<Ingredient>();

        if (curIngrs != null) {
            while (curIngrs.moveToNext())
                ings.add(Ingredient.fromCursor(curIngrs));
            curIngrs.close();
        }
        return ings;
    }
    
    private ArrayList<Step> getSteps(int id) {
    	
    	String selection = ChewContract.Steps.RECIPE_ID + "=?";
    	String selectionArgs[] = { id+"" };
        Cursor curSteps = getContentResolver().query(ChewContract.Steps.CONTENT_URI, null, selection, selectionArgs, null);

        ArrayList<Step> steps = new ArrayList<Step>();

        if (curSteps != null) {
            while (curSteps.moveToNext())
                steps.add(Step.fromCursor(RecipeActivity.this, curSteps));
            curSteps.close();
        }
        return steps;
    }
    
    @Override
    protected void onDestroy(){
    	super.onDestroy();
    	/** **/
    	mainImage.recycle();
    	mainImage = null;
    }
    
}
