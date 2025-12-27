package co.unosend;

public class Unosend {
    private static final String DEFAULT_BASE_URL = "https://api.unosend.co/v1";
    
    private final String apiKey;
    private final String baseUrl;
    private final Emails emails;
    private final Domains domains;
    private final Audiences audiences;
    private final Contacts contacts;

    public Unosend(String apiKey) {
        this(apiKey, DEFAULT_BASE_URL);
    }

    public Unosend(String apiKey, String baseUrl) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("API key is required");
        }
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.emails = new Emails(this);
        this.domains = new Domains(this);
        this.audiences = new Audiences(this);
        this.contacts = new Contacts(this);
    }

    public Emails emails() { return emails; }
    public Domains domains() { return domains; }
    public Audiences audiences() { return audiences; }
    public Contacts contacts() { return contacts; }

    String getApiKey() { return apiKey; }
    String getBaseUrl() { return baseUrl; }
}
