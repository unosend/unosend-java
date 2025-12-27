package co.unosend;

import java.util.List;

public class Contacts {
    private final Unosend client;

    Contacts(Unosend client) { this.client = client; }

    public Contact create(CreateContactRequest request) throws UnosendException {
        return HttpClient.post(client, "/contacts", request, Contact.class);
    }

    public ContactList list(String audienceId) throws UnosendException {
        return HttpClient.get(client, "/audiences/" + audienceId + "/contacts", ContactList.class);
    }

    public void delete(String contactId) throws UnosendException {
        HttpClient.delete(client, "/contacts/" + contactId, Void.class);
    }

    public static class CreateContactRequest {
        private String audienceId, email, firstName, lastName;
        public CreateContactRequest audienceId(String v) { audienceId = v; return this; }
        public CreateContactRequest email(String v) { email = v; return this; }
        public CreateContactRequest firstName(String v) { firstName = v; return this; }
        public CreateContactRequest lastName(String v) { lastName = v; return this; }
    }

    public static class Contact {
        private String id, email, firstName, lastName, createdAt;
        private boolean unsubscribed;
        public String getId() { return id; }
        public String getEmail() { return email; }
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public boolean isUnsubscribed() { return unsubscribed; }
    }

    public static class ContactList {
        private List<Contact> data;
        public List<Contact> getData() { return data; }
    }
}
