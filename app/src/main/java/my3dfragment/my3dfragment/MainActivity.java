package my3dfragment.my3dfragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private FrameLayout framelayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);
        framelayout = (FrameLayout) findViewById(R.id.fragment_content);
        ReplaceFragmentMethod();


    }

    private void ReplaceFragmentMethod(){

        getFragmentManager().beginTransaction().replace(R.id.fragment_content, new Fragment_First()).commit();


    }


    public void applyRotation(final boolean zheng, final Fragment fragment,
                              final float start, final float end) {
        // Find the center of the container
        final float centerX = framelayout.getWidth() / 2.0f;
        final float centerY = framelayout.getHeight() / 2.0f;

        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        final Util_Rotate3DAnimation rotation = new Util_Rotate3DAnimation(
                start, end, centerX, centerY, 310.0f, true);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        Log.e("1","执行到了这里");
        rotation.setAnimationListener(new DisplayNextView(zheng, fragment));// 添加监听执行现实内容的切换
        framelayout.startAnimation(rotation);// 执行上半场翻转动画
    }

    private final class DisplayNextView implements Animation.AnimationListener {
        private final boolean mPosition;
        private final Fragment mfragment;

        private DisplayNextView(boolean zheng, Fragment fragment) {
            mPosition = zheng;
            mfragment = fragment;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            Log.e("1","结束");
            framelayout.post(new SwapViews(mPosition, mfragment));// 添加新的View
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }
    private final class SwapViews implements Runnable {
        private final boolean mPosition;
        private final Fragment mfragment;

        public SwapViews(boolean position, Fragment fragment) {
            mPosition = position;
            mfragment = fragment;
        }

        public void run() {
            final float centerX = framelayout.getWidth() / 2.0f;
            final float centerY = framelayout.getHeight() / 2.0f;
            Util_Rotate3DAnimation rotation;
            FragmentTransaction tration =getFragmentManager().beginTransaction();
            tration.replace(R.id.fragment_content,mfragment);
           // FragmentTransaction tration = getSupportFragmentManager()
           //         .beginTransaction();
          //  tration.replace(R.id.fragment_content, mfragment);
            if (mPosition) {
                rotation = new Util_Rotate3DAnimation(-90, 0, centerX, centerY,
                        310.0f, false);
                Log.e("1","没问题");
            } else {
                rotation = new Util_Rotate3DAnimation(90, 0, centerX, centerY,
                        310.0f, false);
            }
            tration.commit();
            Log.e("1", "提交");
            rotation.setDuration(500);
            rotation.setFillAfter(true);
            rotation.setInterpolator(new DecelerateInterpolator());
            framelayout.startAnimation(rotation);
        }
    }

}
