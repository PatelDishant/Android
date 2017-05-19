package dev.dishant.exekutivefades;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Toast;

public class mainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        HorizontalScrollView scrollView = (HorizontalScrollView) findViewById(R.id.scrollView);
        LinearLayout topLayout = new LinearLayout(this);
        topLayout.setOrientation(LinearLayout.HORIZONTAL);

        for (int imageCount = 0; imageCount <= 10; imageCount++)
        {
            final ImageView haircuts = new ImageView(this);
            haircuts.setAdjustViewBounds(true);
            haircuts.setTag(imageCount);
            haircuts.setImageResource(R.drawable.display);
            haircuts.setPadding(20, 0, 20, 0);

            topLayout.addView(haircuts);

            haircuts.setOnClickListener(new OnClickListener()
            {

                @Override
                public void onClick(View v)
                {
                    // TODO Auto-generated method stub
                    Intent barberPage = new Intent(mainScreen.this, BarberPage.class);
                    startActivity(barberPage);
                }
            });
        }
        scrollView.addView(topLayout);
    }
}
