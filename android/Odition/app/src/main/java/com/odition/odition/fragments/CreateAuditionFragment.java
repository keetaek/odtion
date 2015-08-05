package com.odition.odition.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.odition.odition.MainActivity;
import com.odition.odition.R;
import com.odition.odition.model.Audition;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.SaveCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAuditionFragment extends Fragment {

    @Bind(R.id.edt_title)
    EditText titleEditText;
    @Bind(R.id.edt_summary)
    EditText summaryEditText;
    @Bind(R.id.edt_instructions)
    EditText instructionsEditText;
    @Bind(R.id.edt_application_deadline)
    EditText applicationDeadlineEditText;
    @Bind(R.id.edt_project_start_date)
    EditText projectStartDateEditText;
    @Bind(R.id.edt_project_end_date)
    EditText projectEndDateEditText;
    @Bind(R.id.edt_status)
    EditText statusEditText;
    @Bind(R.id.edt_location)
    EditText locationEditText;
    @Bind(R.id.edt_payment)
    EditText paymentEditText;
    @Bind(R.id.edt_logo)
    EditText logoEditText;


    public CreateAuditionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_audition, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_create_audition)
    public void onSubmit(Button button) {

        Audition audition = makeAudition();
        audition.saveEventually(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    ((MainActivity) getActivity()).itemSelection(R.id.nav_home);
                }
            }
        });

    }

    Audition makeAudition() {
        ParseACL acl = new ParseACL();
        acl.setPublicReadAccess(true);

        Audition audition = new Audition();
        audition.setACL(acl);
//        audition.setApplicationDeadline(null);
        audition.setInstructions(instructionsEditText.getText().toString());
        audition.setLocation(locationEditText.getText().toString());
        audition.setLogo(logoEditText.getText().toString());
        audition.setPayment(paymentEditText.getText().toString());
//        audition.setProjectEndDate(null);
//        audition.setProjectStartDate(null);
//        audition.setRoles(null);
        audition.setStatus(statusEditText.getText().toString());
        audition.setSummary(summaryEditText.getText().toString());
        audition.setTitle(titleEditText.getText().toString());

        return audition;
    }


}
