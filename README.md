# Command-line parser

### Overview

I was trying to learn how to use reflection, so i created my own simple Java library for parsing command-line arguments, which 
automatically maps them to object fields using annotations. It allows users to define parameters in their classes and popluale 
them with command-line inputs.

--- 

### Features

- Parses input from command-line.
- Uses annotations to map the input to object fields.
- Handles duplicate parameter keys in both input and object fields. 
- automatically sets field values using reflection.

---

### Project structure

```plaintext
command-line-parser/
│── src/
|   |
│   ├── annotations/
│   │   └── Parameter.java 
|   |   
│   ├── error/
│   │   └── DuplicateParameterKeyException.java
|   |
│   ├── parser/
│   │   ├── CommandLineParser.java
│   │   ├── InputMapper.java
│   │   └── ObjectFieldMapper.java
|   |
└── README.md
```
---

### Usage

**Define a class with annotated fields:**
Create a class with fields annotated with `@Parameter` and specify the corresponding command-line argument key.

*Note* -> If there are duplicated parameter key-names, a `DuplicatedParameterKetException` will be thrown.

```Java
import annotations.Parameter;

public class Person {

    @Parameter(key = "-f")
    private String firstName;

    @Parameter(key = "-l")
    private String lastName;

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }
}
```

**Parse command-line arguments:**
Use `CommandLineParser.runParser()` to populate the fields of an object based on command-line inputs. 

*Note* -> 1. If an argument value contains spaces, you must enclose it in double quotes (`""`) when passing it in the command-line.
          2. If there are duplicated parameter key-names, a `DuplicatedParameterKetException` will be thrown.

*Example comman-line arguments:*
```bash
-f George -l Orwell
```

```Java
import parser.CommandLineParser;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {

        Person person = new Person();
        CommandLineParser.runParser(person, args);

        System.out.println(person); // Output: George Orwell
    }
}

```

### Running the application

1. Clone the repository.
```bash
git clone https://github.com/surfaceUsed/command-line-parser.git
```

2. Navigate to the project dkirectory.
```bash
cd path/to/command-line-parser
```

3. Package into a JAR file.
```bash
jar cf command-line-parser.jar -C out . 
```

