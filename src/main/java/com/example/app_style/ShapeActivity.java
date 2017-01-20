package com.example.app_style;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShapeActivity extends Activity {

    @Bind(R.id.toRectangle)
    Button toRectangle;
    @Bind(R.id.toOval)
    Button toOval;
    @Bind(R.id.toLine)
    Button toLine;
    @Bind(R.id.toRing)
    Button toRing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.toRectangle, R.id.toOval, R.id.toLine, R.id.toRing})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toRectangle:
                startActivity(new Intent(this, RectangleActivity.class));
                break;
            case R.id.toOval:
                startActivity(new Intent(this, OvalActivity.class));
                break;
            case R.id.toLine:
                startActivity(new Intent(this, LineActivity.class));
                break;
            case R.id.toRing:
                startActivity(new Intent(this, RingActivity.class));
                break;
        }
    }
}
