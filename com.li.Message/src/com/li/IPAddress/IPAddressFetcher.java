package com.li.IPAddress;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

// 定义一个接口，声明获取IPv4地址的方法
public interface IPAddressFetcher {
    String getIPv4Address() throws SocketException;
}