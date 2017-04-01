# _hair-salon_

#### _hair-salon, 03-31-2017_

#### By _**Cameron Jensen_

## Description
This app allows a hair salon to be able to manage their sylists and clients.


## Specifications

| Behavior                   | Input Example     | Output Example    |
| -------------------------- | -----------------:| -----------------:|
|add stylist|form name, phone|Samantha, 829-2839|
|add client to sylist|form name, phone, stylist|Betty 892-2343, Samantha|
|update stylist|Samantha, 829-2839|Samantha Brown, 800-7737|
|update client|Betty 892-2343, Samantha|Betty 323-9890, Liz|
|delete stylist|Samantha Brown, 800-7737| empty|
|delete client | Betty 323-9890, Liz | empty |

## Contact

email: jensenseanc@gmail.com

## Bugs / Issues

currently when a stylist is deleted the clients are not moved anywhere, make sure to update client stylist before a stylist is deleted.

## Setup/Installation Requirements

* Open github https://github.com/seacamjen/hair-salon
* Clone the repository
* In PSQL: CREATE DATABASE hair_salon, \c hair_salon
* CREATE TABLE stylists (id serial PRIMARY KEY, name varchar, phone varchar);
* CREATE TABLE clients (id serial PRIMARY KEY, name varchar, phone varchar, stylist_id int);
* Run the command 'gradle run'
* Open browser and go to localhost:4567


### License

Copyright (c) 2017 **_Cameron Jensen_**

This software is licensed under the MIT license.
