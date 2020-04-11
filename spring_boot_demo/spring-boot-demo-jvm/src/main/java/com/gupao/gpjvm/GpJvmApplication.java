package com.gupao.gpjvm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GpJvmApplication {
    public static void main(String[] args) {
        SpringApplication.run(GpJvmApplication.class, args);
    }
}

//-Xmx100M  -Xms100M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=heap.hprof
//-XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -Xloggc:gc.log
//手动打印内存信息  jmap -dump:format=b,file=heap.hprof PID
