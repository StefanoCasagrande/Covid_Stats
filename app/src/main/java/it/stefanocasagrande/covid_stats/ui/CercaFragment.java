package it.stefanocasagrande.covid_stats.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.stefanocasagrande.covid_stats.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CercaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CercaFragment extends Fragment {


    public CercaFragment() {
        // Required empty public constructor
    }

    public static CercaFragment newInstance() {
        CercaFragment fragment = new CercaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cerca, container, false);

        return v;
    }
}