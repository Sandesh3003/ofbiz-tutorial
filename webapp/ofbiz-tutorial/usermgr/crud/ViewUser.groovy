def partyId=context.get("partyId");

context.personalDetails = select("firstName", "lastName", "birthDate", "gender")
        .from("Person").where("partyId", partyId).queryOne();

context.postalAddresses = select("address1", "address2", "city", "postalCode", "countryGeoId", "contactMechPurposeTypeId")
        .from("UserAndParty")
        .where("partyId", partyId, "contactMechTypeId", "POSTAL_ADDRESS").queryList();

context.telecomNumbers=select("countryCode", "contactNumber", "areaCode","contactMechPurposeTypeId")
        .from("UserAndParty")
        .where("partyId", partyId, "contactMechTypeId", "TELECOM_NUMBER").queryList();

context.emailAddresses=select("emailAddress", "contactMechPurposeTypeId")
        .from("UserAndParty")
        .where("partyId", partyId, "contactMechTypeId","EMAIL_ADDRESS").queryList();