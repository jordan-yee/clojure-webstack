# Overview

This is minimal-ish example implementation of a full stack web server using a
select set of the latest (as of 2023) leading Clojure/Script libraries &
practices.

To use this project as a template, first clone it, then you can rename the
project namespace prefix using `clojure.tools.namespace.move/move-ns` from
a REPL:
```
;; TODO: adapt this example call into a script to rename all `webstack` segments
(mv/move-ns 'webstack.core 'my-app.core "src/clj/" ["test" "src"])
```

# First Time Setup

- Install Clojure w/ CLI tools: https://clojure.org/guides/install_clojure
  - Practicalli config (optional): https://practical.li/clojure/clojure-cli/practicalli-config/
- Install Java: https://sdkman.io/
- Install Node w/ npm: https://github.com/nvm-sh/nvm#installing-and-updating

# Testing

Start a REPL w/ a test runner that automatically re-runs tests after each change:
```
clojure -M:repl/reloaded
user=> (test-watch)
```

# Development

To start a REPL for development run:
```
clojure -M:repl/reloaded
user=> (go)
```

After making a change, run:
```
user=> (reset)
```

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
