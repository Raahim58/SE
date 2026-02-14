package com.example.emotions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/*
  adapter for the summary list.
  each row shows emotion + count + percent.
*/
public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.SummaryVH> {

    private final List<SummaryRow> items = new ArrayList<>();
    private final NumberFormat pct = NumberFormat.getPercentInstance(Locale.getDefault());

    public SummaryAdapter() {
        pct.setMaximumFractionDigits(0); // 41% not 41.6666%
    }

    public void setItems(List<SummaryRow> rows) {
        items.clear();
        items.addAll(rows);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SummaryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_summary, parent, false);
        return new SummaryVH(row);
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryVH holder, int position) {
        SummaryRow r = items.get(position);

        holder.txtEmotion.setText(
                r.getEmotionType().getEmoji() + " " + r.getEmotionType().getLabel()
        );

        holder.txtCount.setText(String.valueOf(r.getCount()));
        holder.txtPercent.setText(pct.format(r.getFraction()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class SummaryVH extends RecyclerView.ViewHolder {

        TextView txtEmotion;
        TextView txtCount;
        TextView txtPercent;

        SummaryVH(@NonNull View itemView) {
            super(itemView);
            txtEmotion = itemView.findViewById(R.id.txtSummaryEmotion);
            txtCount = itemView.findViewById(R.id.txtSummaryCount);
            txtPercent = itemView.findViewById(R.id.txtSummaryPercent);
        }
    }
}
