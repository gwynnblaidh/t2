package com.monCRUD.mongodata;

import org.bson.types.ObjectId;

import com.monCRUD.model.User;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class UserConverter {
	public static DBObject toDBObject(User u) {

		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.append("name", u.getName()).append("country", u.getCountry());
		if (u.getId() != null)
			builder = builder.append("_id", new ObjectId(u.getId()));
		return builder.get();
	}

	// convert DBObject Object to Person
	// take special note of converting ObjectId to String
	public static User toUser(DBObject doc) {
		User u = new User();
		u.setName((String) doc.get("name"));
		u.setCountry((String) doc.get("age"));
		ObjectId id = (ObjectId) doc.get("_id");
		u.setId(id.toString());
		return u;

	}
}
