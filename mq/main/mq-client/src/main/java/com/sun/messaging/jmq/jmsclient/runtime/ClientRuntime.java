/*
 * Copyright (c) 2012, 2017 Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2022 Contributors to Eclipse Foundation. All rights reserved.
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

package com.sun.messaging.jmq.jmsclient.runtime;

import com.sun.messaging.jmq.jmsclient.runtime.impl.ClientRuntimeImpl;

public abstract class ClientRuntime {

    /**
     * Get an instance of client runtime object.
     *
     * @return an instance of client runtime object.
     */
    public static ClientRuntime getRuntime() {
        return MyInstance.runtime;
    }

    /**
     * Create the singleton broker instance. Only one instance can be created per JVM.
     *
     *
     * @see BrokerInstance
     */
    public abstract BrokerInstance createBrokerInstance() throws ClassNotFoundException, IllegalAccessException, InstantiationException;

    /**
     * Check if there is an embedded broker running in the current JVM.
     *
     * @return true if it is. Otherwise, return false.
     */
    public abstract boolean isEmbeddedBrokerRunning();

    /**
     * The only way to get an instance of this class is to use getRuntime() method.
     */
    protected ClientRuntime() {
    }

    /**
     * Client runtime singleton instance is constructed here.
     */
    private static class MyInstance {
        private static final ClientRuntime runtime = ClientRuntimeImpl.getClientRuntimeImpl();
    }

}
