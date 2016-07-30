package com.restroshop.components;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Count widget is a counter having two button with - and + signs.
 * Counter value increases with click on + button and decreases with -
 * <p/>
 * <p/>
 * Created by Kuldeep Yadav on 29/07/16.
 */
public class CountWidget extends LinearLayout {

    /**
     * Maximum/minimum allowed value of counter.
     * Counter can't increase/decrease more than the Max/Min
     * value even if plus/minus button is pressed.
     *
     * TODO: At max/min value, Plus/Minus Button should be disabled
     */
    private int max;
    private int min;

    /**
     * Count view holding the count
     */
    private EditText countView;

    public CountWidget(Context context) {
        super(context);
        init(context, null);
    }

    public CountWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CountWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CountWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    /**
     * Initialize count widget.
     */
    private void init(final Context context, AttributeSet attrs) {

        if(attrs != null) {
            TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs,R.styleable.CountWidget,0, 0);
            max = attributes.getInt(R.styleable.CountWidget_max, Integer.MAX_VALUE);
            min = attributes.getInt(R.styleable.CountWidget_min, 0);
        }

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.count_widget, this, true);

        LinearLayout container = (LinearLayout) getChildAt(0);

        ImageView minusButton = (ImageView) container.getChildAt(0);
        countView = (EditText) container.getChildAt(1);
        ImageView plusButton = (ImageView) container.getChildAt(2);

        countView.setText(Integer.toString(min));
        countView.setCursorVisible(false);

        minusButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeypad(context);
                String countText = countView.getText().toString();
                int count = min;
                if (!countText.isEmpty()) {
                    try {
                        count = Integer.parseInt(countText);
                    } catch (NumberFormatException e) {
                        count = min;
                    }
                }
                if (count > min) count--;
                countView.setText(Integer.toString(count));
            }
        });

        plusButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeypad(context);
                String countText = countView.getText().toString();
                int count = max;
                if (!countText.isEmpty()) {
                    try {
                        count = Integer.parseInt(countText);
                    } catch (NumberFormatException e) {
                        count = min;
                    }
                }
                if (count < max) count++;
                countView.setText(Integer.toString(count));
            }
        });
    }

    /**
     * Hide keypad.
     *
     * @param context activity context
     */
    private void hideKeypad(Context context) {
        final InputMethodManager imm =
                (InputMethodManager) context
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(countView.getWindowToken(), 0);
    }

    /**
     * Set max allowed value of counter.
     *
     * @param max maximum value of counter
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Get maximum allowed value of counter.
     *
     * @return maximum allowed value of counter.
     */
    public int getMax(){
        return this.max;
    }

    /**
     * Get value of counter.
     *
     * @return count
     */
    public int getValue() {
        return Integer.parseInt(countView.getText().toString());
    }

    /**
     * Get minimum allowed value of counter.
     *
     * @return minumm allowed value of counter.
     */
    public int getMin() {
        return min;
    }

    /**
     * Set minimum allowed value of counter.
     *
     * @param min mimimum allowed value of counter.
     */
    public void setMin(int min) {
        this.min = min;
    }
}
