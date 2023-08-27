package com.ttknpdev.log;

import com.ttknpdev.action.ActionLogic;
import com.ttknpdev.service.MyHttpClient;
import org.apache.log4j.Logger;

public class Logging {
    public final static Logger actionLogic = Logger.getLogger(ActionLogic.class);
    public final static Logger myHttpClient = Logger.getLogger(MyHttpClient.class);
}
