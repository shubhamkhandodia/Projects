package com.example.fitbuddy;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

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
    ListView exercise_list_view_object;
    FirebaseUser currentuser;
    Button showdatabutton;
    private DocumentReference exercise_doc_ref = FirebaseFirestore.getInstance().document("Sample_exercise_data/my_exercise");

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

        showdatabutton = view.findViewById(R.id.show_exercise_plan);

        exercisetextview = view.findViewById(R.id.textView_exercise);

        currentuser = FirebaseAuth.getInstance().getCurrentUser();

        try {
            if(currentuser!=null)
            {
                exercisetextview.setText("Hello "+currentuser.getDisplayName()+" here's your workout plan for this week");
            }
        }
        catch (Exception e)
        {
            Snackbar.make(view, "Can't get the firebase account", Snackbar.LENGTH_LONG).show();
        }

        exercise_list_view_object = view.findViewById(R.id.exercise_list_view);

        showdatabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                showdatabutton.setVisibility(View.INVISIBLE);

                try
                {
                    if(currentuser!=null)
                    {
                        exercisetextview.setText("Hello "+currentuser.getDisplayName()+" here's your full workout plan for a week");
                        exercisetextview.setVisibility(View.VISIBLE);
                    }
                }
                catch (Exception e)
                {
                    Snackbar.make(view, "Can't get the firebase account", Snackbar.LENGTH_LONG).show();
                }

                HashMap<String, ArrayList> user = new HashMap<>();

                ArrayList<String> exercise_array_list = new ArrayList<>();

                exercise_array_list.add("Day 1: Legs/Abs");
                exercise_array_list.add("Day 2: Chest");
                exercise_array_list.add("Day 3: Back/Abs*");
                exercise_array_list.add("Day 4: Rest");
                exercise_array_list.add("Day 5: Shoulder/Abs*");
                exercise_array_list.add("Day 6: Arms");
                exercise_array_list.add("Day 7: Rest");

                user.put("Exercise List of "+currentuser.getDisplayName(),exercise_array_list);

                exercise_doc_ref.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG","Exercise Data has been added");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG","Exercise data can't be received ",e);
                    }
                });


                ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,exercise_array_list);

                exercise_list_view_object.setAdapter(stringArrayAdapter);

                exercise_list_view_object.setVisibility(View.VISIBLE);
            }
        });



        return view;
    }
}