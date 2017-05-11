package edu.galileo.nestedrecycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.galileo.nestedrecycler.model.Actor;

public class ActorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Actor> actors;

    public ActorAdapter(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_actor, parent, false);

        ActorAdapter.ViewHolder holder = new ActorAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder vh = (ViewHolder) holder;

        Actor actor = actors.get(position);

        vh.actorView.setText(actor.getName());
    }

    @Override
    public int getItemCount() {
        return actors.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.actor_name) TextView actorView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
