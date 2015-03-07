package m.k.android.sample.view.myscrollview;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Scroller;

public class BothScrollView extends FrameLayout {
	static final int ANIMATED_SCROLL_GAP = 250;
	static final float MAX_SCROLL_FACTOR = 0.5f;
	private long mLastScroll;
	private Scroller mScroller;
	private float mLastMotionX;
	private float mLastMotionY;
	private boolean mIsBeingDragged = false;
	private VelocityTracker mVelocityTracker;
	private boolean mFillViewport;
	private boolean mSmoothScrollingEnabled = true;
	private int mTouchSlop;
	private int mMinimumVelocity;
	private int mMaximumVelocity;
	private int mActivePointerId = INVALID_POINTER;
	private static final int INVALID_POINTER = -1;

	public BothScrollView(Context context) {
		this(context, null);
	}


	public BothScrollView(Context context, AttributeSet attrs) {
		this(context, attrs, R.attr.bothScrollViewStyle); 
	}


	public BothScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initScrollView();
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ScrollView, defStyle, 0);
		setFillViewport(a.getBoolean(R.styleable.ScrollView_fillViewport, false));
		a.recycle();
	}


	@Override protected float getTopFadingEdgeStrength() {
		if (getChildCount() == 0) {
			return 0.0f;
		}
		final int length = getVerticalFadingEdgeLength();
		if (getScrollY() < length) {
			return getScrollY() / (float) length;
		}
		return 1.0f;
	}


	@Override protected float getBottomFadingEdgeStrength() {
		if (getChildCount() == 0) {
			return 0.0f;
		}
		final int length = getVerticalFadingEdgeLength();
		final int bottomEdge = getHeight() - getPaddingBottom();
		final int span = getChildAt(0).getBottom() - getScrollY() - bottomEdge;
		if (span < length) {
			return span / (float) length;
		}
		return 1.0f;
	}


	@Override protected float getLeftFadingEdgeStrength() {
		if (getChildCount() == 0) {
			return 0.0f;
		}
		final int length = getHorizontalFadingEdgeLength();
		if (getScrollX() < length) {
			return getScrollX() / (float) length;
		}
		return 1.0f;
	}


	@Override protected float getRightFadingEdgeStrength() {
		if (getChildCount() == 0) {
			return 0.0f;
		}
		final int length = getHorizontalFadingEdgeLength();
		final int rightEdge = getWidth() - getPaddingRight();
		final int span = getChildAt(0).getRight() - getScrollX() - rightEdge;
		if (span < length) {
			return span / (float) length;
		}
		return 1.0f;
	}


	private void initScrollView() {
		mScroller = new Scroller(getContext());
		setFocusable(true);
		setDescendantFocusability(FOCUS_AFTER_DESCENDANTS);
		setWillNotDraw(false);
		final ViewConfiguration configuration = ViewConfiguration.get(getContext());
		mTouchSlop = configuration.getScaledTouchSlop();
		mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
		mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
	}


	@Override
	public void addView(View child) {
		if (getChildCount() > 0) {
			throw new IllegalStateException("ScrollView can host only one direct child");
		}
		super.addView(child);
	}


	@Override
	public void addView(View child, int index) {
		if (getChildCount() > 0) {
			throw new IllegalStateException("ScrollView can host only one direct child");
		}
		super.addView(child, index);
	}


	@Override
	public void addView(View child, ViewGroup.LayoutParams params) {
		if (getChildCount() > 0) {
			throw new IllegalStateException("ScrollView can host only one direct child");
		}
		super.addView(child, params);
	}


	@Override
	public void addView(View child, int index, ViewGroup.LayoutParams params) {
		if (getChildCount() > 0) {
			throw new IllegalStateException("ScrollView can host only one direct child");
		}
		super.addView(child, index, params);
	}


	public boolean isFillViewport() {
		return mFillViewport;
	}


	public void setFillViewport(boolean fillViewport) {
		if (fillViewport != mFillViewport) {
			mFillViewport = fillViewport;
			requestLayout();
		}
	}


	public boolean isSmoothScrollingEnabled() {
		return mSmoothScrollingEnabled;
	}


	public void setSmoothScrollingEnabled(boolean smoothScrollingEnabled) {
		mSmoothScrollingEnabled = smoothScrollingEnabled;
	}


	@Override
	protected void onMeasure(int wms, int hms) {
		// wms : widthMeasureSpec, hms : heightMeasureSpec
		super.onMeasure(wms, hms);
		if (!mFillViewport) {
			return;
		}
		if (getChildCount() == 0){
			return;
		}

		final int wm = MeasureSpec.getMode(wms); // widthMode
		final int hm = MeasureSpec.getMode(hms); // heightMode
		
		if (wm == MeasureSpec.UNSPECIFIED && hm == MeasureSpec.UNSPECIFIED) {
			return;
		}

		final View child = getChildAt(0);
		int width = getMeasuredWidth();
		int height = getMeasuredHeight();
		int cwms = wms; // childWidthMeasureSpec
		int chms = hms; // childHeightMeasureSpec
		if (child.getMeasuredHeight() < height) {
			chms = MeasureSpec.makeMeasureSpec(height - getPaddingTop() - getPaddingBottom(), MeasureSpec.EXACTLY);
		}
		if (child.getMeasuredWidth() < width) {
			cwms = MeasureSpec.makeMeasureSpec(width - getPaddingLeft() - getPaddingRight(), MeasureSpec.EXACTLY);
		}
		child.measure(cwms, chms);
	}


	private boolean inChild(int x, int y) {
		if (getChildCount() > 0) {
			final View child = getChildAt(0);
			return !(
				x < child.getLeft() - getScrollX() ||
				x >= child.getRight() - getScrollX() ||
				y < child.getTop() - getScrollY() ||
				y >= child.getBottom() - getScrollY()
			);
		}
		return false;
	}


	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		final int action = ev.getAction();
		if ((action == MotionEvent.ACTION_MOVE) && (mIsBeingDragged)) {
			return true;
		}
		switch (action & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_MOVE: {
				final int activePointerId = mActivePointerId;
				if (activePointerId == INVALID_POINTER) {
					break;
				}

				final int pointerIndex = ev.findPointerIndex(activePointerId);
				final float x = ev.getX(pointerIndex);
				final float y = ev.getY(pointerIndex);
				final int xDiff = (int) Math.abs(x - mLastMotionX);
				final int yDiff = (int) Math.abs(y - mLastMotionY);
				if (xDiff > mTouchSlop || yDiff > mTouchSlop) {
					mIsBeingDragged = true;
					mLastMotionX = x;
					mLastMotionY = y;
					//if (getParent() != null) getParent().requestDisallowInterceptTouchEvent(true);
				}
				break;
			}

			case MotionEvent.ACTION_DOWN: {
				final float x = ev.getX();
				final float y = ev.getY();
				if (!inChild((int) x, (int) y)) {
					mIsBeingDragged = false;
					break;
				}

				mLastMotionX = x;
				mLastMotionY = y;
				mActivePointerId = ev.getPointerId(0);

				mIsBeingDragged = !mScroller.isFinished();
				break;
			}

			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:
				mIsBeingDragged = false;
				mActivePointerId = INVALID_POINTER;
				break;
			case MotionEvent.ACTION_POINTER_UP:
				onSecondaryPointerUp(ev);
				break;
		}
		return mIsBeingDragged;
	}


	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN && ev.getEdgeFlags() != 0) {
			return false;
		}

		if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();
		}
		mVelocityTracker.addMovement(ev);

		final int action = ev.getAction();

		switch (action & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN: {
				final float x = ev.getX();
				final float y = ev.getY();
				if (!(mIsBeingDragged = inChild((int) x, (int) y))) {
					return false;
				}
				
				if (!mScroller.isFinished()) {
					mScroller.abortAnimation();
				}
				mLastMotionX = x;
				mLastMotionY = y;
				mActivePointerId = ev.getPointerId(0);
				break;
			}
			case MotionEvent.ACTION_MOVE:
				if (mIsBeingDragged) {
					final int activePointerIndex = ev.findPointerIndex(mActivePointerId);
					final float x = ev.getX(activePointerIndex);
					final float y = ev.getY(activePointerIndex);
					final int deltaX = (int) (mLastMotionX - x);
					final int deltaY = (int) (mLastMotionY - y);
					mLastMotionX = x;
					mLastMotionY = y;

					scrollBy(deltaX, deltaY);
				}
				break;
			case MotionEvent.ACTION_UP: 
				if (mIsBeingDragged) {
					final VelocityTracker velocityTracker = mVelocityTracker;
					velocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
					int initialVelocityX = (int) velocityTracker.getXVelocity(mActivePointerId);
					int initialVelocityY = (int) velocityTracker.getYVelocity(mActivePointerId);
					float velocity = FloatMath.sqrt(initialVelocityX * initialVelocityX + initialVelocityY * initialVelocityY);

					if (getChildCount() > 0 && velocity > mMinimumVelocity) {
						fling(-initialVelocityX, -initialVelocityY);
					}

					mActivePointerId = INVALID_POINTER;
					mIsBeingDragged = false;

					if (mVelocityTracker != null) {
						mVelocityTracker.recycle();
						mVelocityTracker = null;
					}
				}
				break;
			case MotionEvent.ACTION_CANCEL:
				if (mIsBeingDragged && getChildCount() > 0) {
					mActivePointerId = INVALID_POINTER;
					mIsBeingDragged = false;
					if (mVelocityTracker != null) {
						mVelocityTracker.recycle();
						mVelocityTracker = null;
					}
				}
				break;
			case MotionEvent.ACTION_POINTER_UP:
				onSecondaryPointerUp(ev);
				break;
		}
		return true;
	}


	private void onSecondaryPointerUp(MotionEvent ev) {
		final int pointerIndex = (ev.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >>
				MotionEvent.ACTION_POINTER_INDEX_SHIFT;
		final int pointerId = ev.getPointerId(pointerIndex);
		if (pointerId == mActivePointerId) {
			final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
			mLastMotionX = ev.getX(newPointerIndex);
			mLastMotionY = ev.getY(newPointerIndex);
			mActivePointerId = ev.getPointerId(newPointerIndex);
			if (mVelocityTracker != null) {
				mVelocityTracker.clear();
			}
		}
	}


	public final void smoothScrollBy(int dx, int dy) {
		if (getChildCount() == 0) {
			return;
		}
		long duration = AnimationUtils.currentAnimationTimeMillis() - mLastScroll;
		if (duration > ANIMATED_SCROLL_GAP) {
			final int width = getWidth() - getPaddingRight() - getPaddingLeft();
			final int right = getChildAt(0).getWidth();
			final int maxX = Math.max(0, right - width);
			final int scrollX = getScrollX();
			dx = Math.max(0, Math.min(scrollX + dx, maxX)) - scrollX;
			
			final int height = getHeight() - getPaddingBottom() - getPaddingTop();
			final int bottom = getChildAt(0).getHeight();
			final int maxY = Math.max(0, bottom - height);
			final int scrollY = getScrollY();
			dy = Math.max(0, Math.min(scrollY + dy, maxY)) - scrollY;

			mScroller.startScroll(scrollX, scrollY, dx, dy);
			invalidate();
		} else {
			if (!mScroller.isFinished()) {
				mScroller.abortAnimation();
			}
			scrollBy(dx, dy);
		}
		mLastScroll = AnimationUtils.currentAnimationTimeMillis();
	}

	
	public final void smoothScrollTo(int x, int y) {
		smoothScrollBy(x - getScrollX(), y - getScrollY());
	}

	@Override
	protected int computeHorizontalScrollRange() {
		final int count = getChildCount();
		final int contentWidth = getWidth() - getPaddingLeft() - getPaddingRight();
		if (count == 0) {
			return contentWidth;
		}
		return getChildAt(0).getRight();
	}


	@Override
	protected int computeVerticalScrollRange() {
		final int count = getChildCount();
		final int contentHeight = getHeight() - getPaddingBottom() - getPaddingTop();
		if (count == 0) {
			return contentHeight;
		}
		return getChildAt(0).getBottom();
	}


	@Override
	protected int computeHorizontalScrollOffset() {
		return Math.max(0, super.computeHorizontalScrollOffset());
	}


	@Override
	protected int computeVerticalScrollOffset() {
		return Math.max(0, super.computeVerticalScrollOffset());
	}


	@Override
	protected void measureChild(View child, int pwms, int phms) {
		child.measure(
			MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
			MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
		);
	}


	@Override
	protected void measureChildWithMargins(View child, int pwms, int cw, int phms, int ch){
		MarginLayoutParams lp = (MarginLayoutParams)child.getLayoutParams();
		child.measure(
			MeasureSpec.makeMeasureSpec(lp.leftMargin + lp.rightMargin, MeasureSpec.UNSPECIFIED),
			MeasureSpec.makeMeasureSpec(lp.topMargin + lp.bottomMargin, MeasureSpec.UNSPECIFIED)
		);
	}


	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			
			int x = mScroller.getCurrX();
			int y = mScroller.getCurrY();

			if (getChildCount() > 0) {
				View child = getChildAt(0);
				x = clamp(x, getWidth() - getPaddingRight() - getPaddingLeft(), child.getWidth());
				y = clamp(y, getHeight() - getPaddingBottom() - getPaddingTop(), child.getHeight());
				super.scrollTo(x, y);
			}
			awakenScrollBars();

			postInvalidate();
		}
	}


	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		scrollTo(getScrollX(), getScrollY());
	}


	public void fling(int velocityX, int velocityY) {
		if (getChildCount() > 0) {
			int width = getWidth() - getPaddingRight() - getPaddingLeft();
			int right = getChildAt(0).getWidth();
			
			int height = getHeight() - getPaddingBottom() - getPaddingTop();
			int bottom = getChildAt(0).getHeight();
	
			mScroller.fling(
					getScrollX(),
					getScrollY(),
					velocityX,
					velocityY,
					0,
					Math.max(0, right - width),
					0,
					Math.max(0, bottom - height)
			);

			invalidate();
		}
	}


	@Override
	public void scrollTo(int x, int y) {
		if (getChildCount() > 0) {
			View child = getChildAt(0);
			x = clamp(x, getWidth() - getPaddingRight() - getPaddingLeft(), child.getWidth());
			y = clamp(y, getHeight() - getPaddingBottom() - getPaddingTop(), child.getHeight());
			if (x != getScrollX() || y != getScrollY()) {
				super.scrollTo(x, y);
			}
		}
	}


	private int clamp(int n, int my, int child) {
		if (my >= child || n < 0) {
			return 0;
		}
		if ((my+n) > child) {
			return child-my;
		}
		return n;
	}
}