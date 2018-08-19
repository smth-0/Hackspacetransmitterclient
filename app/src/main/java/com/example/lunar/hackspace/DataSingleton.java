package com.example.lunar.hackspace;

class DataSingleton {
    private static final DataSingleton ourInstance = new DataSingleton();
    private Boolean isLoggedIn = Boolean.TRUE;

    static DataSingleton getInstance() {
        return ourInstance;
    }

    Boolean getIsLoggedIn(){
        return isLoggedIn;
    }


    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    private DataSingleton() {
    }
}
