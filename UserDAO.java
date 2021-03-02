package com.monCRUD.mongodata;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.monCRUD.mongodata.UserConverter;
import com.monCRUD.model.User;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class UserDAO {
	private DBCollection col;

	public UserDAO(MongoClient mongo) {
		this.col = mongo.getDB("testbase2").getCollection("Persons");
	}

	public User createUser(User u) {
		DBObject doc = UserConverter.toDBObject(u);
		this.col.insert(doc);
		ObjectId id = (ObjectId) doc.get("_id");
		u.setId(id.toString());
		return u;
	}

	public void updateUser(User u) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(u.getId())).get();
		this.col.update(query, UserConverter.toDBObject(u));
	}

	public List<User> readAllUser() {
		List<User> data = new ArrayList<User>();
		DBCursor cursor = col.find();
		while (cursor.hasNext()) {
			DBObject doc = cursor.next();
			User u = UserConverter.toUser(doc);
			data.add(u);
		}
		return data;
	}

	public void deleteUser(User u) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(u.getId())).get();
		this.col.remove(query);
	}

	public User readUser(User u) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(u.getId())).get();
		DBObject data = this.col.findOne(query);
		return UserConverter.toUser(data);
	}

}
