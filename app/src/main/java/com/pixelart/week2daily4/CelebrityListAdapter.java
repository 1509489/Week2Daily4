package com.pixelart.week2daily4;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CelebrityListAdapter extends RecyclerView.Adapter<CelebrityListAdapter.ViewHolder> {
    private static final String TAG = "CelebrityListAdapter";

    private List<Celebrity> celebrityList;
    private Context context;
    private int lastPosition = -1;
    private boolean isFavorite;

    public CelebrityListAdapter(List<Celebrity> celebrityList) {
        this.celebrityList = celebrityList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        Log.d(TAG, "onCreateViewHolder");
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.celebrity_list_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            animation.setInterpolator(context, android.R.interpolator.bounce);
            holder.itemView.setAnimation(animation);
        }

        Celebrity celebrity = celebrityList.get(position);
        holder.tvName.setText(celebrity.getName());
        holder.tvAge.setText(celebrity.getAge());
        holder.tvWeight.setText(celebrity.getWeight());

        //TODO: Need to implement the method for saving favorite celebrity
        holder.btnFavorite.setOnClickListener((view) -> {
            if (isFavorite)
            {
                holder.btnFavorite.setImageResource(R.drawable.ic_favorite_fill);
                Toast.makeText(context, "Favorite Clicked: fill", Toast.LENGTH_SHORT).show();
                isFavorite = false;
            }
            else
            {
                holder.btnFavorite.setImageResource(R.drawable.ic_favorite_empty);
                Toast.makeText(context, "Favorite Clicked: empty", Toast.LENGTH_SHORT).show();
                isFavorite = true;
            }
        });

        //TODO: Need to find a better way to implement the delete. the changes doesn't reflect on the recyclerview so i have to reload the activity
        holder.btnDelete.setOnClickListener((view) -> {

            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            databaseHelper.deleteCelebrity(holder.tvName.getText().toString());

            Intent refresh = new Intent(context, ViewCelebrityActivity.class);
            context.startActivity(refresh);
        });


    }

    @Override
    public int getItemCount() {
        return celebrityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName, tvAge, tvWeight;
        public ImageButton btnFavorite, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvWeight = itemView.findViewById(R.id.tvWeight);

            btnFavorite = itemView.findViewById(R.id.ibFavorite);
            btnDelete = itemView.findViewById(R.id.ibDelete);
        }
    }
}
