package com.kaellah.headerslib.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.kaellah.headerslib.interfaces.DataEntity;
import com.kaellah.headerslib.interfaces.Header;
import com.kaellah.headerslib.utils.HeadersWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 *         <p>
 *         <p>
 *         RecyclerView.Adapter implementation that
 *         adds the ability to manage headers and their child.
 *         <p>
 *         Changes should be notified through:
 *         {@link #insertNewHeader(Header)}
 *         {@link #insertNewHeader(Header, int)}
 *         {@link #removeHeader(int)}
 *         {@link #insertNewChild(Object, int)}
 *         {@link #insertNewChild(Object, int, int)}
 *         {@link #removeChild(int, int)}
 *         {@link #notifyDataChanged(List)}
 *         methods and not the notify methods of RecyclerView.Adapter.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class HeadersRecyclerViewAdapter<H extends Header<C>, C,
        VIEW_ITEM extends View & DataEntity<VIEW_ITEM, C>,
        VIEW_HEADER extends View & DataEntity<VIEW_HEADER, H>>
        extends RecyclerView.Adapter<HeadersRecyclerViewAdapter.CommonViewHolder> {

    private int HEADER_VIEW_TYPE = 0x1;
    private int CHILD_VIEW_TYPE = 0x2;

    protected class CommonViewHolder<DATA, VIEW extends View & DataEntity<VIEW, DATA>> extends RecyclerView.ViewHolder {

        private VIEW mView;

        CommonViewHolder(VIEW view) {
            super(view);
            mView = view;
        }

        public void bindData(@Nullable DATA data, @NonNull Object... objects) {
            mView.setData(data, objects);
        }
    }

    private class HeaderViewHolder extends CommonViewHolder<H, VIEW_HEADER> {
        HeaderViewHolder(@NonNull VIEW_HEADER view) {
            super(view);
        }
    }

    protected class ItemViewHolder extends CommonViewHolder<C, VIEW_ITEM> {
        ItemViewHolder(@NonNull VIEW_ITEM view) {
            super(view);
        }
    }

    /**
     * A {@link List} of all headers and their children, in order.
     * Changes to this list should be made through the add/remove methods
     * available in {@link HeadersRecyclerViewAdapter}.
     */
    private List<HeadersWrapper<H, C>> mFlatItemList;
    private List<H> mHeaderItemList;

    public HeadersRecyclerViewAdapter(@Nullable List<H> headerItemList) {
        mHeaderItemList = headerItemList == null ? new ArrayList<H>(0) : headerItemList;
        mFlatItemList = headerItemList == null ? new ArrayList<HeadersWrapper<H, C>>(0) : generateFlatItemList(headerItemList);
    }

    /**
     * Implementation of Adapter.onCreateViewHolder(ViewGroup, int)
     * that determines if the list item is a header or a child.
     *
     * @param viewGroup The {@link ViewGroup} into which the new {@link android.view.View}
     *                  will be added after it is bound to an adapter position.
     * @param viewType  The view type of the new {@code android.view.View}.
     * @return A new CommonViewHolder
     * that holds a {@code android.view.View} of the given view type.
     */
    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (isHeaderViewType(viewType)) {
            return new HeaderViewHolder(obtainHeader(viewGroup));

        } else {
            return new ItemViewHolder(obtainItem(viewGroup));
        }
    }

    protected abstract VIEW_ITEM obtainItem(View parent);

    protected abstract VIEW_HEADER obtainHeader(View parent);

    /**
     * Implementation of Adapter.onBindViewHolder(CommonViewHolder, int)
     * that determines if the list item is a header or a child and calls through
     * to the appropriate implementation of either
     * {@link #onBindHeaderViewHolder(HeaderViewHolder, int, Header)} or
     * {@link #onBindItemViewHolder(ItemViewHolder, int, int, Object)}.
     *
     * @param holder       The CommonViewHolder to bind data to
     * @param flatPosition The index in the merged list of children and parents at which to bind
     */
    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(CommonViewHolder holder, int flatPosition) {
        if (flatPosition > mFlatItemList.size()) {
            throw new IllegalStateException("Trying to bind item out of bounds, size " + mFlatItemList.size()
                    + " flatPosition " + flatPosition + ". Was the data changed without a call to notify...()?");
        }
        HeadersWrapper<H, C> headerWrapper = mFlatItemList.get(flatPosition);
        if (headerWrapper.getHeaderItem()) {
            onBindHeaderViewHolder(HeaderViewHolder.class.cast(holder), headerWrapper.getHeaderPosition(), headerWrapper.getHeader());

        } else {
            onBindItemViewHolder(ItemViewHolder.class.cast(holder), headerWrapper.getHeaderPosition(), headerWrapper.getChildPosition(), headerWrapper.getChild());
        }
    }

    private void generateHeaderWrapper(List<HeadersWrapper<H, C>> flatItemList, H header, int headerPosition) {
        HeadersWrapper<H, C> headerWrapper = new HeadersWrapper<>(header, headerPosition);
        flatItemList.add(headerWrapper);
        List<C> childList = header.getChildItems();
        for (int i = 0; i < childList.size(); i++) {
            HeadersWrapper<H, C> childWrapper = new HeadersWrapper<>(childList.get(i), headerPosition, i);
            flatItemList.add(childWrapper);
        }

    }

    /**
     * Generates a full list of all headers and their children, in order.
     *
     * @param headerItemList A list of the headers from
     *                       the {@link HeadersRecyclerViewAdapter}
     * @return A list of all headers and their children
     */
    private List<HeadersWrapper<H, C>> generateFlatItemList(List<H> headerItemList) {
        List<HeadersWrapper<H, C>> flatItemList = new ArrayList<>();
        for (int i = 0; i < headerItemList.size(); i++) {
            H header = headerItemList.get(i);
            generateHeaderWrapper(flatItemList, header, i);
        }
        return flatItemList;
    }

    @Override
    public int getItemCount() {
        return mFlatItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mFlatItemList.get(position).getHeaderItem() ? HEADER_VIEW_TYPE : CHILD_VIEW_TYPE;
    }


    public boolean isHeaderViewType(int viewType) {
        return viewType == HEADER_VIEW_TYPE;
    }

    public void insertNewHeader(H header) {
        insertNewHeader(header, mHeaderItemList.size());
    }

    public void insertNewHeader(H header, int headerPosition) {
        if (headerPosition > mHeaderItemList.size() || headerPosition < 0) {
            throw new IndexOutOfBoundsException("headerPosition =  " + headerPosition + " , Size is " + mHeaderItemList.size());
        }
        notifyDataChanged(mHeaderItemList);
    }

    public void removeHeader(int headerPosition) {
        if (headerPosition > mHeaderItemList.size() - 1 || headerPosition < 0) {
            throw new IndexOutOfBoundsException("headerPosition =  " + headerPosition + " , Size is " + mHeaderItemList.size());
        }
        mHeaderItemList.remove(headerPosition);
        notifyDataChanged(mHeaderItemList);
    }

    public void insertNewChild(C child, int headerPosition) {
        if (headerPosition > mHeaderItemList.size() - 1 || headerPosition < 0) {
            throw new IndexOutOfBoundsException("Invalid headerPosition =  " + headerPosition + " , Size is " + mHeaderItemList.size());
        }
        insertNewChild(child, headerPosition, mHeaderItemList.get(headerPosition).getChildItems().size());
    }

    public void insertNewChild(C child, int headerPosition, int childPosition) {
        if (headerPosition > mHeaderItemList.size() - 1 || headerPosition < 0) {
            throw new IndexOutOfBoundsException("Invalid headerPosition =  " + headerPosition + " , Size is " + mHeaderItemList.size());
        }
        if (childPosition > mHeaderItemList.get(headerPosition).getChildItems().size() || childPosition < 0) {
            throw new IndexOutOfBoundsException("Invalid childPosition =  " + childPosition + " , Size is " + mHeaderItemList.get(headerPosition).getChildItems().size());
        }
        mHeaderItemList.get(headerPosition).getChildItems().add(childPosition, child);
        notifyDataChanged(mHeaderItemList);
    }

    public void removeChild(int headerPosition, int childPosition) {
        if (headerPosition > mHeaderItemList.size() - 1 || headerPosition < 0) {
            throw new IndexOutOfBoundsException("Invalid headerPosition =  " + headerPosition + " , Size is " + mHeaderItemList.size());
        }
        if (childPosition > mHeaderItemList.get(headerPosition).getChildItems().size() - 1 || childPosition < 0) {
            throw new IndexOutOfBoundsException("Invalid childPosition =  " + childPosition + " , Size is " + mHeaderItemList.get(headerPosition).getChildItems().size());
        }
        mHeaderItemList.get(headerPosition).getChildItems().remove(childPosition);
        notifyDataChanged(mHeaderItemList);
    }

    public void notifyDataChanged(List<H> headerItemList) {
        mFlatItemList.clear();
        mFlatItemList.addAll(generateFlatItemList(headerItemList));
        notifyDataSetChanged();
    }

    /**
     * Callback called from onBindViewHolder(CommonViewHolder, int)
     * when the list item bound to is a header.
     * <p>
     *
     * @param holder         The {@code HeaderViewHolder} to bind data to
     * @param headerPosition The position of the parent to bind
     * @param header         The parent which holds the data to be bound to the {@code SVH}
     */
    protected void onBindHeaderViewHolder(HeaderViewHolder holder, int headerPosition, H header) {
        holder.bindData(header, headerPosition);
    }

    /**
     * Callback called from onBindViewHolder(RecyclerView.ViewHolder, int)
     * when the list item bound to is a child.
     * <p>
     *
     * @param holder         The {@code ItemViewHolder} to bind data to
     * @param headerPosition The position of the parent that contains the child to bind
     * @param childPosition  The position of the child to bind
     * @param child          The child which holds that data to be bound to the {@code CVH}
     */
    protected void onBindItemViewHolder(ItemViewHolder holder, int headerPosition, int childPosition, C child) {
        holder.bindData(child, headerPosition, childPosition);
    }
}
