

package com.tcl.uf.management.schedule.oasync.param;

public class DT_OrgInfoSync_ResponseZORG_INFOItem implements java.io.Serializable {
    private String OBJID;   //机构遍号

    private String STEXT;	//机构名称

    private String m_PERNR;

    private String m_CODE; 	//负责人工号

    private String m_NAME;   //负责人

    private String UP_OBJID;   //父机构遍号

    private String UP_STEXT;	//父机构名称

    private String ZZ_ORGUNITNAME;

    private String ZZ_ORG_DEPART;

    public DT_OrgInfoSync_ResponseZORG_INFOItem() {
    }

    public DT_OrgInfoSync_ResponseZORG_INFOItem(
           String OBJID,
           String STEXT,
           String m_PERNR,
           String m_CODE,
           String m_NAME,
           String UP_OBJID,
           String UP_STEXT,
           String ZZ_ORGUNITNAME,
           String ZZ_ORG_DEPART) {
           this.OBJID = OBJID;
           this.STEXT = STEXT;
           this.m_PERNR = m_PERNR;
           this.m_CODE = m_CODE;
           this.m_NAME = m_NAME;
           this.UP_OBJID = UP_OBJID;
           this.UP_STEXT = UP_STEXT;
           this.ZZ_ORGUNITNAME = ZZ_ORGUNITNAME;
           this.ZZ_ORG_DEPART = ZZ_ORG_DEPART;
    }


    /**
     * Gets the OBJID value for this DT_OrgInfoSync_ResponseZORG_INFOItem.
     *
     * @return OBJID
     */
    public String getOBJID() {
        return OBJID;
    }


    /**
     * Sets the OBJID value for this DT_OrgInfoSync_ResponseZORG_INFOItem.
     *
     * @param OBJID
     */
    public void setOBJID(String OBJID) {
        this.OBJID = OBJID;
    }


    /**
     * Gets the STEXT value for this DT_OrgInfoSync_ResponseZORG_INFOItem.
     *
     * @return STEXT
     */
    public String getSTEXT() {
        return STEXT;
    }


    /**
     * Sets the STEXT value for this DT_OrgInfoSync_ResponseZORG_INFOItem.
     *
     * @param STEXT
     */
    public void setSTEXT(String STEXT) {
        this.STEXT = STEXT;
    }


    /**
     * Gets the m_PERNR value for this DT_OrgInfoSync_ResponseZORG_INFOItem.
     *
     * @return m_PERNR
     */
    public String getM_PERNR() {
        return m_PERNR;
    }


    /**
     * Sets the m_PERNR value for this DT_OrgInfoSync_ResponseZORG_INFOItem.
     *
     * @param m_PERNR
     */
    public void setM_PERNR(String m_PERNR) {
        this.m_PERNR = m_PERNR;
    }


    /**
     * Gets the m_CODE value for this DT_OrgInfoSync_ResponseZORG_INFOItem.
     *
     * @return m_CODE
     */
    public String getM_CODE() {
        return m_CODE;
    }


    /**
     * Sets the m_CODE value for this DT_OrgInfoSync_ResponseZORG_INFOItem.
     *
     * @param m_CODE
     */
    public void setM_CODE(String m_CODE) {
        this.m_CODE = m_CODE;
    }


    /**
     * Gets the m_NAME value for this DT_OrgInfoSync_ResponseZORG_INFOItem.
     *
     * @return m_NAME
     */
    public String getM_NAME() {
        return m_NAME;
    }


    /**
     * Sets the m_NAME value for this DT_OrgInfoSync_ResponseZORG_INFOItem.
     *
     * @param m_NAME
     */
    public void setM_NAME(String m_NAME) {
        this.m_NAME = m_NAME;
    }


    /**
     * Gets the UP_OBJID value for this DT_OrgInfoSync_ResponseZORG_INFOItem.
     *
     * @return UP_OBJID
     */
    public String getUP_OBJID() {
        return UP_OBJID;
    }


    /**
     * Sets the UP_OBJID value for this DT_OrgInfoSync_ResponseZORG_INFOItem.
     *
     * @param UP_OBJID
     */
    public void setUP_OBJID(String UP_OBJID) {
        this.UP_OBJID = UP_OBJID;
    }


    /**
     * Gets the UP_STEXT value for this DT_OrgInfoSync_ResponseZORG_INFOItem.
     *
     * @return UP_STEXT
     */
    public String getUP_STEXT() {
        return UP_STEXT;
    }


    /**
     * Sets the UP_STEXT value for this DT_OrgInfoSync_ResponseZORG_INFOItem.
     *
     * @param UP_STEXT
     */
    public void setUP_STEXT(String UP_STEXT) {
        this.UP_STEXT = UP_STEXT;
    }


    /**
     * Gets the ZZ_ORGUNITNAME value for this DT_OrgInfoSync_ResponseZORG_INFOItem.
     *
     * @return ZZ_ORGUNITNAME
     */
    public String getZZ_ORGUNITNAME() {
        return ZZ_ORGUNITNAME;
    }


    /**
     * Sets the ZZ_ORGUNITNAME value for this DT_OrgInfoSync_ResponseZORG_INFOItem.
     *
     * @param ZZ_ORGUNITNAME
     */
    public void setZZ_ORGUNITNAME(String ZZ_ORGUNITNAME) {
        this.ZZ_ORGUNITNAME = ZZ_ORGUNITNAME;
    }


    /**
     * Gets the ZZ_ORG_DEPART value for this DT_OrgInfoSync_ResponseZORG_INFOItem.
     *
     * @return ZZ_ORG_DEPART
     */
    public String getZZ_ORG_DEPART() {
        return ZZ_ORG_DEPART;
    }


    /**
     * Sets the ZZ_ORG_DEPART value for this DT_OrgInfoSync_ResponseZORG_INFOItem.
     *
     * @param ZZ_ORG_DEPART
     */
    public void setZZ_ORG_DEPART(String ZZ_ORG_DEPART) {
        this.ZZ_ORG_DEPART = ZZ_ORG_DEPART;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof DT_OrgInfoSync_ResponseZORG_INFOItem)) return false;
        DT_OrgInfoSync_ResponseZORG_INFOItem other = (DT_OrgInfoSync_ResponseZORG_INFOItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.OBJID==null && other.getOBJID()==null) ||
             (this.OBJID!=null &&
              this.OBJID.equals(other.getOBJID()))) &&
            ((this.STEXT==null && other.getSTEXT()==null) ||
             (this.STEXT!=null &&
              this.STEXT.equals(other.getSTEXT()))) &&
            ((this.m_PERNR==null && other.getM_PERNR()==null) ||
             (this.m_PERNR!=null &&
              this.m_PERNR.equals(other.getM_PERNR()))) &&
            ((this.m_CODE==null && other.getM_CODE()==null) ||
             (this.m_CODE!=null &&
              this.m_CODE.equals(other.getM_CODE()))) &&
            ((this.m_NAME==null && other.getM_NAME()==null) ||
             (this.m_NAME!=null &&
              this.m_NAME.equals(other.getM_NAME()))) &&
            ((this.UP_OBJID==null && other.getUP_OBJID()==null) ||
             (this.UP_OBJID!=null &&
              this.UP_OBJID.equals(other.getUP_OBJID()))) &&
            ((this.UP_STEXT==null && other.getUP_STEXT()==null) ||
             (this.UP_STEXT!=null &&
              this.UP_STEXT.equals(other.getUP_STEXT()))) &&
            ((this.ZZ_ORGUNITNAME==null && other.getZZ_ORGUNITNAME()==null) ||
             (this.ZZ_ORGUNITNAME!=null &&
              this.ZZ_ORGUNITNAME.equals(other.getZZ_ORGUNITNAME()))) &&
            ((this.ZZ_ORG_DEPART==null && other.getZZ_ORG_DEPART()==null) ||
             (this.ZZ_ORG_DEPART!=null &&
              this.ZZ_ORG_DEPART.equals(other.getZZ_ORG_DEPART())));
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
        if (getOBJID() != null) {
            _hashCode += getOBJID().hashCode();
        }
        if (getSTEXT() != null) {
            _hashCode += getSTEXT().hashCode();
        }
        if (getM_PERNR() != null) {
            _hashCode += getM_PERNR().hashCode();
        }
        if (getM_CODE() != null) {
            _hashCode += getM_CODE().hashCode();
        }
        if (getM_NAME() != null) {
            _hashCode += getM_NAME().hashCode();
        }
        if (getUP_OBJID() != null) {
            _hashCode += getUP_OBJID().hashCode();
        }
        if (getUP_STEXT() != null) {
            _hashCode += getUP_STEXT().hashCode();
        }
        if (getZZ_ORGUNITNAME() != null) {
            _hashCode += getZZ_ORGUNITNAME().hashCode();
        }
        if (getZZ_ORG_DEPART() != null) {
            _hashCode += getZZ_ORG_DEPART().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_OrgInfoSync_ResponseZORG_INFOItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://oa.tcl.com/HR/interfaces/OrgInfoSync", ">>DT_OrgInfoSync_Response>ZORG_INFO>item"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OBJID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OBJID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STEXT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "STEXT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("m_PERNR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "M_PERNR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("m_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "M_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("m_NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "M_NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UP_OBJID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "UP_OBJID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UP_STEXT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "UP_STEXT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZZ_ORGUNITNAME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZZ_ORGUNITNAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZZ_ORG_DEPART");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZZ_ORG_DEPART"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
