package com.example.e_dive;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.MyViewHolder> {

    Context context;
    ArrayList<MyTicketTrip> myTicketTrips;

    public TicketAdapter(Context c,ArrayList<MyTicketTrip> p){
        context = c;
        myTicketTrips = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.
                from(context).inflate(R.layout.item_myticket, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.xnama_trip.setText(myTicketTrips.get(i).getNama_trip());
        myViewHolder.xlocation.setText(myTicketTrips.get(i).getLocation());
        myViewHolder.xjumlah_ticket.setText(myTicketTrips.get(i).getJumlah_ticket() + " Tickets");

        final String getNamaTrip  = myTicketTrips.get(i).getNama_trip();

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotomyticketdetail  = new Intent(context, MyTicketDetailActv.class);
                gotomyticketdetail.putExtra("nama_trip", getNamaTrip);
                context.startActivity(gotomyticketdetail);
            }
        });


    }

    @Override
    public int getItemCount() {

        return myTicketTrips.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView xnama_trip, xlocation, xjumlah_ticket;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            xnama_trip = itemView.findViewById(R.id.xnama_trip);
            xlocation = itemView.findViewById(R.id.xlocation);
            xjumlah_ticket = itemView.findViewById(R.id.xjumlah_ticket);
        }
    }
}
