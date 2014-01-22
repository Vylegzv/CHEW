package com.vanderbilt.isis.chew.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class ChewContentProvider extends ContentProvider {

	private String TAG = getClass().getSimpleName();
	
	/**
	 * Helper constants to use with UriMatcher
	 */
	private static final int RECIPES = 1;
	private static final int RECIPE_ID = 2;
	private static final int INGREDIENTS = 3;
	private static final int INGREDIENT_ID = 4;
	private static final int STEPS = 5;
	private static final int STEP_ID = 6;

	/**
	 * UriMatcher that will match different URIs
	 */
	private static final UriMatcher uriMatcher;

	/**
	 * Declare an SQLite open helper to manage database creation and versions
	 */
	private ChewDBHelper myDbOpenHelper;
	
	/**
	 * Declare a UriMatcher to match URIs
	 */
	static {

		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(ChewContract.AUTHORITY, "recipes", RECIPES);
		uriMatcher.addURI(ChewContract.AUTHORITY, "recipes/#", RECIPE_ID);
		uriMatcher.addURI(ChewContract.AUTHORITY, "ingredients", INGREDIENTS);
		uriMatcher.addURI(ChewContract.AUTHORITY, "ingredients/#", INGREDIENT_ID);
		uriMatcher.addURI(ChewContract.AUTHORITY, "steps", STEPS);
		uriMatcher.addURI(ChewContract.AUTHORITY, "steps/#", STEP_ID);
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {

		String rowId = null;
		String table = null;

		switch (uriMatcher.match(uri)) {

		case RECIPE_ID:

			table = ChewContract.Recipes.TABLE;
			rowId = uri.getPathSegments().get(1);
			selection = ChewContract.Recipes._ID
					+ "="
					+ rowId
					+ (!TextUtils.isEmpty(selection) ? " AND (" + selection
							+ ')' : "");
			break;

		case RECIPES:

			table = ChewContract.Recipes.TABLE;
			break;
			
		case INGREDIENT_ID:

			table = ChewContract.Ingredients.TABLE;
			rowId = uri.getPathSegments().get(1);
			selection = ChewContract.Ingredients._ID
					+ "="
					+ rowId
					+ (!TextUtils.isEmpty(selection) ? " AND (" + selection
							+ ')' : "");
			break;

		case INGREDIENTS:

			table = ChewContract.Ingredients.TABLE;
			break;
			
		case STEP_ID:

			table = ChewContract.Steps.TABLE;
			rowId = uri.getPathSegments().get(1);
			selection = ChewContract.Steps._ID
					+ "="
					+ rowId
					+ (!TextUtils.isEmpty(selection) ? " AND (" + selection
							+ ')' : "");
			break;

		case STEPS:

			table = ChewContract.Steps.TABLE;
			break;

		default:

			Log.e(TAG, "Unsupported URI: " + uri);
			return 0;
		}

		/**
		 * To return the number of deleted items, a where clause must be
		 * specified. Pass in 1 to delete all rows.
		 */
		if (selection == null)
			selection = "1";

		int deleteCount = myDbOpenHelper.getWritableDatabase().delete(table,
				selection, selectionArgs);
	    if (deleteCount > 0)
	    	getContext().getContentResolver().notifyChange(uri, null); //uri?

		return deleteCount;
	}

	@Override
	public String getType(Uri uri) {
		
		switch (uriMatcher.match(uri)) {

		case RECIPE_ID:
			return ChewContract.Recipes.CONTENT_ITEM_TYPE;
		case RECIPES:
			return ChewContract.Recipes.CONTENT_TYPE;
		case INGREDIENT_ID:
			return ChewContract.Ingredients.CONTENT_ITEM_TYPE;
		case INGREDIENTS:
			return ChewContract.Ingredients.CONTENT_TYPE;
		case STEP_ID:
			return ChewContract.Steps.CONTENT_ITEM_TYPE;
		case STEPS:
			return ChewContract.Steps.CONTENT_TYPE;
		default:
			Log.e(TAG, "Unsupported URI: " + uri);
			return null;

		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		
		long rowId = 0;
		Uri _uri = null;

		switch (uriMatcher.match(uri)) {

		case RECIPES:

			rowId = myDbOpenHelper.getWritableDatabase().insert(ChewContract.Recipes.TABLE,
					null, values);
			// if added successfully
			if (rowId > 0) {
				_uri = ContentUris.withAppendedId(
						ChewContract.Recipes.CONTENT_URI, rowId);
				getContext().getContentResolver().notifyChange(_uri, null);
			}
			break;
			
		case INGREDIENTS:

			rowId = myDbOpenHelper.getWritableDatabase().insert(ChewContract.Ingredients.TABLE,
					null, values);
			// if added successfully
			if (rowId > 0) {
				_uri = ContentUris.withAppendedId(
						ChewContract.Ingredients.CONTENT_URI, rowId);
				getContext().getContentResolver().notifyChange(_uri, null);
			}
			break;
			
		case STEPS:

			rowId = myDbOpenHelper.getWritableDatabase().insert(ChewContract.Steps.TABLE,
					null, values);
			// if added successfully
			if (rowId > 0) {
				_uri = ContentUris.withAppendedId(
						ChewContract.Steps.CONTENT_URI, rowId);
				getContext().getContentResolver().notifyChange(_uri, null);
			}
			break;
			
		default:

			Log.e(TAG, "Unsupported URI: " + uri);
			return null;
		}
		
		// Notify any observers of the change in the data set.
	    getContext().getContentResolver().notifyChange(_uri, null);
		
		return _uri;
	}
	

	@Override
	public boolean onCreate() {
		
		myDbOpenHelper = new ChewDBHelper(getContext());
		return ((myDbOpenHelper == null) ? false : true);
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		String rowId = null;
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

		switch (uriMatcher.match(uri)) {

		case RECIPE_ID:

			rowId = uri.getPathSegments().get(1);
			queryBuilder.setTables(ChewContract.Recipes.TABLE);
			queryBuilder.appendWhere(ChewContract.Recipes._ID + "=" + rowId);
			break;

		case RECIPES:

			queryBuilder.setTables(ChewContract.Recipes.TABLE);
			break;
			
		case INGREDIENT_ID:

			rowId = uri.getPathSegments().get(1);
			queryBuilder.setTables(ChewContract.Ingredients.TABLE);
			queryBuilder.appendWhere(ChewContract.Ingredients._ID + "=" + rowId);
			break;

		case INGREDIENTS:

			queryBuilder.setTables(ChewContract.Ingredients.TABLE);
			break;
			
		case STEP_ID:

			rowId = uri.getPathSegments().get(1);
			queryBuilder.setTables(ChewContract.Steps.TABLE);
			queryBuilder.appendWhere(ChewContract.Steps._ID + "=" + rowId);
			break;

		case STEPS:

			queryBuilder.setTables(ChewContract.Steps.TABLE);
			break;

		default:

			Log.e(TAG, "Unsupported URI: " + uri);
			return null;
		}

		Cursor cursor = queryBuilder.query(
				myDbOpenHelper.getWritableDatabase(), projection, selection,
				selectionArgs, null, null, sortOrder);

		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		
		String rowId = null;
		String table = null;

		switch (uriMatcher.match(uri)) {

		case RECIPE_ID:

			table = ChewContract.Recipes.TABLE;
			rowId = uri.getPathSegments().get(1);
			selection = ChewContract.Recipes._ID
					+ "="
					+ rowId
					+ (!TextUtils.isEmpty(selection) ? " AND (" + selection
							+ ')' : "");
			break;

		case RECIPES:

			table = ChewContract.Recipes.TABLE;
			break;
			
		case INGREDIENT_ID:

			table = ChewContract.Ingredients.TABLE;
			rowId = uri.getPathSegments().get(1);
			selection = ChewContract.Ingredients._ID
					+ "="
					+ rowId
					+ (!TextUtils.isEmpty(selection) ? " AND (" + selection
							+ ')' : "");
			break;

		case INGREDIENTS:

			table = ChewContract.Ingredients.TABLE;
			break;
			
		case STEP_ID:

			table = ChewContract.Steps.TABLE;
			rowId = uri.getPathSegments().get(1);
			selection = ChewContract.Steps._ID
					+ "="
					+ rowId
					+ (!TextUtils.isEmpty(selection) ? " AND (" + selection
							+ ')' : "");
			break;

		case STEPS:

			table = ChewContract.Steps.TABLE;
			break;

		default:

			Log.e(TAG, "Unsupported URI: " + uri);
			return 0;
		}

		int updateCount = myDbOpenHelper.getWritableDatabase().update(table,
				values, selection, selectionArgs);

		if (updateCount > 0)
			getContext().getContentResolver().notifyChange(ChewContract.Recipes.CONTENT_URI, null);

		return updateCount;
	}
}
