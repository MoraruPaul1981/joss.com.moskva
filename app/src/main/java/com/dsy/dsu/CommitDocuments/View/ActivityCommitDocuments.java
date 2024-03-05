package com.dsy.dsu.CommitDocuments.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dsy.dsu.CommitDocuments.View.ui.fragmentcommitdocuments.FragmentCommitDocuments;
import com.dsy.dsu.R;

public class ActivityCommitDocuments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_documents);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, FragmentCommitDocuments.newInstance())
                    .commitNow();
        }
    }
}