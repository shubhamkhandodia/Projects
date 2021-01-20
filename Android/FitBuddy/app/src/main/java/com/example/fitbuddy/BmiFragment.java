package com.example.fitbuddy;

import android.content.Context;
import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BmiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BmiFragment extends Fragment{

    Double weightdouble,heightdouble,bmi;

    FirebaseUser currentuser;

    TextView bmiuserview;
    EditText height ;
    EditText weight ;
    TextView bmiresult ;
    Button button ;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BmiFragment() {
        // Required empty public constructor
    }

    final DecimalFormat df2 = new DecimalFormat(".##");

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BmiFragment.
     */
    // TODO: Rename and change types and number of parameters


    public static BmiFragment newInstance(String param1, String param2)
    {
        BmiFragment fragment = new BmiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_bmi, container, false);

        height = (EditText) view.findViewById(R.id.height);
        weight = (EditText) view.findViewById(R.id.weight);
        bmiresult = (TextView) view.findViewById(R.id.bmiresult);
        button = (Button) view.findViewById(R.id.bmibutton);
        bmiuserview = (TextView) view.findViewById(R.id.bmiusernameview);


        currentuser = FirebaseAuth.getInstance().getCurrentUser();


        try
        {
            if(currentuser!=null)
            {
                bmiuserview.setText("hello "+currentuser.getDisplayName()+" enter your body specs below");
            }
        }
        catch (Exception e)
        {
            Snackbar.make(view, "Can't get the firebase account", Snackbar.LENGTH_LONG).show();
        }

        weight.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    weightdouble = Double.valueOf(weight.getText().toString());
                    handled = true;
                }
                else if(view.getId() == R.id.bmi_layout)
                {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                return handled;
            }
        });

        height.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    heightdouble = Double.valueOf(height.getText().toString());
                    handled = true;
                }
                else if(view.getId() == R.id.bmi_layout)
                {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                return handled;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                // Do something in response to button click
                bmi = weightdouble/Math.pow(heightdouble,2);

                bmiresult.setText("Your BMI value is "+df2.format(bmi));
                bmiresult.setVisibility(View.VISIBLE);
            }
        });


        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


}