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
    GoogleSignInAccount current_account2;
    ListView exercise_list_view_object;

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

        exercisetextview = view.findViewById(R.id.textView_exercise);

        current_account2 = GoogleSignIn.getLastSignedInAccount(getActivity());

        try {
            if(current_account2!=null)
            {
                exercisetextview.setText("Hello "+current_account2.getDisplayName()+" here's your workout plan for this week");
            }
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(),"can't get the google account",Toast.LENGTH_LONG).show();
        }

        exercise_list_view_object = view.findViewById(R.id.exercise_list_view);

        ArrayList<String> exercise_array_list = new ArrayList<>();

        exercise_array_list.add("Day 1: Legs/Abs");
        exercise_array_list.add("Day 2: Chest");
        exercise_array_list.add("Day 3: Back/Abs*");
        exercise_array_list.add("Day 4: Rest");
        exercise_array_list.add("Day 5: Shoulder/Abs*");
        exercise_array_list.add("Day 6: Arms");
        exercise_array_list.add("Day 7: Rest");

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,exercise_array_list);

        exercise_list_view_object.setAdapter(stringArrayAdapter);

        return view;
    }
}