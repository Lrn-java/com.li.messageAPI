package com.li.IPAddress.GetIP;

import com.li.IPAddress.IPAddressFetcher;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

/**
 * 使用getIPveAddress()方法查找本机IP地址，A类
 * 使用时需要抛出异常SocketException
 *
 * @author Lrn
 */
public class LocalIPAddressFetcher implements IPAddressFetcher {

    /**
     * 重写getIPv4Address()方法
     *
     * @return 返回异常
     * @throws SocketException
     */
    @Override
    public String getIPv4Address() throws SocketException {
        try {
            // 获取所有网络接口的枚举
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface networkInterface : Collections.list(networkInterfaces)) {
                // 检查网络接口是否是上行接口，即连接到外部网络的接口
                if (networkInterface.isUp() && !networkInterface.isLoopback()) {
                    // 获取网络接口的所有IP地址
                    Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                    for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                        // 检查是否是IPv4地址
                        if (inetAddress instanceof java.net.Inet4Address) {
                            return inetAddress.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            throw new SocketException("Failed to get network interfaces");
        }
        // 如果没有找到IPv4地址，返回null或抛出异常
        return null;
    }

    // 检查是否为B类地址
    public boolean isBClassIP(String ip) {
        // B类IP地址的第一位数字在128到191之间
        String firstOctet = ip.split("\\.")[0];
        int firstPart = Integer.parseInt(firstOctet);
        return firstPart >= 128 && firstPart < 192;
    }

    /**
     * 获取B类IP地址
     *
     * @return
     * @throws SocketException
     */
    @Override
    public String getBIPAddress() throws SocketException {
        // 获取所有网络接口
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            // 获取网络接口绑定的所有IP地址
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                // 检查是否是IPv4地址
                if (inetAddress instanceof java.net.Inet4Address) {
                    String ip = inetAddress.getHostAddress();
                    // 检查是否是B类IP地址
                    if (isBClassIP(ip)) {
                        return ip;
                    }
                }
            }
        }
        return "No B class IP address found.";
    }

}