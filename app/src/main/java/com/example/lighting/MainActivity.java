package com.example.lighting;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Led> ledBulbs;
    CardView card1;
    RecyclerView recyclerView;
    private LedAdapter allDataDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("IoTlab").child("SmartSwitch").child("BULBS");
        ledBulbs = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.myBulbs);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            private Object LedAdapter;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.getChildren();
//       this bit loops through all values and gives u the data in each card
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Led ledValues = child.getValue(Led.class);
                    System.out.println("Led Object ==== > " + ledValues);
                    System.out.println("DATA       ==== > " + ledValues.getStatus());
                    ledBulbs.add(ledValues);
                }
                allDataDisplay = new LedAdapter(ledBulbs, MainActivity.this);
                recyclerView.setAdapter(allDataDisplay);
                allDataDisplay.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("Failed to read value" + error.toException());

            }
        });

        LedAdapter ledAdapter = new LedAdapter(ledBulbs, MainActivity.this);
        recyclerView.setAdapter(ledAdapter);


    }
}
//initializations
//  databaseReference = FirebaseDatabase.getInstance().getReference().child("Travels").child("DaysOfTheWeekSimple");
//         database = FirebaseDatabase.getInstance();
//        ledBulbs=new ArrayList<>();
//      //  mDbRef = database.getReference("IoTlab/SmartSwitch/BULBS/");
//        mDbRef = database.getReference("IoTlab/SmartSwitch").child("BULBS");
//        //check = findViewById(R.id.check);
//
//        recyclerView = findViewById(R.id.recyclerView);
//        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        getAllBulbs();
////        retrieveAllBulbs();
////        LedAdapter ledAdapter = new LedAdapter( ledBulbs ,MainActivity.this);
////        recyclerView.setAdapter(ledAdapter);
//
//      /*
//        sw1 = findViewById(R.id.sw1);
//        sw2 = findViewById(R.id.sw_2);
//        sw3 = findViewById(R.id.sw_3);
//        sw4 = findViewById(R.id.sw_4);*/
//        main_on = findViewById(R.id.main_on);
//        main_off = findViewById(R.id.main_off);
///*        card1 = findViewById(R.id.card1);
//        card2 = findViewById(R.id.card2);
//        card3 = findViewById(R.id.card3);
//        card4 = findViewById(R.id.card4);
//*/
//          led = new Led();
//    /*    changeLedStatus(card1, sw1,"BULB_1",led);
//        changeLedStatus(card2, sw2,"BULB_2",led);
//        changeLedStatus(card3, sw3,"BULB_3",led);
//        changeLedStatus(card4, sw4,"BULB_4",led);
//        turnAllBulbsON(main_on,sw1,"BULB_1",led);
//        turnAllBulbsON(main_on,sw2,"BULB_2",led);
//        turnAllBulbsON(main_on,sw3,"BULB_3",led);
//        turnAllBulbsON(main_on,sw4,"BULB_4",led);
//*/
//       /*     main_on.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                led.setStatus("ON");
//                //int led_status[]= LED_STATUS1, LED_STATUS2, LED_STATUS3, LED_STATUS4;
//
//                mDatabase = FirebaseDatabase.getInstance();
//              mDbRef.child("BULB_1/status/").setValue(led.getStatus());
//                    mDbRef.child("BULB_2/status/").setValue(led.getStatus());
//                    mDbRef.child("BULB_3/status/").setValue(led.getStatus());
//                    mDbRef.child("BULB_4/status/").setValue(led.getStatus());
//                    sw1.setBackgroundColor(Color.parseColor("blue"));
//                    sw2.setBackgroundColor(Color.parseColor("blue"));
//                    sw3.setBackgroundColor(Color.parseColor("blue"));
//                    sw4.setBackgroundColor(Color.parseColor("blue"));
//            }
//        });
//        main_off.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                led.setStatus("OFF");
//                mDatabase = FirebaseDatabase.getInstance();
//                mDbRef = mDatabase.getReference("IoTlab/SmartSwitch/BULBS/");
//  /*
//                    mDbRef.child("BULB_1/status/").setValue(led.getStatus());
//                    mDbRef.child("BULB_2/status/").setValue(led.getStatus());
//                    mDbRef.child("BULB_3/status/").setValue(led.getStatus());
//                    mDbRef.child("BULB_4/status/").setValue(led.getStatus());
//                    sw1.setBackgroundColor(Color.parseColor("white"));
//                    sw2.setBackgroundColor(Color.parseColor("white"));
//                    sw3.setBackgroundColor(Color.parseColor("white"));
//                    sw4.setBackgroundColor(Color.parseColor("white"));
//
//            }
//        });
//        */
//    }
///*
//    public void changeLedStatus( CardView card,ImageView bulb,String bulbIDREF , Led led){
//        card.setOnClickListener(v -> {
//            mDatabase = FirebaseDatabase.getInstance();
//            mDbRef = mDatabase.getReference("IoTlab/SmartSwitch/BULBS/");
//            led.setId("");
//            mDbRef.child(bulbIDREF+"/id/").setValue(led.getId());
//            if (cardClicked == 0) {
//                led.setStatus("ON");
//                mDbRef.child(bulbIDREF+"/status/").setValue(led.getStatus());
//                bulb.setBackgroundColor(Color.parseColor("blue"));
//                cardClicked = 1;
//            } else if (cardClicked == 1) {
//                led.setStatus("OFF");
//                mDbRef.child(bulbIDREF+"/status/").setValue(led.getStatus());
//                bulb.setBackgroundColor(Color.parseColor("white"));
//                cardClicked = 0;
//            }
//        });
//    }
//    public void turnAllBulbsON( CardView mainSwitch, ImageView card, String bulbIDREF , Led led ){
//        mainSwitch.setOnClickListener(v -> {
//            mDatabase = FirebaseDatabase.getInstance();
//            mDbRef = mDatabase.getReference("IoTlab/SmartSwitch/BULBS/");
//                  led.setStatus("ON");
//                mDbRef.child(bulbIDREF+"/status/").setValue(led.getStatus());
//                card.setBackgroundColor(Color.parseColor("blue"));
//        });
//    }
//    public void turnAllBulbsOFF( CardView mainSwitch, ImageView card, String bulbIDREF , Led led ){
//        mainSwitch.setOnClickListener(v -> {
//            mDatabase = FirebaseDatabase.getInstance();
//            mDbRef = mDatabase.getReference("IoTlab/SmartSwitch/BULBS/");
//            if (cardClicked == 0) {
//                led.setStatus("OFF");
//                mDbRef.child(bulbIDREF+"/status/").setValue(led.getStatus());
//                card.setBackgroundColor(Color.parseColor("white"));
//            }
//        });
//    }
//    */
//public ArrayList<Led> getAllBulbs(){
//    mDbRef.addValueEventListener(new ValueEventListener() {
//        @Override
//        public void onDataChange( DataSnapshot snapshot) {
//            Log.d(TAG, "onChildAdded:" + snapshot.getKey());
//            Led led = snapshot.getValue(Led.class);
//            System.out.println("=== > Data  : "+ led);
//            System.out.println("=== > Status: "+ led.getStatus());
//
//        }
//
//        @Override
//        public void onCancelled( DatabaseError error) {
//
//        }
//    });
