package com.fragment.sms;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainT1Fragment extends Fragment {


	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View t1Layout = inflater.inflate(R.layout.main_t1_layout, null);

		Button bt_main1 = (Button) t1Layout.findViewById(R.id.bt_main1);
		bt_main1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(getActivity(),"hello java",Toast.LENGTH_SHORT).show();
			}
		});
		return t1Layout;
	}

}