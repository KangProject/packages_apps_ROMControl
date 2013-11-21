package com.aokp.romcontrol.fragments;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceGroup;
import android.preference.PreferenceScreen;
import com.aokp.romcontrol.AOKPPreferenceFragment;
import com.aokp.romcontrol.R;

import java.util.ArrayList;
import java.util.Collections;

public class About extends AOKPPreferenceFragment {
    public static final String TAG = "About";

    Preference mSiteUrl;
    Preference mReviewUrl;
    Preference mIrcUrl;
    Preference mDynamicChangelog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_about);
        addPreferencesFromResource(R.xml.prefs_about);
        mSiteUrl = findPreference("iokp_website");
        mReviewUrl = findPreference("iokp_download");
        mIrcUrl = findPreference("iokp_changelog");
        mDynamicChangelog = findPreference("aokp_dynamic_changelog");

        PreferenceGroup devsGroup = (PreferenceGroup) findPreference("devs");
        ArrayList<Preference> devs = new ArrayList<Preference>();
        for (int i = 0; i < devsGroup.getPreferenceCount(); i++) {
            devs.add(devsGroup.getPreference(i));
        }
        devsGroup.removeAll();
        devsGroup.setOrderingAsAdded(false);
        Collections.shuffle(devs);
        for (int i = 0; i < devs.size(); i++) {
            Preference p = devs.get(i);
            p.setOrder(i);
            devsGroup.addPreference(p);
        }
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
                                         Preference preference) {
        if (preference == mSiteUrl) {
            launchUrl("http://www.infamousdevelopment.com/forum/forum/60-infamous-open-kang-project-iokp/");
            return true;
        } else if (preference == mReviewUrl) {
            launchUrl("http://goo.gl/6UENPD");
            return true;
        } else if (preference == mIrcUrl) {
            launchUrl("http://www.infamousdevelopment.com/forum/topic/264-change-log/");
            return true;
        } else if (preference == mDynamicChangelog) {
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    private void launchUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent website = new Intent(Intent.ACTION_VIEW, uriUrl);
        getActivity().startActivity(website);
    }
}
