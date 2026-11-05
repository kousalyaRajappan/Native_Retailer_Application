package com.topitup.retailer.ui.dashboard;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.topitup.retailer.R;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        int screenWidthDp = getResources().getConfiguration().screenWidthDp;
        int smallestWidthDp = getResources().getConfiguration().smallestScreenWidthDp;
        int orientation = getResources().getConfiguration().orientation;
        RecyclerView grid = view.findViewById(R.id.services_grid);
        grid.setLayoutManager(new GridLayoutManager(requireContext(), resolveColumnCount(
                screenWidthDp, smallestWidthDp, orientation)));
        grid.setAdapter(new ServiceAdapter(createServices(), NavHostFragment.findNavController(this)));
        adaptHeading(view, screenWidthDp);
        view.findViewById(R.id.logout_action).setOnClickListener(ignored ->
                Toast.makeText(requireContext(), "Logout coming soon", Toast.LENGTH_SHORT).show());
        view.findViewById(R.id.wallet_action).setOnClickListener(ignored ->
                Toast.makeText(requireContext(), "Wallet coming soon", Toast.LENGTH_SHORT).show());
    }

    private int resolveColumnCount(int screenWidthDp, int smallestWidthDp, int orientation) {
        if (smallestWidthDp >= 600) {
            return 3;
        }
        if (screenWidthDp >= 1100) {
            return 4;
        }
        if (orientation == Configuration.ORIENTATION_LANDSCAPE && screenWidthDp >= 680) {
            return 3;
        }
        return screenWidthDp >= 720 ? 3 : 2;
    }

    private void adaptHeading(View view, int screenWidthDp) {
        if (screenWidthDp >= 420) {
            return;
        }
        LinearLayout heading = view.findViewById(R.id.dashboard_heading);
        heading.setOrientation(LinearLayout.VERTICAL);
        LinearLayout titleGroup = (LinearLayout) heading.getChildAt(0);
        titleGroup.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        TextView count = view.findViewById(R.id.active_services);
        LinearLayout.LayoutParams countParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        countParams.gravity = Gravity.END;
        countParams.topMargin = dp(8);
        countParams.leftMargin = 0;
        count.setLayoutParams(countParams);
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }

    private List<ServiceItem> createServices() {
        return Arrays.asList(
                new ServiceItem("Transfer to Wallet", "Float Transfer", R.drawable.ic_transfer_wallet,
                        R.color.wallet_icon_bg, R.id.transfer_to_wallet_fragment),
                new ServiceItem("Transfer to Stores", "Inter-store Shift", R.drawable.ic_transfer_stores,
                        R.color.stores_icon_bg, R.id.transfer_to_stores_fragment),
                new ServiceItem("Top It Up Voucher", "Prepaid PINs", R.drawable.ic_topup_voucher,
                        R.color.voucher_icon_bg, R.id.top_it_up_voucher_fragment),
                new ServiceItem("Pay Suppliers", "Vendor Payout", R.drawable.ic_pay_suppliers,
                        R.color.suppliers_icon_bg, R.id.pay_suppliers_fragment),
                new ServiceItem("Wholesaler Payments", "Wholesale Ledger", R.drawable.ic_wholesaler_payments,
                        R.color.wholesaler_icon_bg, R.id.wholesaler_payments_fragment),
                new ServiceItem("Account Payments", "Banking", R.drawable.ic_account_payments,
                        R.color.account_payments_icon_bg, R.id.account_payments_fragment),
                new ServiceItem("Sales Transactions", "Terminal Ledger", R.drawable.ic_sales_transactions,
                        R.color.sales_icon_bg, R.id.sales_transactions_fragment),
                new ServiceItem("Commissions & Invoices", "Tax & Rebates", R.drawable.ic_commissions_invoices,
                        R.color.commissions_icon_bg, R.id.commissions_invoices_fragment),
                new ServiceItem("Financial Records", "Cleared Ledger", R.drawable.ic_financial_records,
                        R.color.financial_icon_bg, R.id.financial_records_fragment),
                new ServiceItem("Shift & Z-Reports", "Daily Cashup", R.drawable.ic_shift_reports,
                        R.color.reports_icon_bg, R.id.shift_z_reports_fragment),
                new ServiceItem("Deposit History", "Bank Slips", R.drawable.ic_deposit_history,
                        R.color.deposit_icon_bg, R.id.deposit_history_fragment),
                new ServiceItem("Money Transfers", "Remittance", R.drawable.ic_money_transfers,
                        R.color.transfers_icon_bg, R.id.money_transfers_fragment),
                new ServiceItem("POS Users", "Cashier Access", R.drawable.ic_pos_users,
                        R.color.pos_users_icon_bg, R.id.pos_users_fragment),
                new ServiceItem("Website Users", "Admin Roles", R.drawable.ic_website_users,
                        R.color.website_users_icon_bg, R.id.website_users_fragment),
                new ServiceItem("My Devices", "Terminals", R.drawable.ic_my_devices,
                        R.color.devices_icon_bg, R.id.my_devices_fragment),
                new ServiceItem("View OTP", "Security Token", R.drawable.ic_view_otp,
                        R.color.otp_icon_bg, R.id.view_otp_fragment),
                new ServiceItem("Supplier Orders", "Stock Dispatch", R.drawable.ic_supplier_orders,
                        R.color.orders_icon_bg, R.id.supplier_orders_fragment),
                new ServiceItem("Banking Details", "EFT Clearing Info", R.drawable.ic_banking_details,
                        R.color.banking_icon_bg, R.color.banking_card, R.color.on_primary,
                        R.color.banking_icon, R.id.banking_details_fragment)
        );
    }
}
