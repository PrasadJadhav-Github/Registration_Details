package com.example.registration_details.Activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.registration_details.DB_Helper.RegistrationMemberDatabaseHelper;
import com.example.registration_details.R;
import com.example.registration_details.databinding.ActivityMemberRegistrationBinding;

import java.util.Calendar;

public class MemberRegistrationActivity extends AppCompatActivity {

    private ActivityMemberRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMemberRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onClickListener();
        setUpRadioGroupListener();
        setUpDatePickers();
    }

    private void onClickListener() {
        binding.btnSubmit.setOnClickListener(view -> handleButtonClick());
    }

    private void setUpRadioGroupListener() {
        binding.roleGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioSingle) {
                binding.etMarriageDate.setVisibility(View.GONE);
                binding.etMarriageDate.setEnabled(false);
                binding.etMarriageDate.clearFocus();
                binding.etMarriageDate.getText().clear();
            } else {
                binding.etMarriageDate.setVisibility(View.VISIBLE);
                binding.etMarriageDate.setEnabled(true);
            }
        });
    }

    private void setUpDatePickers() {
        binding.etDob.setOnClickListener(view -> openDatePickerDialog(binding.etDob));
        binding.etJoiningDate.setOnClickListener(view -> openDatePickerDialog(binding.etJoiningDate));
        binding.etMarriageDate.setOnClickListener(view -> openDatePickerDialog(binding.etMarriageDate));

        binding.etDob.setInputType(0);
        binding.etJoiningDate.setInputType(0);
        binding.etMarriageDate.setInputType(0);
    }

    private void openDatePickerDialog(EditText editText) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
            String formattedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
            editText.setText(formattedDate);
        }, year, month, dayOfMonth);

        datePickerDialog.show();
    }


    //button to handle submit button click
    private void handleButtonClick() {
        String mobileNumber = binding.etMobileNumber.getText().toString();
        String name = binding.etName.getText().toString();
        String role = "";
        if (binding.roleGroup.getCheckedRadioButtonId() == R.id.role_secretary) {
            role = "Secretary";
        } else if (binding.roleGroup.getCheckedRadioButtonId() == R.id.role_member) {
            role = "Member";
        }

        double subscriptionFee = parseDouble(binding.etSubscriptionFee.getText().toString());
        double depositAmount = parseDouble(binding.etDepositAmount.getText().toString());
        double loanAmount = parseDouble(binding.etLoanAmount.getText().toString());
        String gender = binding.etGender.getText().toString();
        String dob = binding.etDob.getText().toString();
        String joiningDate = binding.etJoiningDate.getText().toString();
        String maritalStatus = binding.etMaritalStatus.getText().toString();
        String marriageDate = null;
        if (binding.etMarriageDate.getVisibility() == View.VISIBLE && !binding.etMarriageDate.getText().toString().isEmpty()) {
            marriageDate = binding.etMarriageDate.getText().toString();
        }
        String caste = binding.etCaste.getText().toString();
        String religion = binding.etReligion.getText().toString();
        String category = binding.etCategory.getText().toString();
        String aadharNumber = binding.etAadharNumber.getText().toString();

        if (name.isEmpty() || mobileNumber.isEmpty() || role.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        RegistrationMemberDatabaseHelper dbHelper = new RegistrationMemberDatabaseHelper(this);
        long result = dbHelper.insertRegistrationMemberData(
                mobileNumber, name, role, subscriptionFee, depositAmount, loanAmount,
                gender, dob, joiningDate, maritalStatus, marriageDate, caste, religion, category, aadharNumber
        );

        if (result != -1L) {
            Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error occurred during registration. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    //This function is used to convert a String into a double
    private double parseDouble(String value) {
        return value.isEmpty() ? 0.0 : Double.parseDouble(value);
    }

}
