package com.restroshop.countwidget;

import android.annotation.TargetApi;
import android.content.Context;
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
     * Count view holding the count
     */
    private EditText countView;

    public CountWidget(Context context) {
        super(context);
        init(context, 0);
    }

    public CountWidget(Context context, int count) {
        super(context);
        init(context, count);
    }

    public CountWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, 0);
    }

    public CountWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CountWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, 0);
    }

    /**
     * Initialize count widget.
     */
    private void init(final Context context, final int count) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.count_widget, this, true);

        LinearLayout container = (LinearLayout) getChildAt(0);

        ImageView minusButton = (ImageView) container.getChildAt(0);
        countView = (EditText) container.getChildAt(1);
        ImageView plusButton = (ImageView) container.getChildAt(2);

        countView.setText(Integer.toString(count));
        countView.setCursorVisible(false);

        minusButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeypad(context);
                String countText = countView.getText().toString();
                int count = 0;
                if (!countText.isEmpty()) {
                    try {
                        count = Integer.parseInt(countText);
                    } catch (NumberFormatException e) {
                        count = 0;
                    }
                }
                if (count > 0) count--;
                countView.setText(Integer.toString(count));
            }
        });

        plusButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeypad(context);
                String countText = countView.getText().toString();
                int count = 0;
                if (!countText.isEmpty()) {
                    try {
                        count = Integer.parseInt(countText);
                    } catch (NumberFormatException e) {
                        count = 0;
                    }
                }
                count++;
                countView.setText(Integer.toString(count));
            }
        });
    }

    /**
     * Hide keypad.
     *
     * @param context
     */
    private void hideKeypad(Context context) {
        final InputMethodManager imm =
                (InputMethodManager) context
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(countView.getWindowToken(), 0);
    }

    /**
     * Get value of counter.
     *
     * @return count
     */
    public int getValue() {
        return Integer.parseInt(countView.getText().toString());
    }
}
