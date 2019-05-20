package com.cyberspace.payment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cyberspace.payment.dao.PaymentDAO;
import com.cyberspace.payment.database.Builder;
import com.cyberspace.payment.dto.PaymentDTO;
import com.cyberspace.payment.entity.Payment;

import java.util.Calendar;
import java.util.Locale;

public class UpdatePayment extends AppCompatActivity {

    PaymentDTO mPayment;
    EditText name,amount,purpose;
    TextView register;
    TextView delete;

    private PaymentDAO paymentDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_payment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPayment=(PaymentDTO) getIntent().getSerializableExtra("payment");

        register= findViewById(R.id.bt_register);
        name=findViewById(R.id.name);
        amount= findViewById(R.id.amount);
        purpose=findViewById(R.id.purpose);
        delete= findViewById(R.id.bt_delete);

        paymentDAO= Builder.getDatabase(this).paymentDAO();


        name.setText(mPayment.getCustomer());
        amount.setText(mPayment.getAmount());
        purpose.setText(mPayment.getPurpose());




        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString()=="")
                {
                    name.setError("This is required");
                    return;
                }

                if(amount.getText().toString()=="")
                {
                    amount.setError("This is required");
                    return;
                }

                if(purpose.getText().toString()=="")
                {
                    purpose.setError("This is required");
                    return;
                }

                Payment payment= new Payment();
                payment.t_id= mPayment.getT_id();
                payment.amount=amount.getText().toString();
                payment.created_at=get_date();
                payment.customer=name.getText().toString();
                payment.purpose=purpose.getText().toString();

                updatePayment(payment);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Payment payment= new Payment();
                payment.t_id= mPayment.getT_id();
                payment.amount=amount.getText().toString();
                payment.created_at=get_date();
                payment.customer=name.getText().toString();
                payment.purpose=purpose.getText().toString();
                deletePayment(payment);
            }
        });

    }

    public static String get_date()
    {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        java.text.SimpleDateFormat inputFormat = new java.text.SimpleDateFormat("d MMM yy, h:mm a",Locale.ENGLISH);
        return inputFormat.format(cal.getTime());
    }


    void updatePayment(final Payment payment)
    {
        new AsyncTask<Void, Void,Void>()
        {
            @Override
            protected Void doInBackground(Void... params)
            {
                paymentDAO.updatePayment(payment);
                return null;
            }

            @Override
            protected void onPostExecute(Void result)
            {
                finish();
            }
        }.execute();
    }


    void deletePayment(final Payment payment)
    {
        new AsyncTask<Void, Void,Void>()
        {
            @Override
            protected Void doInBackground(Void... params)
            {
                paymentDAO.deletePayment(payment.t_id);
                return null;
            }

            @Override
            protected void onPostExecute(Void result)
            {
                finish();
            }
        }.execute();
    }

}
