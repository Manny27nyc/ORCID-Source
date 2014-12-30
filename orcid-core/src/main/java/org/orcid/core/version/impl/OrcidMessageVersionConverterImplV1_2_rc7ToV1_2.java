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
package org.orcid.core.version.impl;

import static org.orcid.jaxb.model.message.WorkExternalIdentifierType.OTHER_ID;
import static org.orcid.jaxb.model.message.WorkExternalIdentifierType.WOS;

import org.orcid.core.version.OrcidMessageVersionConverter;
import org.orcid.jaxb.model.message.OrcidMessage;
import org.orcid.jaxb.model.message.OrcidProfile;
import org.orcid.jaxb.model.message.OrcidSearchResult;
import org.orcid.jaxb.model.message.OrcidSearchResults;
import org.orcid.jaxb.model.message.OrcidWork;
import org.orcid.jaxb.model.message.WorkExternalIdentifier;
import org.orcid.jaxb.model.message.WorkExternalIdentifierType;
import org.orcid.jaxb.model.message.WorkExternalIdentifiers;

/**
 * 
 * @author rcpeters
 * 
 */
public class OrcidMessageVersionConverterImplV1_2_rc7ToV1_2 implements OrcidMessageVersionConverter {

    private static final String FROM_VERSION = "1.2_rc7";
    private static final String TO_VERSION = "1.2";

    @Override
    public String getFromVersion() {
        return FROM_VERSION;
    }

    @Override
    public String getToVersion() {
        return TO_VERSION;
    }

    @Override
    public OrcidMessage downgradeMessage(OrcidMessage orcidMessage) {
        if (orcidMessage == null) {
            return null;
        }
        orcidMessage.setMessageVersion(FROM_VERSION);
        OrcidProfile orcidProfile = orcidMessage.getOrcidProfile();
        downgradeProfile(orcidProfile);
        downgradeSearchResults(orcidMessage);
        return orcidMessage;
    }

    private void downgradeProfile(OrcidProfile orcidProfile) {
        if (orcidProfile != null) {
            if (orcidProfile.getOrcidActivities() != null) {
                //nothing between these versions
            }
        }
    }

    private void downgradeSearchResults(OrcidMessage orcidMessage) {
        OrcidSearchResults searchResults = orcidMessage.getOrcidSearchResults();
        if (searchResults != null) {
            for (OrcidSearchResult searchResult : searchResults.getOrcidSearchResult()) {
                downgradeProfile(searchResult.getOrcidProfile());
            }
        }
    }

    @Override
    public OrcidMessage upgradeMessage(OrcidMessage orcidMessage) {
        if (orcidMessage == null) {
            return null;
        }
        orcidMessage.setMessageVersion(TO_VERSION);
        return orcidMessage;
    }

}
