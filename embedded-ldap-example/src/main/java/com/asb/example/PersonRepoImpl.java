package com.asb.example;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.SearchControls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;


@Service
public class PersonRepoImpl implements PersonRepo {

	@Autowired
	private LdapTemplate ldapTemplate;
	
	public static final String BASE_DN = "dc=asb,dc=com";

	@Override
	public List<String> getAllPersonNames() {
		List<String> list = ldapTemplate.search(query().where("objectclass").is("person"),
				new PersonNameAttributesMapper());
		
		return list;
	}

	@Override
	public List<Person> getAllPersons() {
		return ldapTemplate.search(query().where("objectclass").is("person"), new PersonAttributesMapper());
		
		
	}

	@Override
	public Person getPersonNamesByUid(String userId) {
		List<Person> people = ldapTemplate.search(query().where("uid").is(userId), new PersonAttributesMapper());
		return ((null != people && !people.isEmpty()) ? people.get(0) : null);
	}

	private class PersonAttributesMapper implements AttributesMapper<Person> {
		public Person mapFromAttributes(Attributes attrs) throws NamingException {
			Person person = new Person();
			person.setUserId(null != attrs.get("uid") ? (String) attrs.get("uid").get() : null);
			person.setFullName((String) attrs.get("cn").get());
			person.setLastName((String) attrs.get("sn").get());
			person.setDescription(null != attrs.get("description") ? (String) attrs.get("description").get() : null);
			return person;
		}
	}

	private class PersonNameAttributesMapper implements AttributesMapper<String> {
		public String mapFromAttributes(Attributes attrs) throws NamingException {
			return attrs.get("cn").get().toString();
		}
	}
	
	@Override
	public String create(Person p) {
		Name dn = buildDn(p.getUserId());
		ldapTemplate.bind(dn, null, buildAttributes(p));
		return p.getUserId() + " created successfully";
	}

	@Override
	public String update(Person p) {
		Name dn = buildDn(p.getUserId());
		ldapTemplate.rebind(dn, null, buildAttributes(p));
		return p.getUserId() + " updated successfully";
	}

	@Override
	public String remove(String userId) {
		Name dn = buildDn(userId);
		// ldapTemplate.unbind(dn, true); //Remove recursively all entries
		ldapTemplate.unbind(dn);
		return userId + " removed successfully";
	}

	private Attributes buildAttributes(Person p) {

		BasicAttribute ocattr = new BasicAttribute("objectclass");
		ocattr.add("top");
		ocattr.add("person");

		Attributes attrs = new BasicAttributes();
		attrs.put(ocattr);
		attrs.put("uid", p.getUserId());
		attrs.put("cn", p.getFullName());
		attrs.put("sn", p.getLastName());
		attrs.put("description", p.getDescription());
		return attrs;
	}

	public Name buildDn(String userId) {
		return LdapNameBuilder.newInstance(BASE_DN).add("ou", "people").add("uid", userId).build();
	}

	public Name buildBaseDn() {
		return LdapNameBuilder.newInstance(BASE_DN).add("ou", "people").build();
	}

	@Override
	public List<Person> retrieve() {
		SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		List<Person> people = ldapTemplate.search(query().where("objectclass").is("person"),
				new PersonAttributeMapper());
		return people;
	}

	private class PersonAttributeMapper implements AttributesMapper<Person> {

		@Override
		public Person mapFromAttributes(Attributes attributes) throws NamingException {
			
			System.out.println("!!!!!!!!!!!!Manoj Here: "+attributes.toString());
			Person person = new Person();
			person.setUserId(null != attributes.get("uid") ? attributes.get("uid").get().toString() : null);
			person.setFullName(null != attributes.get("cn") ? attributes.get("cn").get().toString() : null);
			person.setLastName(null != attributes.get("sn") ? attributes.get("sn").get().toString() : null);
			person.setDescription(
					null != attributes.get("description") ? attributes.get("description").get().toString() : null);
			return person;
		}
	}
}