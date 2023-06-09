/*
 * Copyright (c) 2000, 2020 Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2022 Contributors to the Eclipse Foundation. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.messaging.jms;

/**
 * The <CODE>com.sun.messaging.jms.Message</CODE> interface defines enhanced capabilities of a JMS Message in
 * Eclipse OpenMQ(tm).
 * <P>
 * It defines
 * <UL>
 * <LI>Additional methods available for custom message acknowledgement behavior.
 * </UL>
 *
 * @see jakarta.jms.Message
 */

public interface Message {

    /**
     * Acknowledges this consumed message only.
     *
     * <P>
     * All consumed JMS messages in Eclipse OpenMQ(tm) support the
     * <CODE>acknowledgeThisMessage</CODE> method for use when a client has specified that its JMS session's consumed
     * messages are to be explicitly acknowledged. By invoking <CODE>acknowledgeThisMessage</CODE> on a consumed message, a
     * client acknowledges only the specific message that the method is invoked on.
     *
     * <P>
     * Calls to <CODE>acknowledgeThisMessage</CODE> are ignored for both transacted sessions and sessions specified to use
     * implicit acknowledgement modes.
     *
     * @exception jakarta.jms.JMSException if the messages fail to get acknowledged due to an internal error.
     * @exception jakarta.jms.IllegalStateException if this method is called on a closed session.
     *
     * @see jakarta.jms.Session#CLIENT_ACKNOWLEDGE
     * @see jakarta.jms.Message#acknowledge() jakarta.jms.Message.acknowledge()
     * @see com.sun.messaging.jms.Message#acknowledgeUpThroughThisMessage()
     */
    void acknowledgeThisMessage() throws jakarta.jms.JMSException;

    /**
     * Acknowledges consumed messages of the session up through and including this consumed message.
     *
     * <P>
     * All consumed JMS messages in Eclipse OpenMQ(tm) support the
     * <CODE>acknowledgeUpThroughThisMessage</CODE> method for use when a client has specified that its JMS session's
     * consumed messages are to be explicitly acknowledged. By invoking <CODE>acknowledgeUpThroughThisMessage</CODE> on a
     * consumed message, a client acknowledges messages starting with the first unacknowledged message and ending with this
     * message that were consumed by the session that this message was delivered to.
     *
     * <P>
     * Calls to <CODE>acknowledgeUpThroughThisMessage</CODE> are ignored for both transacted sessions and sessions specified
     * to use implicit acknowledgement modes.
     *
     * @exception jakarta.jms.JMSException if the messages fail to get acknowledged due to an internal error.
     * @exception jakarta.jms.IllegalStateException if this method is called on a closed session.
     *
     * @see jakarta.jms.Session#CLIENT_ACKNOWLEDGE
     * @see jakarta.jms.Message#acknowledge() jakarta.jms.Message.acknowledge()
     * @see com.sun.messaging.jms.Message#acknowledgeThisMessage()
     */
    void acknowledgeUpThroughThisMessage() throws jakarta.jms.JMSException;

}
