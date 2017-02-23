package com.kaellah.headerslib.interfaces;

import java.util.List;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 *
 * Interface for implementing required methods in a section.
 */

public interface Header<C> {

    List<C> getChildItems();
}
