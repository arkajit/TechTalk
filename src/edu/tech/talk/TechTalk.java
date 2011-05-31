package edu.tech.talk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

public class TechTalk extends ListActivity {
	
	private static final String KEY_PHOTO = "photo";
	
	private static final int ACTIVITY_CREATE=0;
	private static final int INSERT_ID = Menu.FIRST;
	
	private List<Map<String, Object>> mStories;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mStories = new ArrayList<Map<String, Object>>();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, INSERT_ID, 0, R.string.menu_add);
        return true;
    }
    
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) {
            case INSERT_ID:
                createStory();
                return true;
        }

        return super.onMenuItemSelected(featureId, item);
    }
    
    private void createStory() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // use EXTRA_OUTPUT for a larger image size
        //camera.putExtra(MediaStore.EXTRA_OUTPUT, value);
        startActivityForResult(camera, ACTIVITY_CREATE);
    }
    
    private void showPics() {
    	String[] from = new String[] { KEY_PHOTO };
    	int[] to = new int[] { R.id.img1 };
    	SimpleAdapter stories = new SimpleAdapter(this, mStories, 
    			R.layout.story_row, from, to);
    	setListAdapter(stories);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        
        switch(requestCode) {
        case ACTIVITY_CREATE:
        	Uri uri = intent.getData();
        	//Bitmap thumb = (Bitmap) intent.getExtras().get("data");
        	Map<String, Object> row = new HashMap<String, Object>();
        	row.put(KEY_PHOTO, uri); // TODO: img is too big, exceeding VM memory limit
        	mStories.add(row);
        	showPics();
//        	ImageView img = (ImageView) findViewById(R.id.sample_pic);
//        	img.setImageURI(uri);
        	//img.setImageBitmap(thumb);
        	break;
        }
    }
}