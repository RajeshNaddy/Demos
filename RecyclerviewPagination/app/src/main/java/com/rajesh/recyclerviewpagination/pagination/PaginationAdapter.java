package com.rajesh.recyclerviewpagination.pagination;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.rajesh.recyclerviewpagination.R;

import java.util.List;

/**
 * Created by Rajesh on 25/06/2019
 **/

public abstract class PaginationAdapter<T extends Entity> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected static final int ITEM = 0;
    protected static final int LOADING = 1;

    protected Context context;

    public boolean isLoadingAdded = false;

    public PaginationAdapter(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public abstract List<T> getList();

    protected abstract RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater);


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return getList() == null ? 0 : getList().size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == getList().size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    /*
   Helpers
   _________________________________________________________________________________________________
    */

    public void add(T entity) {
        getList().add(entity);
        notifyItemInserted(getList().size() - 1);
    }

    public void addAll(List<T> entityList) {
        for (T entity : entityList) {
            add(entity);
        }
    }

    public void remove(T city) {
        int position = getList().indexOf(city);
        if (position > -1) {
            getList().remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public abstract void addLoadingFooter(); /*{
        isLoadingAdded = true;
        add(new BaseEntity());
    }*/

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = getList().size() - 1;
        T item = getItem(position);

        if (item != null) {
            getList().remove(position);
            notifyItemRemoved(position);
        }
    }

    public T getItem(int position) {
        return getList().get(position);
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }

}
