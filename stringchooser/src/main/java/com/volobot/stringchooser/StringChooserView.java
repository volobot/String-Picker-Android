package com.volobot.stringchooser;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class StringChooserView extends RecyclerView.ViewHolder {

    TextView title_view;

    StringChooserView(@NonNull View itemView) {
        super(itemView);
        title_view = itemView.findViewById(R.id.title);
        if (title_view != null) {

        }
    }
}
