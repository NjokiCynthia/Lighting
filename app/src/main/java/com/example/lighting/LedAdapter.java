package com.example.lighting;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.logging.Logger;

public class LedAdapter extends RecyclerView.Adapter<LedAdapter.ViewHolder>{
    public static final int INFO = 4;

    ArrayList<Led> ledBulbs;
    Context context;
    private int index;

    public LedAdapter(ArrayList<Led> led, Context context) {
        this.ledBulbs = led;
        this.context = context;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public  ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LedAdapter.ViewHolder holder, int position) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // myRef = database.getReference().child("IoTlab").child("SmartSwitch").child("BULBS");
        DatabaseReference myRef = database.getReference().child("IoTlab").child("SmartSwitch").child("BULBS");
       // myRef.child("BULB_1/status/").setValue(led.getStatus());
//        Log.v("ledAdapter", ledBulbs.toString());
//        ledBulbs.get(position).setStatus("hhhhh");
        Log.v("ledAdapter", "ssdsdsd "+ ledBulbs.get(position).getStatus());
        holder.bulb_label.setText(ledBulbs.get(position).getStatus());

        holder.itemView.setOnClickListener(v -> {
            index = position;

            if (position == 0) {
                holder.itemView.setBackgroundColor(Color.BLUE);
                myRef.child("BULB_1/status/").setValue("ON");

            }
              else
                {
                    holder.itemView.setBackgroundColor(Color.WHITE);
                }

            if (position == 1) {
                holder.itemView.setBackgroundColor(Color.BLUE);
                myRef.child("BULB_2/status/").setValue("ON");
            }
            else if (position == 2) {
                holder.itemView.setBackgroundColor(Color.BLUE);
                myRef.child("BULB_3/status/").setValue("ON");
            }
            else if (position == 3) {
                holder.itemView.setBackgroundColor(Color.BLUE);
                myRef.child("BULB_4/status/").setValue("ON");
            }
//            else {
//                holder.itemView.setBackgroundColor(Color.WHITE);
//                myRef.child("BULB_1/status/").setValue("OFF");
//                myRef.child("BULB_2/status/").setValue("OFF");
//                myRef.child("BULB_3/status/").setValue("OFF");
//                myRef.child("BULB_4/status/").setValue("OFF");
//            }


        });
    }
    @Override
    public int getItemCount() {
        return ledBulbs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView bulb_label;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            bulb_label = itemView.findViewById(R.id.bulb_label);

        }
    }
}