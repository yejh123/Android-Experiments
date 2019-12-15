package com.example.helloworld.fragment;


import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.helloworld.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RightFragment extends Fragment implements IFragment {
    private View view;


    public void setMsg(String msg){
        TextView textView = view.findViewById(R.id.right_fragment_text);
        textView.setText(msg);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_right, container, false);
        return view;
    }

}
