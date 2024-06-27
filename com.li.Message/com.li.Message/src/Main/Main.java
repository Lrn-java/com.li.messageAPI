package Main;

import com.li.IPAddress.GetIP.LocalIPAddressFetcher;

import java.net.SocketException;

import static java.lang.System.out;

/**
 * @author Lrn
 */
public class Main {

    public static void main(String[] args) {
        try {
            out.println(new LocalIPAddressFetcher().getBIPAddress());
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

}