# Reader Sample for Kafkaesque 
A sample Java reader using the Apache Pulsar client API to connect to a Kafkaesque service. Readers can retrieve any
message in the backlog. This example retrieves the earliest (or oldest) message. Depending on the message retention
settings, the message may be days old.

The project builds with Maven.
