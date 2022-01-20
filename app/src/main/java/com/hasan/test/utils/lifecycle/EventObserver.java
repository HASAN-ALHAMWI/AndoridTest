package com.hasan.test.utils.lifecycle;

import androidx.lifecycle.Observer;

public class EventObserver<T> implements Observer<Event<T>> {

    private OnChanged<T> onChanged;

    public EventObserver(OnChanged<T> onChanged) {
        this.onChanged = onChanged;
    }

    /**
     * Called when the data is changed.
     */
    @Override
    public void onChanged(Event<T> event) {
        T data = event.getDataOrNull();
        if (data != null)
            onChanged.onChanged(data);
    }

    public interface OnChanged<T> {
        void onChanged(T data);
    }
}
