package com.ksharkapps.PlaycardGrid;

import java.io.File;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;


import com.ksharkapps.fragments.WeBucketGridFragment;
import com.ksharkapps.fragments.WeBucketListFragment;
import com.ksharkapps.fragments.WeListFragment;
import com.ksharkapps.ui.widgets.PagerSlidingTabStrip;
import com.ksharkapps.PlaycardGrid.R;
import com.ksharkapps.PlaycardGrid.R.color;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.widget.ListView;


public class MainActivity extends SherlockFragmentActivity {
	
    private ActionBar actionBar;
    private ListView mList;
    private String[] items;
    private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private MyPagerAdapter adapter;
	private Drawable oldBackground = null;
	private int currentColor =Color.parseColor("#FF96AA39");
	private final Handler handler = new Handler();

    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		pager = (ViewPager) findViewById(R.id.pager);
		adapter = new MyPagerAdapter(getSupportFragmentManager());
		
		pager.setAdapter(adapter);

		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
				.getDisplayMetrics());
		pager.setPageMargin(pageMargin);

		tabs.setViewPager(pager);
		
		actionBar= getSupportActionBar();
		Drawable colorDrawable = new ColorDrawable(currentColor);
		Drawable bottomDrawable = getResources().getDrawable(R.drawable.actionbar_bottom);
		LayerDrawable ld = new LayerDrawable(new Drawable[] { colorDrawable, bottomDrawable });
		actionBar.setBackgroundDrawable(ld);
		actionBar.setIcon(getResources().getDrawable(R.drawable.ic_action_example));
		tabs.setIndicatorColor(currentColor);

		
	}
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSherlock().getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void changeColor(int newColor) {

		tabs.setIndicatorColor(newColor);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {

			Drawable colorDrawable = new ColorDrawable(newColor);
			Drawable bottomDrawable = getResources().getDrawable(R.drawable.actionbar_bottom);
			LayerDrawable ld = new LayerDrawable(new Drawable[] { colorDrawable, bottomDrawable });

			if (oldBackground == null) {

				if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
					ld.setCallback(drawableCallback);
				} else {
					actionBar.setBackgroundDrawable(ld);
				}

			} else {

				TransitionDrawable td = new TransitionDrawable(new Drawable[] { oldBackground, ld });

				if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
					td.setCallback(drawableCallback);
				} else {
					actionBar.setBackgroundDrawable(td);
				}

				td.startTransition(200);

			}

			oldBackground = ld;

			actionBar.setDisplayShowTitleEnabled(false);
			actionBar.setDisplayShowTitleEnabled(true);

		}

		currentColor = newColor;

	}
	
	private Drawable.Callback drawableCallback = new Drawable.Callback() {
		@Override
		public void invalidateDrawable(Drawable who) {
			actionBar.setBackgroundDrawable(who);
		}

		@Override
		public void scheduleDrawable(Drawable who, Runnable what, long when) {
			handler.postAtTime(what, when);
		}

		@Override
		public void unscheduleDrawable(Drawable who, Runnable what) {
			handler.removeCallbacks(what);
		}
	};

	  
	public class MyPagerAdapter extends FragmentPagerAdapter {

		private final String[] TITLES = { "Categories", "Home", "Top Paid", "Top Free", "Top Grossing", "Top New Paid",
				"Top New Free", "Trending" };

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public Fragment getItem(int position) {
			 
			switch (position) {
			case 0:
				return WeListFragment.newInstance(position);

			case 1:
				return WeBucketGridFragment.newInstance(position);

            case 2:
				return WeBucketListFragment.newInstance(position);

            case 3:
				return WeBucketGridFragment.newInstance(position);

            case 4:
				return WeBucketListFragment.newInstance(position);

            case 5:
				return WeBucketListFragment.newInstance(position);

            case 6:
				return WeBucketGridFragment.newInstance(position);

            case 7:
				return WeBucketListFragment.newInstance(position);
			default:
				
				return null;

			
			}
			
		}

	}
	
	




}
