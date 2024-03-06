package com.dsy.dsu.CommitDocuments.View.Window;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.dsy.dsu.CommitDocuments.View.ComponentsUI.GetComponentsUI;
import com.dsy.dsu.CommitDocuments.View.ViewModel.ViewModelCommitDocuments;
import com.dsy.dsu.CommitingPrices.View.Window.MainActivityCommitingPrices;
import com.dsy.dsu.R;


public class FragmentCommitDocuments extends Fragment {

    ViewModelCommitDocuments viewModelCommitDocuments;
    public static FragmentCommitDocuments newInstance() {

        return new FragmentCommitDocuments();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: Use the ViewModel
        viewModelCommitDocuments =((ActivityCommitDocuments)getActivity()).viewModelCommitDocuments;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_commit_documents, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GetComponentsUI getComponentsUI=new GetComponentsUI(view,getActivity(),getContext(),viewModelCommitDocuments);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}