/*
 * Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.custom.application.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.application.common.IdentityApplicationManagementException;
import org.wso2.carbon.identity.application.common.model.ServiceProvider;
import org.wso2.carbon.identity.application.mgt.listener.AbstractApplicationMgtListener;

public class CustomAppManagementListener extends AbstractApplicationMgtListener {

    private static Log log = LogFactory.getLog(CustomAppManagementListener.class);

    @Override
    public int getDefaultOrderId() {
        return 99;
    }

//    @Override
//    public boolean doPreCreateApplication(ServiceProvider serviceProvider, String tenantDomain, String userName)
//            throws IdentityApplicationManagementException {
//        log.info("**************************************************************");
//        return true;
//    }
//
//    @Override
//    public void onPreCreateInbound(ServiceProvider serviceProvider, boolean isUpdate) throws
//            IdentityApplicationManagementException {
//        log.info("###########################");
//    }

    public boolean doPreUpdateApplication(ServiceProvider serviceProvider, String tenantDomain, String userName)
            throws IdentityApplicationManagementException {
        log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        return true;
    }
}
