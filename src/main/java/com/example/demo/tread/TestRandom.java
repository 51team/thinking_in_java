package com.example.demo.tread;

import java.util.*;
import java.util.concurrent.*;

public class TestRandom implements Callable<Set> {

   // private int min;

    private int expectnnum;

    private Set <Integer> set=new HashSet<>();

    @Override
    public Set call() throws Exception {
        Random random=new Random();
        while (set.size()<expectnnum){
            int i=random.nextInt(expectnnum);
            set.add(i);
        }
        System.out.println("over"+set.size());
        return set;
    }

    public TestRandom() {
    }

    public TestRandom(int expectnnum) {
//        this.min = min;
        this.expectnnum = expectnnum;
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Long begin=System.currentTimeMillis();

        List<Future> results=new ArrayList<>();
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i=0;i<15;i++){
            results.add(executor.submit(new TestRandom(100000)));
        }
        executor.shutdown();
        while (true){
            if(executor.isShutdown()){
                System.out.println("end ===");
                break;
            }
            Thread.sleep(200);
        }
        for (int i = 0; i <15 ; i++) {

            System.out.println(results.get(i).get());
        }
        long end =System.currentTimeMillis();

        System.out.println("end... "+(end-begin));

    }

}
