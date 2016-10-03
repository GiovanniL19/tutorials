package uk.co.nashulver.fragmentexample;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Nic on 2016-09-25.
 * Doesn't use almost any of the life-cyle events
 * as it is a very simple example.
 */
public class FragmentOne extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState)
	{
// Simple tell-tale (note the getActivity() method - gets a reference to the containing Activity)
//		Toast toast = Toast.makeText(getActivity(), "onCreateView, FragmentOne", Toast.LENGTH_SHORT);
//		toast.show();

		//Inflate the layout for this fragment
		return inflater.inflate(
				R.layout.fragment_one, container, false);
	}
}
