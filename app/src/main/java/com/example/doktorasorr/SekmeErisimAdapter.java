package com.example.doktorasorr;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SekmeErisimAdapter extends FragmentPagerAdapter {

    public SekmeErisimAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        switch(position)
        {
            case 0:
                GruplarFragment gruplarFragment = new GruplarFragment();
                return gruplarFragment;

            case 1:

            ChatsFragment chatsFragment = new ChatsFragment();
            return chatsFragment;

            case 2:
                DoktorlarFragment doktorlarFragment = new DoktorlarFragment();
                return doktorlarFragment;

            default:
                return null;




        }



    }

    @Override
    public int getCount()
    {
        return 3;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Gruplar";

            case 1:
                return "";

            case 2:
                return "";

            default:
                return null;

        }
    }
}

