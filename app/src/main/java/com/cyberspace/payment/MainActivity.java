package com.cyberspace.payment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.cyberspace.payment.adapter.PaymentAdapter;
import com.cyberspace.payment.dao.PaymentDAO;
import com.cyberspace.payment.database.Builder;
import com.cyberspace.payment.dto.PaymentDTO;
import com.cyberspace.payment.entity.Payment;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private PaymentAdapter mAdapter;
    private List<PaymentDTO> mList =new ArrayList<>();


    private PaymentDAO paymentDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        paymentDAO= Builder.getDatabase(this).paymentDAO();

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(getApplicationContext(),Register.class);
                startActivity(register);
            }
        });

        mRecyclerView= findViewById(R.id.list);


        mRecyclerView.setItemAnimator(new SlideInUpAnimator());
        mAdapter = new PaymentAdapter(this, mList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                try {
                    Intent retIntent= new Intent(MainActivity.this,UpdatePayment.class);
                    retIntent.putExtra("payment",mList.get(position));
                    startActivity(retIntent);

                } catch (Exception e) {
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }


        }));

    }

    List<Payment> paymentList;

    void getPayments()
    {
        mList.clear();


        new AsyncTask<Void,Void,Void>()
        {
            @Override
            protected Void doInBackground(Void... params)
            {
                paymentList= paymentDAO.getPayments();
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                if(!paymentList.isEmpty())
                {
                   for (int i=0; i<paymentList.size(); i++)
                   {
                       PaymentDTO paymentDTO= new PaymentDTO();
                       paymentDTO.setT_id(paymentList.get(i).t_id);
                       paymentDTO.setAmount(paymentList.get(i).amount);
                       paymentDTO.setCreated_at(paymentList.get(i).created_at);
                       paymentDTO.setCustomer(paymentList.get(i).customer);
                       paymentDTO.setPurpose(paymentList.get(i).purpose);
                       mList.add(paymentDTO);
                   }
                    mAdapter.notifyDataSetChanged();
                }
            }

        }.execute();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        getPayments();
    }
}
