package uk.co.nashulver.fragmentexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.Toast;

public class MainFrag extends AppCompatActivity
{

@Override
protected void onCreate(Bundle savedInstanceState)
{
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main_frag);
}

public void showFragment(View view)
{
	Fragment fragment;
	if (view == findViewById(R.id.btn_one)){
		fragment = new FragmentOne();
		Toast toast = Toast.makeText(this, "Button 1 Clicked, Showing 1st Fragment", Toast.LENGTH_SHORT);
		toast.show();
	} else if(view == findViewById(R.id.btn_two)) {
		fragment = new FragmentTwo();
		Toast toast = Toast.makeText(this, "Button 2 Clicked, Showing 1st Fragment", Toast.LENGTH_SHORT);
		toast.show();
	} else if(view == findViewById(R.id.btn_three)) {
		fragment = new FragmentThree();
		Toast toast = Toast.makeText(this, "Button 3 Clicked, Showing 1st Fragment", Toast.LENGTH_SHORT);
		toast.show();
	}else{
		fragment = new FragmentThree();	// default fragment
	}

	// replaces one fragment with another
	FragmentManager fragmentManager = getFragmentManager();
	FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	fragmentTransaction.replace(R.id.fragment_place_holder, fragment);
	fragmentTransaction.commit();
}

public void showsSecondFragment(View view)
{
	Fragment fragment;
	if (view == findViewById(R.id.btn2_one)){
		fragment = new FragmentOne();
		Toast toast = Toast.makeText(this, "Button 1 Clicked, Showing 2nd Fragment", Toast.LENGTH_SHORT);
		toast.show();
	} else if(view == findViewById(R.id.btn2_two)) {
		fragment = new FragmentTwo();
		Toast toast = Toast.makeText(this, "Button 2 Clicked, Showing 2nd Fragment", Toast.LENGTH_SHORT);
		toast.show();
	} else if(view == findViewById(R.id.btn2_three)) {
		fragment = new FragmentThree();
		Toast toast = Toast.makeText(this, "Button 3 Clicked, Showing 2nd Fragment", Toast.LENGTH_SHORT);
		toast.show();
	}else{
		fragment = new FragmentThree();	// default fragment
	}

	// replaces one fragment with another
	FragmentManager fragmentManager = getFragmentManager();
	FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	fragmentTransaction.replace(R.id.fragment_three_place_holder, fragment);
	fragmentTransaction.commit();
}

}
