package com.example.fitbuddy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
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
 * Use the {@link exerciseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class exerciseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView exercisetextview;
    //GoogleSignInAccount current_account2;
    ListView exercise_list;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public exerciseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment exerciseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static exerciseFragment newInstance(String param1, String param2) {
        exerciseFragment fragment = new exerciseFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_exercise, container, false);

        exercisetextview = view.findViewById(R.id.bmiresult);

        exercise_list = view.findViewById(R.id.exercise_list_view);

        ArrayList<String> exerciselist = new ArrayList<>();

        exerciselist.add("Day 1: Legs/Abs");
        exerciselist.add("Day 2: Chest");
        exerciselist.add("Day 3: Back/Abs*");
        exerciselist.add("Day 4: Rest");
        exerciselist.add("Day 5: Shoulder/Abs*");
        exerciselist.add("Day 6: Arms");
        exerciselist.add("Day 7: Rest");

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,exerciselist);

        exercise_list.setAdapter(stringArrayAdapter);

//        current_account2 = GoogleSignIn.getLastSignedInAccount(getActivity());
//
//        try {
//            if(current_account2!=null)
//            {
//                exercisetextview.setText("Welcome to the exercise fragment "+current_account2.getDisplayName()+" here are your account details/n"+current_account2.getGivenName()+" "+current_account2.getEmail());
//            }
//        }
//        catch (Exception e)
//        {
//            Toast.makeText(getContext(),"can't get the google account",Toast.LENGTH_LONG).show();
//        }
//
//

        return view;
    }
}