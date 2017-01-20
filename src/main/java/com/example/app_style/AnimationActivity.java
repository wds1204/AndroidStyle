package com.example.app_style;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationActivity extends Activity {

    @Bind(R.id.bt_onFadeIn)
    Button btOnFadeIn;
    @Bind(R.id.bt_onFadeOut)
    Button btOnFadeOut;
    @Bind(R.id.ib_onZoomIn)
    ImageButton ibOnZoomIn;
    @Bind(R.id.ib_onZoomOut)
    ImageButton ibOnZoomOut;
    @Bind(R.id.ib_onMoveLeft2Right)
    ImageButton ibOnMoveLeft2Right;
    @Bind(R.id.ib_onMoveInFromBottom)
    ImageButton ibOnMoveInFromBottom;
    @Bind(R.id.ib_onRotate)
    ImageButton ibOnRotate;
    @Bind(R.id.ib_onMoveAndScale)
    ImageButton ibOnMoveAndScale;
    @Bind(R.id.bt_onValueAnimatorTest)
    Button btOnValueAnimatorTest;
    @Bind(R.id.bt_onObjectAnimatorTest)
    Button btOnObjectAnimatorTest;
    @Bind(R.id.bt_onAnimatorSetTest)
    Button btOnAnimatorSetTest;
    private int maxWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
        // 获取屏幕宽度
        maxWidth = getWindowManager().getDefaultDisplay().getWidth();
    }

    @OnClick({R.id.bt_onFadeIn, R.id.bt_onFadeOut, R.id.ib_onZoomIn, R.id.ib_onZoomOut, R.id.ib_onMoveLeft2Right, R.id.ib_onMoveInFromBottom, R.id.ib_onRotate, R.id.ib_onMoveAndScale,R.id.bt_onValueAnimatorTest, R.id.bt_onObjectAnimatorTest, R.id.bt_onAnimatorSetTest})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_onFadeIn:
                Toast.makeText(AnimationActivity.this, "淡入", Toast.LENGTH_SHORT).show();
                AlphaAnimation animation = (AlphaAnimation) AnimationUtils.loadAnimation(this, R.anim.fade_in);

                btOnFadeIn.startAnimation(animation);
                break;
            case R.id.bt_onFadeOut:
                AnimationSet animationSet = new AnimationSet(true);
                Animation outanimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
                outanimation.setFillAfter(true);
                animationSet.addAnimation(outanimation);
               /* //动画停在结束的位置
                animationSet.setFillAfter(true);*/
                btOnFadeOut.startAnimation(animationSet);

                break;
            case R.id.ib_onZoomIn:

                ibOnZoomIn.startAnimation(AnimationUtils.loadAnimation(this, R.anim.zoom_in));
                break;
            case R.id.ib_onZoomOut:
                ibOnZoomOut.startAnimation(AnimationUtils.loadAnimation(this, R.anim.zoom_out));
                break;
            case R.id.ib_onMoveLeft2Right:
                ibOnMoveLeft2Right.startAnimation(AnimationUtils.loadAnimation(this, R.anim.move_left_to_right));
                break;
            case R.id.ib_onMoveInFromBottom:
                ibOnMoveInFromBottom.startAnimation(AnimationUtils.loadAnimation(this, R.anim.move_in_from_bottom));
                break;
            case R.id.ib_onRotate:
                ibOnRotate.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate_one));
                break;
            case R.id.ib_onMoveAndScale:
                ibOnMoveAndScale.startAnimation(AnimationUtils.loadAnimation(this, R.anim.move_and_scale));
                break;
            case R.id.bt_onValueAnimatorTest:

                ValueAnimator valueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.value_animator);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    private IntEvaluator mEvaluator = new IntEvaluator();
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        // 当前动画值，即为当前宽度比例值
                        int currentValue = (Integer) animator.getAnimatedValue();
                        // 根据比例更改目标view的宽度
                        btOnValueAnimatorTest.getLayoutParams().width = maxWidth * currentValue / 100;
                        btOnValueAnimatorTest.requestLayout();
                    }
                });
                valueAnimator.start();
                break;
            case R.id.bt_onObjectAnimatorTest:
                ViewWrapper wrapper = new ViewWrapper(view, maxWidth);
                ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.object_animator);
                objectAnimator.setTarget(wrapper);
                objectAnimator.start();
                break;
            case R.id.bt_onAnimatorSetTest:
                ViewWrapper mwrapper = new ViewWrapper(view, maxWidth);
                AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.animator_set);
                animatorSet.setTarget(mwrapper);
                animatorSet.start();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    private static class ViewWrapper {
        private View target;
        private int maxWidth;

        public ViewWrapper(View target, int maxWidth) {
            this.target = target;
            this.maxWidth = maxWidth;
        }

        public int getWidth() {
            return target.getLayoutParams().width;
        }

        public void setWidth(int widthValue) {
            target.getLayoutParams().width = maxWidth * widthValue / 100;
            target.requestLayout();
        }

        public void setMarginTop(int margin) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) target.getLayoutParams();
            layoutParams.setMargins(0, margin, 0, 0);
            target.setLayoutParams(layoutParams);
        }
    }

}
