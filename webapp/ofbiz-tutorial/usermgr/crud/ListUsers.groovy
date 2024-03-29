context.userAndPartyList = from("UserAndParty").queryList()

context.countries= from("Geo").where("geoTypeId","COUNTRY").queryList()