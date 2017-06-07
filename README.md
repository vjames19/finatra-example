# finatra-example
Example project to showcase [finatra](https://twitter.github.io/finatra/) and [slick](http://slick.lightbend.com/) capabilities and to serve as a skeleton project for future ones.

# Overview
The project consists of a really contrived example of a blog platform.

The platform supports users, posts and comments

## Domain
* User
  * A user has a username
  * Can create many posts
  * Can create many comments
* Post
  * Has content represented by a String
  * It can only be created by an existing user
* Comment
  * Has text represented by a String
  * Can only be added by an existing user to an existing post.


# Modules

## domain
This module only contains the classes representing the domain and the logic needed to achieve it.

## domain-implementation
This module defines the implementation of the interfaces defined in the domain module.

It uses Postgres as the datastore and uses [slick's code generation](http://slick.lightbend.com/doc/3.1.0/code-generation.html)
to create the DAO and DTO from the Schema.

## finatra-api
This modules provides the REST api using finatra.


# How to run

First you need postgres installed and running on the default port.

It should have a user "postgres" with password "postgres" and a database called "blog"

## Flyway migration
Run the migration and it should create the schema and populate the tables.
```
sbt flywayMigrate
```

## Running the server
```
sbt run
```

## Testing
```
sbt test
```
