package net.paulboocock.services;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends ActionBarActivity {

    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        updateUI();
    }
    public static MainActivity getInstace(){
        return instance;
    }

    public void updateUI() {
        ImageView downloadedImage = (ImageView)findViewById(R.id.imageView1);

        try {
            String root = getApplicationContext().getFilesDir().getAbsolutePath();
            File file = new File (root, "Image.jpg");
            FileInputStream fis = new FileInputStream(file);
            Bitmap bmp = BitmapFactory.decodeStream(fis);

            downloadedImage.setImageBitmap(bmp);
            fis.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "No Image Found", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onButtonClick(View view)
    {
        Intent intent = new Intent(this, DownloadImageService.class);
        startService(intent);
    }
}
