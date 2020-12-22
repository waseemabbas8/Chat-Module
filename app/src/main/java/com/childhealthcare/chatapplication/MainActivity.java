package com.childhealthcare.chatapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;

import com.childhealthcare.chatapplication.model.ChatMessage;
import com.childhealthcare.chatapplication.ui.main.chat.ChatAdapter;
import com.childhealthcare.chatapplication.ui.main.chat.ChatViewModel;

public class MainActivity extends AppCompatActivity {

    private ChatViewModel mViewModel;
    private ChatAdapter mAdapter;

    private RecyclerView mChatRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChatRecyclerView = findViewById(R.id.rv_messages);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel = new ViewModelProvider(this).get(ChatViewModel.class);

        if (mAdapter == null) {
            mAdapter = new ChatAdapter(176);
            mChatRecyclerView.setAdapter(mAdapter);
        }

        mViewModel.messages.observe(this, chatMessages -> {
            mAdapter.submitList(chatMessages);

            int first = ((LinearLayoutManager) mChatRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
            if (first < 2) {
                new Handler().postDelayed(() -> {
                    mChatRecyclerView.smoothScrollToPosition(0);
                }, 500);
            }
        });

    }
}