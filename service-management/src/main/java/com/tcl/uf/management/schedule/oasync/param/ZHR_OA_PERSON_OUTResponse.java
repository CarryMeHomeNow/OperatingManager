/**
 * ZHR_OA_PERSON_OUTResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tcl.uf.management.schedule.oasync.param;

public class ZHR_OA_PERSON_OUTResponse implements java.io.Serializable {
    private String RET_FLAG;

    private String RET_MESSAGE;

    private ZTHR_PERSON_OUT[] IT_PERSON;

    public ZHR_OA_PERSON_OUTResponse() {
    }

    public ZHR_OA_PERSON_OUTResponse(
           String RET_FLAG,
           String RET_MESSAGE,
           ZTHR_PERSON_OUT[] IT_PERSON) {
           this.RET_FLAG = RET_FLAG;
           this.RET_MESSAGE = RET_MESSAGE;
           this.IT_PERSON = IT_PERSON;
    }


    /**
     * Gets the RET_FLAG value for this ZHR_OA_PERSON_OUTResponse.
     *
     * @return RET_FLAG
     */
    public String getRET_FLAG() {
        return RET_FLAG;
    }


    /**
     * Sets the RET_FLAG value for this ZHR_OA_PERSON_OUTResponse.
     *
     * @param RET_FLAG
     */
    public void setRET_FLAG(String RET_FLAG) {
        this.RET_FLAG = RET_FLAG;
    }


    /**
     * Gets the RET_MESSAGE value for this ZHR_OA_PERSON_OUTResponse.
     *
     * @return RET_MESSAGE
     */
    public String getRET_MESSAGE() {
        return RET_MESSAGE;
    }


    /**
     * Sets the RET_MESSAGE value for this ZHR_OA_PERSON_OUTResponse.
     *
     * @param RET_MESSAGE
     */
    public void setRET_MESSAGE(String RET_MESSAGE) {
        this.RET_MESSAGE = RET_MESSAGE;
    }


    /**
     * Gets the IT_PERSON value for this ZHR_OA_PERSON_OUTResponse.
     *
     * @return IT_PERSON
     */
    public ZTHR_PERSON_OUT[] getIT_PERSON() {
        return IT_PERSON;
    }


    /**
     * Sets the IT_PERSON value for this ZHR_OA_PERSON_OUTResponse.
     *
     * @param IT_PERSON
     */
    public void setIT_PERSON(ZTHR_PERSON_OUT[] IT_PERSON) {
        this.IT_PERSON = IT_PERSON;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ZHR_OA_PERSON_OUTResponse)) return false;
        ZHR_OA_PERSON_OUTResponse other = (ZHR_OA_PERSON_OUTResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.RET_FLAG==null && other.getRET_FLAG()==null) ||
             (this.RET_FLAG!=null &&
              this.RET_FLAG.equals(other.getRET_FLAG()))) &&
            ((this.RET_MESSAGE==null && other.getRET_MESSAGE()==null) ||
             (this.RET_MESSAGE!=null &&
              this.RET_MESSAGE.equals(other.getRET_MESSAGE()))) &&
            ((this.IT_PERSON==null && other.getIT_PERSON()==null) ||
             (this.IT_PERSON!=null &&
              java.util.Arrays.equals(this.IT_PERSON, other.getIT_PERSON())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getRET_FLAG() != null) {
            _hashCode += getRET_FLAG().hashCode();
        }
        if (getRET_MESSAGE() != null) {
            _hashCode += getRET_MESSAGE().hashCode();
        }
        if (getIT_PERSON() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIT_PERSON());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getIT_PERSON(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ZHR_OA_PERSON_OUTResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", ">ZHR_OA_PERSON_OUT.Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RET_FLAG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RET_FLAG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RET_MESSAGE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RET_MESSAGE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IT_PERSON");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IT_PERSON"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "ZTHR_PERSON_OUT"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
