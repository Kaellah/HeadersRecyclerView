package com.kaellah.headerslib.interfaces;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 06.06.16
 */
public interface DataEntity<SELF, DATA> {

    void setData(@Nullable DATA data, @NonNull Object... objects);

    @Nullable
    DATA getData();

    @NonNull
    SELF getSelf();
}