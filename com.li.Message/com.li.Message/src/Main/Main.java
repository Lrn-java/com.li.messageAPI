package Main;

import com.li.IPAddress.IpUtil.LocalIPAddressFetcher;

import java.net.SocketException;

import static com.li.EncryptionTools.Sha1Util.encryptToSha1;
import static com.li.EncryptionTools.Sha1Util.verifySha1;
import static java.lang.System.out;

/**
 * 用来测试写好的方法
 * @author Lrn
 */
public class Main {

    public static void main(String[] args) {

        out.println(new LocalIPAddressFetcher().getIPv6Address());
        try {
            out.println(new LocalIPAddressFetcher().getIPv4Address());
            out.println(new LocalIPAddressFetcher().getBIPAddress());
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        String yuan = "Hello World!";
        String sha1Hash = encryptToSha1(yuan);
        out.println(sha1Hash);

        String has1 = "2ef7bde608ce5404e97d5f042f95f89f1c232871";

        boolean isEqulse = verifySha1(has1, yuan);

        if (isEqulse) {
            out.println("true");
        } else {
            out.println("false");
        }


    }


}