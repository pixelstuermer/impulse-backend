[![Build Status](https://travis-ci.org/pixelstuermer/impulse-backend.svg?branch=master)](https://travis-ci.org/pixelstuermer/impulse-backend)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

# impulse-backend
Backend services of the impulse project

## SwaggerUI
The SwaggerUI for detailed API description can be found here:

    {base-url}/api/swagger-ui.html

## Config Variables
To connect to a MongoDB database and to a collection, the following variables have to be set:

    spring.data.mongodb.uri={secret}
    application.collection.name={secret}