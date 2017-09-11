package com.softvison.com.movieapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import model.MoviesList;

/**
 * Created by ajay on 8/9/17.
 */

class MoviesDealAdapter extends RecyclerView.Adapter<MoviesDealAdapter.DealAdapter>  implements View.OnClickListener{

    List<MoviesList.Result> resultList;
    private PostItemListener mItemListener;
    Context mContext;



    public MoviesDealAdapter(Context mcoContext, List<MoviesList.Result> resultList, PostItemListener itemListener) {
       this.resultList=resultList;
        this.mItemListener = itemListener;
        this.mContext=mcoContext;
    }

    @Override
public DealAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false);
        return new DealAdapter(view);
        }

@Override
public void onBindViewHolder(DealAdapter holder, int position) {
        MoviesList.Result result=resultList.get(position);

        holder.tile.setText(result.getTitle());

        holder.originalLanguage.setText(result.getOverview());


        }

@Override
public int getItemCount() {
        return resultList.size();
        }

    @Override
    public void onClick(View v) {
        Toast.makeText(mContext,"Item Clicked",Toast.LENGTH_LONG).show();
       /* MoviesList.Result item = getItem(getAdapterPosition());
        this.mItemListener.onPostClick(item.getAnswerId());*/

        notifyDataSetChanged();
    }


    public class DealAdapter extends RecyclerView.ViewHolder {
    public TextView tile,originalLanguage;
    View parentLayout;
        PostItemListener mItemListener;

    public DealAdapter(View view) {
        super(view);

        tile=(TextView)view.findViewById(R.id.tv_movies_title);
        originalLanguage=(TextView)view.findViewById(R.id.tv_movies_language);

    }
}

    private MoviesList.Result getItem(int adapterPosition) {
        return resultList.get(adapterPosition);
    }
    public interface PostItemListener {
        void onPostClick(long id);
    }
}
