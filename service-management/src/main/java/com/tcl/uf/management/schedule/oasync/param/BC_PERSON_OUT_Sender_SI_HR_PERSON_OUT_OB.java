/**
 * BC_PERSON_OUT_Sender_SI_HR_PERSON_OUT_OB.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tcl.uf.management.schedule.oasync.param;

public interface BC_PERSON_OUT_Sender_SI_HR_PERSON_OUT_OB extends javax.xml.rpc.Service {
    public String getHTTPS_PortAddress();

    public SI_HR_PERSON_OUT_OB getHTTPS_Port() throws javax.xml.rpc.ServiceException;

    public SI_HR_PERSON_OUT_OB getHTTPS_Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public String getHTTP_PortAddress();

    public SI_HR_PERSON_OUT_OB getHTTP_Port() throws javax.xml.rpc.ServiceException;

    public SI_HR_PERSON_OUT_OB getHTTP_Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
