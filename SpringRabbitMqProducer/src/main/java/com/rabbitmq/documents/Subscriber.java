package com.rabbitmq.documents;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SubscriberInfo")
public class Subscriber {

	@Id
	private ObjectId id;
	private String subs_uid;
	
	@Indexed
	private String subs_name;
	private String subs_address;
	private String subs_type;
	private String subs_cellno;

	public Subscriber(String subs_uid, String subs_name, String subs_address, String subs_type, String subs_cellno) {

		this.subs_uid = subs_uid;
		this.subs_name = subs_name;
		this.subs_address = subs_address;
		this.subs_type = subs_type;
		this.subs_cellno = subs_cellno;
	}

	Subscriber() {

	}

	@Override
	public String toString() {
		return "SubscriberInfo: [id=" + id + ", subs_uid=" + subs_uid + ", subs_name=" + subs_name + ", subs_address="
				+ subs_address + ", subs_type=" + subs_type + ", subs_cellno=" + subs_cellno + "]";
	}

	public String getSubs_uid() {
		return subs_uid;
	}

	public void setSubs_uid(String subs_uid) {
		this.subs_uid = subs_uid;
	}

	public String getSubs_name() {
		return subs_name;
	}

	public void setSubs_name(String subs_name) {
		this.subs_name = subs_name;
	}

	public String getSubs_address() {
		return subs_address;
	}

	public void setSubs_address(String subs_address) {
		this.subs_address = subs_address;
	}

	public String getSubs_type() {
		return subs_type;
	}

	public void setSubs_type(String subs_type) {
		this.subs_type = subs_type;
	}

	public String getSubs_cellno() {
		return subs_cellno;
	}

	public void setSubs_cellno(String subs_cellno) {
		this.subs_cellno = subs_cellno;
	}

}
