package com.example.registration_details.DB_Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.registration_details.Model_Class.RegistrationMember;
import com.example.registration_details.Database.RegistrationMemberDatabase;

import java.util.ArrayList;
import java.util.List;

public class RegistrationMemberDatabaseHelper extends SQLiteOpenHelper {

    public RegistrationMemberDatabaseHelper(Context context) {
        super(context, RegistrationMemberDatabase.DATABASE_NAME, null, RegistrationMemberDatabase.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + RegistrationMemberDatabase.TABLE_NAME + " (" +
                RegistrationMemberDatabase.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RegistrationMemberDatabase.COLUMN_MOBILE_NUMBER + " TEXT, " +
                RegistrationMemberDatabase.COLUMN_NAME + " TEXT, " +
                RegistrationMemberDatabase.COLUMN_ROLE + " TEXT, " +
                RegistrationMemberDatabase.COLUMN_SUBSCRIPTION_FEE + " REAL, " +
                RegistrationMemberDatabase.COLUMN_DEPOSIT_AMOUNT + " REAL, " +
                RegistrationMemberDatabase.COLUMN_LOAN_AMOUNT + " REAL, " +
                RegistrationMemberDatabase.COLUMN_GENDER + " TEXT, " +
                RegistrationMemberDatabase.COLUMN_DOB + " TEXT, " +
                RegistrationMemberDatabase.COLUMN_JOINING_DATE + " TEXT, " +
                RegistrationMemberDatabase.COLUMN_MARITAL_STATUS + " TEXT, " +
                RegistrationMemberDatabase.COLUMN_MARRIAGE_DATE + " TEXT, " +
                RegistrationMemberDatabase.COLUMN_CASTE + " TEXT, " +
                RegistrationMemberDatabase.COLUMN_RELIGION + " TEXT, " +
                RegistrationMemberDatabase.COLUMN_CATEGORY + " TEXT, " +
                RegistrationMemberDatabase.COLUMN_AADHAR_NUMBER + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RegistrationMemberDatabase.TABLE_NAME);
        onCreate(db);
    }


    //insert function to insert value into database
    public long insertRegistrationMemberData(
            String mobileNumber, String name, String role, double subscriptionFee, double depositAmount,
            double loanAmount, String gender, String dob, String joiningDate, String maritalStatus,
            String marriageDate, String caste, String religion, String category, String aadharNumber) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RegistrationMemberDatabase.COLUMN_MOBILE_NUMBER, mobileNumber);
        values.put(RegistrationMemberDatabase.COLUMN_NAME, name);
        values.put(RegistrationMemberDatabase.COLUMN_ROLE, role);
        values.put(RegistrationMemberDatabase.COLUMN_SUBSCRIPTION_FEE, subscriptionFee);
        values.put(RegistrationMemberDatabase.COLUMN_DEPOSIT_AMOUNT, depositAmount);
        values.put(RegistrationMemberDatabase.COLUMN_LOAN_AMOUNT, loanAmount);
        values.put(RegistrationMemberDatabase.COLUMN_GENDER, gender);
        values.put(RegistrationMemberDatabase.COLUMN_DOB, dob);
        values.put(RegistrationMemberDatabase.COLUMN_JOINING_DATE, joiningDate);
        values.put(RegistrationMemberDatabase.COLUMN_MARITAL_STATUS, maritalStatus);
        values.put(RegistrationMemberDatabase.COLUMN_MARRIAGE_DATE, marriageDate);
        values.put(RegistrationMemberDatabase.COLUMN_CASTE, caste);
        values.put(RegistrationMemberDatabase.COLUMN_RELIGION, religion);
        values.put(RegistrationMemberDatabase.COLUMN_CATEGORY, category);
        values.put(RegistrationMemberDatabase.COLUMN_AADHAR_NUMBER, aadharNumber);

        long result = db.insert(RegistrationMemberDatabase.TABLE_NAME, null, values);
        db.close();
        return result;
    }


    //fetch function to fetch and display list
    public List<RegistrationMember> getSpecificRegistrationMembers() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<RegistrationMember> memberList = new ArrayList<>();

        String query = "SELECT " +
                RegistrationMemberDatabase.COLUMN_NAME + ", " +
                RegistrationMemberDatabase.COLUMN_MOBILE_NUMBER + ", " +
                RegistrationMemberDatabase.COLUMN_ROLE + ", " +
                RegistrationMemberDatabase.COLUMN_SUBSCRIPTION_FEE + ", " +
                RegistrationMemberDatabase.COLUMN_DEPOSIT_AMOUNT + ", " +
                RegistrationMemberDatabase.COLUMN_LOAN_AMOUNT +
                " FROM " + RegistrationMemberDatabase.TABLE_NAME;

        try (Cursor cursor = db.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(RegistrationMemberDatabase.COLUMN_NAME));
                    String mobileNumber = cursor.getString(cursor.getColumnIndexOrThrow(RegistrationMemberDatabase.COLUMN_MOBILE_NUMBER));
                    String role = cursor.getString(cursor.getColumnIndexOrThrow(RegistrationMemberDatabase.COLUMN_ROLE));
                    double subscriptionFee = cursor.getDouble(cursor.getColumnIndexOrThrow(RegistrationMemberDatabase.COLUMN_SUBSCRIPTION_FEE));
                    double depositAmount = cursor.getDouble(cursor.getColumnIndexOrThrow(RegistrationMemberDatabase.COLUMN_DEPOSIT_AMOUNT));
                    double loanAmount = cursor.getDouble(cursor.getColumnIndexOrThrow(RegistrationMemberDatabase.COLUMN_LOAN_AMOUNT));

                    RegistrationMember member = new RegistrationMember(name, mobileNumber, role, subscriptionFee, depositAmount, loanAmount);
                    memberList.add(member);
                } while (cursor.moveToNext());
            }
        }
        db.close();

        return memberList;
    }

}
