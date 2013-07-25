package com.ksharkapps.fragments;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.MenuItem;
import com.ksharkapps.fragments.WeBucketGridFragment.CustomAdapter;
import com.ksharkapps.PlaycardGrid.R;


public class WeListFragment extends SherlockFragment{
	
	private CustomAdapter adapter;
	private Fragment mContent;
	private String[] listitems;
	private ListView cardList;
	private Context mContext;
	private static ProgressBar progress;
    private static final String ARG_POSITION = "position";


	
	public static WeListFragment newInstance(int position) {
		WeListFragment f = new WeListFragment();
		Bundle b = new Bundle();
		b.putInt(ARG_POSITION, position);
		f.setArguments(b);
		return f;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup group,
			Bundle saved) {
		View v = inflater.inflate(R.layout.play_listview, group, false);
		cardList =(ListView)v.findViewById(R.id.card_list);
		progress = (ProgressBar)v.findViewById(R.id.progressBar);

		return v;
	}

	private void showMessage(String message) {
		Toast.makeText(getActivity(), message,
				Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		new AsyncTask<String[], Long, Long>(){
 			
			  @Override
				protected Long doInBackground(String[]... params) {
				
				  listitems = new String[1000];
					for(int i=0 ;i<1000 ;i++){
						listitems[i] = "Temple run";
					}
				  

					return null;
				}
				protected void onPreExecute() {
					
					progress.setVisibility(View.VISIBLE);
					cardList.setVisibility(View.GONE);
				}
              
				@Override
		        public void onProgressUpdate(Long... value) {
		            
		        }
				@Override
				protected void onPostExecute(Long result){
					
					    adapter = new CustomAdapter(getActivity(),listitems);
					    cardList.setAdapter(adapter);
						cardList.setVisibility(View.VISIBLE);

					    cardList.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> av, View v, int position,
									long id) {
								// TODO Auto-generated method stub
								showMessage("position :" +position);
							}
					    	
						});
					progress.setVisibility(View.GONE);

				}
		
   	    }.execute();
   	
		
	
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		if (savedInstanceState != null)
			mContent = getActivity().getSupportFragmentManager().getFragment(
					savedInstanceState, "mContent");
		super.onCreate(savedInstanceState);
	}

	

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case android.R.id.home:
			getFragmentManager().popBackStack();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	
	public class CustomAdapter extends ArrayAdapter<String> {
		
       private Activity mActivity;
       private String[] items;
	
		public CustomAdapter(Activity act,String[] items) {
				
			super(getActivity(), R.layout.play_listitem,items);
			this.items = items;
			this.mActivity=act;

			}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final ViewHolder holder;

			if (convertView == null) {
				LayoutInflater inflater = mActivity.getLayoutInflater(); 
				convertView = inflater.inflate(R.layout.play_listitem, null); 
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.name.setText(items[position]);
			
			
			return (convertView);
		}

	}

class ViewHolder {
	public TextView name = null;
	public TextView info = null;
	public TextView modified = null;
	public CheckBox select = null;
	public ImageView image = null, app_icon = null;
	public LinearLayout rowlayout;

	ViewHolder(View row) {
		name = (TextView) row.findViewById(R.id.projectName);
		info = (TextView) row.findViewById(R.id.companyName);
		modified = (TextView) row.findViewById(R.id.Name);
        image = (ImageView) row.findViewById(R.id.listicon);
		
	}

	void populateFrom(String s) {
		name.setText(s);
	}
}


	


}
