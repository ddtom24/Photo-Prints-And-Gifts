package com.example.photoprintsandgifts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.photoprintsandgifts.adapters.FrameAdapter;
import com.example.photoprintsandgifts.adapters.StickersAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FrameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StickersFragment extends BottomSheetDialogFragment implements StickersAdapter.StickersAdapterListener {
    RecyclerView rvStickersFragment;
    Button addStickers;
    int stickersSelected = -1;

    AddStickersListener listener;
    public void setListener(AddStickersListener listener){
        this.listener = listener;
    }
    static StickersFragment instance;

    public static StickersFragment getInstance(){
        if(instance == null)
            instance = new StickersFragment();

        return instance;
    }
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StickersFragment() {
        // Required empty public constructor
    }

    /**
     * Factory method used to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FrameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StickersFragment newInstance(String param1, String param2) {
        StickersFragment fragment = new StickersFragment();
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
        View itemView = inflater.inflate(R.layout.fragment_stickers, container, false);
        rvStickersFragment = (RecyclerView) itemView.findViewById(R.id.rv_stickers_fragment);
        addStickers = (Button) itemView.findViewById(R.id.btn_stickers_fragment_add_stickers);

        rvStickersFragment.setHasFixedSize(true);
        rvStickersFragment.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        rvStickersFragment.setAdapter(new StickersAdapter(getContext(), this));

        addStickers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onAddStickers(stickersSelected);

            }
        });

        return itemView;

    }
    public void onStickersSelected(int stickers){
        stickersSelected = stickers;
    }


}