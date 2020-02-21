package com.example.systemprogramminglab6;

import android.graphics.Color;
import android.os.Bundle;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class Introactivity extends TutorialActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(new Step.Builder().setTitle("Than you for downloadeing Imageviewer")
                .setContent("This is content text")
                .setBackgroundColor(Color.parseColor("#68DD0D"))
                .setDrawable(R.drawable.icon)
                .setSummary("With this app you will scroll pictures with ease")
                .build());

        addFragment(new Step.Builder().setTitle("Bring happiness to your life")
                .setContent("This is content text")
                .setBackgroundColor(Color.parseColor("#68DD0D"))
                .setDrawable(R.drawable.icon)
                .setSummary("Use the search function to find pictures")
                .build());


        addFragment(new Step.Builder().setTitle("Do your chores")
                .setContent("This is content text")
                .setBackgroundColor(Color.parseColor("#68DD0D"))
                .setDrawable(R.drawable.icon)
                .setSummary("With this app you will be succcesfull in school")
                .build());

    }

    public void currentFragmentPosition(int position) {

    }
}
