/**
 * =============================================================================
 *
 * ORCID (R) Open Source
 * http://orcid.org
 *
 * Copyright (c) 2012-2014 ORCID, Inc.
 * Licensed under an MIT-Style License (MIT)
 * http://orcid.org/open-source-license
 *
 * This copyright and license information (including a link to the full license)
 * shall be included in its entirety in all copies or substantial portion of
 * the software.
 *
 * =============================================================================
 */
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.09 at 01:52:56 PM BST 
//

package org.orcid.jaxb.model.record.summary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.orcid.jaxb.model.record.Group;
import org.orcid.jaxb.model.record.GroupableActivity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "identifiers", "workSummary" })
@XmlRootElement(name = "work-group", namespace = "http://www.orcid.org/ns/activities")
public class WorkGroup implements Group, Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "identifiers", namespace = "http://www.orcid.org/ns/activities")
    private Identifiers identifiers;
    @XmlElement(name = "work-summary", namespace = "http://www.orcid.org/ns/work")
    private List<WorkSummary> workSummary;

    public Identifiers getIdentifiers() {
        if (identifiers == null)
            identifiers = new Identifiers();
        return identifiers;
    }

    public List<WorkSummary> getWorkSummary() {
        if (workSummary == null)
            workSummary = new ArrayList<WorkSummary>();
        return workSummary;
    }
    
    @Override
    public Collection<? extends GroupableActivity> getActivities() {
        return getWorkSummary();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((identifiers == null) ? 0 : identifiers.hashCode());
        result = prime * result + ((workSummary == null) ? 0 : workSummary.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WorkGroup other = (WorkGroup) obj;
        if (identifiers == null) {
            if (other.identifiers != null)
                return false;
        } else if (!identifiers.equals(other.identifiers))
            return false;
        if (workSummary == null) {
            if (other.workSummary != null)
                return false;
        } else if (!workSummary.equals(other.workSummary))
            return false;
        return true;
    }

}
