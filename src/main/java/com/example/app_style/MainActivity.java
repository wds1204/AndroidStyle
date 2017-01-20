package com.example.app_style;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @Bind(R.id.toShape)
    Button toShape;
    @Bind(R.id.toSelector)
    Button toSelector;
    @Bind(R.id.toLayerList)
    Button toLayerList;
    @Bind(R.id.toAnimation)
    Button toAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.toShape, R.id.toSelector, R.id.toLayerList, R.id.toAnimation})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toShape:
                startActivity(new Intent(this, ShapeActivity.class));
                break;
            case R.id.toSelector:
                startActivity(new Intent(this, SelectorActivity.class));
                break;
            case R.id.toLayerList:
                startActivity(new Intent(this, LayerListActivity.class));
                break;
            case R.id.toAnimation:
                startActivity(new Intent(this, AnimationActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
