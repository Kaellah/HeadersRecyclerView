package com.kaellah.headersrecyclerview.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.kaellah.headerslib.adapter.HeadersRecyclerViewAdapter;
import com.kaellah.headersrecyclerview.model.SectionHeader;
import com.kaellah.headersrecyclerview.model.User;
import com.kaellah.headersrecyclerview.view.HeaderView;
import com.kaellah.headersrecyclerview.view.ItemView;

import java.util.List;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 23.02.17
 */

public class SimpleAdapter extends HeadersRecyclerViewAdapter<SectionHeader, User, ItemView, HeaderView> {

    public SimpleAdapter(@Nullable List<SectionHeader> headerItemList) {
        super(headerItemList);
    }

    @Override
    protected ItemView obtainItem(View parent) {
        return new ItemView(parent.getContext());
    }

    @Override
    protected HeaderView obtainHeader(View parent) {
        return new HeaderView(parent.getContext());
    }
}
