package com.example.demo.tread;

import java.util.*;
import java.util.concurrent.*;

public class Random1Test implements Callable<Integer> {

    private int   random;


    @Override
    public Integer call() throws Exception {
            Random ran=new Random();
            int i=ran.nextInt(random);
//        System.out.println(i);

        return i;
    }

    public Random1Test(int random, int min) {
        this.random = random;

    }



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future> list =new ArrayList<>();

        ExecutorService executorService= Executors.newCachedThreadPool();

        for (int i = 0; i <10 ; i++) {
            list.add( executorService.submit(new Random1Test(1000,i*100) ));
        }
        executorService.isShutdown();

        for(int i=0;i<list.size();i++){
            list.get(i);
            System.out.println(list.get(i).get());
        }

    }
}
