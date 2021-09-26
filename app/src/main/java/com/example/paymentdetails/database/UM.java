package com.example.paymentdetails.database;

import android.provider.BaseColumns;

public final class UM {
    private UM() {
    }

    public static class Payments implements BaseColumns {
        public static final String TABLE_NAME = "payments";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_COUNTRY = "country";
        public static final String COLUMN_NAME_NUMBER_OF_PARTICIPANTS = "number_of_participants";
        public static final String COLUMN_NAME_CONTACT_NUMBER = "contact_number";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PRICE_AND_PAYMENTTYPE = "price_and_paymenttype";
    }
}
