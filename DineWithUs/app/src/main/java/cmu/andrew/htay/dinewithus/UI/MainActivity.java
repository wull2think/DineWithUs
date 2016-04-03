package cmu.andrew.htay.dinewithus.UI;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cmu.andrew.htay.dinewithus.R;
import cmu.andrew.htay.dinewithus.fragment.FragmentPageManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewPager vp = (ViewPager) findViewById(R.id.view_pager);
        System.out.println("ViewPage - " + vp);
        vp.setAdapter(new FragmentPageManager(getSupportFragmentManager(),
                MainActivity.this));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_bar);
        System.out.println("tabLayout - " + tabLayout);
        tabLayout.setupWithViewPager(vp);
    }
}
