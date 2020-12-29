package it.stefanocasagrande.covid_stats.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import it.stefanocasagrande.covid_stats.Covid_Interface;
import it.stefanocasagrande.covid_stats.MainActivity;
import it.stefanocasagrande.covid_stats.R;
import it.stefanocasagrande.covid_stats.json_classes.regions.Regions;
import it.stefanocasagrande.covid_stats.json_classes.reports.Total_Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements Covid_Interface, View.OnClickListener {

    TextView tv_date;
    TextView tv_confirmed_cases;
    TextView tv_confirmed_diff;
    TextView tv_deaths;
    TextView tv_deaths_diff;
    TextView tv_recovered;
    TextView tv_recovered_diff;
    TextView tv_active;
    TextView tv_active_diff;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
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
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        tv_date = v.findViewById(R.id.tv_date);
        tv_confirmed_cases = v.findViewById(R.id.tv_confirmed_cases);
        tv_confirmed_diff = v.findViewById(R.id.tv_confirmed_diff);
        tv_deaths = v.findViewById(R.id.tv_deaths);
        tv_deaths_diff = v.findViewById(R.id.tv_deaths_diff);
        tv_recovered = v.findViewById(R.id.tv_recovered);
        tv_recovered_diff = v.findViewById(R.id.tv_recovered_diff);
        tv_active = v.findViewById(R.id.tv_active);
        tv_active_diff = v.findViewById(R.id.tv_active_diff);

        Button btn_aggiorna = v.findViewById(R.id.btn_refresh);
        btn_aggiorna.setOnClickListener(this);

        ((MainActivity) getActivity()).getTotalReport(this, null);

        return v;
    }

    @Override
    public void newReportAvailable(Total_Response wResponse) {

        tv_date.setText(wResponse.getData().getdate_dd_MM_yyyy());

        tv_confirmed_cases.setText(String.valueOf(wResponse.getData().getconfirmed()));
        tv_confirmed_diff.setText(String.valueOf(wResponse.getData().getconfirmed_diff()));

        tv_deaths.setText(String.valueOf(wResponse.getData().getdeaths()));
        tv_deaths_diff.setText(String.valueOf(wResponse.getData().getdeaths_diff()));

        tv_recovered.setText(String.valueOf(wResponse.getData().getrecovered()));
        tv_recovered_diff.setText(String.valueOf(wResponse.getData().getrecovered_diff()));

        tv_active.setText(String.valueOf(wResponse.getData().getactive()));
        tv_active_diff.setText(String.valueOf(wResponse.getData().getactive_diff()));
    }

    @Override
    public void newNationsAvailable(Regions wResponse) {

    }

    @Override
    public void onClick(View view) {
        ((MainActivity) getActivity()).Aggiorna_Report_Totali(this);
    }
}