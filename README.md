# Quarkus Example for Google Cloud Pub/Sub

* This project uses Quarkus and SmallRye Reactive Message to integrate with Google Cloud Pub/Sub.
* There is no official document to describe how to use Cloud Pub/Sub connector in SmallRye Reactive Message. Here is what I found a way to do it.

### Prerequisite
In order to run this application, you must have a Google account and a Billing Account associated to this Google account.  
Install [Java 17 SDK](https://adoptium.net/temurin/releases/) if you haven't already installed it.  
Install [Google Cloud SDK](https://cloud.google.com/sdk/docs/).  
Create Service Account with roles/pubsub.admin.

### Setup
In your Gradle Kotlin project, add the dependency to your build.gradle.kts:  

** **Should use the same version as smallrye-reactive-messaging-api**

```kotlin
implementation("io.smallrye.reactive:smallrye-reactive-messaging-gcp-pubsub:3.22.1")
```

### Running the application in dev mode
You can run your application in dev mode that enables live coding using:
```shell script
export PROJECT_ID=$(gcloud config get-value project)
```
```shell script
export GOOGLE_APPLICATION_CREDENTIALS=/${replace_it_with_your_credentials_path}
```
```shell script
./gradlew quarkusDev
```

### Try it
```shell
curl --location 'http://localhost:8080/hello'
```

## Related Guides

- SmallRye Reactive Messaging ([guide](https://quarkus.io/guides/reactive-messaging)): Produce and consume messages and implement event driven and data streaming applications
- RESTEasy Reactive ([guide](https://quarkus.io/guides/resteasy-reactive)): A JAX-RS implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- Kotlin ([guide](https://quarkus.io/guides/kotlin)): Write your services in Kotlin
- SmallRye Health ([guide](https://quarkus.io/guides/microprofile-health)): Monitor service health
- Kubernetes ([guide](https://quarkus.io/guides/kubernetes)): Generate Kubernetes resources from annotations

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

### SmallRye Health

Monitor your application's health using SmallRye Health

[Related guide section...](https://quarkus.io/guides/smallrye-health)
