package com.example.hiringtask.ui.main;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public abstract class BaseBindingActivity<B extends ViewBinding> extends AppCompatActivity {

    public B binding;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewBinding();

        setContentView(binding.getRoot());


    }


    /**
     * this method should be override by every childs
     *
     * @return Binding ViewBinding reference
     */
    protected abstract B getViewBinding();


    public void showProgressDialog() {
        progressDialog = ProgressDialog.show(this, "", "Please wait...");
    }

    public void hideProgressDialog() {
        if (progressDialog == null) {
            return;
        }
        progressDialog.dismiss();
    }

}
