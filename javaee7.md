# Chapter 1 - Java EE 7 at a glance

Java EE is a set of specifications for developing enterprise applications.
Common concerns of enterprise applications are distribution, transaction,
security, interoperability. The specs provide partial solutions to the
concerns. Some of the specs are JTA (transaction), JPA (persistence), JMS
(messaging), JAX-RS (web service).

The specs address the concerns by providing APIs. The APIs are implemented by
components. To satisfy the APIs, the components have to be executed in
specialized runtime enviroments which are nested inside JVM runtime. These
runtime environments are called containers. Java EE containers are: Applet
Container, Application Client Container, Web Container and EJB Container.

When run inside a container, the components are provided with certain services
such as transaction mangement, validation, messaging, web service, dependency
injection, and so on.

# Chapter 2 - Context and Dependency Injection

Components in Java EE containers are called beans. There are various types of
beans depending on the services provided to them.

Managed beans are beans with basic services: resource injection, life-cycle
management, and interception.

Enterprise Java Bean (EJB) are managed beans with additional services:
transaction, security, concurrency, ect.

Servlets are managed beans with additional services.

A bean depends on other beans to work. The container can provide a bean
with its dependencies via injection. This is called dependency injection.

Beans are only needed during certain windows of time which are called scopes.
The predefined scopes are: Application, Request, Session, and Conversation.

Besides business logic, beans have to fullfill various cross-cutting concerns
(reporting, transation, security, ect.) Cross-cutting concerns can be managed
with interception, delegation or event-driven services.


