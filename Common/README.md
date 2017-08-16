# Common

## Purpose
This package contains reusable components for consumption by other modules and applications.

## Top Level Packages
The following are the top-level packages within this project:

### AtomicOptional

#### Concept
`companyB.common.atomic.AtomicOptional` is class for holding Optional containers whose contents may change but whose basic contract will not. In other words, immutability for the Optional itself is guaranteed, but not that of its value.

#### Usage
```java
    AtomicOptional<List<Integer>>atomicOptional = new AtomicOptional<>(Arrays.asList(1,2,3));
    Optional<List<Integer>>actual = atomicOptional.get();
    List<Intger> list = actual.get();
```
### Conversion

#### Concept
`companyB.common.conversion.Converter` converts Strings representations into various supported datatypes.

#### Usage
```java
    Converter converter = new Converter();
    Boolean value  = converter.convert(Boolean.class,"False");
```

### Encrypted

#### Concept


#### Usage

### CollectionsSplitter

#### Concept
Splits Collection into a List of Lists, either into `n` number of equally sized lists, or `x` number of lists each of which
has at a minimum of `n` items.

#### Usage

### EventLogger

#### Concept
This package provides a single point of contact for logging specific events or even just plain messages in a unified
format. This makes it possible to develop logging messages that can be written company-wide and easily indexed and
searched by applications such as Splunk. This module consists of four distinct parts:

1. **EventLogger**: This class actually performs the logging.
2. **EventCode**: This class represents an Event Code as specified by the user.
3. **LogMessageFormatter**: Implementations of this class specify how to format the logging messages themselves.
4. **EventLoggerFactory**: Provides singleton instances of EventLogger.

#### Usage
1. Define an EventCode for use:
    ```java
        EventCode defaultEvent = new EventCode("foo",42);
    ```
2. Get an EventLogger from the EventLoggerFactory:
    i. Binding to a class:
        ```java
            EventLoggerFactory factory = new EventLoggerFactory();
            EventLogger logger = factory.getEventLogger(Foo.class);
        ```
    ii. Binding to a name:
    ```java
        EventLogger logger = factory.getEventLogger("Foo");
    ```
    iii. Binding to a specific instance:
    ```java
        EventLogger logger = factory.getEventLogger(this);
    ```
3. (Optional) Add an implementation of LogMessageFormatter:
    ```java
        EventLogger logger = factory.getEventLogger(this).withFormatter(new CustomMessageFormatter());
    ```
If this step is skipped, an instance of DefaultLogMessageFormatter will be used.
4.  Use this logger to log messages:
    ```java
        logger.info(defaultEvent,"this is a message",null,null);
    ```
    You can optionally supply properties and a throwable to the message to be logged.

#### Decoration
Optionally, you can use the EventLoggerFactory.decorate() method in conjunction with the `@EventLog` annotation to decorate
fields within your classes / instances with instances of EventLogger:
1.  Decorate your field:
    ```java
        @EventLog
        private EventLogger eventLogger;
    ```
2.  Use the factory method to decorate the field:
    ```java
        Foo foo = EventLoggerFactory.decorate(Foo.class);
        // -or-
        Foo foo = EventLoggerFactory.decorate(new Foo());
     ```
The `@EventLog` supports two optional parameters:
*   **name**(String)  - The name of the EventLogger. If this is not supplied, then the canonical class name is used.
*   **logMessageFormatter**(Class) - Class that implements LogMessageFormatter.
    If this is not supplied then an instance of DefaultLogMessageFormatter is constructed and attached to the EventLogger.
    If this **is** supplied, then the implementing class is required to have a single no-args publicly accessible constructor.

## Logging
All logging is done via SLF4J. You will need to provide your own runtime implementations.

## Maven Dependency Declaration
```xml
    <dependency>
        <groupId>companyB</groupId>
        <artifactId>Common</artifactId>
        <version>${common.version}</version>
    </dependency>
```