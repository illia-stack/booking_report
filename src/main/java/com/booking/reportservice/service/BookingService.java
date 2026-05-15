package com.booking.reportservice.service;

import com.booking.reportservice.model.Booking;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class BookingService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${supabase.anon.key}")
    private String supabaseAnonKey;

    private final String SUPABASE_URL = "https://3_online_booking.supabase.co/rest/v1/bookings";

    /**
     * Ruft Buchungen von Supabase über REST API ab.
     * @param userToken JWT Token des angemeldeten Users für RLS
     * @return Liste von Buchungen
     */
    public List<Booking> getBookings(String userToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(userToken);           // User JWT für RLS
        headers.set("apikey", supabaseAnonKey);     // Supabase Anon Key
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Booking[]> response = restTemplate.exchange(
                SUPABASE_URL,
                HttpMethod.GET,
                entity,
                Booking[].class
        );

        return Arrays.asList(response.getBody());
    }
}