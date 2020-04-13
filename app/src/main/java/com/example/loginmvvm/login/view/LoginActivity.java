package com.example.loginmvvm.login.view;

import android.os.Bundle;
import android.widget.Toast;

import com.example.loginmvvm.R;
import com.example.loginmvvm.databinding.ActivityLoginBinding;
import com.example.loginmvvm.login.model.LoginFields;
import com.example.loginmvvm.login.viewmodel.LoginViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupBindings(savedInstanceState);
    }

    private void setupBindings(Bundle savedInstanceState) {
        ActivityLoginBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        if (savedInstanceState == null) {
            viewModel.init();
        }
        activityBinding.setModel(viewModel);
        setupButtonClick();
    }

    private void setupButtonClick() {
        viewModel.getLoginFields().observe(this, new Observer<LoginFields>() {
            @Override
            public void onChanged(LoginFields loginModel) {
                Toast.makeText(LoginActivity.this,
                        "Email " + loginModel.getEmail() + ", Password " + loginModel.getPassword(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
