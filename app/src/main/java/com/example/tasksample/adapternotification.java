package com.example.tasksample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapternotification extends RecyclerView.Adapter<adapternotification.ViewHolder> {
    private List<DeshboardUser> values; // i make own

    public adapternotification(List<DeshboardUser> adapternotification) //its a constructor just right click and select generet and select constructor
    {
        this.values = adapternotification;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());//all type own
        View v =inflater.inflate(R.layout.notificationitem, parent,false);//all type own
        ViewHolder vH = new ViewHolder(v);
        return vH;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final DeshboardUser name = values.get(position);//position return the item position
        holder.notificationtv.setText("ID :" +name.getNid()+"\n AppKey :" +name.getAppkey()
                +"\n Ntext :"+name.getNtext()+"\n Ncd :"+name.getNcd()+"\n Ned :"+name.getNed()); //here we can shafal not impact on output

    }

    @Override
    public int getItemCount() {
        return values.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder //here we extends recylerview.viewholder (1)
    {
        public TextView notificationtv;
        public View layout;
        //then also autoimpliment viewholder constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView); //here is the sequence of print out your output which want to show first title or description
            notificationtv =(TextView) itemView.findViewById(R.id.notific_id);

        }
    }
}
