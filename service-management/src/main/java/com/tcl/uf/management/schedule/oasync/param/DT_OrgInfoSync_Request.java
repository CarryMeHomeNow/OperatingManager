/**
 * DT_OrgInfoSync_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tcl.uf.management.schedule.oasync.param;

public class DT_OrgInfoSync_Request  implements java.io.Serializable {
    private String z_SOBID;

    private String z_KEYDATE;

    private String z_PWD;

    public DT_OrgInfoSync_Request() {
    }

    public DT_OrgInfoSync_Request(
           String z_SOBID,
           String z_KEYDATE,
           String z_PWD) {
           this.z_SOBID = z_SOBID;
           this.z_KEYDATE = z_KEYDATE;
           this.z_PWD = z_PWD;
    }


    /**
     * Gets the z_SOBID value for this DT_OrgInfoSync_Request.
     *
     * @return z_SOBID
     */
    public String getZ_SOBID() {
        return z_SOBID;
    }


    /**
     * Sets the z_SOBID value for this DT_OrgInfoSync_Request.
     *
     * @param z_SOBID
     */
    public void setZ_SOBID(String z_SOBID) {
        this.z_SOBID = z_SOBID;
    }


    /**
     * Gets the z_KEYDATE value for this DT_OrgInfoSync_Request.
     *
     * @return z_KEYDATE
     */
    public String getZ_KEYDATE() {
        return z_KEYDATE;
    }


    /**
     * Sets the z_KEYDATE value for this DT_OrgInfoSync_Request.
     *
     * @param z_KEYDATE
     */
    public void setZ_KEYDATE(String z_KEYDATE) {
        this.z_KEYDATE = z_KEYDATE;
    }


    /**
     * Gets the z_PWD value for this DT_OrgInfoSync_Request.
     *
     * @return z_PWD
     */
    public String getZ_PWD() {
        return z_PWD;
    }


    /**
     * Sets the z_PWD value for this DT_OrgInfoSync_Request.
     *
     * @param z_PWD
     */
    public void setZ_PWD(String z_PWD) {
        this.z_PWD = z_PWD;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof DT_OrgInfoSync_Request)) return false;
        DT_OrgInfoSync_Request other = (DT_OrgInfoSync_Request) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.z_SOBID==null && other.getZ_SOBID()==null) ||
             (this.z_SOBID!=null &&
              this.z_SOBID.equals(other.getZ_SOBID()))) &&
            ((this.z_KEYDATE==null && other.getZ_KEYDATE()==null) ||
             (this.z_KEYDATE!=null &&
              this.z_KEYDATE.equals(other.getZ_KEYDATE()))) &&
            ((this.z_PWD==null && other.getZ_PWD()==null) ||
             (this.z_PWD!=null &&
              this.z_PWD.equals(other.getZ_PWD())));
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
        if (getZ_SOBID() != null) {
            _hashCode += getZ_SOBID().hashCode();
        }
        if (getZ_KEYDATE() != null) {
            _hashCode += getZ_KEYDATE().hashCode();
        }
        if (getZ_PWD() != null) {
            _hashCode += getZ_PWD().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_OrgInfoSync_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://oa.tcl.com/HR/interfaces/OrgInfoSync", "DT_OrgInfoSync_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("z_SOBID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Z_SOBID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("z_KEYDATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Z_KEYDATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("z_PWD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Z_PWD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
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
