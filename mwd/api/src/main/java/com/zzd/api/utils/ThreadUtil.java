package com.zzd.api.utils;

import java.util.concurrent.*;

/**
 * @author
 * @date
 * @describe
 */
public class ThreadUtil{
    private static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(16, 32,
            15, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(1024),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static Executor getExecutor(){
        return threadPool;
    }
    public static void addTask(Runnable task) {
        threadPool.execute(task);
    }

    public static <T> Future<T> addTask(Callable<T> task) {
        return threadPool.submit(task);
    }
}
