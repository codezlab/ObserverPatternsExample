package com.codezlab.observerpatterndemo;

import android.os.Handler;

import java.util.Observable;

/**
 * Created by coderzlab on 8/9/17.
 * http://codezlab.com
 */

public class UserRepository extends Observable {

    private String userName;
    private String userMobileNumber;

    private static Object mLock = new Object();

    private static UserRepository USER_REPO_INSTANCE = null;

    private UserRepository() {
    }

    /* get Singleton instance of UserRepository  */
    public static UserRepository getInstance() {
        synchronized (mLock) {
            if (USER_REPO_INSTANCE == null)
                USER_REPO_INSTANCE = new UserRepository();
            return USER_REPO_INSTANCE;
        }
    }



    // Simulate network
    public void loadUserDataFromRemote() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setUserData("Matrix Devz", "00-000-00000");
            }
        }, 10000);
    }

    public void setUserData(String userName, String userMobileNumber) {
        this.userName = userName;
        this.userMobileNumber = userMobileNumber;
        setChanged();
        notifyObservers();
    }

    public String getUserName() {
        return userName;
    }

    public String getUserMobileNumber() {
        return userMobileNumber;
    }
}
