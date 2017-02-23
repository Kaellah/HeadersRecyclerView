package com.kaellah.headersrecyclerview.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.TextView;

import com.kaellah.headerslib.interfaces.DataEntity;
import com.kaellah.headersrecyclerview.R;
import com.kaellah.headersrecyclerview.model.User;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 23.02.17
 */

public class ItemView extends LinearLayoutCompat
        implements DataEntity<ItemView, User> {

    // VIEWS
    private final TextView mTvName;
    private final TextView mTvDescription;

    public ItemView(Context context) {
        super(context);

        {
            inflate(getContext(), R.layout.item_view, this);
            super.setOrientation(VERTICAL);

            final int space = context.getResources().getDimensionPixelSize(R.dimen.margin_medium);
            super.setPadding(space, space, space, space);
        }

        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvDescription = (TextView) findViewById(R.id.tv_description);
    }

    @Override
    public void setData(@Nullable User user, @NonNull Object... objects) {
        if (user == null) {
            return;
        }

        mTvName.setText(user.getName());
        mTvDescription.setText(user.getDescription());
    }

    @Nullable
    @Override
    public User getData() {
        return null;
    }

    @NonNull
    @Override
    public ItemView getSelf() {
        return this;
    }
}
