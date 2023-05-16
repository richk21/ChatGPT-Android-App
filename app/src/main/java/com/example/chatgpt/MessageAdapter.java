package com.example.chatgpt;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder>{

    List<Message> messageList;
    public MessageAdapter(List<Message> messageList ) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chatView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, null);
        MyViewHolder myViewHolder = new MyViewHolder(chatView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Message message = messageList.get(position);
        if(message.getSentBy().equals(Message.SENT_BY_ME)){
            //message by the user, hide the linear layout meant for the bot part and only show the user's LinearLayout
            holder.leftChatView.setVisibility(View.GONE);
            holder.rightChatView.setVisibility(View.VISIBLE);

            holder.leftImageView.setVisibility(View.GONE);

            holder.rightTextView.setText(message.getMsg());
        }else{
            //message by the bot, hide user's linear layout and show bot's
            holder.leftChatView.setVisibility(View.VISIBLE);
            holder.rightChatView.setVisibility(View.GONE);

            holder.leftImageView.setVisibility(View.VISIBLE);

            holder.leftTextView.setText(message.getMsg());
        }

        holder.leftTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.leftTextView.setBackgroundColor(Color.rgb(158,196,186));
                holder.copybutton.setVisibility(View.VISIBLE);
            }
        });

        holder.copybutton.setOnClickListener(view -> {
            String text = holder.leftTextView.getText().toString();

            ClipboardManager clipboard = (ClipboardManager) view.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText(null, text);
            clipboard.setPrimaryClip(clip);

            holder.leftTextView.setBackgroundColor(Color.rgb(56, 87, 78 ));
            holder.copybutton.setVisibility(View.GONE);

        });
    }



    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        LinearLayout leftChatView, rightChatView;
        TextView leftTextView, rightTextView;
        ImageView leftImageView;
        FloatingActionButton copybutton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            leftChatView  = itemView.findViewById(R.id.left_chat_view);
            rightChatView = itemView.findViewById(R.id.right_chat_view);
            leftTextView = itemView.findViewById(R.id.left_text);
            rightTextView = itemView.findViewById(R.id.right_text);
            leftImageView = itemView.findViewById(R.id.botpic);
            copybutton = itemView.findViewById(R.id.copybutton);
            copybutton.setVisibility(View.GONE);
        }
    }
}
