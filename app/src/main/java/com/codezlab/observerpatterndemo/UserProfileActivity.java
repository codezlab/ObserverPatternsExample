package com.codezlab.observerpatterndemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class UserProfileActivity extends AppCompatActivity implements Observer {

    private TextView userNameTV;
    private TextView userMobileTV;

    private UserRepository userRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userNameTV=(TextView) findViewById(R.id.userNameTV);
        userMobileTV=(TextView) findViewById(R.id.userMobileTV);

        userRepository = UserRepository.getInstance();
        userRepository.addObserver(this);

        userRepository.loadUserDataFromRemote();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        /* remove observer at this point */
        if(userRepository!=null)
            userRepository.deleteObserver(this);

    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable!=null && observable instanceof UserRepository) {

            /* Typecast to UserRepository */
            UserRepository userRepository=(UserRepository)observable;

            /* update UI by using getters methods */
            userMobileTV.setText(userRepository.getUserName());
            userMobileTV.setText(userRepository.getUserMobileNumber());

        }

    }

}
