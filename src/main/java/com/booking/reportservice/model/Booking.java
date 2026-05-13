package com.booking.reportservice.model;

public class Booking {

public Long id;
public Long user_id;
public Long property_id;

public String check_in;
public String check_out;

public Double total_price;

public String status;

public String created_at;
public String updated_at;

public String stripe_session_id;
public String stripe_payment_intent_id;

}