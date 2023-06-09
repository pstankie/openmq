/*
 * Copyright (c) 2000, 2017 Oracle and/or its affiliates. All rights reserved.
 * Copyright 2021 Contributors to the Eclipse Foundation
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

package com.sun.messaging.jmq.jmsserver.persist.jdbc.sharecc;

import com.sun.messaging.jmq.jmsserver.util.BrokerException;
import com.sun.messaging.jmq.jmsserver.persist.jdbc.comm.CommBaseDAOImpl;
import com.sun.messaging.jmq.jmsserver.persist.jdbc.comm.CommDBManager;

import java.sql.*;

public abstract class ShareConfigRecordBaseDAOImpl extends CommBaseDAOImpl {

    @Override
    protected CommDBManager getDBManager() throws BrokerException {

        return ShareConfigChangeDBManager.getDBManager();
    }

    @Override
    protected void closeSQLObjects(ResultSet rs, Statement stmt, Connection conn, Throwable ex) throws BrokerException {

        getDBManager().closeSQLObjects(rs, stmt, conn, ex);
    }
}
