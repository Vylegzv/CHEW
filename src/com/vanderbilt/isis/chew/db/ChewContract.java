package com.vanderbilt.isis.chew.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class ChewContract {

	public static final String AUTHORITY = "com.vanderbilt.isis.chew.provider";
	
	public static final Uri AUTHORITY_URI = Uri.parse("content://"+AUTHORITY);
	
	/** Recipes **/
	public static final class Recipes implements BaseColumns {
		
		public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, "recipes");
		
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/com.vanderbilt.isis.chew.recipes";
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/com.vanderbilt.isis.chew.recipes";
		
		/** Table name **/
		public static final String TABLE = "Recipe";
		
		/** Column names **/
		public static final String SHORT_TITLE = "short_title";
		public static final String LONG_TITLE = "long_title";
		public static final String RECIPE_IMAGE = "recipe_image";
		public static final String FAVORITE = "favorite";
		
		
	}
	
	/** Ingredients **/
	public static final class Ingredients implements BaseColumns {
		
		public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, "ingredients");
		
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/com.vanderbilt.isis.chew.ingredients";
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/com.vanderbilt.isis.chew.ingredients";
		
		/** Table name **/
		public static final String TABLE = "RecipeIngredients";
		
		/** Column names **/
		public static final String RECIPE_ID = "recipe_id";
		public static final String INGREDIENT = "ingredient";
		public static final String LONG_DESCRIPTION = "longer_description";
		
	}
	
	/** Steps **/
	public static final class Steps implements BaseColumns {
		
		public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, "steps");
		
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/com.vanderbilt.isis.chew.steps";
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/com.vanderbilt.isis.chew.steps";
		
		/** Table name **/
		public static final String TABLE = "RecipeSteps";
		
		/** Column names **/
		public static final String RECIPE_ID = "recipe_id";
		public static final String STEP = "step";
		public static final String STEP_IMAGE = "step_image";
		
	}
}

























