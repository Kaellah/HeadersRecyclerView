package com.kaellah.headersrecyclerview.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaellah.headerslib.interfaces.DataEntity;
import com.kaellah.headersrecyclerview.R;
import com.kaellah.headersrecyclerview.model.SectionHeader;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 23.02.17
 */

public class HeaderView extends TextView
        implements DataEntity<HeaderView, SectionHeader> {

    public HeaderView(Context context) {
        super(context);

        super.setLines(1);
        super.setEllipsize(TextUtils.TruncateAt.END);
        super.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimensionPixelSize(R.dimen.text_big));
        super.setTypeface(Typeface.DEFAULT_BOLD);
        super.setTextColor(ContextCompat.getColor(context, android.R.color.white));
        super.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        super.setAllCaps(true);

        final int space = context.getResources().getDimensionPixelSize(R.dimen.margin_medium);
        super.setPadding(space, space, space, space);

        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_VERTICAL;
        super.setLayoutParams(params);
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        super.setLayoutParams(params);
    }

    @Override
    public void setData(@Nullable SectionHeader header, @NonNull Object... objects) {
        if (header == null) {
            return;
        }

        super.setText(header.getSectionText());
    }

    @Nullable
    @Override
    public SectionHeader getData() {
        return null;
    }

    @NonNull
    @Override
    public HeaderView getSelf() {
        return this;
    }
}
