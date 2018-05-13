package com.example.demo.guavaretry;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.Callable;

@Service
public class MyService {

    Retryer<Boolean> retryer;

    RestServices restServices;

    @Autowired
    public MyService(RestServices restServices, Retryer<Boolean> retryer) {
        this.restServices = restServices;
        this.retryer = retryer;
    }

    public Callable<Boolean> getCallable(int order) {
        return new Callable<Boolean>() {
            public Boolean call() throws Exception {
                System.out.println("hello: " + new Date());
                try {
                    restServices.callRestService(order);
                    return true;
                } catch (Exception exp) {
                    /*exp.printStackTrace();*/
                    return false;
                }
            }
        };
    }

    public boolean retry(int order) {

        try {
            return retryer.call(getCallable(order));
        } catch (RetryException e) {
            /*e.printStackTrace()*/;
            return false;
        } catch (Exception e) {
            /*e.printStackTrace();*/
            return false;
        }
    }

    public void runService() {

        for (int order = 0 ; order <10 ; order ++ ) {
            retry(order);
        }

    }
}
