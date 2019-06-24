package com.volobot.stringchooser;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class StringChooserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = StringChooserAdapter.class.getName();
    private static final int VIEW_TYPE_PADDING = 1;
    private static final int VIEW_TYPE_ITEM = 2;

    private List<String> strings;
    private int endHeight;
    private int selectedItem = -1;

    public StringChooserAdapter(List<String> strings, int endHeight) {
        this.strings = strings;
        //Adding Two empty string at both end
        strings.add(0, "");
        strings.add("");
        this.endHeight = endHeight;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView;
        if (VIEW_TYPE_ITEM == viewType) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_card_18, parent, false);
        } else {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_card_18, parent, false);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) convertView.getLayoutParams();
            layoutParams.height = endHeight;
            convertView.setLayoutParams(layoutParams);
        }
        return new StringChooserView(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Log.d(TAG, "position " + position);
        if (viewHolder instanceof StringChooserView) {
            ((StringChooserView) viewHolder).title_view.setText(strings.get(position));
            if (position == selectedItem) {
                Log.d(TAG, "match " + position);
                ((StringChooserView) viewHolder).title_view.setTextSize(30);
            } else {
                ((StringChooserView) viewHolder).title_view.setTextSize(20);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0 || position == (getItemCount() - 1)) return VIEW_TYPE_PADDING;
        return VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        if (strings == null) return 0;
        return strings.size();
    }

    public void setSelectedItem(int selectedItem) {
        if (this.selectedItem != selectedItem) {
            this.selectedItem = selectedItem;
            notifyDataSetChanged();
        }
    }
}