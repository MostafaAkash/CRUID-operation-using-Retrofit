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

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder>{
    private Context context;
    private ArrayList<People> personList;

    public PersonAdapter(Context context,ArrayList<People> personList) {
        this.context = context;
        this.personList = personList;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.list_layout,parent,false);
        PersonViewHolder viewHolder = new PersonViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        People person = personList.get(position);
        holder.textViewName.setText(person.getName());
        holder.textViewAge.setText(person.getAge());
        holder.textViewRoll.setText(person.getRoll());
        holder.textViewId.setText(String.valueOf(person.getId()));
        holder.imageView.setText(person.getImagePath());
       // Glide.with(context).load(person.getImagePath()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return personList.size();
    }


    class PersonViewHolder extends RecyclerView.ViewHolder
    {
        TextView imageView;
        TextView textViewName,textViewId,textViewAge,textViewRoll;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view_id_list_ac);
            textViewId = itemView.findViewById(R.id.own_id_list_ac);
            textViewRoll = itemView.findViewById(R.id.roll_id_list_ac);
            textViewAge = itemView.findViewById(R.id.age_id_list_ac);
            textViewName = itemView.findViewById(R.id.name_id_list_ac);


        }


    }

}
