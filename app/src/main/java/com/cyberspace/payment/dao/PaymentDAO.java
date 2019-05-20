package com.cyberspace.payment.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.cyberspace.payment.entity.Payment;

import java.util.List;

@Dao
public interface PaymentDAO {

    @Query("SELECT * FROM tb_payment")
    List<Payment> getPayments();

    @Query("SELECT * FROM tb_payment WHERE t_id= :id")
    Payment getPayment(int id);

    @Query("DELETE FROM tb_payment WHERE t_id= :id")
    void deletePayment(int id);

    @Insert
    long addPayment(Payment payment);

    @Update
    void updatePayment(Payment payment);
}
