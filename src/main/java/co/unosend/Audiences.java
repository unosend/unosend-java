package co.unosend;

import java.util.List;

public class Audiences {
    private final Unosend client;

    Audiences(Unosend client) { this.client = client; }

    public Audience create(String name) throws UnosendException {
        return HttpClient.post(client, "/audiences", new CreateRequest(name), Audience.class);
    }

    public AudienceList list() throws UnosendException {
        return HttpClient.get(client, "/audiences", AudienceList.class);
    }

    public void delete(String audienceId) throws UnosendException {
        HttpClient.delete(client, "/audiences/" + audienceId, Void.class);
    }

    private static class CreateRequest { String name; CreateRequest(String n) { name = n; } }

    public static class Audience {
        private String id, name, createdAt;
        public String getId() { return id; }
        public String getName() { return name; }
        public String getCreatedAt() { return createdAt; }
    }

    public static class AudienceList {
        private List<Audience> data;
        public List<Audience> getData() { return data; }
    }
}
