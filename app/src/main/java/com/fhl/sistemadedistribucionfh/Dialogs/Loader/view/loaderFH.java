package com.fhl.sistemadedistribucionfh.Dialogs.Loader.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.fhl.sistemadedistribucionfh.R;
public class loaderFH extends DialogFragment {
    public static final String TAG = loaderFH.class.getSimpleName();
    private TextView txvTripsTitleLoader;
    private boolean hasTitle = false;
    private String title="";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_NoTitleBar);
        hasTitle();
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loader_fh, container, false);
        txvTripsTitleLoader = view.findViewById(R.id.txvTripsTitleLoader);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.customTransparentalfa);
        setCancelable(false);

        if (hasTitle) {
            txvTripsTitleLoader.setVisibility(View.VISIBLE);
            txvTripsTitleLoader.setText(title);
        } else {
            txvTripsTitleLoader.setVisibility(View.GONE);
        }

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getDialog() != null && getDialog().isShowing()) {
                    dismiss();
                }
            }
        }, 18000); // 18 seconds

        return view;
    }
    private void hasTitle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            hasTitle = bundle.getBoolean("HAS_TITLE");
            title=bundle.getString("title");
        }
    }

}
