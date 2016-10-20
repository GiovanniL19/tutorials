package net.paulboocock.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Toast;

public class DownloadImageService extends Service {

    public class DownloadThread extends Thread {
        private Context _context;
        private int _ID;

        public DownloadThread(Context context, int ID) {
            _context = context;
            _ID = ID;
        }

        @Override
        public void run() {

            doWork();

            super.run();
        }

        public void doWork() {
            Bitmap bmp;
            URL imageURL = null;

            try {
                //Just some random high res images on the internet
                List<String> images = new ArrayList<String>();

                images.add("https://static.pexels.com/photos/102617/pexels-photo-102617.jpeg");
                images.add("https://static.pexels.com/photos/185699/pexels-photo-185699.jpeg");
                images.add("https://static.pexels.com/photos/121506/pexels-photo-121506.jpeg");
                images.add("https://static.pexels.com/photos/117386/pexels-photo-117386.jpeg");
                images.add("https://static.pexels.com/photos/106144/rubber-duck-bath-duck-toys-costume-106144.jpeg");

                Random random = new Random();
                int randomNumber = random.nextInt(images.size());

                imageURL = new URL(images.get(randomNumber));

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            try {
                HttpURLConnection conn = (HttpURLConnection) imageURL.openConnection();
                conn.setDoInput(true);
                conn.connect();
                InputStream is = conn.getInputStream();
                bmp = BitmapFactory.decodeStream(is);

                String root = _context.getFilesDir().getAbsolutePath();
                File file = new File(root, "Image.jpg");

                if (file.exists())
                    file.delete();

                try {
                    FileOutputStream out = new FileOutputStream(file);
                    bmp.compress(CompressFormat.JPEG, 90, out);
                    out.flush();
                    out.close();

                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(_context)
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setContentTitle("Image Downloaded")
                            .setContentText("Tap to view!");

                    // Creates an explicit intent for an Activity in your app
                    Intent resultIntent = new Intent(_context, MainActivity.class);

                    // The stack builder object will contain an artificial back	stack for the started Activity. This ensures that navigating backward
                    // from the Activity leads out of your application to the Home screen. A little unnecessary here but good practice!
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(_context);
                    // Adds the back stack for the Intent (but not the Intent itself)
                    stackBuilder.addParentStack(MainActivity.class);
                    // Adds the Intent that starts the Activity to the top of the stack
                    stackBuilder.addNextIntent(resultIntent);
                    PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                    mBuilder.setContentIntent(resultPendingIntent);

                    NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.notify(1, mBuilder.build());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Stop the service using the ID, so that we don't stop
            // the service in the middle of handling another job
            stopSelf(_ID);
        }
    }

    @Override
    public void onCreate() {
        // We do nothing here as we are going to create a new thread for each
        // request
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "downloading image...", Toast.LENGTH_SHORT).show(); // Executes on UI thread!

        DownloadThread thread = new DownloadThread(this, startId);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

    @Override
    public void onDestroy() {
       //Toast.makeText(this, "service finished", Toast.LENGTH_SHORT).show();

        //Send the broadcast
        Intent intent = new Intent();
        intent.setAction("net.paulboocock.services.processComplete");
        //Make it compatible for all android versions
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);

        System.out.println("Broadcast has began");
    }

}
