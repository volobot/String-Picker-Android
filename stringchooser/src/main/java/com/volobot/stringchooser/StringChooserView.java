package com.volobot.stringchooser;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class StringChooserView extends RecyclerView.ViewHolder {

    TextView title_view;

    StringChooserView(@NonNull View itemView) {
        super(itemView);
        title_view = itemView.findViewById(R.id.title);
        if (title_view != null) {

        }
    }
}
