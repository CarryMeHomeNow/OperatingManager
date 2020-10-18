/**
 * BC_PERSON_OUT_Sender_SI_HR_PERSON_OUT_OBLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tcl.uf.management.schedule.oasync.param;

public class BC_PERSON_OUT_Sender_SI_HR_PERSON_OUT_OBLocator extends org.apache.axis.client.Service implements BC_PERSON_OUT_Sender_SI_HR_PERSON_OUT_OB {

    public BC_PERSON_OUT_Sender_SI_HR_PERSON_OUT_OBLocator() {
    }


    public BC_PERSON_OUT_Sender_SI_HR_PERSON_OUT_OBLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public BC_PERSON_OUT_Sender_SI_HR_PERSON_OUT_OBLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for HTTPS_Port
    private String HTTPS_Port_address = "https://10.118.1.233/XISOAPAdapter/MessageServlet?senderParty=&senderService=BC_PERSON_OUT_Sender&receiverParty=&receiverService=&interface=SI_HR_PERSON_OUT_OB&interfaceNamespace=http%3A%2F%2Foa.tcl.com%2FHR%2Finterfaces%2FPerson_Out";

    public String getHTTPS_PortAddress() {
        return HTTPS_Port_address;
    }

    // The WSDD service name defaults to the port name.
    private String HTTPS_PortWSDDServiceName = "HTTPS_Port";

    public String getHTTPS_PortWSDDServiceName() {
        return HTTPS_PortWSDDServiceName;
    }

    public void setHTTPS_PortWSDDServiceName(String name) {
        HTTPS_PortWSDDServiceName = name;
    }

    public SI_HR_PERSON_OUT_OB getHTTPS_Port() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(HTTPS_Port_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getHTTPS_Port(endpoint);
    }

    public SI_HR_PERSON_OUT_OB getHTTPS_Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
        	SI_HR_PERSON_OUT_OBBindingStub _stub = new SI_HR_PERSON_OUT_OBBindingStub(portAddress, this);
            _stub.setPortName(getHTTPS_PortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setHTTPS_PortEndpointAddress(String address) {
        HTTPS_Port_address = address;
    }


    // Use to get a proxy class for HTTP_Port
    private String HTTP_Port_address = "http://10.118.1.233:50000/XISOAPAdapter/MessageServlet?senderParty=&senderService=BC_PERSON_OUT_Sender&receiverParty=&receiverService=&interface=SI_HR_PERSON_OUT_OB&interfaceNamespace=http%3A%2F%2Foa.tcl.com%2FHR%2Finterfaces%2FPerson_Out";

    public String getHTTP_PortAddress() {
        return HTTP_Port_address;
    }

    // The WSDD service name defaults to the port name.
    private String HTTP_PortWSDDServiceName = "HTTP_Port";

    public String getHTTP_PortWSDDServiceName() {
        return HTTP_PortWSDDServiceName;
    }

    public void setHTTP_PortWSDDServiceName(String name) {
        HTTP_PortWSDDServiceName = name;
    }

    public SI_HR_PERSON_OUT_OB getHTTP_Port() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(HTTP_Port_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getHTTP_Port(endpoint);
    }

    public SI_HR_PERSON_OUT_OB getHTTP_Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
        	SI_HR_PERSON_OUT_OBBindingStub _stub = new SI_HR_PERSON_OUT_OBBindingStub(portAddress, this);
            _stub.setPortName(getHTTP_PortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setHTTP_PortEndpointAddress(String address) {
        HTTP_Port_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (SI_HR_PERSON_OUT_OB.class.isAssignableFrom(serviceEndpointInterface)) {
                SI_HR_PERSON_OUT_OBBindingStub _stub = new SI_HR_PERSON_OUT_OBBindingStub(new java.net.URL(HTTPS_Port_address), this);
                _stub.setPortName(getHTTPS_PortWSDDServiceName());
                return _stub;
            }
            if (SI_HR_PERSON_OUT_OB.class.isAssignableFrom(serviceEndpointInterface)) {
                SI_HR_PERSON_OUT_OBBindingStub _stub = new SI_HR_PERSON_OUT_OBBindingStub(new java.net.URL(HTTP_Port_address), this);
                _stub.setPortName(getHTTP_PortWSDDServiceName());
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
        if ("HTTPS_Port".equals(inputPortName)) {
            return getHTTPS_Port();
        }
        else if ("HTTP_Port".equals(inputPortName)) {
            return getHTTP_Port();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://oa.tcl.com/HR/interfaces/Person_Out", "BC_PERSON_OUT_Sender_SI_HR_PERSON_OUT_OB");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://oa.tcl.com/HR/interfaces/Person_Out", "HTTPS_Port"));
            ports.add(new javax.xml.namespace.QName("http://oa.tcl.com/HR/interfaces/Person_Out", "HTTP_Port"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {

if ("HTTPS_Port".equals(portName)) {
            setHTTPS_PortEndpointAddress(address);
        }
        else
if ("HTTP_Port".equals(portName)) {
            setHTTP_PortEndpointAddress(address);
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
