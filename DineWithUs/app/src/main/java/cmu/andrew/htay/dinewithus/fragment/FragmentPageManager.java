package cmu.andrew.htay.dinewithus.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * Created by HuiJun on 3/30/16.
 */

//tab manager for handling tabbing views
public class FragmentPageManager extends FragmentPagerAdapter {

    final int PAGE_COUNT = 4;
    private String tabNames[] = new String[]
            { "Appointments", "Profile", "Stores", "Schedule" };
    private Fragment currentFragment;

    public FragmentPageManager(FragmentManager fm, Context context) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        //case on the order in the tabNames array
        System.out.println("POSITION "+position);
        switch(position) {
            case 0: //appointment
                return AppointmentFragmentHolder.newInstance();
            case 1: //profile
                return ProfileFragment.newInstance();
            case 2: //stores
                return StoresFragmentHolder.newInstance();
            case 3: //schedule
                return ScheduleFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);

        if (currentFragment != object) {
            Fragment newFragment = (Fragment) object;
            currentFragment = newFragment;
        }
    }

    public boolean isPrimaryFrag(Fragment frag) {
        return frag == currentFragment;
    }
}
