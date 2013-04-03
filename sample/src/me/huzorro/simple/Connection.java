package me.huzorro.simple;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class Connection {
    /**
     * 
     */
    public Connection() {
        // TODO Auto-generated constructor stub
    }
    public void writeRequest(LoginRequest o) {
        Timer timer = new Timer();
        timer.schedule(new RerLogin(o), 10 * 1000);
        o.addTimer(timer);
    }
    
    class RerLogin extends TimerTask{
        private LoginRequest o;
        public RerLogin(LoginRequest o) {
            this.o = o;
        }
        /* (non-Javadoc)
         * @see java.util.TimerTask#run()
         */
        @Override
        public void run() {
            // TODO Auto-generated method stub            
            System.out.println(o);
            System.out.println("ok");
        }
        
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Connection c = new Connection();
        c.writeRequest(new LoginRequest());
        System.out.println("abc");
        int a = 123;
        int b = 456;
        a ^= b;
        b ^= a;
        a ^= b;
        System.out.println(a);
        System.out.println(b);
        
        int d = a | b;
        
        System.out.println(d);
        
        int e = d & 123;
        
        System.out.println(e);
        
        int f = 0x342388;
        
        System.out.println(f >> 16);
        
    }

}
