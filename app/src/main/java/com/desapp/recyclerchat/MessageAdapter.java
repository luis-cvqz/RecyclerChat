package com.desapp.recyclerchat;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.desapp.recyclerchat.databinding.MessageItemBinding;

public class MessageAdapter extends ListAdapter<Message, MessageAdapter.MessageViewHolder> {

    public static final DiffUtil.ItemCallback<Message> DIFF_CALLBACK = new DiffUtil.ItemCallback<Message>() {
        @Override
        public boolean areItemsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return oldItem.equals(newItem);
        }
    };

    protected MessageAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MessageItemBinding binding = MessageItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new MessageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = getItem(position);
        holder.bind(message);
    }

    class MessageViewHolder extends RecyclerView.ViewHolder {
        private MessageItemBinding binding;

        public MessageViewHolder(@NonNull MessageItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("ResourceAsColor")
        public void bind (Message message) {
            binding.txtMessage.setText(message.getMessage());
            if (message.getSender().equals(Message.DEVICE)) {
                binding.txtMessage.setBackgroundResource(R.drawable.device_chat_bubble);
                binding.txtMessage.setTextColor(R.color.white);
                binding.txtMessage.setGravity(1);
            }
            binding.executePendingBindings();
        }
    }
}
