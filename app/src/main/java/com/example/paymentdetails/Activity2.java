package com.example.paymentdetails;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.paymentdetails.database.DataBaseHelper;

import java.util.List;

public class Activity2 extends AppCompatActivity {

    EditText etname, etcountry,etnumofparticipants,etcontactnumber,etemail,etpriceandpaymenttype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        etname = findViewById(R.id.etname);
        etcountry = findViewById(R.id.etcountry);
        etnumofparticipants = findViewById(R.id.etnumofparticipants);
        etcontactnumber = findViewById(R.id.etcontactnumber);
        etemail = findViewById(R.id.etemail);
        etpriceandpaymenttype = findViewById(R.id.etpriceandpaymenttype);
    }

public void saveUser(View view){
        String name = etname.getText().toString();
        String country = etcountry.getText().toString();
        String number_of_participants = etnumofparticipants.getText().toString();
        String contact_number = etcontactnumber.getText().toString();
        String email = etemail.getText().toString();
        String price_and_paymenttype = etpriceandpaymenttype.getText().toString();
    DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        if(name.isEmpty()||country.isEmpty()||number_of_participants.isEmpty()||contact_number.isEmpty()||email.isEmpty()||price_and_paymenttype.isEmpty()){
            Toast.makeText(this, "Enter values", Toast.LENGTH_SHORT).show();
        }else{
            long inserted = dataBaseHelper.addInfo(name,country,number_of_participants,contact_number,email,price_and_paymenttype);

            if(inserted>0){
                Toast.makeText(this,"Data inserted successfully",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show();
            }

        }
}
public void viewAll(View view){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

    List info = dataBaseHelper.readAll();

    String[] infoArray = (String[]) info.toArray(new String[0]);

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Payment Details");

    builder.setItems(infoArray, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

            String name = infoArray[i].split(":")[0];

            etname.setText(name);


        }
    });
    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

        }
    });

    AlertDialog dialog = builder.create();
    dialog.show();

}

   public void deleteUser(View view){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        String name = etname.getText().toString();

        if(name.isEmpty()){
            Toast.makeText(this,"Select a user",Toast.LENGTH_SHORT).show();
        }else{
            dataBaseHelper.deleteInfo(name);
            Toast.makeText(this,name+"User is deleted",Toast.LENGTH_SHORT).show();
        }
   }
}