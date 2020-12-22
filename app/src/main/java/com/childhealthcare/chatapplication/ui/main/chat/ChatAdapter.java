package com.childhealthcare.chatapplication.ui.main.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.childhealthcare.chatapplication.R;
import com.childhealthcare.chatapplication.model.ChatMessage;

public class ChatAdapter extends PagedListAdapter<ChatMessage, ChatAdapter.ChatMessageViewHolder> {

    private int senderId;

    public ChatAdapter(int senderId) {
        super(diffCallback);
        this.senderId = senderId;
    }

    @NonNull
    @Override
    public ChatMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);

        return new ChatMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatMessageViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).getUser().getId() == senderId) {
            return R.layout.item_right_message;
        }
        return R.layout.item_left_message;
    }

    private static final DiffUtil.ItemCallback<ChatMessage> diffCallback = new DiffUtil.ItemCallback<ChatMessage>() {
        @Override
        public boolean areItemsTheSame(@NonNull ChatMessage oldItem, @NonNull ChatMessage newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull ChatMessage oldItem, @NonNull ChatMessage newItem) {
            return oldItem.getText().equals(newItem.getText());
        }
    };

    public static class ChatMessageViewHolder extends RecyclerView.ViewHolder {

        private View itemView;

        public ChatMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

        void bind(ChatMessage item) {
            TextView msgBody = itemView.findViewById(R.id.msg_body);
            TextView msgTime = itemView.findViewById(R.id.time);
            msgBody.setText(item.getText());

//            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
//            Date date = null;//You will get date object relative to server/client timezone wherever it is parsed
//            try {
//                date = dateFormat.parse(item.getTime());
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //If you need time just put specific format for time like 'HH:mm:ss'
//            String dateStr = formatter.format(date);

            msgTime.setText(item.getText());
        }
    }

}
