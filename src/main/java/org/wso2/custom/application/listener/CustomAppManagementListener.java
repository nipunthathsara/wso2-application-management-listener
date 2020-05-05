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
import org.wso2.carbon.identity.application.common.model.InboundAuthenticationRequestConfig;
import org.wso2.carbon.identity.application.common.model.ServiceProvider;
import org.wso2.carbon.identity.application.mgt.listener.AbstractApplicationMgtListener;
import org.wso2.carbon.identity.oauth.IdentityOAuthAdminException;
import org.wso2.carbon.identity.oauth.OAuthAdminServiceImpl;
import org.wso2.carbon.identity.oauth.dto.OAuthConsumerAppDTO;

public class CustomAppManagementListener extends AbstractApplicationMgtListener {

    private static Log log = LogFactory.getLog(CustomAppManagementListener.class);
    private static OAuthAdminServiceImpl oAuthAdminService = new OAuthAdminServiceImpl();
    public static final String OAUTH2 = "oauth2";

    @Override
    public int getDefaultOrderId() {
        return 99;
    }

    public boolean doPreUpdateApplication(ServiceProvider serviceProvider, String tenantDomain, String userName)
            throws IdentityApplicationManagementException {
        // Get the inbound authentication basic configurations from the received SP object.
        InboundAuthenticationRequestConfig[]  configs = serviceProvider.getInboundAuthenticationConfig().getInboundAuthenticationRequestConfigs();
        String inboundAuthKey;
        OAuthConsumerAppDTO app;
        // Iterate to check if SP has oauth2 configs. if so, get the client key.
        for (InboundAuthenticationRequestConfig config : configs) {
            if (OAUTH2.equals(config.getInboundAuthType())) {
                inboundAuthKey = config.getInboundAuthKey();
                break;
            }
        }
        // Update the Oauth app to Bypass the client credentials.
        if (inboundAuthKey != null) {
            try {
                app = oAuthAdminService.getOAuthApplicationData(inboundAuthKey);
                app.setBypassClientCredentials(true);
                oAuthAdminService.updateConsumerApplication(app);
            } catch (IdentityOAuthAdminException e) {
                log.error("Error while obtaining/updating oauth app data for consumer key : " + inboundAuthKey);
                throw new IdentityApplicationManagementException;
            }
        }
        log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        return true;
    }
}
