/**
 * SI_OrgInfoSync_OBServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package com.tcl.uf.management.schedule.oasync.param;

import com.tcl.uf.management.schedule.oasync.SI_OrgInfoSync_OBService;

public class SI_OrgInfoSync_OBServiceLocator extends org.apache.axis.client.Service implements SI_OrgInfoSync_OBService {

    public SI_OrgInfoSync_OBServiceLocator() {
    }


    public SI_OrgInfoSync_OBServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SI_OrgInfoSync_OBServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SI_OrgInfoSync_OBPort
    private String SI_OrgInfoSync_OBPort_address = "http://"+SyncConfig.SYNC_SAP_IP+":"+SyncConfig.SYNC_SAP_PORT+"/XISOAPAdapter/MessageServlet?channel=*:BC_HR_OrgInfoSync_Sender:CC_HR_OrgInfoSync_SOAP&version=3.0&Sender.Service=x&Interface=x%5Ex";

    public String getSI_OrgInfoSync_OBPortAddress() {
        return SI_OrgInfoSync_OBPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String SI_OrgInfoSync_OBPortWSDDServiceName = "SI_OrgInfoSync_OBPort";

    public String getSI_OrgInfoSync_OBPortWSDDServiceName() {
        return SI_OrgInfoSync_OBPortWSDDServiceName;
    }

    public void setSI_OrgInfoSync_OBPortWSDDServiceName(String name) {
        SI_OrgInfoSync_OBPortWSDDServiceName = name;
    }

    public SI_OrgInfoSync_OB getSI_OrgInfoSync_OBPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SI_OrgInfoSync_OBPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSI_OrgInfoSync_OBPort(endpoint);
    }

    public SI_OrgInfoSync_OB getSI_OrgInfoSync_OBPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            SI_OrgInfoSync_OBBindingStub _stub = new SI_OrgInfoSync_OBBindingStub(portAddress, this);
            _stub.setPortName(getSI_OrgInfoSync_OBPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSI_OrgInfoSync_OBPortEndpointAddress(String address) {
        SI_OrgInfoSync_OBPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (SI_OrgInfoSync_OB.class.isAssignableFrom(serviceEndpointInterface)) {
                SI_OrgInfoSync_OBBindingStub _stub = new SI_OrgInfoSync_OBBindingStub(new java.net.URL(SI_OrgInfoSync_OBPort_address), this);
                _stub.setPortName(getSI_OrgInfoSync_OBPortWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("SI_OrgInfoSync_OBPort".equals(inputPortName)) {
            return getSI_OrgInfoSync_OBPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://oa.tcl.com/HR/interfaces/OrgInfoSync", "SI_OrgInfoSync_OBService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://oa.tcl.com/HR/interfaces/OrgInfoSync", "SI_OrgInfoSync_OBPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {

if ("SI_OrgInfoSync_OBPort".equals(portName)) {
            setSI_OrgInfoSync_OBPortEndpointAddress(address);
        }
        else
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
