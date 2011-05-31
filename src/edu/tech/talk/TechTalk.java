package edu.tech.talk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class TechTalk extends Activity {
	
	private static final int ACTIVITY_CREATE=0;
	private static final int INSERT_ID = Menu.FIRST;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
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
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, ACTIVITY_CREATE);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        
        switch(requestCode) {
        case ACTIVITY_CREATE:
        	Bitmap thumb = (Bitmap) intent.getExtras().get("data");
        	ImageView img = (ImageView) findViewById(R.id.sample_pic);
        	img.setImageBitmap(thumb);
        	break;
        }
    }
}