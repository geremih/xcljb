# XCLJB

XCLJB (X protocol Clojure-language Binding) is a Clojure language binding for the X Window System.

## Motivation

To make X11 programming a little less painful.

Using JNA with JNAerator to access the XCB functions results in the following:

```clojure
(. org.freedesktop.xcb.XcbLibrary/INSTANCE
   (xcb_create_window conn
                      (byte (org.freedesktop.xcb.XcbLibrary/XCB_COPY_FROM_PARENT))
                      window
                      (. screen root)
                      (short 0) (short 0)
                      (short 150) (short 150)
                      (short 10)
                      (short org.freedesktop.xcb.XcbLibrary$xcb_window_class_t/XCB_WINDOW_CLASS_INPUT_OUTPUT)
                      (. screen root_visual)
                      0 nil)
```

Besides the ugly names (underscores ... *ugh*), if any of the `byte` or `short` casts are left out, the function cannot be invoked. Note that the enum `xcb_window_class_t` in C is now an inner class in Java, making what could have been a keyword access more difficult than it should. It's possible to work around some of these problems by writing the Java interface by hand or hiding things behind macros, but I wanted something better.

## Installation

### Leiningen

Add the following to the `project.clj` dependencies:

    [xcljb "0.1.0-SNAPSHOT"]

## Usage

Since Java does not have access to Unix domain socket, one would have to either make the X server listen on a TCP port, or connect an existing Unix domain socket to a TCP port. There are several ways of achieving the latter, one of which is by using [socat](http://www.dest-unreach.org/socat/). If your X server is running on display 0 (by looking at the `DISPLAY` environment variable, of form "[host]:[display].[screen]"), you can create a listening TCP socket at localhost on port 6000 by running the following command:

    socat TCP-LISTEN:6000,fork,bind=localhost UNIX-CONNECT:/tmp/.X11-unix/X0

## Conventions

* Functions are in `lisp-case`
* Keywords are in `:lisp-case`
* Enums are represented as Clojure maps in `UPPER-CASE`
* Structs, replies, events, and errors are represented as records in `CamelCase`

  Examples:
    * Struct: `Segment`
    * Reply: `GetWindowAttributesReply`
    * Event: `KeyPressEvent`
    * Error: `RequestError`

* (Sending of) requests are represented as functions
  * The return value of requests (replies/errors), if there are any, are represented as Clojure promises

* Integer types are represented as Clojure integers
* List types are represented as Clojure sequences
  * Exception: list of `char` and `STRING8` are represented as Clojure strings

* `BOOL` and `BOOL32` types are represented as Clojure booleans
* Valueparam types are represented as Clojure maps

## Examples

Examples are listed under the `src/xcljb/examples/` directory.

[TinyCLJWM](https://github.com/noodlewiz/tinycljwm) is [TinyWM](http://incise.org/tinywm.html) in Clojure.

## Todo

* Extensions

* Core protocol, as yet unimplemented

  * Event: ClientMessage
  * Union: ClientMessageData

## Credits

* The Clojure community, for a great language.
* [XCB project](http://xcb.freedesktop.org/), for the XML protocol descriptions.
* Zach Tellman, for the great library [Gloss](https://github.com/ztellman/gloss).

## License

Copyright © 2013 Vincent W. Chen

Distributed under the MIT License, same as [XCB](http://xcb.freedesktop.org/). See `LICENSE` for detail.
