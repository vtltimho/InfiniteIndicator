package cn.lightsky.infiniteindicator;

import android.support.v4.view.ViewPager;
import android.view.View;


/**
 * Created by lightsky on 2016/10/29.
 */

public class IndicatorConfiguration {

    public static final int DEFAULT_INTERVAL = 2500;
    public static final double DEFAULT_SCROLL_FACTOR = 1.2;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    public enum IndicatorPosition {
        Center("Center_Bottom", R.id.default_center_indicator),
        Center_Bottom("Center_Bottom", R.id.default_center_bottom_indicator),
        Right_Bottom("Right_Bottom", R.id.default_bottom_right_indicator),
        Left_Bottom("Left_Bottom", R.id.default_bottom_left_indicator),
        Center_Top("Center_Top", R.id.default_center_top_indicator),
        Right_Top("Right_Top", R.id.default_center_top_right_indicator),
        Left_Top("Left_Top", R.id.default_center_top_left_indicator);

        private final String name;
        private final int id;

        private IndicatorPosition(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public String toString() {
            return name;
        }

        public int getResourceId() {
            return id;
        }
    }

    private int pageResId;
    private View indicator;
    private double scrollFactor;
    private boolean isAutoScroll;
    private boolean isLoop;
    private boolean isDrawIndicator;
    private boolean isStopScrollWhenTouch;
    private int direction;
    private long interval;
    private final ImageLoader imageLoader;
    private final ViewPager.OnPageChangeListener onPageChangeListener;
    private final OnPageClickListener mOnPageClickListener;
    private final IndicatorPosition presentIndicator;

    private IndicatorConfiguration(Builder builder) {
        imageLoader = builder.imageLoader;
        isLoop = builder.isLoop;
        pageResId = builder.pageResId;
        interval = builder.interval;
        direction = builder.direction;
        isDrawIndicator = builder.isDrawIndicator;
        isAutoScroll = builder.isAutoScroll;
        scrollFactor = builder.scrollFactor;
        presentIndicator = builder.indicatorPosition;
        indicator = builder.indicator;
        mOnPageClickListener = builder.onPageClickListener;
        isStopScrollWhenTouch = builder.isStopScrollWhenTouch;
        onPageChangeListener = builder.onPageChangeListener;
    }

    public ImageLoader getImageLoader() {
        if (imageLoader == null) {
            throw new RuntimeException("You should set ImageLoader first");
        }
        return imageLoader;
    }

    public int getDirection() {
        return direction;
    }

    public boolean isLoop() {
        return isLoop;
    }

    public boolean isDrawIndicator() {
        return isDrawIndicator;
    }

    public long getInterval() {
        return interval;
    }

    public boolean isStopWhenTouch() {
        return isStopScrollWhenTouch;
    }

    public boolean isAutoScroll() {
        return isAutoScroll;
    }

    public double getScrollFactor() {
        return scrollFactor;
    }

    public IndicatorPosition getPageIndicator() {
        return presentIndicator;
    }

    public ViewPager.OnPageChangeListener getOnPageChangeListener() {
        return onPageChangeListener;
    }

    public OnPageClickListener getOnPageClickListener() {
        return mOnPageClickListener;
    }

    public int getPageResId() {
        return pageResId;
    }

    public static class Builder{
        private ImageLoader imageLoader;
        private double scrollFactor = DEFAULT_SCROLL_FACTOR;
        private boolean isLoop = true;
        private boolean isAutoScroll = true;
        private boolean isStopScrollWhenTouch = true;
        private boolean isDrawIndicator = true;
        private View indicator;
        private OnPageClickListener onPageClickListener;
        private int direction = RIGHT;
        private long interval = DEFAULT_INTERVAL;
        private ViewPager.OnPageChangeListener onPageChangeListener;
        private IndicatorPosition indicatorPosition = IndicatorPosition.Center_Bottom;
        private int pageResId;

        public Builder imageLoader(ImageLoader imageLoader) {
            this.imageLoader = imageLoader;
            return this;
        }

        /**
         * set whether is loop when reaching the last or first item, default is true
         *
         * @param isLoop
         */
        public Builder isLoop(boolean isLoop) {
            this.isLoop = isLoop;
            return this;
        }

        public Builder isDrawIndicator(boolean isDrawIndicator) {
            this.isDrawIndicator = isDrawIndicator;
            return this;
        }

        /**
         * auto scroll direction, default is {@link #RIGHT} *
         */
        public Builder direction(int direction) {
            this.direction = direction;
            return this;
        }

        /**
         * auto scroll time in milliseconds, default is {@link #DEFAULT_INTERVAL} *
         */
        public Builder internal(long interval) {
            this.interval = interval;
            return this;
        }

        /**
         * whether stop auto scroll when touching, default is true *
         */
        public Builder isStopWhenTouch(boolean isStopScrollWhenTouch) {
            this.isStopScrollWhenTouch = isStopScrollWhenTouch;
            return this;
        }

        /**
         * whether auto scroll when initialize done *
         */
        public Builder isAutoScroll(boolean isAutoScroll) {
            this.isAutoScroll = isAutoScroll;
            return this;
        }

        /**
         * set the factor by which the duration of sliding animation will change
         */
        public Builder setScrollDurationFactor(double scrollFactor) {
            this.scrollFactor = scrollFactor;
            return this;
        }

        public Builder position(IndicatorPosition indicatorPosition) {
            this.indicatorPosition = indicatorPosition;
            return this;
        }

        public Builder onPageClickListener(OnPageClickListener onPageClickListener) {
            this.onPageClickListener = onPageClickListener;
            return this;
        }

        /**
         * provide custome page layout , but you should provide a imageview and
         * it's id must is slider_image , otherwise the imageview will be empty
         *
         * @param resId the layout id of your custome page
         * @return
         */
        public Builder pageResId(int resId) {
            this.pageResId = resId;
            return this;
        }

        /**
         * set the indicator for your page,default will not draw,or you can use
         * default indicator ,and you should set isDrawIndicator true
         * @param indicator
         * @return
         */
        public Builder indicator(View indicator) {
            this.indicator = indicator;
            return this;
        }

        public Builder onPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
            this.onPageChangeListener = onPageChangeListener;
            return this;
        }

        public IndicatorConfiguration build() {
            return new IndicatorConfiguration(this);
        }
    }
}
