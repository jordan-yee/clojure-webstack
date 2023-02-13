# Overview

This is an minimal example implementation of a full stack web server using a
select set of the latest (as of 2023) leading Clojure/Script libraries &
practices.

# First Time Setup

TODO

# Development

TODO

# Testing

TODO

# Building

Clean only:
```
clj -T:build clean
```

Clean and build uberjar:
```
clj -T:build uber
```

# Running the Application

After building you can run it with:
```
# Remember to update the version in this command as needed!
java -jar target/webstack-1.2.1-standalone.jar
```
