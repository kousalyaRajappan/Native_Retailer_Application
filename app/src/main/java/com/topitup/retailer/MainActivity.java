package com.topitup.retailer;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.topitup.retailer.common.ImmersiveActivity;

public class MainActivity extends ImmersiveActivity {
    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_main);
        NavHostFragment host = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavController nav = host.getNavController();
        findViewById(R.id.tab_home).setOnClickListener(view -> nav.navigate(R.id.home_fragment));
        findViewById(R.id.tab_wholesale_pay).setOnClickListener(
                view -> nav.navigate(R.id.wholesale_pay_fragment));
        findViewById(R.id.tab_services).setOnClickListener(
                view -> nav.navigate(R.id.supplier_orders_fragment));
        findViewById(R.id.tab_balance).setOnClickListener(view -> nav.navigate(R.id.balance_fragment));
        findViewById(R.id.tab_account).setOnClickListener(view -> nav.navigate(R.id.account_fragment));
        nav.addOnDestinationChangedListener((controller, destination, args) ->
                updateSelection(destination.getId()));
    }

    private void updateSelection(int destinationId) {
        setTabSelected(R.id.tab_home, R.id.icon_home, R.id.label_home,
                destinationId == R.id.home_fragment);
        setTabSelected(R.id.tab_wholesale_pay, R.id.icon_wholesale_pay, R.id.label_wholesale_pay,
                destinationId == R.id.wholesale_pay_fragment);
        setTabSelected(R.id.tab_balance, R.id.icon_balance, R.id.label_balance,
                destinationId == R.id.balance_fragment);
        setTabSelected(R.id.tab_account, R.id.icon_account, R.id.label_account,
                destinationId == R.id.account_fragment);
    }

    private void setTabSelected(int tabId, int iconId, int labelId, boolean selected) {
        int color = ContextCompat.getColor(this,
                selected ? R.color.dashboard_primary : R.color.dashboard_secondary);
        View tab = findViewById(tabId);
        ImageView icon = findViewById(iconId);
        TextView label = findViewById(labelId);
        tab.setAlpha(1f);
        icon.setColorFilter(color);
        label.setTextColor(color);
    }
}
