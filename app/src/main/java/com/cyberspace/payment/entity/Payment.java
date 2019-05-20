package com.cyberspace.payment.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tb_payment")
public class Payment {
    @PrimaryKey(autoGenerate = true)
    public int t_id;


    @ColumnInfo
    public String created_at;

    @ColumnInfo
    public String amount;

    @ColumnInfo
    public String purpose;

    @ColumnInfo
    public String customer;


}
