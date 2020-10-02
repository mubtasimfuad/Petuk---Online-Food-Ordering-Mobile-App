package com.fuad.petuk.database;
import com.fuad.petuk.R;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fuad.petuk.Register;
import com.fuad.petuk.ViewHolder.CartAdapter;
import com.fuad.petuk.model.Order;
import com.fuad.petuk.model.Request;
import com.fuad.petuk.model.User;
import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.app.PendingIntent.getActivity;


public class Cart extends Register  {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference requests;
    TextView txtTotalPrice;


    Button btnPlace;
    List<Order> cart = new ArrayList<>();
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //Firebase
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        //init
        recyclerView = (RecyclerView) findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        txtTotalPrice = (TextView) findViewById(R.id.total);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        btnPlace = (Button) findViewById(R.id.btnPlaceOrder);
        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();

            }
        });
        loadListFood();


    }


    private void showAlertDialog() {

        LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.orderalertdialog, null);
//text_entry is an Layout XML file containing two text field to display in alert dialog
        final EditText name = (EditText) textEntryView.findViewById(R.id.NameEdit);
        final EditText address = (EditText) textEntryView.findViewById(R.id.AddressEdit);
        final EditText phone = (EditText) textEntryView.findViewById(R.id.PhoneEdit);

        name.setText("", TextView.BufferType.EDITABLE);
        address.setText("", TextView.BufferType.EDITABLE);
        phone.setText("", TextView.BufferType.EDITABLE);

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setIcon(R.drawable.ic_cart)
                .setTitle("Delivery Details:")
                .setView(textEntryView)
                .setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                //Create new Request
                                Request request = new Request(

                                        name.getText().toString(),
                                        address.getText().toString(),
                                        phone.getText().toString(),
                                        txtTotalPrice.getText().toString(),
                                        cart
                                );
                                requests.child(String.valueOf(System.currentTimeMillis())).setValue(request);
                                //Delete Cart
                                new Database(getBaseContext()).CleanCart();
                                Toast.makeText(Cart.this, "Thank You!!, For your Order", Toast.LENGTH_SHORT).show();
                                finish();

                                /* User clicked OK so do some stuff */
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                            }
                        });
        alert.show();
    }

    private void loadListFood() {
        cart = new Database(this).getCarts();
        adapter = new CartAdapter(cart, this);
        recyclerView.setAdapter(adapter);
        //calculate total price
        int total = 0;
        for (Order order : cart)
            total += (Integer.parseInt(order.getPrice())) * (Integer.parseInt(order.getQuantity()));

        txtTotalPrice.setText((total) + "৳");


    }



    }



