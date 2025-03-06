# Command-line parser

### Overview

 I created this Java library as a learning project to explore reflection. It provides a simple way to parse command-line arguments and 
 automatically map them to object fields using annotations. By defining parameters in a class, users can easily populate them with 
 command-line inputs, making argument handling more intuitive and efficient.

--- 

### Features

- Parses input from command-line.
- Uses annotations to map the input to object fields.
- Handles duplicate parameter keys in both input and object fields. 
- Automatically sets field values using reflection.
- Supports all Java primitive data types (int, double, boolean, etc.), and their wrapper classes.

---

### Project structure

```plaintext
command-line-parser/
│── src/
│   │
│   ├── annotations/
│   │   └── Parameter.java
│   │
│   ├── enums/
│   │   └── DataTypes.java
│   │
│   ├── error/
│   │   ├── ArgumentDataTypeException.java
│   │   ├── DuplicateParameterKeyException.java
│   │   └── ObjectFieldDataTypeException.java
│   │
│   └── parser/
│       ├── CommandLineParser.java
│       ├── DataTypeValidator.java
│       ├── InputArgumentMapper.java
│       └── ObjectFieldMapper.java   
│
└── README.md
```
---

### Usage

**Define a class with annotated fields:**
Create a class with fields annotated with `@Parameter` and specify the corresponding command-line argument key.

**Notes:** 
- If duplicate parameter keys exist, an unchecked `DuplicateParameterKeyException` is thrown.
- If a field's data type is not a Java primitive or its wrapper class, an unchecked `ObjectFieldDataTypeException` is thrown.

```Java
import annotations.Parameter;

public class Person {

    @Parameter(key = "-f")
    private String firstName;

    @Parameter(key = "-l")
    private String lastName;

    @Parameter(key = "-a")
    private int age;

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName + ", age: " + this.age;
    }
}
```

**Parse command-line arguments:**
Use `CommandLineParser.runParser()` to populate the fields of an object based on command-line inputs. 

**Notes:**
- If an argument value contains spaces, special characters, or could be misinterpreted (e.g., negative numbers), enclose it in 
double quotes (`""`) when passing it in the command-line.
- If duplicate argument keys exist, an unchecked `DuplicateParameterKeyException` is thrown.
- If a provided value cannot be converted to the expected primitive type, an unchecked `ArgumentDataTypeException` is thrown.

*Example comman-line arguments:*
```bash
-f George -l Orwell -a 80
```

```Java
import parser.CommandLineParser;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {

        Person person = new Person();
        CommandLineParser.runParser(person, args);

        System.out.println(person); // Output: George Orwell, age: 80
    }
}

```

### Running the application

**Requirements**
- Java 17, or later.
- [Apache Maven](https://maven.apache.org/download.cgi).

**Installation**

1. Clone the repository.
```bash
git clone https://github.com/surfaceUsed/command-line-parser.git
```

2. Navigate to the project dkirectory.
```bash
cd path/to/command-line-parser
```

3. Clean, compile and package the project.
```bash
mvn clean
mvn compile
mvn package
```

The complete `.jar` file will be located in the `target/` directory.

---

