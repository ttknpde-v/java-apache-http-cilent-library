package com.ttknpdev.action;

import com.ttknpdev.log.Logging;
import com.ttknpdev.service.MyHttpClient;

public class ActionLogic  {
    public static void main(String[] args) {
        Logging.actionLogic.assertLog(false ,"test alert logging");
        MyHttpClient myHttpClient = new MyHttpClient();
        // myHttpClient.testHttpGet();
        // myHttpClient.testReads();
        // myHttpClient.testCreate();
        // myHttpClient.testUpdate();
        myHttpClient.testDelete();
    }
}
