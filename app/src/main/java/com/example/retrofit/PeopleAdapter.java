package com.example.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>{
    private Context context;
    private List<People> personArrayList;

    public PeopleAdapter(Context context, List<People> personArrayList) {
        this.context = context;
        this.personArrayList = personArrayList;
    }

    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_layout,parent,false);
        PeopleViewHolder viewHolder = new PeopleViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
        People person = personArrayList.get(position);
        holder.textViewAge.setText(String.valueOf(person.getAge()));
        holder.textViewRoll.setText(person.getRoll());
        holder.textViewId.setText(String.valueOf(person.getId()));
        holder.textViewName.setText(person.getName());
        Glide.with(context).load(person.getImagePath()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return personArrayList.size();
    }

    class PeopleViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName,textViewId,textViewAge,textViewRoll;
        private ImageView imageView;
        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view_id_list_ac);
            textViewName = itemView.findViewById(R.id.name_id_list_ac);
            textViewId = itemView.findViewById(R.id.own_id_list_ac);
            textViewRoll = itemView.findViewById(R.id.roll_id_list_ac);
            textViewAge = itemView.findViewById(R.id.age_id_list_ac);
        }
    }
}
