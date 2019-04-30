package com.example.demo.tread;

public class TestTread  extends Thread {

    @Override
    public void run() {
        try {
        for (int i = 0; i <20 ; i++) {
            System.out.println(this.getName());

                sleep(200);

        }
        System.out.println("end ");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
          TestTread  t1=new TestTread();
          t1.start();
            TestTread  t2=new TestTread();
            t2.start();
    }
}

