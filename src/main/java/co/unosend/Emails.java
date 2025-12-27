package co.unosend;

import java.util.*;

public class Emails {
    private final Unosend client;

    Emails(Unosend client) { this.client = client; }

    public SendEmailResponse send(SendEmailRequest request) throws UnosendException {
        return HttpClient.post(client, "/emails", request, SendEmailResponse.class);
    }

    public static class SendEmailRequest {
        private String from;
        private Object to;
        private String subject;
        private String html;
        private String text;
        private List<String> cc;
        private List<String> bcc;
        private String replyTo;
        private String priority;
        private String templateId;
        private Map<String, Object> templateData;
        private String scheduledFor;

        private SendEmailRequest() {}

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final SendEmailRequest r = new SendEmailRequest();

            public Builder from(String from) { r.from = from; return this; }
            public Builder to(String to) { r.to = to; return this; }
            public Builder to(List<String> to) { r.to = to; return this; }
            public Builder subject(String subject) { r.subject = subject; return this; }
            public Builder html(String html) { r.html = html; return this; }
            public Builder text(String text) { r.text = text; return this; }
            public Builder cc(List<String> cc) { r.cc = cc; return this; }
            public Builder bcc(List<String> bcc) { r.bcc = bcc; return this; }
            public Builder replyTo(String replyTo) { r.replyTo = replyTo; return this; }
            public Builder priority(String priority) { r.priority = priority; return this; }
            public Builder templateId(String templateId) { r.templateId = templateId; return this; }
            public Builder templateData(Map<String, Object> templateData) { r.templateData = templateData; return this; }
            public Builder scheduledFor(String scheduledFor) { r.scheduledFor = scheduledFor; return this; }

            public SendEmailRequest build() {
                if (r.from == null) throw new IllegalArgumentException("from is required");
                if (r.to == null) throw new IllegalArgumentException("to is required");
                if (r.subject == null) throw new IllegalArgumentException("subject is required");
                return r;
            }
        }
    }

    public static class SendEmailResponse {
        private String id;
        private String from;
        private List<String> to;
        private String subject;
        private String status;
        private String createdAt;

        public String getId() { return id; }
        public String getFrom() { return from; }
        public List<String> getTo() { return to; }
        public String getSubject() { return subject; }
        public String getStatus() { return status; }
        public String getCreatedAt() { return createdAt; }
    }
}
