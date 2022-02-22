package com.richa.easyride.cycle.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.richa.easyride.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class BookingFragment extends Fragment implements View.OnClickListener {
    //Initialize variable
    EditText   startDate,  endDate,  timePickup,  timeDropoff,
               totalAmount ;
    Button payment;
    TextView bookCycle, tag_state_description, fromDate, pickupTime,locationIcon,storeLocation,
            storeIcon,storeName, payableAmount;
    int t1Hour, t1Minute, t2Hour, t2Minute;
    DatePickerDialog.OnDateSetListener setListener;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booking, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Assign Variable
        bookCycle = view.findViewById(R.id.bookCycle);
        tag_state_description = view.findViewById(R.id.tag_state_description);
        fromDate = view.findViewById(R.id.fromDate);
        startDate = view.findViewById(R.id.startDate);

        endDate = view.findViewById(R.id.endDate);
        pickupTime = view.findViewById(R.id.pickupTime);
        timePickup = view.findViewById(R.id.timePickup);

        timeDropoff = view.findViewById(R.id.timeDropoff);
        locationIcon = view.findViewById(R.id.locationIcon);
        storeIcon = view.findViewById(R.id.storeIcon);
        storeLocation = view.findViewById(R.id.storeLocation);
        storeName = view.findViewById(R.id.storeName);
        payableAmount = view.findViewById(R.id.payableAmount);
        totalAmount = view.findViewById(R.id.totalAmount);
        payment = view.findViewById(R.id.payment);
        payment.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final  int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
            });

            setListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month = month+1;
                    String date = day+"/"+month+"/"+year;
                    fromDate.setText(date);
                }
            };

            startDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(
                            getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int day) {
                            month = month+1;
                            String date = day+"/"+month+"/"+year;
                            startDate.setText(date);
                        }
                    }, year, month, day);
                    datePickerDialog.show();

                }
            });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        endDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });

        timePickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //Initialize hour and minute
                        t1Hour = hourOfDay;
                        t1Minute = minute;
                        //Initilize calendar
                        Calendar calendar = Calendar.getInstance();
                        //Set hour and minute
                        calendar.set(0,0,0, t1Hour, t1Minute);
                        //Set selected time on text view
                        timePickup.setText(DateFormat.format("hh:mm aa", calendar));

                    }
                }, 12,0,false
                );
                //Displayed previous selected time
                timePickerDialog.updateTime(t1Hour,t1Minute);
                //Show Dialog
                timePickerDialog.show();
            }
        });

        timeDropoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //Initialize hour and minute
                        t2Hour = hourOfDay;
                        t2Minute = minute;
                        //Store hour and minute in string
                        String time = t2Hour + ":" + t2Minute;
                        //Initialize 24 hours time format
                        SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                        try{
                            Date date = f24Hours.parse(time);
                            //Initialize 12 hours time format
                            SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
                            //Set selected time oon text view
                            timeDropoff.setText(f12Hours.format(date));
                        }catch (ParseException e){
                            e.printStackTrace();
                        }
                    }
                }, 12, 0,false
                );
                //Set transparent background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Displayed previous selected time
                timePickerDialog.updateTime(t2Hour, t2Minute);
                //Show dialog
                timePickerDialog.show();

            }
        });
        }

    @Override
    public void onClick(View v) {

    }
}



