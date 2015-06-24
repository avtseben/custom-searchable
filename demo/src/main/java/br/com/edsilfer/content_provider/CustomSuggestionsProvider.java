package br.com.edsilfer.content_provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import br.com.customsearchable.contract.CustomProviderColumns;

/**
 * This class provides a custom provider that follows the library contract. It fills the cursor with fake
 * that will be displayed in the search result list
 */
public class CustomSuggestionsProvider extends ContentProvider {

    private static final int GET_SAMPLE = 0;
    private static final int GET_CUSTOM_SUGGESTIONS = 1;

    private static final String PROVIDER_NAME = "br.com.edsilfer.content_provider.CustomSuggestionsProvider";
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "GET_SAMPLE", GET_SAMPLE);
        uriMatcher.addURI(PROVIDER_NAME, "suggestions", GET_CUSTOM_SUGGESTIONS);
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    // Content Provider SQL callbacks ______________________________________________________________
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case GET_SAMPLE:
                return getFakeData();
            case GET_CUSTOM_SUGGESTIONS:
                return getFakeData();
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    // Util ________________________________________________________________________________________
    private Cursor getFakeData () {
        // Columns explanation: http://developer.android.com/guide/topics/search/adding-custom-suggestions.html#HandlingSuggestionQuery
        MatrixCursor mc = new MatrixCursor(new String[] {
                CustomProviderColumns._ID,
                CustomProviderColumns.SUGGEST_COLUMN_TEXT_1,
                CustomProviderColumns.SUGGEST_COLUMN_TEXT_2,
                CustomProviderColumns.SUGGEST_COLUMN_ICON_1,
                CustomProviderColumns.SUGGEST_COLUMN_ICON_2,
                CustomProviderColumns.SUGGEST_COLUMN_INTENT_ACTION,
                CustomProviderColumns.SUGGEST_COLUMN_INTENT_DATA,
                CustomProviderColumns.SUGGEST_COLUMN_INTENT_DATA_ID,
                CustomProviderColumns.SUGGEST_COLUMN_INTENT_EXTRA_DATA,
                CustomProviderColumns.SUGGEST_COLUMN_QUERY,
                CustomProviderColumns.SUGGEST_COLUMN_SHORTCUT_ID,
                CustomProviderColumns.SUGGEST_COLUMN_SPINNER_WHILE_REFRESHING
        });

        String[] fakeRow1 = {"ID1", "Mock data 1", "Sub mock data 1", null, null, null, null, null, null, null, null, null};
        String[] fakeRow2 = {"ID2", "Mock data 2", "Sub mock data 2", null, null, null, null, null, null, null, null, null};
        String[] fakeRow3 = {"ID3", "Mock data 3", "Sub mock data 3", null, null, null, null, null, null, null, null, null};

        mc.addRow(fakeRow1);
        mc.addRow(fakeRow2);
        mc.addRow(fakeRow3);

        return mc;
    }
}
