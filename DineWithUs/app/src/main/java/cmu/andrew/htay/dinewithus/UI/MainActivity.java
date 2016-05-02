package cmu.andrew.htay.dinewithus.UI;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cmu.andrew.htay.dinewithus.R;
import cmu.andrew.htay.dinewithus.fragment.shared.FragmentPageManager;
import cmu.andrew.htay.dinewithus.fragment.shared.HolderFragment;
import cmu.andrew.htay.dinewithus.fragment.shared.MyPageChangeListener;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private FragmentPageManager pageManager;
    public String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = "Niteesh Sundaram";

        vp = (ViewPager) findViewById(R.id.view_pager);
        System.out.println("ViewPage - " + vp);
        pageManager = new FragmentPageManager(getSupportFragmentManager(),
                MainActivity.this);

        vp.addOnPageChangeListener(new MyPageChangeListener(pageManager));
        vp.setAdapter(pageManager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_bar);
        System.out.println("tabLayout - " + tabLayout);

        vp.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(vp);
    }

    public void loginMethod(View view) {
        //do login operations
    }

    public void addToFavorites(View view) {
        //add current resteraunt to favorites
    }



    @Override
    public void onBackPressed() {
        System.out.println("BACK PRESSED");
        // if there is a fragment and the back stack of this fragment is not empty,
        // then emulate 'onBackPressed' behaviour, because in default, it is not working
        FragmentManager fm = getSupportFragmentManager();
        for (Fragment frag : fm.getFragments()) {
            if (frag.isVisible() && pageManager.isPrimaryFrag(frag) && frag instanceof HolderFragment) {
                FragmentManager childFm = frag.getChildFragmentManager();
                if (childFm.getBackStackEntryCount() > 0) {
                    childFm.popBackStack();
                    return;
                }
            }
        }
        super.onBackPressed();
    }

}
