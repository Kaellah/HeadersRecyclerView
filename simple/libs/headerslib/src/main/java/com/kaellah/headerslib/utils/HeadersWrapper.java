package com.kaellah.headerslib.utils;

import com.kaellah.headerslib.interfaces.Header;

/**
 * @param <H> Header list item
 * @param <C> Child list item
 * @author Chekashov R.(email:roman_woland@mail.ru)
 *
 * Wrapper used to link metadata with a list item.
 */

public class HeadersWrapper<H extends Header<C>, C> {

    private boolean mHeaderItem;
    private H mHeader;
    private C mChild;
    private int mHeaderPosition;
    private int mChildPosition;

    /**
     * Constructor to wrap a mHeader object of type {@link H}.
     *
     * @param header The parent object to wrap
     */
    public HeadersWrapper(H header, int headerPosition) {
        mHeaderItem = true;
        mHeader = header;
        mHeaderPosition = headerPosition;
        mChildPosition = -1;
    }

    /**
     * Constructor to wrap a mChild object of type {@link C}.
     *
     * @param child The mChild object to wrap
     */
    public HeadersWrapper(C child, int headerPosition, int childPosition) {
        mChild = child;
        mHeaderPosition = headerPosition;
        mHeaderItem = false;
        mChildPosition = childPosition;
    }

    public boolean getHeaderItem() {
        return mHeaderItem;
    }

    public H getHeader() {
        return mHeader;
    }

    public C getChild() {
        return mChild;
    }

    public int getHeaderPosition() {
        return mHeaderPosition;
    }

    public int getChildPosition() {
        if (mChildPosition == -1) {
            throw new IllegalAccessError("This is not mChild");
        }
        return mChildPosition;
    }
}
