package com.cyberspace.payment.database;

import android.arch.persistence.room.RoomDatabase;

import com.cyberspace.payment.dao.PaymentDAO;
import com.cyberspace.payment.entity.Payment;


@android.arch.persistence.room.Database(entities = {Payment.class}, version = 1,exportSchema = false)

public abstract class Database extends RoomDatabase {

    public abstract PaymentDAO paymentDAO();
}
