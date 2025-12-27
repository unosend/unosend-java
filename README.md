# Unosend Java SDK

The official Java SDK for [Unosend](https://unosend.co) - Email API for developers.

## Installation

### Maven

```xml
<dependency>
    <groupId>co.unosend</groupId>
    <artifactId>unosend-java</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'co.unosend:unosend-java:1.0.0'
```

## Requirements

- Java 11 or higher

## Quick Start

```java
import co.unosend.Unosend;
import co.unosend.Emails.SendEmailRequest;
import co.unosend.Emails.SendEmailResponse;

public class Main {
    public static void main(String[] args) {
        Unosend client = new Unosend("un_your_api_key");

        try {
            SendEmailResponse response = client.emails().send(
                SendEmailRequest.builder()
                    .from("hello@yourdomain.com")
                    .to("user@example.com")
                    .subject("Welcome!")
                    .html("<h1>Hello!</h1><p>Welcome to our platform.</p>")
                    .build()
            );

            System.out.println("Email sent: " + response.getId());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

## Priority Sending

```java
SendEmailResponse response = client.emails().send(
    SendEmailRequest.builder()
        .from("noreply@yourdomain.com")
        .to("user@example.com")
        .subject("Your verification code: 847293")
        .html("<p>Your OTP code is: <strong>847293</strong></p>")
        .priority("high")
        .build()
);
```

## Using Templates

```java
import java.util.Map;

SendEmailResponse response = client.emails().send(
    SendEmailRequest.builder()
        .from("hello@yourdomain.com")
        .to("user@example.com")
        .subject("Welcome!")
        .templateId("550e8400-e29b-41d4-a716-446655440000")
        .templateData(Map.of(
            "first_name", "John",
            "company_name", "Acme Inc"
        ))
        .build()
);
```

## Error Handling

```java
import co.unosend.UnosendException;

try {
    client.emails().send(request);
} catch (UnosendException e) {
    System.err.println("API Error: " + e.getMessage());
    System.err.println("Status Code: " + e.getStatusCode());
}
```

## License

MIT
