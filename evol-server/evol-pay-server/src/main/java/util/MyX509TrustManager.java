package util;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 自定义信任管理器
 * @author fjing
 * @date  2017年7月2日
 *
 */
public class MyX509TrustManager implements X509TrustManager {

    public void checkClientTrusted(X509Certificate[] arg0, String arg1)
            throws CertificateException {

    }

    public void checkServerTrusted(X509Certificate[] arg0, String arg1)
            throws CertificateException {

    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

}
