package cmu.andrew.htay.dinewithus.fragment.shared;


import android.support.v4.view.ViewPager;

/**
 * Created by HuiJun on 4/30/16.
 */
public class MyPageChangeListener implements ViewPager.OnPageChangeListener {

    int oldPosition = -1;
    int currPosition = -1;

    // This method will be invoked when a new page becomes selected.
    @Override
    public void onPageSelected(int position) {
        oldPosition = currPosition;
        currPosition = position;
        System.out.println("LEAVING: " + oldPosition);
        System.out.println("SELECTED: " + position);

    }

    // This method will be invoked when the current page is scrolled
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        //pageManager.getRegisteredFragment(position);
    }

    // Called when the scroll state changes:
    // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
    @Override
    public void onPageScrollStateChanged(int state) {
        // Code goes here
    }
}
