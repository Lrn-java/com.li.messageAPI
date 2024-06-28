package com.li.IPAddress;


import java.net.InetAddress;
import java.net.SocketException;


// 定义一个接口，声明获取IPv4地址的方法
public interface IPAddressFetcher {
    //获取IPV4地址
    String getIPv4Address() throws SocketException;

    //获取IPV6地址
    String getIPv6Address() throws SocketException;

    //获取本机B类地址
    InetAddress getBIPAddress() throws SocketException;

    //判断是否为B类地址
    boolean isBClassIP(String ip);
}