package com.topitup.retailer.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.topitup.retailer.common.ImmersiveActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.topitup.retailer.MainActivity;
import com.topitup.retailer.R;

public class LoginActivity extends ImmersiveActivity {

    private View retailerPortal;
    private View supplierPortal;
    private TextView loginButtonLabel;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_login);

        retailerPortal = findViewById(R.id.retailer_portal);
        supplierPortal = findViewById(R.id.supplier_portal);
        loginButtonLabel = findViewById(R.id.login_button_label);

        retailerPortal.setOnClickListener(v -> selectPortal(false));
        supplierPortal.setOnClickListener(v -> selectPortal(true));
        selectPortal(false);

        findViewById(R.id.login_button).setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        findViewById(R.id.forgot_password).setOnClickListener(v ->
                Toast.makeText(this, "Password recovery coming soon", Toast.LENGTH_SHORT).show());
        findViewById(R.id.create_account).setOnClickListener(v ->
                Toast.makeText(this, "Account registration coming soon", Toast.LENGTH_SHORT).show());

        setupPasswordToggle();
    }

    private void selectPortal(boolean supplier) {
        retailerPortal.setSelected(!supplier);
        supplierPortal.setSelected(supplier);
        float elevation = getResources().getDisplayMetrics().density * 2f;
        retailerPortal.setElevation(supplier ? 0f : elevation);
        supplierPortal.setElevation(supplier ? elevation : 0f);
        loginButtonLabel.setText(supplier ? R.string.login_supplier : R.string.login);
    }

    private void setupPasswordToggle() {
        TextInputLayout passwordLayout = findViewById(R.id.password_layout);
        TextInputEditText password = findViewById(R.id.password);
        passwordLayout.setEndIconOnClickListener(v -> {
            boolean hidden = password.getTransformationMethod() instanceof PasswordTransformationMethod;
            int start = password.getSelectionStart();
            int end = password.getSelectionEnd();
            if (hidden) {
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                passwordLayout.setEndIconDrawable(R.drawable.ic_eye_off);
            } else {
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                passwordLayout.setEndIconDrawable(R.drawable.ic_eye);
            }
            int length = password.getText() == null ? 0 : password.getText().length();
            int selectionStart = Math.max(0, Math.min(start, length));
            int selectionEnd = Math.max(selectionStart, Math.min(end, length));
            password.setSelection(selectionStart, selectionEnd);
        });
    }
}
