package com.example.registration_details.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.registration_details.Model_Class.RegistrationMember;
import com.example.registration_details.R;

import java.util.List;

public class MembersDetailsAdapter extends RecyclerView.Adapter<MembersDetailsAdapter.MemberViewHolder> {

    private List<RegistrationMember> memberList;

    public MembersDetailsAdapter(List<RegistrationMember> memberList) {
        this.memberList = memberList;
    }

    public static class MemberViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvMobile;
        TextView tvRole;
        TextView tvSubscriptionFee;
        TextView tvDepositAmount;
        TextView tvLoanAmount;

        public MemberViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_member_name);
            tvMobile = itemView.findViewById(R.id.tv_mobile_no);
            tvRole = itemView.findViewById(R.id.tv_member_role);
            tvSubscriptionFee = itemView.findViewById(R.id.tv_subscription_amt);
            tvDepositAmount = itemView.findViewById(R.id.editText_loan_amount);
            tvLoanAmount = itemView.findViewById(R.id.editText_deposit_amount);
        }
    }

    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_members_details, parent, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MemberViewHolder holder, int position) {
        RegistrationMember member = memberList.get(position);

        holder.tvName.setText("Name: " + member.getName());
        holder.tvMobile.setText("Mobile: " + member.getMobileNumber());

        String role = member.getRole();
        holder.tvRole.setText("Role: " + role);

        holder.tvSubscriptionFee.setText("Subscription Fee: " + member.getSubscriptionFee());
        holder.tvDepositAmount.setText("Deposit  " + member.getDepositAmount());
        holder.tvLoanAmount.setText("Loan  " + member.getLoanAmount());
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }
}
