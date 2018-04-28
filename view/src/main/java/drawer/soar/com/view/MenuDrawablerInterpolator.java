package drawer.soar.com.view;

import android.view.animation.Interpolator;

public class MenuDrawablerInterpolator implements Interpolator {
        private final float mFactor;
        private final double mDoubleFactor;

        public MenuDrawablerInterpolator() {
            mFactor = 1.0f;
            mDoubleFactor = 2.0;
        }

        public MenuDrawablerInterpolator(float factor) {
            mFactor = factor;
            mDoubleFactor = 2 * mFactor;
        }

        @Override
        public float getInterpolation(float input) {
            if (mFactor == 1.0f) {
                return input * input;
            } else {
                return (float)Math.pow(input, mDoubleFactor);
            }
        }
    }

