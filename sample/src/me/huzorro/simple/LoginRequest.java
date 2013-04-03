package me.huzorro.simple;

import java.util.Timer;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class LoginRequest {
    private Timer timer;
    /**
     * 
     */
    public LoginRequest() {
        // TODO Auto-generated constructor stub
    }
    public void addTimer(Timer timer) {
        this.timer = timer;
        this.timer.cancel();
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
