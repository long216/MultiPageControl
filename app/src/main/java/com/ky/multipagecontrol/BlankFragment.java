package com.ky.multipagecontrol;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ky.library.MultiPageControlManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    private View mInflate;
    private Button mButton;
    private MultiPageControlManager multiPageControlManager;

    public static BlankFragment getInstance() {
        return new BlankFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflate = inflater.inflate(R.layout.fragment_blank, container, false);
        // Inflate the layout for this fragment
        mButton = mInflate.findViewById(R.id.button1);

        multiPageControlManager = MultiPageControlManager.getInstance().init((ViewGroup) mInflate,R.layout.base_loading,R.layout.base_retry,R.layout.base_empty);

        multiPageControlManager.showEmpty();
        return mInflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();

    }
}
