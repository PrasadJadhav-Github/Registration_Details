package com.example.registration_details.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.registration_details.Adapter.MembersDetailsAdapter;
import com.example.registration_details.DB_Helper.RegistrationMemberDatabaseHelper;
import com.example.registration_details.Model_Class.RegistrationMember;
import com.example.registration_details.databinding.ActivityRegistrationDetailsBinding;

import java.util.List;

public class RegistrationDetailsActivity extends AppCompatActivity {

    private ActivityRegistrationDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize ViewBinding
        binding = ActivityRegistrationDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Setup Toolbar
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Registration Details");
        }

        // Setup RecyclerView and Button Click
        setupRecyclerView();
        setupButtonClick();
    }

    private void setupButtonClick() {
        binding.btnAddNew.setOnClickListener(view -> {
            Intent intent = new Intent(RegistrationDetailsActivity.this, MemberRegistrationActivity.class);
            startActivity(intent);
        });
    }

    private void setupRecyclerView() {
        binding.rvMembers.setLayoutManager(new LinearLayoutManager(this));

        // Fetch data from the database
        RegistrationMemberDatabaseHelper dbHelper = new RegistrationMemberDatabaseHelper(this);
        List<RegistrationMember> memberList = dbHelper.getSpecificRegistrationMembers();

        // Set the total count of members
        binding.tvMembersCount.setText("Members Count: " + memberList.size());

        if (!memberList.isEmpty()) {
            MembersDetailsAdapter adapter = new MembersDetailsAdapter(memberList);
            binding.rvMembers.setAdapter(adapter);
            binding.tvNoData.setVisibility(View.GONE);
        } else {
            binding.tvNoData.setText("No members found.");
            binding.tvNoData.setVisibility(View.VISIBLE);
        }
    }
}
