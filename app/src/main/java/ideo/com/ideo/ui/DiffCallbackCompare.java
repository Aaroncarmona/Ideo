package ideo.com.ideo.ui;

import android.support.v7.util.DiffUtil;

import java.util.List;

public class DiffCallbackCompare extends DiffUtil.Callback{

    private List<Person> oldList;
    private List<Person> newList;

    public DiffCallbackCompare(List<Person> oldList, List<Person> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }
}
