package org.cyclopsgroup.jmxterm.cc;

import org.apache.commons.lang3.Validate;
import org.cyclopsgroup.jmxterm.Connection;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;

/**
 * Identifies a JMX connection
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
class ConnectionImpl implements Connection {
  private final JMXConnector connector;

  private final JMXServiceURL url;

  /**
   * @param connector JMX connector
   * @param url JMX service URL object
   */
  ConnectionImpl(JMXConnector connector, JMXServiceURL url) {
    Validate.notNull(connector, "JMX connector can't be NULL");
    Validate.notNull(url, "JMX service URL can't be NULL");
    this.connector = connector;
    this.url = url;
  }

  /**
   * Close current connection
   *
   * @throws IOException Communication error
   */
  void close() throws IOException {
    connector.close();
  }

  /**
   * @return JMX connector
   */
  public final JMXConnector getConnector() {
    return connector;
  }

  @Override
  public String getConnectorId() throws IOException {
    return connector.getConnectionId();
  }

  @Override
  public MBeanServerConnection getServerConnection() throws IOException {
    return connector.getMBeanServerConnection();
  }

  @Override
  public final JMXServiceURL getUrl() {
    return url;
  }
}
