package singtel.irshadillias.language.interview.views.base;

import android.support.v7.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<T extends RecyclerView.ViewHolder, D>
        extends RecyclerView.Adapter<T>{

    public abstract void setData(List<D> data);
}