package com.easymvp.simple.his;//package cc.easyandroid.simple.his;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TabHost;
//import android.widget.TabWidget;
//
//import java.util.ArrayList;
//
//import cc.easyandroid.easyui.pojo.EasyTab;
//
///**
// * Tab+Fragment+ViewPager
// *
// * @author 耳东 www.kubeiwu.com
// */
//public abstract class EasyFragmentTabsPager extends EasyTabBaseFragment {
//	private TabHost mTabHost;
//	private ViewPager mViewPager;
//	private TabsPagerAdapter mTabsAdapter;
//
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		TabConfig tabConfig = onCreatTabConfig();
//		if (tabConfig == null) {
//			tabConfig = TabConfig.getSimpleInstance();
//		}
//		mTabHost = ViewFactory.getTabHostAndPagerView(inflater.getContext(), tabConfig.getTabGravity());
//		mTabHost.setup();
//
//		mViewPager = (ViewPager) mTabHost.findViewById(EasyR.id.pager);
//		mTabsAdapter = new TabsPagerAdapter(this, mTabHost, mViewPager);
//
//		creatTab();
//		int tabcount = mTabHost.getTabWidget().getChildCount();
//		if (tabcount == 0) {
//			throw new IllegalArgumentException("Please in the onCreatTab() method to addTab");
//		}
//		if (tabConfig.getWidgetBackgroundResource() != 0) {
//			mTabHost.getTabWidget().setBackgroundResource(tabConfig.getWidgetBackgroundResource());
//		}
//		if (tabConfig.getWidgetDividerDrawableResId() != 0) {
//			mTabHost.getTabWidget().setDividerDrawable(tabConfig.getWidgetDividerDrawableResId());
//		}
//		return mTabHost;
//	}
//
//	public void setOffscreenPageLimit(int limit) {
//		mViewPager.setOffscreenPageLimit(limit);
//	}
//
//	public ViewPager getViewPager() {
//
//		return mViewPager;
//	}
//
//	/**
//	 * eg:EATab eaTab=new EATab(tabSpec, tabView, yourFragment.class, bundle);
//	 */
//	void addTab(EasyTab eaTab) {
//		mTabsAdapter.addTab(mTabHost.newTabSpec(eaTab.getTabSpec())//
//				.setIndicator(eaTab.getTabView()), eaTab.getYourFragment(), eaTab.getBundle());
//	}
//
//	/**
//	 * 适配器
//	 *
//	 * @author 耳东 www.kubeiwu.com
//	 */
//	public class TabsPagerAdapter extends FragmentPagerAdapter implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {
//		private Context mContext;
//		private TabHost mTabHost;
//		private ViewPager mViewPager;
//		private ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();
//
//		class TabInfo {
//			@SuppressWarnings("unused")
//			private String tag;
//			private Class<?> clss;
//			private Bundle args;
//
//			TabInfo(String _tag, Class<?> _class, Bundle _args) {
//				tag = _tag;
//				clss = _class;
//				args = _args;
//			}
//		}
//
//		class DummyTabFactory implements TabHost.TabContentFactory {
//			private final Context mContext;
//
//			public DummyTabFactory(Context context) {
//				mContext = context;
//			}
//
//			@Override
//			public View createTabContent(String tag) {
//				View v = new View(mContext);
//				v.setMinimumWidth(0);
//				v.setMinimumHeight(0);
//				return v;
//			}
//		}
//
//		public TabsPagerAdapter(Fragment activity, TabHost tabHost, ViewPager pager) {
//			super(activity.getFragmentManager());// 在fragment中使用
//			mContext = activity.getActivity();
//			mTabHost = tabHost;
//			mViewPager = pager;
//			mTabHost.setOnTabChangedListener(this);
//			mViewPager.setAdapter(this);
//			mViewPager.setOnPageChangeListener(this);
//		}
//
//		public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
//			tabSpec.setContent(new DummyTabFactory(mContext));
//			String tag = tabSpec.getTag();
//
//			TabInfo info = new TabInfo(tag, clss, args);
//			mTabs.add(info);
//			mTabHost.addTab(tabSpec);
//			notifyDataSetChanged();
//		}
//
//		@Override
//		public int getCount() {
//			return mTabs.size();
//		}
//
//		@Override
//		public Fragment getItem(int position) {
//			TabInfo info = mTabs.get(position);
//			return Fragment.instantiate(mContext, info.clss.getName(), info.args);
//		}
//
//		@Override
//		public void onTabChanged(String tabId) {
//			int position = mTabHost.getCurrentTab();
//			mViewPager.setCurrentItem(position);
//		}
//
//		@Override
//		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//		}
//
//		@Override
//		public void onPageSelected(int position) {
//
//			TabWidget widget = mTabHost.getTabWidget();
//			int oldFocusability = widget.getDescendantFocusability();
//			widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
//			mTabHost.setCurrentTab(position);
//			widget.setDescendantFocusability(oldFocusability);
//		}
//
//		@Override
//		public void onPageScrollStateChanged(int state) {
//
//		}
//	}
//}
