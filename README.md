# Overview

This is an minimal example implementation of a full stack web server using a
select set of the latest (as of 2023) leading Clojure/Script libraries &
practices.

# First Time Setup

Install the [Practicalli Configuration for Clojure CLI](https://practical.li/clojure/clojure-cli/practicalli-config/)
for a curated set of Clojure CLI-based tooling:
```
# Backup your existing user config if needed, then...
rm -rf $HOME/.clojure
git clone https://github.com/practicalli/clojure-deps-edn.git $HOME/.clojure
```

Actually, the essential aliases from the Practicalli config have been copied
into this project's deps.edn, but others may still be useful.

# Development

To start a REPL for development run:
```
clojure -M:repl/reloaded
```

# Testing

TODO

# Building

The version is set in `build.clj` as `<major>.<minor>.<git-revs>`, where the
`<major>` and `<minor>` segments must be set manually, but the `<git-revs>`
segment is automatically computed. You will likely want to extend this.

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
java -jar target/webstack-0.0.10-standalone.jar
```
