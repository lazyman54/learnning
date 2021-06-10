package com.ek.study.performance;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.storm.shade.com.google.common.base.Stopwatch;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/12/13
 */
public class GatewayPerformance {

    public GatewayPerformance() {
        this.httpClient = HttpClientBuilder.create().build();
    }

    private final HttpClient httpClient;

    public ExecutorService executorService;

    public HttpUriRequest request;

    public int successCount;
    public int failCount;
    private Stopwatch stopwatch;
    private volatile boolean isWathInit = false;
    ObjectMapper objectMapper = new ObjectMapper();


    private final ThreadFactory defaultFactory = new ThreadFactory() {

        private final AtomicInteger ato = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            SecurityManager s = System.getSecurityManager();
            ThreadGroup group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            Thread thread = new Thread(group, r, "performance-" + ato.getAndIncrement());
            thread.setDaemon(true);
            return thread;
        }
    };

    private final AtomicInteger exeCount = new AtomicInteger(0);


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        GatewayPerformance gatewayPerformance = new GatewayPerformance();
        gatewayPerformance.request = RequestBuilder.get("http://127.0.0.1:8080/demo/validata1?a=1&b=2&c=2&d=2&e=2&f=2&g=2&h=2").build();
        int maxRe = 50000;
        gatewayPerformance.doTest(100, maxRe);
        System.out.println("一共执行了" + maxRe + "个请求，其中成功了：" + gatewayPerformance.successCount + "个，失败了：" + gatewayPerformance.failCount + "个");

    }

    public void doTest(int concurrentNum, int tps) throws InterruptedException, ExecutionException {
        executorService = new ThreadPoolExecutor(concurrentNum, concurrentNum << 1, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), defaultFactory);

        CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);

        for (int i = 0; i < tps; i++) {
            completionService.submit(() -> this.doRequest(request));
        }

        for (int i = 0; i < tps; i++) {
            String ss = completionService.take().get();

            if (isSuccess(ss)) {
                successCount++;
            } else {
                failCount++;
            }

        }
        stopwatch.stop();
        System.out.println("cost time: [" + stopwatch + "]");

    }

    private boolean isSuccess(String response) {
        Map map;
        try {
            map = objectMapper.readValue(response, Map.class);
        } catch (IOException e) {
            return false;
        }
        return (Boolean) map.get("success");

    }


    private String doRequest(HttpUriRequest request) {
        if (!isWathInit) {
            synchronized (this) {
                if (!isWathInit) {
                    isWathInit = true;
                    System.out.println("init");
                    stopwatch = Stopwatch.createStarted();
                }
            }
        }
        String content;
        try {
            HttpResponse response = httpClient.execute(request);

            System.out.println("线程：" + Thread.currentThread().getName() + "*****执行完第：" + exeCount.incrementAndGet() + "个请求了");
            content = EntityUtils.toString(response.getEntity());
            EntityUtils.consume(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return content;
    }

}
