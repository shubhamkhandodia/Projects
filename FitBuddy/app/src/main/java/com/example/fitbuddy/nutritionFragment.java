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
 * Use the {@link nutritionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class nutritionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView nutritiontextview;
    FirebaseUser currentuser;
    ListView nutrition_list_view_object;
    Button showdatabutton;
    private DocumentReference nutrition_doc_ref = FirebaseFirestore.getInstance().document("Sample_nutrition_data/my_nutrition");

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

        showdatabutton = view.findViewById(R.id.show_nutrition_plan);
        nutritiontextview = view.findViewById(R.id.textView_nutrition);
        nutrition_list_view_object = view.findViewById(R.id.nutrition_list_view);

        currentuser = FirebaseAuth.getInstance().getCurrentUser();


        showdatabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                showdatabutton.setVisibility(View.INVISIBLE);

                try
                {
                    if(currentuser!=null)
                    {
                        nutritiontextview.setText("Hello "+currentuser.getDisplayName()+" here's your calorie fulfillment plan for each day");
                        nutritiontextview.setVisibility(View.VISIBLE);
                    }
                }
                catch (Exception e)
                {
                    Snackbar.make(view, "Can't get the firebase account", Snackbar.LENGTH_LONG).show();
                }

                HashMap<String, ArrayList> user = new HashMap<>();

                ArrayList<String> nutritionlist = new ArrayList<>();

                nutritionlist.add("Protein: 21.4 grams");
                nutritionlist.add("Fat: 18 grams");
                nutritionlist.add("Net Carbohydrates: 52 grams");
                nutritionlist.add("Fiber: 6.6 grams");
                nutritionlist.add("Vitamin A: 700–900 mcg");
                nutritionlist.add("Vitamin D: 600–800 IU");
                nutritionlist.add("Vitamin E: 15 mg");
                nutritionlist.add("Vitamin K: 90–120 mcg");

                user.put("Nutrition List of "+currentuser.getDisplayName(),nutritionlist);

                nutrition_doc_ref.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG","Nutrition Data has been added");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG","Nutrition data can't be received ",e);
                    }
                });


                ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,nutritionlist);

                nutrition_list_view_object.setAdapter(stringArrayAdapter);

                nutrition_list_view_object.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }
}