package com.eurofins.eesb.amqp.config;

import java.nio.file.Paths;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * JSSE configuration properties from Spring property sources (yaml, properties, ENV) synchronized to JVM system properties of the JSSE reference
 * implementation.
 * 
 * <i>NOTE: The system properties described in this section are not guaranteed to continue to have the same names and types (system or security) or
 * even to exist at all in future releases. They are also not guaranteed to be examined and used by any other JSSE implementations.</i>
 * 
 * @see https://docs.oracle.com/javase/8/docs/technotes/guides/security/jsse/JSSERefGuide.html
 */
@ConfigurationProperties(prefix = "javax.net.ssl")
@SuppressWarnings("static-method")
public class JSSEProperties {

  public String getKeyStore() {
    return System.getProperty("javax.net.ssl.keyStore");
  }

  public String getKeyStorePassword() {
    return System.getProperty("javax.net.ssl.keyStorePassword");
  }

  public String getTrustStore() {
    return System.getProperty("javax.net.ssl.trustStore");
  }

  public String getTrustStorePassword() {
    return System.getProperty("javax.net.ssl.trustStorePassword");
  }

  public void setKeyStore(String keyStorePath) {
    Paths.get(keyStorePath); // or throws InvalidPathException
    System.setProperty("javax.net.ssl.keyStore", keyStorePath);
  }

  public void setKeyStorePassword(String keyStorePassword) {
    System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassword);
  }

  public void setTrustStore(String trustStorePath) {
    Paths.get(trustStorePath); // or throws InvalidPathException
    System.setProperty("javax.net.ssl.trustStore", trustStorePath);
  }

  public void setTrustStorePassword(String trustStorePassword) {
    System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
  }

  @Override
  public String toString() {
    return "JSSEProperties [keyStore=" + getKeyStore() + ", trustStore=" + getTrustStore() + "]";
  }
}
