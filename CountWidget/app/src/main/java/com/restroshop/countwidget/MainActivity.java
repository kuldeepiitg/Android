package com.restroshop.countwidget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.restroshop.components.CountWidget;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.btn);
        final CountWidget counter1 = (CountWidget) findViewById(R.id.counter1);
        final CountWidget counter2 = (CountWidget) findViewById(R.id.counter2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "first counter: " + counter1.getValue() + ", second counter: " + counter2.getValue();
                Log.i("TAG", message);
            }
        });
    }
}
