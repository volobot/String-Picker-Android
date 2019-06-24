package com.volobot.stringchooser;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;


import java.util.List;

public class StringChooser extends RecyclerView {


    private List<String> strings;

    public StringChooser(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }



}
