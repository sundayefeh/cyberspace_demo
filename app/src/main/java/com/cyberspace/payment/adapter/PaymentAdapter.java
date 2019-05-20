package com.cyberspace.payment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cyberspace.payment.R;
import com.cyberspace.payment.dto.PaymentDTO;

import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder>  {

    private List<PaymentDTO> transactionList;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView amount;
        public TextView purpose;
        public TextView date;
        public TextView name;


        public ViewHolder(View view) {
            super(view);

            amount=(TextView)view.findViewById(R.id.amount);
            purpose=(TextView)view.findViewById(R.id.purpose);
            date=(TextView)view.findViewById(R.id.date);
            name=view.findViewById(R.id.name);

        }
    }


    public PaymentAdapter (Context cont,List<PaymentDTO> mlist) {
        context=cont;
        this.transactionList = mlist;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PaymentDTO mTransaction = transactionList.get(position);
        final ViewHolder mHolder = holder;

        mHolder.date.setText(mTransaction.getCreated_at());
        mHolder.purpose.setText(mTransaction.getPurpose());
        mHolder.amount.setText("₦"+ mTransaction.getAmount());
        mHolder.name.setText(mTransaction.getCustomer());


    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

}
