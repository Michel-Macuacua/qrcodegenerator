package com.michelconsulting.qrcodegenerator;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class MainActivity extends AppCompatActivity {

    //Initialize variable
    EditText et_input;
    Button bt_generate;
    ImageView iv_output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variable
        et_input = findViewById(R.id.et_input);
        bt_generate = findViewById(R.id.bt_generate);
        iv_output = findViewById(R.id.iv_output);

        bt_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get input value from edit text
                String sText = et_input.getText().toString().trim();
                //Initialize multi format writer
                MultiFormatWriter writer = new MultiFormatWriter();
                //Initialize bit matrix
                try {
                    BitMatrix matrix = writer.encode(sText, BarcodeFormat.QR_CODE, 350, 350);
                    //Initialize barcode encoder
                    BarcodeEncoder encoder = new BarcodeEncoder();
                    //Initialize bitmap
                    Bitmap bitmap = encoder.createBitmap(matrix);
                    //Set bitmap on image view
                    iv_output.setImageBitmap(bitmap);
                    //Initialize input manager
                    InputMethodManager manager = (InputMethodManager) getSystemService(
                            Context.INPUT_METHOD_SERVICE
                    );
                    //Hide soft keyboard
                    manager.hideSoftInputFromWindow(et_input.getApplicationWindowToken(), 0
                    );
                } catch (WriterException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}