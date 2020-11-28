package com.example.fitbuddy;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link nutritionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class nutritionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView nutritiontextview;
    GoogleSignInAccount currentaccount1;
    ListView nutrition_list_view_object;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public nutritionFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment nutritionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static nutritionFragment newInstance(String param1, String param2) {
        nutritionFragment fragment = new nutritionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_nutrition, container, false);


        currentaccount1 = GoogleSignIn.getLastSignedInAccount(getActivity());

        try {
            if(currentaccount1!=null)
            {
                nutritiontextview.setText("Welcome to the exercise fragment "+currentaccount1.getDisplayName()+" here are your account details/n"+currentaccount1.getGivenName()+" "+currentaccount1.getEmail());
            }
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(),"can't get the google account",Toast.LENGTH_LONG).show();
        }


        nutrition_list_view_object = view.findViewById(R.id.bmiresult);
        nutritiontextview = view.findViewById(R.id.textView_nutrition);

        nutritiontextview.setText("Hello "+currentaccount1.getDisplayName()+" here's your calorie fulfillment plan for a week");

        nutrition_list_view_object = view.findViewById(R.id.nutrition_list_view);

        ArrayList<String> nutritionlist = new ArrayList<>();

        nutritionlist.add("Protein: 21.4 grams");
        nutritionlist.add("Fat: 18 grams");
        nutritionlist.add("Net Carbohydrates: 52 grams");
        nutritionlist.add("Fiber: 6.6 grams");
        nutritionlist.add("Vitamin A: 700–900 mcg");
        nutritionlist.add("Vitamin D: 600–800 IU");
        nutritionlist.add("Vitamin E: 15 mg");
        nutritionlist.add("Vitamin K: 90–120 mcg");

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,nutritionlist);

        nutrition_list_view_object.setAdapter(stringArrayAdapter);

        return view;
    }
}