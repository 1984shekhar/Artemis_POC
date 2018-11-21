/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mycompany.interceptor;

import org.apache.activemq.artemis.api.core.ActiveMQException;
import org.apache.activemq.artemis.api.core.Interceptor;
import org.apache.activemq.artemis.api.core.Message;
import org.apache.activemq.artemis.core.protocol.core.Packet;
import org.apache.activemq.artemis.core.protocol.core.impl.wireformat.SessionReceiveMessage;
import org.apache.activemq.artemis.core.protocol.core.impl.wireformat.SessionSendMessage;
import org.apache.activemq.artemis.spi.core.protocol.RemotingConnection;

/**
 * A simple Interceptor implementation
 */
public class SimpleInterceptor implements Interceptor {

   public boolean intercept(final Packet packet, final RemotingConnection connection) throws ActiveMQException {
      
      if (packet instanceof SessionSendMessage) {
    	  System.out.println("SimpleInterceptor gets called!.... Packet: " + packet.getClass().getName() + "RemotingConnection: " + connection.getRemoteAddress() );
         SessionSendMessage realPacket = (SessionSendMessage) packet;
         Message msg = realPacket.getMessage();
         if((msg.getTimestamp()>0) && msg.getUserID()!=null)
         System.out.println("Msg: "+msg.toString());
      }
      else if (packet instanceof SessionReceiveMessage) {
    	  System.out.println("SimpleInterceptor gets called!.... Packet: " + packet.getClass().getName() + "RemotingConnection: " + connection.getRemoteAddress() );
    	  SessionReceiveMessage realPacket = (SessionReceiveMessage) packet;
          Message msg = realPacket.getMessage();
          if((msg.getTimestamp()>0) && msg.getUserID()!=null)
          System.out.println("Msg: "+msg.toString());
       }
      
      // We return true which means "call next interceptor" (if there is one) or target.
      // If we returned false, it means "abort call" - no more interceptors would be called and neither would
      // the target
      return true;
   }

}
