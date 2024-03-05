package com.dsy.dsu.CommitDocuments.View.ui.fragmentcommitdocuments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsy.dsu.R;


public class FragmentCommitDocuments extends Fragment {

    private ViewModelCommitDocuments mViewModel;

    public static FragmentCommitDocuments newInstance() {
        return new FragmentCommitDocuments();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ViewModelCommitDocuments.class);
        // TODO: Use the ViewModel
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_commit_documents, container, false);
    }

}