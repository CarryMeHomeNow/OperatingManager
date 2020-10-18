/**
 * SI_OrgInfoSync_OBService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tcl.uf.management.schedule.oasync;

import com.tcl.uf.management.schedule.oasync.param.SI_OrgInfoSync_OB;

import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;

public interface SI_OrgInfoSync_OBService extends Service {
    public String getSI_OrgInfoSync_OBPortAddress();

    public SI_OrgInfoSync_OB getSI_OrgInfoSync_OBPort() throws ServiceException;

    public SI_OrgInfoSync_OB getSI_OrgInfoSync_OBPort(java.net.URL portAddress) throws ServiceException;
}
