package org.orcid.core.adapter.jsonidentifier.converter;

import org.orcid.core.adapter.jsonidentifier.JSONExternalIdentifier;
import org.orcid.core.adapter.jsonidentifier.JSONFundingExternalIdentifiers;
import org.orcid.core.adapter.jsonidentifier.JSONUrl;
import org.orcid.core.utils.JsonUtils;
import org.orcid.jaxb.model.common_v2.Url;
import org.orcid.jaxb.model.record_rc1.FundingExternalIdentifierType;
import org.orcid.jaxb.model.record_v2.ExternalID;
import org.orcid.jaxb.model.record_v2.ExternalIDs;
import org.orcid.jaxb.model.record_v2.Relationship;
import org.orcid.pojo.ajaxForm.PojoUtil;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class JSONFundingExternalIdentifiersConverterV2 extends BidirectionalConverter<ExternalIDs, String> {
    
    private ExternalIdentifierTypeConverter conv = new ExternalIdentifierTypeConverter();

    @Override
    public String convertTo(ExternalIDs source, Type<String> destinationType, MappingContext context) {
        JSONFundingExternalIdentifiers jsonFundingExternalIdentifiers = new JSONFundingExternalIdentifiers();
        for (ExternalID externalID : source.getExternalIdentifier()) {
            JSONExternalIdentifier jsonExternalIdentifier = new JSONExternalIdentifier();
            if (externalID.getType() != null) {
                jsonExternalIdentifier.setType(conv.convertTo(externalID.getType(), null, context));
            }

            if (externalID.getUrl() != null) {
                jsonExternalIdentifier.setUrl(new JSONUrl(externalID.getUrl().getValue()));
            }

            if (!PojoUtil.isEmpty(externalID.getValue())) {
                jsonExternalIdentifier.setValue(externalID.getValue());
            }

            if (externalID.getRelationship() != null) {
                jsonExternalIdentifier.setRelationship(conv.convertTo(externalID.getRelationship().value(), null, context));
            }
            jsonFundingExternalIdentifiers.getFundingExternalIdentifier().add(jsonExternalIdentifier);
        }
        return JsonUtils.convertToJsonString(jsonFundingExternalIdentifiers);
    }

    @Override
    public ExternalIDs convertFrom(String source, Type<ExternalIDs> destinationType, MappingContext context) {
        JSONFundingExternalIdentifiers fundingExternalIdentifiers = JsonUtils.readObjectFromJsonString(source, JSONFundingExternalIdentifiers.class);
        ExternalIDs externalIDs = new ExternalIDs();
        for (JSONExternalIdentifier externalIdentifier : fundingExternalIdentifiers.getFundingExternalIdentifier()) {
            ExternalID id = new ExternalID();
            if (externalIdentifier.getType() == null) {
                id.setType(FundingExternalIdentifierType.GRANT_NUMBER.value());
            } else {
                id.setType(externalIdentifier.getType().toLowerCase());
            }
            if (externalIdentifier.getUrl() != null && !PojoUtil.isEmpty(externalIdentifier.getUrl().getValue())) {
                Url url = new Url(externalIdentifier.getUrl().getValue());
                id.setUrl(url);
            }

            if (!PojoUtil.isEmpty(externalIdentifier.getValue())) {
                id.setValue(externalIdentifier.getValue());
            }

            if (externalIdentifier.getRelationship() != null) {
                id.setRelationship(Relationship.fromValue(conv.convertFrom(externalIdentifier.getRelationship(), null, context)));
            }
            externalIDs.getExternalIdentifier().add(id);
        }
        return externalIDs;
    }

}
