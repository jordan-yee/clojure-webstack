# Overview

This is a template / reference implementation for a full stack web server that
uses a select set of the latest (as of 2023) leading Clojure/Script libraries &
practices.

Key Developer Experience Goals:
- Support REPL Driven Development with `clojure.tools.namespace/refresh` support
- Support Test Driven Development with a live test runner (Kaocha)
- Support Hot Code Reloading & first-class npm library support with ShadowCLJS
- Include top-notch tooling and instrumentation support
  - Portal           : REPL-connected data inspector (see also Reveal)
  - Snitch           : Inline-def args & bindings for REPL use
  - Flowstorm        : Tracing debugger
  - Function Schemas : Dynamic & static schema checking
- Include the libraries that all Clojure-based web application projects use or
  wish they used (while a few have acceptable alternatives, all are
  well-established options). See the `deps.edn`.
- Some technology decisions are TBD by the specific project:
  - Auth (Implement own or integrate w/ service?)
  - Database (Good 'Ol Postrgres or something shinier like Datomic?)
  - UI framework or styling library (MUI? Bootstrap? Bulma? Tailwind? Other?)
  - Architecture/organization of the domain code
  - Hosting service (Heroku? Digital Ocean? AWS? Other?)

Key Components Include:
- Clojure CLI (deps.edn)-based server project with:
  - clojure.tools.namespace/refresh : REPL code reloading
  - Mount                           : Stateful component management
  - Ring                            : Interface for HTTP web server
  - Jetty                           : Web Server
  - Reitit                          : Routing
  - Malli                           : Schemas
  - Kaocha                          : Testing
- ShadowCLJS-based client project with:
  - Reagent                         : ReactJS wrapper
  - Re-frame                        : State management

# Using as a Template

Using my own, select tools (Kakoune, fd, and rg):
```
# Clone the project:
git clone https://github.com/jordan-yee/clojure-webstack.git

# Delete .git so you can re-init as a different, fresh project:
rm -rf .git

# Rename all occurrences of "webstack", then "Webstack" to your app's name:
fd -t f | kak -f "%swebstack<ret>cmyapp"
fd -t f | kak -f "%sWebstack<ret>cMyapp"

# Identify all "webstack" directories
fd -t d webstack
# Rename these manually:
mv src/clj/webstack src/clj/myapp
mv src/cljs/webstack src/cljs/myapp
mv test/clj/webstack test/clj/myapp
mv test/cljs/webstack test/cljs/myapp

# Search for stray files & file contents that were missed:
fd webstack
rg -i webstack

# Re-init a fresh Git repo for your new project
git init
git add .
git commit -m "project init from clojure-webstack"
```

# First Time Setup

- Install Clojure w/ CLI tools: https://clojure.org/guides/install_clojure
  - Practicalli config (optional): https://practical.li/clojure/clojure-cli/practicalli-config/
- Install Java: https://sdkman.io/
- Install Node w/ npm: https://github.com/nvm-sh/nvm#installing-and-updating
  - Global ShadowCLJS installation (optional): `npm install -g shadow-cljs`
    - This will let you run the `shadow-cljs` command directly later.
    - Without this, you must run commands with `npx` like, `npx shadow-cljs help`.

# Updating Dependencies

Assuming you installed the Practicalli config, you can identify available
dependency updates by running:
```
clojure -T:search/outdated
```

# Server

## Testing

To start a test runner that automatically re-runs tests after each change:
```
clojure -X:watch-test
```

OR to start the test runner from the REPL:
```
clojure -M:repl/reloaded
user=> (test-watch)
```

## Development

To start a REPL for development run:
```
clojure -M:repl/reloaded
user=> (go)
```

After making a change, run:
```
user=> (reset)
```

## Building

Build commands are defined by `build.clj`.

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

## Running the Server

After building you can run it with:
```
# Remember to update the version in this command as needed!
java -jar target/webstack-0.0.10-standalone.jar
```

# Client

## Testing

To start the test watcher run:
```
shadow-cljs watch test
```

The test results will be shown in the browser at: http://localhost:8021

## Development

To start building the client in watch mode run:
```
shadow-cljs watch app
```

To start a ClojureScript REPL connected to the development build run:
```
shadow-cljs cljs-repl app
```

## Building

To clean existing build artifacts run:
```
clj -T:build clean-client
```

To create an optimized production build of the client run:
```
shadow-cljs release app
```

> This outputs a file with a hash-versioned name to prevents caching issues.

# Known Issues

- Attempting to use re-frame-debux tracing macros causes re-frame-10x to explode
  after mousing over it.

# QuickRef

Client:
```
shadow-cljs watch test
# Open [test page](http://localhost:8021/) in your browser
shadow-cljs watch app
shadow-cljs cljs-repl app
```

Server:
```
clojure -X:watch-test
clojure -M:repl/reloaded
user=> (go)
user=> (reset)
# Open [client](http://localhost:3000/) in your browser
```
