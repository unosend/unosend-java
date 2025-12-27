package co.unosend;

import java.util.List;

public class Domains {
    private final Unosend client;

    Domains(Unosend client) { this.client = client; }

    public Domain create(String domain) throws UnosendException {
        return HttpClient.post(client, "/domains", new CreateRequest(domain), Domain.class);
    }

    public DomainList list() throws UnosendException {
        return HttpClient.get(client, "/domains", DomainList.class);
    }

    public Domain verify(String domainId) throws UnosendException {
        return HttpClient.post(client, "/domains/" + domainId + "/verify", null, Domain.class);
    }

    public void delete(String domainId) throws UnosendException {
        HttpClient.delete(client, "/domains/" + domainId, Void.class);
    }

    private static class CreateRequest { String domain; CreateRequest(String d) { domain = d; } }

    public static class Domain {
        private String id, name, status, createdAt;
        private List<DnsRecord> records;
        public String getId() { return id; }
        public String getName() { return name; }
        public String getStatus() { return status; }
        public List<DnsRecord> getRecords() { return records; }
    }

    public static class DnsRecord {
        private String type, name, value, status;
        public String getType() { return type; }
        public String getName() { return name; }
        public String getValue() { return value; }
        public String getStatus() { return status; }
    }

    public static class DomainList {
        private List<Domain> data;
        public List<Domain> getData() { return data; }
    }
}
