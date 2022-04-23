package com.richa.easyride.checkout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.audiofx.DynamicsProcessing;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.khalti.checkout.helper.Config;
import com.khalti.checkout.helper.KhaltiCheckOut;
import com.khalti.checkout.helper.OnCheckOutListener;
import com.khalti.checkout.helper.PaymentPreference;
import com.khalti.utils.Constant;
import com.richa.easyride.R;
import com.richa.easyride.api.ApiClient;
import com.richa.easyride.api.response.Category;
import com.richa.easyride.api.response.Cycle;
import com.richa.easyride.api.response.RegisterResponse;
import com.richa.easyride.utils.SharedPrefUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomDialogActivity extends AppCompatActivity {
    public static String key = "pKey";
    long total;
    Cycle cycle;
    TextView buttonContinue, pickuptime, dropofftime, pickupdate, dropoffdate, totalAmountTV, nameTV, cyclerateTV;
    DatePickerDialog.OnDateSetListener setListener;
    LinearLayout checkOutLL;
    ImageView back, codIV, khaltiIV;
    int t1hour, t1minute, t2hour, t2minute;
    int p_type = 1;
    String payStatus, date, pick_time, drop_time;
    String p_ref = "COD";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_dialog);
        if (getIntent().getSerializableExtra(key) == null)
            finish();
        cycle = (Cycle) getIntent().getSerializableExtra(key);

        pickupdate = findViewById(R.id.pickupdate);
        pickuptime = findViewById(R.id.pickuptime);
//        dropoffdate = findViewById(R.id.dropoffdate);
        dropofftime = findViewById(R.id.dropofftime);
//        buttonContinue = findViewById(R.id.buttonContinue);
        back =  findViewById(R.id.back);
        totalAmountTV = findViewById(R.id.totalAmountTV);
        cyclerateTV = findViewById(R.id.cyclerateTV);
        nameTV = findViewById(R.id.nameTV);
        codIV = findViewById(R.id.codIV);
        khaltiIV = findViewById(R.id.khaltiIV);
        checkOutLL = findViewById(R.id.checkOutLL);

        cyclerateTV.setText(cycle.getRentalRate()+"");
        nameTV.setText(cycle.getCycleName());
        backOnClick();

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);



        pickupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        BottomDialogActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                         date = day+"/"+month+"/"+year;
                        pickupdate.setText(date);

                    }
                },year,month,day
                );
                datePickerDialog.show();
            }
        });

        pickuptime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        BottomDialogActivity.this,
                        android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //Initialize hour and minute
                                t1hour = hourOfDay;
                                t1minute = minute;

                                //Store hour and minute in string
                                String time = t1hour + ":" + t1minute;

                                //Initialize 24 hours time format
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Hours.parse(time);
                                    //Initialize 12 hours time format
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm:aa"
                                    );
                                    //Set selected time  on text view
                                     pickuptime.setText(f12Hours.format(date));
                                     pick_time = f12Hours.format(date);

                                } catch (ParseException e){
                                    e.printStackTrace();
                                }

                            }
                        }, 12, 0, false
                );

                //Set transparent background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Displayed previous selected time
                timePickerDialog.updateTime(t1hour, t1minute);
                //Show dialog
                timePickerDialog.show();
            }
        });

        checkOutLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p_type == 1){
                    checkOut();

                } else {
                    khaltiCheckOut();

                }
            }
        });

        khaltiIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p_type = 2;
                khaltiIV.setBackground(getResources().getDrawable(R.drawable.box_shape_selected));
                codIV.setBackground(getResources().getDrawable(R.drawable.unselect_box));
            }
        });

        codIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p_type = 1;
                khaltiIV.setBackground(getResources().getDrawable(R.drawable.unselect_box));
                codIV.setBackground(getResources().getDrawable(R.drawable.box_shape_selected));
            }
        });

//        dropoffdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog datePickerDialog = new DatePickerDialog(
//                        BottomDialogActivity.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        month = month+1;
//                        String date = day+"/"+month+"/"+year;
//                        dropoffdate.setText(date);
//                    }
//                },year,month,day
//                );
//                datePickerDialog.show();
//            }
//        });

        dropofftime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        BottomDialogActivity.this,
                        android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //Initialize hour and minute
                                t2hour = hourOfDay;
                                t2minute = minute;

                                //Store hour and minute in string
                                String time = t2hour + ":" + t2minute;

                                //Initialize 24 hours time format
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Hours.parse(time);
                                    //Initialize 12 hours time format
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm:aa"
                                    );
                                    //Set selected time  on text view
                                    dropofftime.setText(f12Hours.format(date));
                                    drop_time = f12Hours.format(date);

//                                    Toast.makeText(BottomDialogActivity.this, , Toast.LENGTH_SHORT).show();
                                    System.out.println(timeCalculation());
                                    totalAmountTV.setText("Rs. "+cycle.getRentalRate()*timeCalculation());

                                    total = (long) (cycle.getRentalRate()*timeCalculation());

                                } catch (ParseException e){
                                    e.printStackTrace();
                                }

                            }
                        }, 12, 0, false
                );

                //Set transparent background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Displayed previous selected time
                timePickerDialog.updateTime(t2hour, t2minute);
                //Show dialog
                timePickerDialog.show();
            }
        });



//        buttonContinueOnClick();
    }

    private void khaltiCheckOut() {
        payStatus = "Paid";
        Map<String, Object> map = new HashMap<>();
        map.put("merchant_extra", "This is extra data");

        com.khalti.checkout.helper.Config.Builder builder = new Config.Builder("test_public_key_b94f64c04006421d937f9e74975d8a60",  cycle.getCycleId() + "",
                cycle.getCycleName(), total * 100,  new OnCheckOutListener() {
            @Override
            public void onError(@NonNull String action, @NonNull Map<String, String> errorMap) {
                Log.i(action, errorMap.toString());
            }

            @Override
            public void onSuccess(@NonNull Map<String, Object> data) {
                payStatus = "Paid";
                p_ref = "Khalti";
                String key = SharedPrefUtils.getString(BottomDialogActivity.this, getString(R.string.api_key));

                Call<RegisterResponse> orderCall = ApiClient.getClient().rent(key, cycle.getCycleId(),date,total,payStatus,pick_time,drop_time,p_type,p_ref,cycle.getImages().get(1) );
                orderCall.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        if (response.isSuccessful()){
                            Intent intent = new Intent(BottomDialogActivity.this, OrderCompleteActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {

                    }
                });
            }
        })
                .paymentPreferences(new ArrayList<PaymentPreference>() {{
                    add(PaymentPreference.KHALTI);
                    add(PaymentPreference.EBANKING);
                    add(PaymentPreference.MOBILE_BANKING);
                    add(PaymentPreference.CONNECT_IPS);
                    add(PaymentPreference.SCT);
                }})
                .additionalData(map)
                .productUrl("http://example.com/product")
                .mobile("9800000000");
        com.khalti.checkout.helper.Config config = builder.build();
        KhaltiCheckOut khaltiCheckOut = new KhaltiCheckOut(this, config);
        khaltiCheckOut.show();


    }

    private void backOnClick() {
        back.setOnClickListener(v -> finish());
    }

    private double timeCalculation() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date startDate = simpleDateFormat.parse(pickuptime.getText()+"");
        Date endDate = simpleDateFormat.parse(dropofftime.getText()+"");

        long difference = endDate.getTime() - startDate.getTime();
        if(difference<0){
            Date dateMax = simpleDateFormat.parse("12:00");
            Date dateMin = simpleDateFormat.parse("00:00");
            difference=(dateMax.getTime() - startDate.getTime()) + (endDate.getTime()-dateMin.getTime());

        }
        int days = (int) (difference / (1000*60*60*24));
        double hours = (double) ((difference - (1000*60*60*24*days)) / (1000*60*60));
        double min = (double) (difference - (1000*60*60*24*days)- (1000*60*60*hours)) / (1000*60);
        double hr= min/60+hours;
        return  hr;
//        Log.i("log_tag","Hours: "+hours+", Mins: "+min);

    }


//    private void buttonContinueOnClick() {
//        buttonContinue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    timeCalculation();
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
////                Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
////                startActivity(intent);
//            }
//        });
//    }

    private void checkOut(){
        payStatus = "Pending";
        String key = SharedPrefUtils.getString(this, getString(R.string.api_key));
        Call<RegisterResponse> orderCall = ApiClient.getClient().rent(key, cycle.getCycleId(),date,total,payStatus,pick_time,drop_time,p_type,p_ref,cycle.getImages().get(1) );
        orderCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()){
                    Intent intent = new Intent(BottomDialogActivity.this, OrderCompleteActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

            }
        });
    }
}