package com.example.hp.mommy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Hp on 04-02-2017.
 */

public class Consultancy extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.consultancy, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label3);

        return rootView;
    }
}
