package com.mobcb.statement.mpchart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.ViewGroup;

import com.mobcb.statement.R;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2015/4/13.
 */
public class FragmentManager {
    private final String TAG = "FragmentManager";
    private static FragmentManager instance = null;

    private FragmentActivity mActivity;

    private FragmentManager(FragmentActivity mActivity) {
        this.mActivity = mActivity;

        // Check the memory
        if (!MemoryManager.hasAcailMemory()) {
            // use softmap
            UICACHE = new MySoftMap<String, Fragment>();

        } else {
            // use hashmap
            UICACHE = new HashMap<String, Fragment>();
        }
    }

    public static FragmentManager getInstance(FragmentActivity mActivity) {
        if (instance != null && mActivity == instance.mActivity) {
            return instance;
        } else {
            instance = new FragmentManager(mActivity);
            return instance;
        }
    }

    public static FragmentManager getInstance() {
        return instance;
    }

    private static Map<String, Fragment> UICACHE;

    /**
     * add to cache
     *
     * @param fragmentName
     * @param fragment
     */
    private void addFragment(String fragmentName, Fragment fragment) {
        if (UICACHE != null) {
            UICACHE.put(fragmentName, fragment);
        }
    }

    /**
     * get fragment
     *
     * @param fragmentName
     * @return
     */
    public Fragment getFragment(String fragmentName) {
        if (UICACHE == null && mActivity != null) {
            ViewGroup vg = (ViewGroup) mActivity.findViewById(R.id.content);
            vg.removeAllViews();
        }
        if (UICACHE != null && UICACHE.get(fragmentName) != null) {
            return UICACHE.get(fragmentName);
        } else {
            return null;
        }
    }

    private static Fragment _currentFragment = null;

    /**
     * get the current show fragment
     *
     * @return
     */
    public Fragment getCurrentFragment() {
        return _currentFragment;
    }

    /**
     * clear the fragment cache
     */
    public void clearFragments() {
        UICACHE.clear();
        instance = null;
    }

    /**
     * show some frament
     *
     * @param fragmentName
     */
    public void showFragment(String fragmentName) {
        showFragment(fragmentName, null);
    }

    /**
     * show some frament
     *
     * @param fragmentName
     * @param bundle
     */
    public void showFragment(String fragmentName, Bundle bundle) {
        if (fragmentName == null || fragmentName.equals(""))
            return;
        hideFragments();
        Fragment fragment = getFragment(fragmentName);
        if (fragment != null) {
            Log.d(TAG, "fragment is not null,SHOW!!!");
            mActivity.getSupportFragmentManager().beginTransaction()
                    .show(fragment).commitAllowingStateLoss();
            _currentFragment = fragment;
        } else {
            Log.d(TAG, "fragment is null,newInstance and ADD!!!");
            try {
                fragment = (Fragment) Class.forName(fragmentName).newInstance();
                fragment.setArguments(bundle);
                addFragment(fragmentName, fragment);
                mActivity.getSupportFragmentManager().beginTransaction().
                        add(R.id.content, fragment).commitAllowingStateLoss();
                _currentFragment = fragment;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * hide all fragment
     */
    public void hideFragments() {
        try {
            if (UICACHE != null) {
                Iterator iter = UICACHE.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    Object key = entry.getKey();
                    Object val = entry.getValue();
                    mActivity.getSupportFragmentManager().beginTransaction().hide((Fragment) val).commitAllowingStateLoss();
                }
            } else {
                try {
                    ((ViewGroup) mActivity.findViewById(R.id.content)).removeAllViews();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showFragment(String fragmentName, Bundle bundle, int enter, int exit) {
        if (fragmentName == null || fragmentName.equals(""))
            return;
        //hideFragments();
        Fragment fragment = getFragment(fragmentName);
        if (fragment != null) {
            Log.d(TAG, "fragment is not null,SHOW!!!");
            mActivity.getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(enter, exit)
                    .show(fragment).commitAllowingStateLoss();
            _currentFragment = fragment;
        } else {
            Log.d(TAG, "fragment is null,newInstance and ADD!!!");
            try {
                fragment = (Fragment) Class.forName(fragmentName).newInstance();
                fragment.setArguments(bundle);
                addFragment(fragmentName, fragment);
                mActivity.getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(enter, exit)
                        .add(R.id.content, fragment).commitAllowingStateLoss();
                _currentFragment = fragment;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
