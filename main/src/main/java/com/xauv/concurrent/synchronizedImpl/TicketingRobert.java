package com.xauv.concurrent.synchronizedImpl;

public class TicketingRobert {
    public static volatile Integer count = 1000;

    public static void main(String[] args) {

        for(int i=0;i<10;i++) {
            new Thread(()->{
                new TicketingRobert().decrease();
            }).start();
        }
    }

    public void decrease() {
        synchronized (this) {
            while (TicketingRobert.count > 0) {
                TicketingRobert.count -= 1;
                System.out.println(TicketingRobert.count);
            }
        }
    }
}
