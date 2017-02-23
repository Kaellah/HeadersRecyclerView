package com.kaellah.headersrecyclerview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kaellah.headersrecyclerview.R;
import com.kaellah.headersrecyclerview.adapter.SimpleAdapter;
import com.kaellah.headersrecyclerview.model.SectionHeader;
import com.kaellah.headersrecyclerview.model.User;
import com.kaellah.headersrecyclerview.utility.Utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        {
            setContentView(R.layout.activity_main);
        }

        final SimpleAdapter adapter = new SimpleAdapter(getData());

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private List<SectionHeader> getData() {
        final List<SectionHeader> list = new ArrayList<>();
        final SecureRandom rnd = new SecureRandom();
        final Random random = new Random();

        for (int i = 0; i < Utils.randInt(4, 10, random); i++) {
            list.add(new SectionHeader(User.randomList(rnd, Utils.randInt(4, 10, random), random), Utils.randomString(rnd, Utils.randInt(4, 10, random)), i));
        }

        return list;
    }
}
