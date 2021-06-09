package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database.dbCauHoi;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.sql.Blob;

public class MainActivity extends AppCompatActivity {
    
    com.example.database.dbCauHoi dbCauHoi;
    TextView cauHoi, cauHoiA, cauHoiB, cauHoiC, cauHoiD;
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cauHoi = (TextView) findViewById(R.id.CauHoi);
        cauHoiA = (TextView) findViewById(R.id.a);
        cauHoiB = (TextView) findViewById(R.id.b);
        cauHoiC = (TextView) findViewById(R.id.c);
        cauHoiD = (TextView) findViewById(R.id.d);
        imgView = (ImageView) findViewById(R.id.imageView);


        // Khoi tao database
        dbCauHoi = new dbCauHoi(this, "CauHoiDataBase.sqlite", null, 1);
        
        // Tao bang table book
        dbCauHoi.QueryData("CREATE TABLE IF NOT EXISTS CauHoi (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " CauHoi VARCHAR(200)," +
                " CauHoiA VARCHAR(200)," +
                " CauHoiB VARCHAR(200)," +
                " CauHoiC VARCHAR(200)," +
                " CauHoiD VARCHAR(200)," +
                " Image VARCHAR(100)," +
                " DapAn VARCHAR(10))");
        // insert
         dbCauHoi.QueryData("INSERT INTO CauHoi (ID, CauHoi, CauHoiA, CauHoiB, CauHoiC, CauHoiD, Image, DapAn)" +
                " VALUES " +
                "(null, 'Câu hỏi?', 'Câu hỏi A', 'Câu hỏi B', 'Câu hỏi C', 'Câu hỏi D', 'bienbao1', 'A')");


        // select data
        Cursor cursor = dbCauHoi.GetData("SELECT * FROM CauHoi");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String CauHoi = cursor.getString(1);
            String CauHoiA = cursor.getString(2);
            String CauHoiB = cursor.getString(3);
            String CauHoiC = cursor.getString(4);
            String CauHoiD = cursor.getString(5);
            String img = cursor.getString(6);
            String DapAn = cursor.getString(7);

            cauHoi.setText(CauHoi);
            cauHoiA.setText(CauHoiA);
            cauHoiB.setText(CauHoiB);
            cauHoiC.setText(CauHoiC);
            cauHoiD.setText(CauHoiD);
            int resID = getResId(img, R.drawable.class);
            imgView.setImageResource(resID);
            Log.e("", String.valueOf(resID));
        }
    }

    // Get string
    public static int getResId(String resName, Class<?> c) {
        try {
            Log.e("", "thành công");
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}