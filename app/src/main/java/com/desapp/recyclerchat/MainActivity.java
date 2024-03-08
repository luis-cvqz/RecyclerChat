package com.desapp.recyclerchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.desapp.recyclerchat.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MessageAdapter adapter;
    ArrayList<Message> messagesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recycler.setLayoutManager(new LinearLayoutManager(this));

        messagesList = new ArrayList<>();
        messagesList.add(new Message(
                Message.DEVICE, "Write a message to start a conversation"));

        adapter = new MessageAdapter();
        binding.recycler.setAdapter(adapter);
        adapter.submitList(messagesList);
    }

    public void sendMessage(View view) {
        String sentMessage = binding.editMessage.getText().toString();

        if (!sentMessage.isEmpty()) {
            messagesList.add(new Message(Message.USER, sentMessage));
            adapter.notifyItemInserted(messagesList.size() - 1);
            binding.editMessage.getText().clear();
            binding.recycler.smoothScrollToPosition(messagesList.size() - 1);

            new Handler().postDelayed(this::deviceReply, 5000);
        } else {
            Toast.makeText(this, "Empty message", Toast.LENGTH_SHORT).show();
        }
    }

    void deviceReply() {
        String replyMessage = "Message received";

        messagesList.add(new Message(Message.DEVICE, replyMessage));
        adapter.notifyItemInserted(messagesList.size() - 1);
        binding.recycler.smoothScrollToPosition(messagesList.size() - 1);
    }
}