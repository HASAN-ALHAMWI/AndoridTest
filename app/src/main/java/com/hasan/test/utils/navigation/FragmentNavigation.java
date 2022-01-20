package com.hasan.test.utils.navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentNavigation {

    private final FragmentManager fragmentManager;

    private Bundle arguments;

    private boolean addToBackStack;
    private String backStackTag;

    private AddingType addingType;

    public FragmentNavigation(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public FragmentNavigation addToBackStack() {
        this.addToBackStack = true;
        return this;
    }

    public FragmentNavigation addToBackStack(String tag) {
        this.backStackTag = tag;
        return this;
    }

    /**
     * These arguments will be ignored when navigate using Class<? extends Fragment>
     */
    public FragmentNavigation setArguments(Bundle arguments) {
        this.arguments = arguments;
        return this;
    }

    public FragmentNavigation add() {
        this.addingType = AddingType.ADD;
        return this;
    }

    public FragmentNavigation replace() {
        this.addingType = AddingType.REPLACE;
        return this;
    }

    public void navigate(int containerId, Class<? extends Fragment> fragmentClass, Bundle bundle, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (addingType == AddingType.ADD)
            transaction.add(containerId, fragmentClass, bundle, tag);
        else if (addingType == AddingType.REPLACE)
            transaction.replace(containerId, fragmentClass, bundle, tag);

        if (addToBackStack)
            transaction.addToBackStack(backStackTag == null ? fragmentClass.getSimpleName() : backStackTag);

        transaction.commit();
    }

    public void navigate(int containerId, Class<? extends Fragment> fragmentClass, Bundle bundle) {
        navigate(containerId, fragmentClass, bundle, null);
    }

    public void navigate(int containerId, Class<? extends Fragment> fragmentClass) {
        navigate(containerId, fragmentClass, null, null);
    }

    public void navigate(int containerId, Fragment fragment, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (arguments != null)
            fragment.setArguments(arguments);

        if (addingType == AddingType.ADD)
            transaction.add(containerId, fragment, tag);
        else if (addingType == AddingType.REPLACE)
            transaction.replace(containerId, fragment, tag);

        if (addToBackStack)
            transaction.addToBackStack(backStackTag == null ? fragment.getClass().getSimpleName() : tag);

        transaction.commit();
    }

    private enum AddingType {
        ADD, REPLACE
    }
}
