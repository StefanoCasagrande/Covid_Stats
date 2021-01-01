package it.stefanocasagrande.covid_stats.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;
import java.util.Date;

import it.stefanocasagrande.covid_stats.Covid_Interface;
import it.stefanocasagrande.covid_stats.MainActivity;
import it.stefanocasagrande.covid_stats.R;
import it.stefanocasagrande.covid_stats.json_classes.reports.Data_Reports;
import it.stefanocasagrande.covid_stats.json_classes.reports.Province_Response;
import it.stefanocasagrande.covid_stats.json_classes.reports.Total_Response;

import static it.stefanocasagrande.covid_stats.Common.Common.AddDotToInteger;

public class ProvinceReportFragment extends Fragment implements Covid_Interface, View.OnClickListener{

    TextView tv_date;
    TextView tv_confirmed_cases;
    TextView tv_confirmed_diff;
    TextView tv_deaths;
    TextView tv_deaths_diff;
    TextView tv_recovered;
    TextView tv_recovered_diff;
    TextView tv_active;
    TextView tv_active_diff;
    TextView tv_fatality_rate;
    TextView tv_region;

    static String iso;
    static Date selected_date;
    static String province;

    public ProvinceReportFragment() {
        // Required empty public constructor
    }

    public static ProvinceReportFragment newInstance(String p_iso, Date p_date, String p_province) {
        ProvinceReportFragment fragment = new ProvinceReportFragment();

        iso = p_iso;
        selected_date = p_date;
        province = p_province;

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

        tv_region = v.findViewById(R.id.tv_region);
        tv_date = v.findViewById(R.id.tv_date);
        tv_confirmed_cases = v.findViewById(R.id.tv_confirmed_cases);
        tv_confirmed_diff = v.findViewById(R.id.tv_confirmed_diff);
        tv_deaths = v.findViewById(R.id.tv_deaths);
        tv_deaths_diff = v.findViewById(R.id.tv_deaths_diff);
        tv_recovered = v.findViewById(R.id.tv_recovered);
        tv_recovered_diff = v.findViewById(R.id.tv_recovered_diff);
        tv_active = v.findViewById(R.id.tv_active);
        tv_active_diff = v.findViewById(R.id.tv_active_diff);
        tv_fatality_rate = v.findViewById(R.id.tv_fatality_rate);

        ((MainActivity) getActivity()).getReportByProvince(iso, selected_date, province, this);

        Button btn_refresh = v.findViewById(R.id.btn_refresh);
        btn_refresh.setOnClickListener(this);

        return v;
    }

    @Override
    public void newProvinceReportAvailable(Province_Response wResponse) {

        if (wResponse.getDatas().size()==1)
        {
            tv_date.setText(wResponse.getDatas().get(0).getdate_dd_MM_yyyy());

            tv_confirmed_cases.setText(AddDotToInteger(wResponse.getDatas().get(0).getconfirmed()));
            tv_confirmed_diff.setText(AddDotToInteger(wResponse.getDatas().get(0).getconfirmed_diff()));

            tv_deaths.setText(AddDotToInteger(wResponse.getDatas().get(0).getdeaths()));
            tv_deaths_diff.setText(AddDotToInteger(wResponse.getDatas().get(0).getdeaths_diff()));

            tv_recovered.setText(AddDotToInteger(wResponse.getDatas().get(0).getrecovered()));
            tv_recovered_diff.setText(AddDotToInteger(wResponse.getDatas().get(0).getrecovered_diff()));

            tv_active.setText(AddDotToInteger(wResponse.getDatas().get(0).getactive()));
            tv_active_diff.setText(AddDotToInteger(wResponse.getDatas().get(0).getactive_diff()));

            DecimalFormat df = new DecimalFormat("#.#####");
            tv_fatality_rate.setText(df.format(wResponse.getDatas().get(0).getfatality_rate()));

            if (wResponse.getDatas().get(0).getregion().province!=null && !wResponse.getDatas().get(0).getregion().province.equals(""))
                tv_region.setText(String.format("%s (%s)", wResponse.getDatas().get(0).getregion().province, wResponse.getDatas().get(0).getregion().name));
            else
                tv_region.setText(wResponse.getDatas().get(0).getregion().name);
        }
        else
        {
            tv_date.setText(wResponse.getDatas().get(0).getdate_dd_MM_yyyy());
            tv_region.setText(wResponse.getDatas().get(0).getregion().name);

            int confirmed=0;
            int confirmed_diff=0;

            int deaths=0;
            int deaths_diff=0;

            int recovered=0;
            int recovered_diff=0;

            int active=0;
            int active_diff=0;

            double fatality_rate=0;

            for(Data_Reports var : wResponse.getDatas())
            {
                confirmed += var.getconfirmed();
                confirmed_diff += var.getconfirmed_diff();

                deaths += var.getdeaths();
                deaths_diff += var.getdeaths_diff();

                recovered += var.getrecovered();
                recovered_diff += var.getrecovered_diff();

                active += var.getactive();
                active_diff += var.getactive_diff();

                fatality_rate+=var.getfatality_rate();
            }

            tv_confirmed_cases.setText(AddDotToInteger(confirmed));
            tv_confirmed_diff.setText(AddDotToInteger(confirmed_diff));

            tv_deaths.setText(AddDotToInteger(deaths));
            tv_deaths_diff.setText(AddDotToInteger(deaths_diff));

            tv_recovered.setText(AddDotToInteger(recovered));
            tv_recovered_diff.setText(AddDotToInteger(recovered_diff));

            tv_active.setText(AddDotToInteger(active));
            tv_active_diff.setText(AddDotToInteger(active_diff));

            DecimalFormat df = new DecimalFormat("#.#####");
            tv_fatality_rate.setText(df.format(fatality_rate/wResponse.getDatas().size()));
        }

    }

    @Override
    public void newReportAvailable(Total_Response wResponse) {}

    @Override
    public void onClick(View view) {
        ((MainActivity) getActivity()).Update_Province_Report(iso, province, this);
    }
}