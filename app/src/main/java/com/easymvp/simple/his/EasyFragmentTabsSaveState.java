package com.easymvp.simple.his;//package cc.easyandroid.simple.his;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import cc.easyandroid.easyui.pojo.EasyTab;
//import cc.easyandroid.easyui.view.EasyFragmentTabHostSaveState;
//
///**
// * Tab+Fragment
// *
// * @author 耳东
// *
// */
//public abstract class EasyFragmentTabsSaveState extends EasyTabBaseFragment {
//	protected EasyFragmentTabHostSaveState mFragmentTabHost;
//
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		TabConfig tabConfig = onCreatTabConfig();
//
//		if (tabConfig == null) {
//			tabConfig = TabConfig.getSimpleInstance();
//		}
//
//		View view = ViewFactory.getKFragmentTabHostSaveStateView(inflater.getContext(), tabConfig.getTabGravity());
//		mFragmentTabHost = (EasyFragmentTabHostSaveState) view.findViewById(android.R.id.tabhost);
//		mFragmentTabHost.setup(view.getContext(), getChildFragmentManager(), EasyR.id.realtabcontent);
//
//		creatTab();
//
//		int tabcount = mFragmentTabHost.getTabWidget().getChildCount();
//		if (tabcount == 0) {
//			throw new IllegalArgumentException("Please in the onCreatTab() method to addTab");
//		}
//		mFragmentTabHost.getTabWidget().setBackgroundResource(tabConfig.getWidgetBackgroundResource());
//		return view;
//	}
//
//
//	/**
//	 * eg:EATab eaTab=new EATab(tabSpec, tabView, yourFragment.class, bundle);
//	 */
//	  void addTab(EasyTab eaTab) {
//		mFragmentTabHost.addTab(mFragmentTabHost.newTabSpec(eaTab.getTabSpec())//
//				.setIndicator(eaTab.getTabView()), eaTab.getYourFragment(), eaTab.getBundle());
//	}
//}
