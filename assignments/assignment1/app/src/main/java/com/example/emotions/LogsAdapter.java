package com.example.emotions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/*
  takes a list of LogEntry objects and tells RecyclerView how to turn each one into a visible row (emoji + timestamp).
*/
public class LogsAdapter extends RecyclerView.Adapter<LogsAdapter.LogVH> { // each row will be represented by a LogVH object
    private final List<LogEntry> items = new ArrayList<>(); // all the data to be stored
    // called by activity whenever it wants to refresh the list
    public void setItems(List<LogEntry> logs) {
        items.clear();
        items.addAll(logs);
        notifyDataSetChanged();
    }

    // take item_log.xml and turn it into a view object -> creates one row of log
    @NonNull
    @Override
    public LogVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_log, parent, false);
        return new LogVH(row);
    }

    // adds the data in that row
    @Override
    public void onBindViewHolder(@NonNull LogVH holder, int position) {
        LogEntry e = items.get(position);
        holder.txtEmotion.setText(e.getEmotionType().getEmoji() + " " + e.getEmotionType().getLabel());
        holder.txtTime.setText(DateUtils.formatTime(e.getTimestamp()));
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    // records data once and stores them
    static class LogVH extends RecyclerView.ViewHolder {
        TextView txtEmotion;
        TextView txtTime;
        LogVH(@NonNull View itemView) {
            super(itemView);
            txtEmotion = itemView.findViewById(R.id.txtRowEmotion);
            txtTime = itemView.findViewById(R.id.txtRowTime);
        }
    }
}

