package uk.co.nashulver.fragmentexample;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Nic on 2016-09-25.
 */

public class FragmentThree extends Fragment
{
@Override
public View onCreateView(LayoutInflater inflater,
						 ViewGroup container, Bundle savedInstanceState)
{
	//Inflate the layout for this fragment
	return inflater.inflate(
			R.layout.fragment_three, container, false);
}
}