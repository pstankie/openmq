/*
 * Copyright (c) 2000, 2017 Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2021, 2022 Contributors to the Eclipse Foundation
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

package com.sun.messaging.ums.readonly.impl;

import com.sun.messaging.ums.common.Constants;
import com.sun.messaging.ums.readonly.DefaultReadOnlyService;
import com.sun.messaging.ums.readonly.ReadOnlyMessageFactory;
import com.sun.messaging.ums.readonly.ReadOnlyRequestMessage;
import com.sun.messaging.ums.readonly.ReadOnlyResponseMessage;
import com.sun.messaging.ums.readonly.ReadOnlyService;
import com.sun.messaging.ums.service.UMSServiceException;
import com.sun.messaging.ums.service.UMSServiceImpl;
import java.net.URLDecoder;
import java.util.Map;
import java.util.Properties;

/**
 * This class is used for debugging purposes.
 *
 * @author chiaming
 */
public class ssendwithdrawn implements ReadOnlyService {

    private Properties initParams = null;

    /**
     * initialize with the servlet init params.
     *
     */
    @Override
    public void init(Properties initParams) {
        this.initParams = initParams;
    }

    @Override
    public ReadOnlyResponseMessage request(ReadOnlyRequestMessage request) {

        try {

            Map map = request.getMessageProperties();

            String destName = request.getMessageProperty(Constants.DESTINATION_NAME);

            String msg = request.getMessageProperty("text");
            msg = URLDecoder.decode(msg, "UTF8");

            String domain = request.getMessageProperty(Constants.DOMAIN);
            boolean isTopic = Constants.TOPIC_DOMAIN.equals(domain);

            String domainName = (isTopic ? "Topic" : "Queue");

            UMSServiceImpl service = (UMSServiceImpl) this.initParams.get(DefaultReadOnlyService.JMSSERVICE);

            service.sendText(null, isTopic, destName, msg, map);

            String respMsg = "Message sent: " + msg + ", destination = " + destName + ", domain=" + domainName;

            ReadOnlyResponseMessage response = ReadOnlyMessageFactory.createResponseMessage();

            response.setResponseMessage(respMsg);

            return response;

        } catch (Exception e) {

            UMSServiceException umse = new UMSServiceException(e);

            throw umse;
        }
    }
}
