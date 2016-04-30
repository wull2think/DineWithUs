package cmu.andrew.htay.dinewithus.fragment.shared;


import android.support.v4.view.ViewPager;

import cmu.andrew.htay.dinewithus.fragment.appointment.AppointmentFragment;
import cmu.andrew.htay.dinewithus.fragment.appointment.AppointmentFragmentHolder;
import cmu.andrew.htay.dinewithus.fragment.profile.ProfileFragment;
import cmu.andrew.htay.dinewithus.fragment.schedule.ScheduleFragment;
import cmu.andrew.htay.dinewithus.fragment.schedule.ScheduleFragmentHolder;
import cmu.andrew.htay.dinewithus.fragment.stores.StoresFragment;
import cmu.andrew.htay.dinewithus.fragment.stores.StoresFragmentHolder;

/**
 * Created by HuiJun on 4/30/16.
 */
public class MyPageChangeListener implements ViewPager.OnPageChangeListener {

    int oldPosition = 0;
    int currPosition = 0;
    FragmentPageManager pageManager;

    public MyPageChangeListener(FragmentPageManager pageManager) {
        this.pageManager = pageManager;
    }

    // This method will be invoked when a new page becomes selected.
    @Override
    public void onPageSelected(int position) {
        oldPosition = currPosition;
        currPosition = position;
        System.out.println("LEAVING: " + oldPosition);
        System.out.println("SELECTED: " + position);

        switch(oldPosition) {
            case 0:
                break;
            case 1:
                ProfileFragment pf = (ProfileFragment)pageManager.getRegisteredFragment(oldPosition);
                pf.sendUpdate();
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                System.out.println("View does not exist, no updates needed");
                break;
        }

        switch(currPosition) {
            case 0:
                AppointmentFragmentHolder afh = (AppointmentFragmentHolder)pageManager.getRegisteredFragment(currPosition);
                AppointmentFragment af = afh.getAppointmentFragment();
                af.getUpdate();
                break;
            case 1:
                ProfileFragment pf = (ProfileFragment)pageManager.getRegisteredFragment(currPosition);
                pf.getUpdate();
                break;
            case 2:
                StoresFragmentHolder sfh = (StoresFragmentHolder)pageManager.getRegisteredFragment(currPosition);
                StoresFragment sf = sfh.getStoresFragment();
                sf.getUpdate();
                break;
            case 3:
                ScheduleFragmentHolder sbfh = (ScheduleFragmentHolder)pageManager.getRegisteredFragment(currPosition);
                ScheduleFragment sbf = sbfh.getScheduleFragment();
                sbf.getUpdate();
                break;
            default:
                System.out.println("View does not exist, no updates needed");
                break;
        }

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
