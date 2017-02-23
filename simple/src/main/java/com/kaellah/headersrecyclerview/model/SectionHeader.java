package com.kaellah.headersrecyclerview.model;

import android.support.annotation.NonNull;

import com.kaellah.headerslib.interfaces.Header;

import java.util.List;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 23.02.17
 */

public class SectionHeader
        implements Header<User>, Comparable<SectionHeader> {

    private List<User> mChildList;
    private String mSectionText;
    private int mIndex;

    public SectionHeader(List<User> childList, String sectionText, int index) {
        mChildList = childList;
        mSectionText = sectionText;
        mIndex = index;
    }

    @Override
    public List<User> getChildItems() {
        return mChildList;
    }

    public String getSectionText() {
        return mSectionText;
    }

    @Override
    public int compareTo(@NonNull SectionHeader another) {
        return this.mIndex > another.mIndex ? -1 : 1;
    }
}
