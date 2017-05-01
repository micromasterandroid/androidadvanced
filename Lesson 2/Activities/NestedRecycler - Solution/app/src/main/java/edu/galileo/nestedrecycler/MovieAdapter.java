package edu.galileo.nestedrecycler;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.galileo.nestedrecycler.model.Actor;
import edu.galileo.nestedrecycler.model.Movie;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.nested_recycler_actor, parent, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder vh = (ViewHolder) holder;

        Movie movie = movies.get(position);

        vh.textViewMovieName.setText(movie.getName() + " Cast:");
        addActorLayoutManager(vh.recyclerViewActor, movie.getActors());
    }

    private void addActorLayoutManager(RecyclerView recyclerViewActor, List<Actor> actors) {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewActor.setLayoutManager(manager);
        ActorAdapter actorAdapter = new ActorAdapter(actors);
        recyclerViewActor.setAdapter(actorAdapter);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rv_actor) RecyclerView recyclerViewActor;
        @BindView(R.id.tv_movieName) TextView textViewMovieName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
