package com.db.lens.app.ui.notifications;

import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.db.lens.app.MainActivity;
import com.db.lens.app.databinding.FragmentNotificationsBinding;
import com.db.lens.app.model.HttpBin;
import com.db.lens.app.rest.util.RestCall;
import com.db.lens.app.rest.util.RestCallbackImpl;

import retrofit2.Response;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        RestCall<HttpBin> call = MainActivity.getTestService().getIp();
        call.enqueue(new RestCallbackImpl<HttpBin>(call, getActivity()) {

            @Override
            public void success(Response<HttpBin> response) {

                final HttpBin httpBin = response.body();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        textView.setText("Your IP: " + httpBin.getOrigin());
                    }
                });
            }
        });

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}