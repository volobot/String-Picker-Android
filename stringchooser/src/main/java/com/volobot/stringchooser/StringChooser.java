package com.volobot.stringchooser;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;



import java.util.List;

public class StringChooser extends RecyclerView {
    private static final String TAG = StringChooser.class.getName();
    private List<String> strings;
    private StringChooserCallback stringChooserCallback;
    @ColorInt
    private int selectedColor;
    private int selectedSize;
    @ColorInt
    private int notSelectedColor;
    private int notSelectedSize ;
    private int selectedValue;
    float firstItemHeightPicker;
    int allPixelsPicker;
    int finalHeightPicker;
    float itemHeightDate;

    float paddingPicker;
    private StringChooserAdapter stringChooserAdapter;

    public StringChooser(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StringChooser);
        selectedColor=typedArray.getColor(R.styleable.StringChooser_selectedColor,getResources().getColor(android.R.color.holo_blue_dark));
        selectedSize= typedArray.getDimensionPixelSize(
                R.styleable.StringChooser_selectedSize,18);
        notSelectedColor=typedArray.getColor(R.styleable.StringChooser_notSelectedColor,
                getResources().getColor(android.R.color.darker_gray));
        notSelectedSize= typedArray.getDimensionPixelSize(R.styleable.StringChooser_notSelectedSize,
                12);
        typedArray.recycle();
        Log.d(TAG,"selected Size"+selectedSize);
    }

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
        initAdapter();
    }

    private void initAdapter() {

        ViewTreeObserver vtoDate = getViewTreeObserver();
        vtoDate.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                getViewTreeObserver().removeOnPreDrawListener(this);
                finalHeightPicker = getMeasuredHeight();
                itemHeightDate = selectedSize+60;//TODO: Remove this hard coded value
                paddingPicker = (finalHeightPicker - itemHeightDate) / 2;
                firstItemHeightPicker = paddingPicker;
                allPixelsPicker = 0;

                final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                setLayoutManager(linearLayoutManager);
                addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                    }

                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        allPixelsPicker += dy;
                        setDateValue();
                    }
                });


                stringChooserAdapter = new StringChooserAdapter(strings,
                        (int) firstItemHeightPicker,
                        selectedColor,
                        selectedSize,
                        notSelectedColor,
                        notSelectedSize
                );
                setAdapter(stringChooserAdapter);
                return true;
            }
        });
    }


    private void setDateValue() {
        int expectedPositionDateColor = Math.round((allPixelsPicker + paddingPicker - firstItemHeightPicker) / itemHeightDate);
        selectedValue=expectedPositionDateColor+1;
        if (stringChooserAdapter != null) {
            stringChooserAdapter.setSelectedItem(selectedValue);
            if(stringChooserCallback!=null){
                stringChooserCallback.onStringPickerValueChange(strings.get(selectedValue),selectedValue);
            }
        }
    }

    public int getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
    }

    public int getSelectedSize() {
        return selectedSize;
    }

    public void setSelectedSize(int selectedSize) {
        this.selectedSize = selectedSize;
    }

    public StringChooserCallback getStringChooserCallback() {
        return stringChooserCallback;
    }

    public void setStringChooserCallback(StringChooserCallback stringChooserCallback) {
        this.stringChooserCallback = stringChooserCallback;
    }

    public int getSelectedValue() {
        return selectedValue;
    }

    public String getSelectedString() {
        if (strings != null && strings.size() > selectedValue) {
            return strings.get(selectedValue);
        }
        return null;
    }

    public interface StringChooserCallback {
        void onStringPickerValueChange(String s, int position);
    }
}
