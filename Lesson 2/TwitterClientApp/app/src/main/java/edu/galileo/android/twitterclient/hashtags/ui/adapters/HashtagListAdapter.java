package edu.galileo.android.twitterclient.hashtags.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.galileo.android.twitterclient.R;

/**
 * Created by ykro.
 */
public class HashtagListAdapter extends RecyclerView.Adapter<HashtagListAdapter.ViewHolder> {
    private List<String> items;

    public HashtagListAdapter(ArrayList<String> items) {
        this.items = items;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_hashtag_text, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtHashtag.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtHashtag)
        TextView txtHashtag;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
