package com.fragment.sms;



import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainT3Fragment extends Fragment {


	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View t1Layout = inflater.inflate(R.layout.main_t3_layout, null);
		return t1Layout;
	}

}