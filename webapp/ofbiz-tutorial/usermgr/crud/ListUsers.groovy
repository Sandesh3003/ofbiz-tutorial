context.userAndPartyList = from("UserAndParty").queryList()

context.userList = select("partyId", "userLoginId", "firstName", "middleName", "lastName").from("UserAndParty").distinct(true).queryList();

context.countries= from("Geo").where("geoTypeId","COUNTRY").queryList()