# Overview

This is a template / reference implementation for a full stack web server that
uses a select set of the latest (as of 2023) leading Clojure/Script libraries &
practices.

Getting a smooth and efficient development workflow requires quite a bit of
setup and decision making up front. This project's primary goal is to start you
in a place where the core technology setup work is done so that you can start
cranking out the actual application you want to build. It should provide a good
baseline from which to build a small to mid-size full-stack web application.

Key Developer Experience Goals:
- Support REPL Driven Development with `clojure.tools.namespace/refresh` support
- Support Test Driven Development with a live test runner (Kaocha)
- Support hot code reloading & first-class npm library support with ShadowCLJS
- Include key tooling and instrumentation support
  - Portal           : REPL-connected data inspector
  - Snitch           : Inline-def args & bindings for REPL use
  - Flowstorm        : Tracing debugger
  - Function Schemas : Dynamic & static schema checking
- Include the key tools or libraries that all Clojure-based web application
  projects use or wish they used (while a few have acceptable alternatives, all
  are well-established options).
- Leave certain key decisions to the project such as:
  - How authentication & authorization should be implemented
  - What database to use
  - UI framework or styling library
  - Architecture/organization of the domain code

Key Components Include:
- deps.edn-based server project with:
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

TODO: Convert into an actual template...
TODO: Can we do this more simply with a simple sed script?
To use this project as a template, first clone it, then you can rename the
project namespace prefix using `clojure.tools.namespace.move/move-ns` from
a REPL:
```
(mv/move-ns 'webstack.core 'my-app.core "src/clj/" ["test" "src"])
```

> This project is intended for my own use, and reflects my own experience
> regarding the best way to do things, which remains a work-in-progress.

# First Time Setup

- Install Clojure w/ CLI tools: https://clojure.org/guides/install_clojure
  - Practicalli config (optional): https://practical.li/clojure/clojure-cli/practicalli-config/
- Install Java: https://sdkman.io/
- Install Node w/ npm: https://github.com/nvm-sh/nvm#installing-and-updating
  - Global ShadowCLJS installation (optional): `npm install -g shadow-cljs`
    - This will let you run the `shadow-cljs` command directly later.
    - Without this, you can must run commands with `npx` like, `npx shadow-cljs help`.

# Updating Dependencies

Assuming you installed the Practicalli config, you can identify available
dependency updates by running:
```
clojure -T:search/outdated
```

# Server

## Testing

To just start a test runner that automatically re-runs tests after each change:
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

To start the test watcher in a REPL run:
```
shadow-cljs clj-repl
=> (shadow/watch :test)
```

The test results will be shown in the browser at http://localhost:8021

## Development

To start building the client in watch mode with a connected a ClojureScript REPL:
```
shadow-cljs clj-repl
=> (shadow/watch :app)
=> (shadow/repl :app)
```

TODO: Include Devcards or Storybook?

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

# Deploying the Full-Stack Web Application

This project aims to be hosting service-agnostic, but here is the general
architecture you'll be shooting for:
- Create a production build of the client & its supporting assets, which outputs
  to the `resources/public/` directory.
- Create a production uberjar build of the server, which will contain the
  contents of the `resources` directory.
- Deploy the standalone uberjar to the hosting service of your choice.

# QuickRef

> Each code block represents a different terminal.
> Testing & development instances are all kept separate.

```
shadow-cljs clj-repl
=> (shadow/watch :test)
```

```
shadow-cljs clj-repl
=> (shadow/watch :app)
=> (shadow/repl :app)
```

```
clojure -X:watch-test
```

```
clojure -M:repl/reloaded
user=> (go)
user=> (reset)
```
