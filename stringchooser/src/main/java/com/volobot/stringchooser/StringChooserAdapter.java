package com.volobot.stringchooser;

import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StringChooserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = StringChooserAdapter.class.getName();
    private static final int VIEW_TYPE_PADDING = 1;
    private static final int VIEW_TYPE_ITEM = 2;

    private List<String> strings;
    private int endHeight;
    private int selectedItem = -1;
    private int selectedColor;
    private int selectedSize;
    private int notSelectedColor;
    private int notSelectedSize;
    private float notSelectedOpacity;

    public StringChooserAdapter(List<String> strings,
                                int endHeight,
                                int selectedColor,
                                int selectedSize,
                                int notSelectedColor,
                                int notSelectedSize,
                                float notSelectedOpacity) {
        this.strings = strings;
        strings.add(0, "");
        strings.add("");
        this.selectedColor=selectedColor;
        this.selectedSize=selectedSize;
        this.endHeight = endHeight;
        this.notSelectedColor=notSelectedColor;
        this.notSelectedSize=notSelectedSize;
        this.notSelectedOpacity = notSelectedOpacity;

        Log.d(TAG,"const "+selectedSize);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView;

        if (VIEW_TYPE_ITEM == viewType) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_card_18, parent, false);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) convertView.getLayoutParams();
            layoutParams.height = selectedSize+60;//:TODO remove this hard coded value
            Log.d(TAG,"height"+selectedSize);
            convertView.setLayoutParams(layoutParams);
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

        Log.d(TAG,"height"+selectedSize);
        if (viewHolder instanceof StringChooserView) {
            ((StringChooserView) viewHolder).title_view.setText(strings.get(position));
            if (position == selectedItem) {
                ((StringChooserView) viewHolder).title_view.setTextSize(TypedValue.COMPLEX_UNIT_PX,selectedSize);
                ((StringChooserView) viewHolder).title_view.setTextColor(selectedColor);
                ((StringChooserView) viewHolder).title_view.setAlpha(1.0f);
            } else {
                ((StringChooserView) viewHolder).title_view.setTextSize(TypedValue.COMPLEX_UNIT_PX,notSelectedSize);
                ((StringChooserView) viewHolder).title_view.setTextColor(notSelectedColor);
                ((StringChooserView) viewHolder).title_view.setAlpha(notSelectedOpacity);
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
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    notifyDataSetChanged();
                }
            });
        }
    }
}